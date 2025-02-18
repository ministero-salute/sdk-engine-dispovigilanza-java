/* SPDX-License-Identifier: BSD-3-Clause */
 
package it.mds.sdk.flusso.dispovig.regole.custom;

import java.lang.reflect.InvocationTargetException;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorValue;

import it.mds.sdk.gestoreesiti.modelli.Esito;
import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
import it.mds.sdk.libreriaregole.exception.ValidazioneImpossibileException;
import it.mds.sdk.libreriaregole.regole.beans.Parametri;
import it.mds.sdk.libreriaregole.regole.beans.RegolaGenerica;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@XmlDiscriminatorValue("RegolaAnnoPosterioreCrossCampo")
public class RegolaAnnoPosterioreCrossCampo extends RegolaGenerica {
	
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
	public static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public RegolaAnnoPosterioreCrossCampo(String nome, String codErrore, String desErrore, Parametri parametri) {
		super(nome, codErrore, desErrore, parametri);
	}

	/**
	 *
	 * Controlla che il valore del campo passato in ingresso(una data solo anno) sia posteriore a un'altra data all'interno dello stesso DTO (se presente).
	 * nomeCampoValidatore é il parametro che contiene il nome del record del DTO con cui voglio comparare il dato in ingresso
	 * @param nomeCampo campo da validare
	 * @param recordDtoGenerico DTO del record del flusso
	 * @return lista di {@link it.mds.sdk.gestoreesiti.modelli.Esito}
	 */
	@Override
	public List<Esito> valida(String nomeCampo, RecordDtoGenerico recordDtoGenerico) {
		List<Esito> listaEsiti = new ArrayList<>();
		try {
			String annoDaComparareString = (String)recordDtoGenerico.getCampo(nomeCampo);
			String nomeCampoValidatore = this.getParametri().getParametriMap().get("nomeCampoValidatore");
			String dataValidatoreString = (String)recordDtoGenerico.getCampo(nomeCampoValidatore);
			
			if(dataValidatoreString != null){
				Year annoDaComparare ;
				if(annoDaComparareString!= null ) {
					annoDaComparare = Year.parse(annoDaComparareString,formatter);
					Year dataValidatore = Year.parse(dataValidatoreString, formatterDate);
					if(annoDaComparare.isAfter(dataValidatore)){						
						listaEsiti.add(creaEsitoKO(nomeCampo,this.getCodErrore(),this.getDesErrore()));
					}else{
						listaEsiti.add(creaEsitoOk(nomeCampo));
					}
				}else {
					listaEsiti.add(creaEsitoOk(nomeCampo));
							//.withDescrizione("Il campo  " + nomeCampo + " non presente. Non é possibile validare questa regola")
				}
			}else{
				listaEsiti.add(creaEsitoOk(nomeCampo));
			}
		} catch (InvocationTargetException | NoSuchMethodException |IllegalAccessException  e) {
			log.error("Non è possibile validare la regola di data posteriore del campo " + nomeCampo, e);
			throw new ValidazioneImpossibileException("Non è possibile validare la regola di data posteriore del campo " + nomeCampo );
		}catch (DateTimeParseException dataE) {
			listaEsiti.add(creaEsitoKO(nomeCampo,this.getCodErrore(),"Formato anno non corretto"));
		}
		return listaEsiti;
	}

}
