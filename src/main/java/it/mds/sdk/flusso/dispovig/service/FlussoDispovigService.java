/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import it.mds.sdk.connettoremds.ConnettoreMds;
import it.mds.sdk.connettoremds.dispovig.webservice.bean.UploadFileResponse;
import it.mds.sdk.connettoremds.exception.ConnettoreMdsException;
import it.mds.sdk.connettoremds.gaf.webservices.bean.ArrayOfUploadEsito;
import it.mds.sdk.connettoremds.gaf.webservices.bean.ResponseUploadFile;
import it.mds.sdk.connettoremds.gaf.webservices.bean.UploadEsito;
import it.mds.sdk.flusso.dispovig.parser.regole.RecordDtoDispovig;
import it.mds.sdk.flusso.dispovig.parser.regole.conf.ConfigurazioneFlussoDispovig;
import it.mds.sdk.flusso.dispovig.parser.regole.ParserTracciatoImpl;
import it.mds.sdk.flusso.dispovig.parser.regole.RecordDtoDispovig;
import it.mds.sdk.flusso.dispovig.tracciato.TracciatoSplitterImpl;
import it.mds.sdk.flusso.dispovig.tracciato.bean.output.FlsDispovig;
import it.mds.sdk.gestoreesiti.GestoreRunLog;
import it.mds.sdk.gestoreesiti.conf.Configurazione;
import it.mds.sdk.gestoreesiti.modelli.Esito;
import it.mds.sdk.gestoreesiti.modelli.InfoRun;
import it.mds.sdk.gestoreesiti.modelli.ModalitaOperativa;
import it.mds.sdk.gestoreesiti.modelli.StatoRun;
import it.mds.sdk.gestoreesiti.validazioneXSD.MainTester;
import it.mds.sdk.gestorefile.GestoreFile;
import it.mds.sdk.gestorefile.exception.XSDNonSupportedException;
import it.mds.sdk.gestorefile.factory.GestoreFileFactory;
import it.mds.sdk.libreriaregole.dtos.CampiInputBean;
import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
import it.mds.sdk.libreriaregole.exception.ValidazioneImpossibileException;
import it.mds.sdk.libreriaregole.gestorevalidazione.BloccoValidazione;
import it.mds.sdk.libreriaregole.gestorevalidazione.GestoreValidazione;
import it.mds.sdk.libreriaregole.parser.ParserRegole;
import it.mds.sdk.libreriaregole.parser.ParserTracciato;
import it.mds.sdk.libreriaregole.regole.beans.RegoleFlusso;
import it.mds.sdk.libreriaregole.tracciato.TracciatoSplitter;
import it.mds.sdk.libreriaregole.validator.ValidationEngine;
import it.mds.sdk.rest.exception.ParseCSVException;
import lombok.extern.slf4j.Slf4j;

@Component("flussoDispovigService")
@Slf4j
public class FlussoDispovigService {

    private ParserRegole parserRegole;
    private ParserTracciato parserTracciato;
    private ValidationEngine validationEngine;
    private TracciatoSplitter<RecordDtoDispovig> tracciatoSplitter;
    private ConnettoreMds connettoreMds;
    private final ConfigurazioneFlussoDispovig conf;
    
	private final Configurazione config = new Configurazione();
//	private static final String PERIODO_RIFERIMENTO_SEPARATOR = "I";
//	private static final String MDS_RESP_OK = "IF00";
    private static final String XML_FLUSSO_TAG = "</flsProSoc><flsProSoc>";
	private final String NOME_FILE_XSD_DISPOVIG = "DISPOVIG.xsd";

    @Autowired
    public FlussoDispovigService(@Qualifier("parserRegoleDispovig") final ParserRegole parserRegole,
                            @Qualifier("parserTracciato") final ParserTracciato parserTracciato,
                            @Qualifier("validationEngine") final ValidationEngine validationEngine,
                            @Qualifier("tracciatoSplitter") final TracciatoSplitter<RecordDtoDispovig> tracciatoSplitter,
                            @Qualifier("connettoreMds") final ConnettoreMds connettoreMds,
    						@Qualifier("configurazioneFlussoDispovig") ConfigurazioneFlussoDispovig conf) {
        this.parserRegole = parserRegole;
        this.parserTracciato = parserTracciato;
        this.validationEngine = validationEngine;
        this.tracciatoSplitter = tracciatoSplitter;
        this.connettoreMds = connettoreMds;
        this.conf = conf;
    }
    
    
    @Async
	public void validazioneBlocchi(int dimensioneBlocco, String nomeFile, RegoleFlusso regoleFlusso, String idRun,
			String idClient, ModalitaOperativa modalitaOperativa, String periodoRiferimento, String annoRiferimento,
			String codiceRegione, GestoreRunLog gestoreRunLog) {
		
		// Loop
		List<RecordDtoGenerico> records;
		int inizio = 0;
		int fine = dimensioneBlocco;
		ParserTracciatoImpl parser = getParserTracciatoImpl();
		CampiInputBean campiInputBean = CampiInputBean.builder().withAnnoRiferimentoInput(annoRiferimento)
				.withCodiceRegioneInput(codiceRegione).withPeriodoRiferimentoInput(periodoRiferimento).build();
		// Legge un blocco dal CSV e scrive records, su questo blocco chiama il valida
		// flusso
		FlsDispovig flsDispovig = null;
		File file = getfileFromPath(conf.getFlusso().getPercorso() + nomeFile);
		 String dateFormat = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String nomeFileXml = conf.getXmlOutput().getPercorso() + "SDK_DISPO_VIG_" + dateFormat + "_" + idRun
				+ ".xml";

		
		String nomeFileXmlTmp = nomeFileXml + "tmp";
		int totale = 0;
		int accettati = 0;
		int scartati = 0;
		int numeroBlocco = 0;
		int numRecordValidati = 0;
		final String percorso = String.format("%s/ESITO_%s.json", config.getEsito().getPercorso(), idRun);
		final String percorsoTemp = String.format("%s/ESITO_%s_TEMP.json", config.getEsito().getPercorso(), idRun);
		do {
			numeroBlocco++;
			log.info("Inizio elaborazione blocco {} con dimensione massima {} di idRun {}", numeroBlocco,
					dimensioneBlocco, idRun);
			try {
				records = parser.parseTracciatoBlocco(file, inizio, fine);
			} catch (ParseCSVException pe) {
				log.error("Errore di parsing del blocco {} idRun {}", numeroBlocco, idRun, pe);
				InfoRun infoRun = gestoreRunLog.cambiaStatoRun(idRun, StatoRun.KO_GENERICO);
				infoRun.setDescrizioneStatoEsecuzione(pe.getMessage());
				infoRun.setDataFineEsecuzione(new Timestamp(System.currentTimeMillis()));
				gestoreRunLog.updateRun(infoRun);
				return;
			}
			inizio = inizio + dimensioneBlocco;
			fine = fine + records.size();
			records.forEach(r -> r.setCampiInput(campiInputBean));
			BloccoValidazione bloccoValidazione = validationEngine.startValidaFlussoBlocco(records, regoleFlusso, idRun,
					gestoreRunLog, numRecordValidati);

			numRecordValidati += records.size();

			totale = totale + bloccoValidazione.getNumeroRecord();
			scartati = scartati + bloccoValidazione.getScartati();
			accettati = accettati + bloccoValidazione.getNumeroRecord() - bloccoValidazione.getScartati();
			// scrittura esito parziale o append
			validationEngine.creaFileEsiti(bloccoValidazione);
			// Scrittura XML solo se almeno un record è presente
			List<RecordDtoDispovig> recordConv = bloccoValidazione.getRecordList().stream()
					.map(k -> (RecordDtoDispovig) k).collect(Collectors.toList());
			boolean isWriteOk = true;
			if (!recordConv.isEmpty()) {
				log.debug("Per il blocco {} di idRun {} presenti {} record da trasformare in xml", numeroBlocco, idRun,
						recordConv.size());
				flsDispovig = ((TracciatoSplitterImpl) tracciatoSplitter).creaDispovig(recordConv,flsDispovig);
			} else {
				log.warn("Nessun record valido per il blocco {} di idRun {}, non viene generato xml", numeroBlocco,
						idRun);
				isWriteOk = false;
			}

			// creo xml solo se sono presenti record accettati
			if (isWriteOk) {
				GestoreFile gestoreFile = GestoreFileFactory.getGestoreFile("XML");
//				URL urlXsd = this.getClass().getClassLoader().getResource("DISPOVIG.xsd");
//				log.debug("URL dell'XSD per la validazione idrun {} : {}", idRun, urlXsd);
//				log.info("Inizio scrittura file temporaneo {} con xsd {} per idRun {}", nomeFileXmlTmp,
//						urlXsd.getFile(), idRun);
				try {
					gestoreFile.scriviDtoFragment(flsDispovig, nomeFileXmlTmp);
				} catch (XSDNonSupportedException e) {
					log.error("XSD non validato. ", e);
					InfoRun infoRun = gestoreRunLog.cambiaStatoRun(idRun, StatoRun.KO_VALIDAZIONE_SDK);
					infoRun.setDescrizioneStatoEsecuzione(e.getMessage());
					infoRun.setDataFineEsecuzione(new Timestamp(System.currentTimeMillis()));
					gestoreRunLog.updateRun(infoRun);
					boolean isFileEsitiCleaned = validationEngine.formatJsonEsiti(percorso, percorsoTemp);
					if (!isFileEsitiCleaned) {
						log.warn(
								"ATTENZIONE: Un'operazione tra copia, rename o eliminazione del file temp degli esiti non è andata a buon fine.");
						throw new RuntimeException();
					}
					return;
				}
				log.info("Scritto file temporaneo {} per idRun {}", nomeFileXmlTmp, idRun);
				flsDispovig = null;
				System.gc();
			}

			log.info("Terminata elaborazione blocco {} di idRun {} con questi risultati:\n"
					+ "Record elaborati: {}\nRecord accettati: {}\nRecord scartati: {}\n"
					+ "Totale dei blocchi elaborati fino ad ora:\nRecord elaborati: {}\nRecord accettati: {}\nRecord "
					+ "scartati: {}", numeroBlocco, idRun, bloccoValidazione.getNumeroRecord(),
					bloccoValidazione.getNumeroRecord() - bloccoValidazione.getScartati(),
					bloccoValidazione.getScartati(), totale, accettati, scartati);
		} while (fine - inizio >= dimensioneBlocco);

		boolean isFileEsitiCleaned = validationEngine.formatJsonEsiti(percorso, percorsoTemp);
		if (!isFileEsitiCleaned) {
			log.warn(
					"ATTENZIONE: Un'operazione tra copia, rename o eliminazione del file temp degli esiti non è andata a buon fine.");
			throw new RuntimeException();
		}

		InfoRun infoRun = gestoreRunLog.getRun(idRun);

		// TODO rendere il file XML utilizzabile
		if (accettati > 0) {
			log.info("Inizio pulizia file {}", nomeFileXmlTmp);
			String nomeFilePulito = "";
			try {
				nomeFilePulito = validationEngine.puliziaFileDispovig(nomeFileXmlTmp, nomeFileXml, XML_FLUSSO_TAG);
//				boolean isFileXMLValidated = validationEngine.validateXML(nomeFilePulito, NOME_FILE_XSD_DISPOVIG);
				boolean isFileXMLValidated = true;
				if (!isFileXMLValidated) {
					// log.warn("ATTENZIONE: Un'operazione tra copia, rename o eliminazione del file
					// temp XML OUTPUT non è andata a buon fine.");
					throw new XSDNonSupportedException();
				}
			} catch (XSDNonSupportedException x) {
				log.error("XSD non validato.", x);
				infoRun.setStatoEsecuzione(StatoRun.KO_VALIDAZIONE_SDK);
				infoRun.setDescrizioneStatoEsecuzione(x.getMessage());
				infoRun.setDataFineEsecuzione(new Timestamp(System.currentTimeMillis()));
				gestoreRunLog.updateRun(infoRun);
				return;
			}
			log.info("Pulito file {}", nomeFilePulito);
		}

		infoRun.setNumeroRecord(totale);
		infoRun.setNumeroRecordScartati(scartati);
		infoRun.setNumeroRecordAccettati(accettati);
		infoRun.setDataFineEsecuzione(new Timestamp(System.currentTimeMillis()));
		infoRun = gestoreRunLog.updateRun(infoRun);

		// Update inforun ad elaborata se modalitàOperativa = T
		if (modalitaOperativa == modalitaOperativa.T) {
			log.debug("modalità operativa T, idRun {} in stato elaborata", idRun);
			infoRun.setStatoEsecuzione(StatoRun.ELABORATA);
			gestoreRunLog.updateRun(infoRun);
			return;
		}
		String soglia = conf.getSogliaInvio().getSoglia();
		var divisiore = new BigDecimal(totale);
		BigDecimal sogliaCalcolata = new BigDecimal(accettati).divide(divisiore, 1, RoundingMode.FLOOR)
				.multiply(new BigDecimal("100"));
		log.info("Soglia calcolata {}, soglia fissata {}", sogliaCalcolata, soglia);
		if (accettati > 0 && (sogliaCalcolata.compareTo(new BigDecimal(soglia)) >= 0)
				&& modalitaOperativa.equals(ModalitaOperativa.P)) {
//			periodoRiferimento = periodoRiferimento + PERIODO_RIFERIMENTO_SEPARATOR;
			this.inviaTracciatoDispovig(idRun, nomeFileXml, gestoreRunLog, periodoRiferimento, annoRiferimento);
		} else if (accettati == 0) {
			log.warn("Record da mandare al MdS vuoti per idRun {}", idRun);
			infoRun = gestoreRunLog.cambiaStatoRun(idRun, StatoRun.KO_INVIO_SOGLIA);
			infoRun.setDescrizioneStatoEsecuzione("Soglia minima non raggiunta");
			gestoreRunLog.updateRun(infoRun);
		} else {
			log.warn("Soglia {} non raggiunta, è {}", soglia, sogliaCalcolata);
			infoRun = gestoreRunLog.cambiaStatoRun(idRun, StatoRun.KO_INVIO_SOGLIA);
			infoRun.setDescrizioneStatoEsecuzione("Soglia minima non raggiunta");
			gestoreRunLog.updateRun(infoRun);
		}
	}
    
    public void inviaTracciatoDispovig(String idRun, String nomeFileXml, GestoreRunLog gestoreRunLog,
			String periodoRiferimento, String annoRiferimento) {
		InfoRun infoRun = gestoreRunLog.getRun(idRun);

		infoRun.setNomeFileOutputMds(nomeFileXml);
		gestoreRunLog.updateRun(infoRun);
		List<Path> listPath = List.of(Path.of(nomeFileXml));
		try {
//			ResponseUploadFile resp = connettoreMds.invioTracciati(listPath, CATEGORIA_FLUSSI, "", periodoRiferimento,
//					annoRiferimento);
			Path path = listPath.get(0);
            UploadFileResponse resp= connettoreMds.invioTracciatoDispovig(path.getFileName().toString());
            String esito = resp.getReturn();
            ArrayList<String> codiceDVO = new ArrayList<String>();
            String codiceEsito = "";
            String errorText = "";
            StringTokenizer stEsito = new StringTokenizer(esito,"||");
            while (stEsito.hasMoreTokens()) {
            	codiceEsito = stEsito.nextToken();
            	
            	// elenco codice DVO inseriti
            	String codDVO = stEsito.nextToken();
            	StringTokenizer stCodDVO = new StringTokenizer(codDVO,"$$");
            	while (stCodDVO.hasMoreTokens()) {
            		codiceDVO.add(stCodDVO.nextToken());
				}
				errorText = stEsito.nextToken();
			}
            if ("0".equalsIgnoreCase(codiceEsito)){
           	 log.debug("Riposta MDS ok per run {}, cambio stato a {} e copia xml", idRun, StatoRun.ELABORATA);
               // gestoreRunLog.cambiaStatoRun(idRun, StatoRun.ELABORATA);
                infoRun = gestoreRunLog.cambiaStatoRun(idRun, StatoRun.ELABORATA);
                for (Path pathDaCopiare : listPath) {
                    Files.copy(pathDaCopiare,
                            Paths.get(conf.getSent().getPercorsoSent(), pathDaCopiare.getFileName().toString()));
                }  
                errorText="INFO: il file è stato correttamente acquisito dal servizio";                	
            }else {
           	 log.warn("errore presente in risposta mds per idRun {} : {}", idRun, resp.getReturn());
              //  gestoreRunLog.cambiaStatoRun(idRun, StatoRun.KO_INVIO_MINISTERO);
                infoRun = gestoreRunLog.cambiaStatoRun(idRun, StatoRun.KO_INVIO_MINISTERO);
                codiceDVO=null;
                errorText="ERRORE: il file non è stato acquisito dal servizio";        
            }
           infoRun.setCodiceErroreInvioFlusso(codiceEsito);
           infoRun.setIdUploads(codiceDVO);
           infoRun.setTestoErroreInvioFlusso(errorText);
           gestoreRunLog.updateRun(infoRun);
           
		} catch (ConnettoreMdsException | IOException e) {
			log.error("Errore invioFlussi al ministero per file {}", listPath);
			infoRun = gestoreRunLog.cambiaStatoRun(idRun, StatoRun.KO_INVIO_MINISTERO);
			infoRun.setDescrizioneStatoEsecuzione(e.getMessage());
			gestoreRunLog.updateRun(infoRun);
		} catch (Throwable t) {
			infoRun = gestoreRunLog.cambiaStatoRun(idRun, StatoRun.KO_GENERICO);
			log.error("Errore generico idRun {}", idRun, t);
			infoRun.setDescrizioneStatoEsecuzione(t.getMessage());
			gestoreRunLog.updateRun(infoRun);
		}
	}
    
    public void fileCopy(Path pathDaCopiare, Path path) throws IOException {
		Files.copy(pathDaCopiare, path);
	}

	public ParserTracciatoImpl getParserTracciatoImpl() {
		return new ParserTracciatoImpl();
	}

	public File getfileFromPath(String s) {
		return new File(s);
	}

	public MainTester getMainTester() {
		return new MainTester();
	}
    
//    @Async
//    public void startValidaFlusso(List<RecordDtoGenerico> records, RegoleFlusso regoleFlusso, String idRun, String idClient, ModalitaOperativa modalitaOperativa,
//                                  String periodoRiferimento, String annoRiferimento, String soglia, GestoreRunLog gestoreRunLog) {
//        log.debug("Inizio validazione flusso ");
//        GestoreValidazione gestoreValidazione;
//        List<RecordDtoGenerico> accettati;
//        InfoRun infoRun;
//        try {
//            gestoreValidazione = new GestoreValidazione(validationEngine, parserRegole, parserTracciato);
//            accettati = gestoreValidazione.gestioneValidazione(records, regoleFlusso, idRun, idClient);
//            infoRun = gestoreRunLog.getRun(idRun);
//        } catch (ValidazioneImpossibileException vie) {
//            infoRun = gestoreRunLog.cambiaStatoRun(idRun, StatoRun.KO_GESTIONE_VALIDAZIONE);
//            log.error("Errore validazione idrun {}", idRun, vie);
//            infoRun.setDescrizioneStatoEsecuzione(vie.getMessage());
//            gestoreRunLog.updateRun(infoRun);
//            return;
//        }catch (Throwable t){
//            infoRun = gestoreRunLog.cambiaStatoRun(idRun,StatoRun.KO_GENERICO);
//            log.error("Errore generico idRun {}", idRun, t);
//            infoRun.setDescrizioneStatoEsecuzione(t.getMessage());
//            gestoreRunLog.updateRun(infoRun);
//            return;
//        }
//        log.debug("Dividi tracciato ");
//        List<Path> listPath = null;
//        try {
//            if (accettati.size() > 0) {
//                listPath = tracciatoSplitter.dividiTracciato(accettati.stream().map(element -> (RecordDtoDispovig) element).collect(Collectors.toList()), idRun);
//            }
//        } catch (XSDNonSupportedException e) {
//            log.error("XSD non validato. ", e);
//            infoRun = gestoreRunLog.cambiaStatoRun(idRun, StatoRun.KO_VALIDAZIONE_SDK);
//            infoRun.setDescrizioneStatoEsecuzione(e.getMessage());
//            gestoreRunLog.updateRun(infoRun);
//            return;
//        }catch (Throwable t){
//            infoRun = gestoreRunLog.cambiaStatoRun(idRun,StatoRun.KO_GENERICO);
//            log.error("Errore generico idRun {}", idRun, t);
//            infoRun.setDescrizioneStatoEsecuzione(t.getMessage());
//            gestoreRunLog.updateRun(infoRun);
//            return;
//        }
//        //Update inforun ad elaborata se modalitàOperativa = T
//        if (modalitaOperativa == modalitaOperativa.T) {
//            log.debug("modalità operativa T, idRun {} in stato elaborata", idRun);
//            infoRun.setStatoEsecuzione(StatoRun.ELABORATA);
//            gestoreRunLog.updateRun(infoRun);
//            return;
//        }
//        if ((accettati.size() / records.size()) * 100 >= Integer.parseInt(soglia) && modalitaOperativa.equals(ModalitaOperativa.P)) {
//        	log.debug("Soglia " + soglia + " raggiunta e modalita P");
//            infoRun.setNomeFileOutputMds(listPath.toString());
//            gestoreRunLog.updateRun(infoRun);
//            try {
////                ResponseUploadFile resp = connettoreMds.invioTracciati(listPath, CATEGORIA_FLUSSI, "", periodoRiferimento, annoRiferimento);
//               Path path = listPath.get(0);
//               UploadFileResponse resp= connettoreMds.invioTracciatoDispovig(path.getFileName().toString());
//               String esito = resp.getReturn();
//               String errorCode = "";
//               String errorText = "";
//                 if ("0".equalsIgnoreCase(esito)){
//                	 log.debug("Riposta MDS ok per run {}, cambio stato a {} e copia xml", idRun, StatoRun.ELABORATA);
//                     gestoreRunLog.cambiaStatoRun(idRun, StatoRun.ELABORATA);
//                     for (Path pathDaCopiare : listPath) {
//                         Files.copy(pathDaCopiare,
//                                 Paths.get(conf.getSent().getPercorsoSent() + pathDaCopiare.getFileName().toString()));
//                     }  
//                     errorCode="0";
//                     errorText="INFO: il file è stato correttamente preso in carico dal servizio";                	
//                 }else {
//                	 log.warn("errore presente in risposta mds per idRun {} : {}", idRun, resp.getReturn());
//                     gestoreRunLog.cambiaStatoRun(idRun, StatoRun.KO_INVIO_MINISTERO);
//                     errorCode="9";
//                     errorText="ERRORE: il file non è stato preso in carico dal servizio";        
//                 }
//                infoRun.setCodiceErroreInvioFlusso(errorCode);
//                infoRun.setTestoErroreInvioFlusso(errorText);
//                gestoreRunLog.updateRun(infoRun);
//            } catch (ConnettoreMdsException | IOException e) {
//            	log.error("Errore invioFlussi al ministero per file {}", listPath);
//                infoRun = gestoreRunLog.cambiaStatoRun(idRun, StatoRun.KO_INVIO_MINISTERO);
//                infoRun.setDescrizioneStatoEsecuzione(e.getMessage());
//                gestoreRunLog.updateRun(infoRun);
//            }catch (Throwable t){
//                infoRun = gestoreRunLog.cambiaStatoRun(idRun,StatoRun.KO_GENERICO);
//                log.error("Errore generico idRun {}", idRun, t);
//                infoRun.setDescrizioneStatoEsecuzione(t.getMessage());
//                gestoreRunLog.updateRun(infoRun);
//                return;
//            }
//        } else if ((accettati.size() / records.size()) * 100 < Integer.parseInt(soglia) && modalitaOperativa.equals(ModalitaOperativa.P)) {
//            log.warn("L'invio per idRun {} non è stato effettuato perchè non è stata raggiunta la soglia minima. Soglia = {}", idRun, (accettati.size() / records.size()));
//            infoRun = gestoreRunLog.cambiaStatoRun(idRun, StatoRun.KO_INVIO_SOGLIA);
//            infoRun.setDescrizioneStatoEsecuzione("Soglia minima non raggiunta");
//            gestoreRunLog.updateRun(infoRun);
//        }
//    }
    
    
//    public List<Esito> startValidaRecord(RecordDtoGenerico dtoGenerico, RegoleFlusso regoleFlusso, String idRun, String idClient, ModalitaOperativa modalitaOperativa) {
//    	GestoreValidazione gestoreValidazione = new GestoreValidazione(validationEngine, parserRegole, parserTracciato);
//        List<Esito> esiti = gestoreValidazione.gestioneValidazioneRecord(dtoGenerico, regoleFlusso, idRun);
//        Boolean isOK = esiti.stream().filter(e -> !e.isValoreEsito()).collect(Collectors.toList()).isEmpty();
//        if (isOK && modalitaOperativa.equals(ModalitaOperativa.P)) {
//            RecordDtoDispovig recordDtodir = (RecordDtoDispovig) dtoGenerico;
//            List<RecordDtoDispovig> recordList = new ArrayList<>();
//            recordList.add(recordDtodir);
//            tracciatoSplitter.dividiTracciato(recordList, idRun);
//        }
//        return esiti;
//    }
}
