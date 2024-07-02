/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.parser.regole;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.mds.sdk.flusso.dispovig.regole.custom.RegolaIncongruenzaCrossDueCampi;
import it.mds.sdk.flusso.dispovig.regole.custom.RegolaObbligatorietaCondizionataAlmenoUnoCampi;
import it.mds.sdk.flusso.dispovig.regole.custom.RegolaObbligatorietaCondizionataMultiCampo;
import it.mds.sdk.flusso.dispovig.regole.custom.RegolaObbligatorietaCondizionataValoreCampoVuoto;
import it.mds.sdk.flusso.dispovig.regole.custom.RegolaUguaglianzaCrossCampoCondizionato;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigAzioniIntraprese;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigClassCND;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigCodStruttSan;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigCodiceDispositivo;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigComuneFabbr;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigComuneMandatario;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigDenomFabbr;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigDenomMandatario;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigDescComuneFabbr;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigDescComuneMandatario;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigDescNazioneFabbr;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigDescNazioneMandatario;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigDescProvinciaFabbr;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigDescProvinciaMandatario;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigDescRegioneFabbr;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigDescRegioneMandatario;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigIndirizzoFabbr;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigIndirizzoMandatario;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigLocalitaFabbr;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigLocalitaMandatario;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigLuogoAziendaOspLocale;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigLuogoIncidente;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigNazioneFabbr;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigNazioneMandatario;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigNomeDispositivo;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigProvinciaFabbr;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigProvinciaMandatario;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigRegioneFabbr;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigRegioneMandatario;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigStruttSan;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigTextAzioniIntraprese;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigTipoDispositivo;
import it.mds.sdk.flusso.dispovig.regole.custom.adhoc.RegolaDispovigTipoIVD;
import it.mds.sdk.libreriaregole.regole.beans.Campo;
import it.mds.sdk.libreriaregole.regole.beans.Parametri;
import it.mds.sdk.libreriaregole.regole.beans.RegolaGenerica;
import it.mds.sdk.libreriaregole.regole.beans.RegoleFlusso;

public class CreazioneRegoleCustomDispovig {
	
	public static RegoleFlusso creaRegoleDispovig(RegoleFlusso regoleFlusso) {
		// aggiungi regole custom
		List<RegolaGenerica> regoleModelloDispositivo = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleCodiceFabbrDisositivo = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleVersioneSoftware = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoUtilizzo = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoDescUtilizzo = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoAltroUtilizzoDispo = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoAltraConseguenza = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoLuogoDispoDispo = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoAltreAzioniIntraprese = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoCompilatoreNome = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoCompilatoreCognome = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoCompilatoreQualificaComp = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoCompilatoreTelefono = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoCompilatoreFax = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoCompilatoreEmail = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleLuogoCodAziOspLocale = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleTipoDispositivo = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleCodiceDispositivo = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleNomeDispositivo = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleDispositivoCND = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleTipoIVD = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleDenomFabbricante = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleNazioneFabbricante = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleDescNazioneFabbricante = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleRegioneFabbricante = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleDescRegioneFabbricante = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleProvinciaFabbricante = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleDescProvinciaFabbricante = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleComuneFabbricante = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleDescComuneFabbricante = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleLocalitaFabbricante = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleIndirizzoFabbricante = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleDenominazioneMandatario = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleNazioneMandatario = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleDescNazioneMandatario = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleRegioneMandatario = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleDescRegioneMandatario = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleProvinciaMandatario = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleDescProvinciaMandatario = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleComuneMandatario = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleDescComuneMandatario = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleLocalitaMandatario = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleIndirizzoMandatario = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleStruttSanitaria = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleCodStruttSanitaria = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleAzioniIntraprese = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleLuogoEpisodioCodStruttura = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleLuogoEpisodioNomeStruttura = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleLuogoEpisodioAnnoValid = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoLuogoEpisodioTelefono = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoLuogoEpisodioFax = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleEventoLuogoEpisodioEmail = new ArrayList<RegolaGenerica>();
		List<RegolaGenerica> regoleTextAzioniIntraprese = new ArrayList<RegolaGenerica>();
		
			
        Campo rapporto_dispositivo_modelloDispositivo = creaRegolaModelloDispositivo(regoleModelloDispositivo, "rapporto_dispositivo_modelloDispositivo",null);
        Campo rapporto_dispositivo_codiceFabbrDispositivo = creaRegolaCodiceFabbrDisositivo(regoleCodiceFabbrDisositivo, "rapporto_dispositivo_codiceFabbrDispositivo",null);
        Campo rapporto_dispositivo_versioneSoftware = creaRegolaVersioneSoftware(regoleVersioneSoftware, "rapporto_dispositivo_versioneSoftware",null);
        Campo rapporto_evento_utilizzo = creaRegolaEventoUtilizzo(regoleEventoUtilizzo, "rapporto_evento_utilizzo",null);
        Campo rapporto_evento_descUtilizzo = creaRegolaEventoDescUtilizzo(regoleEventoDescUtilizzo, "rapporto_evento_descUtilizzo",null);
        Campo rapporto_evento_altroUtilizzoDispo = creaRegolaEventoAltroUtilizzoDispo(regoleEventoAltroUtilizzoDispo, "rapporto_evento_altroUtilizzoDispo",null);
        Campo rapporto_evento_altraConseguenza = creaRegolaEventoAltraConseguenza(regoleEventoAltraConseguenza, "rapporto_evento_altraConseguenza",null);
        Campo rapporto_evento_luogoDisponibilitaDispositivo = creaRegolaEventoLuogoDispoDispo(regoleEventoLuogoDispoDispo, "rapporto_evento_luogoDisponibilitaDispositivo",null);
        Campo rapporto_evento_altreAzioniIntraprese = creaRegolaEventoAltreAzioniIntraprese(regoleEventoAltreAzioniIntraprese, "rapporto_evento_altreAzioniIntraprese",null);
        Campo rapporto_compilatore_nome = creaRegolaEventoCompilatoreNome(regoleEventoCompilatoreNome, "rapporto_compilatore_nome",null);
        Campo rapporto_compilatore_cognome = creaRegolaEventoCompilatoreCognome(regoleEventoCompilatoreCognome, "rapporto_compilatore_cognome",null);
        Campo rapporto_compilatore_qualificaCompilatore = creaRegolaEventoCompilatoreQualificaComp(regoleEventoCompilatoreQualificaComp, "rapporto_compilatore_qualificaCompilatore",null);
        Campo rapporto_compilatore_telefonoCompilatore = creaRegolaEventoCompilatoreTelefono(regoleEventoCompilatoreTelefono, "rapporto_compilatore_telefonoCompilatore",null);
        Campo rapporto_compilatore_faxCompilatore = creaRegolaEventoCompilatoreFax(regoleEventoCompilatoreFax, "rapporto_compilatore_faxCompilatore",null);
        Campo rapporto_compilatore_emailCompilatore = creaRegolaEventoCompilatoreEmail(regoleEventoCompilatoreEmail, "rapporto_compilatore_emailCompilatore",null);
        Campo rapporto_luogoEpisodio_telefono = creaRegolaEventoLuogoEpisodioTelefono(regoleEventoLuogoEpisodioTelefono, "rapporto_luogoEpisodio_telefono",null);
        Campo rapporto_luogoEpisodio_fax = creaRegolaEventoLuogoEpisodioFax(regoleEventoLuogoEpisodioFax, "rapporto_luogoEpisodio_fax",null);
        Campo rapporto_luogoEpisodio_email = creaRegolaEventoLuogoEpisodioEmail(regoleEventoLuogoEpisodioEmail, "rapporto_luogoEpisodio_email",null);
        Campo rapporto_luogoEpisodio_codAziendaOspLocale = creaRegolaLuogoCodAziOspLocale(regoleLuogoCodAziOspLocale, "rapporto_luogoEpisodio_codAziendaOspLocale",null);
        Campo rapporto_dispositivo_tipoDispositivo = creaRegolaTipoDispositivo(regoleTipoDispositivo, "rapporto_dispositivo_tipoDispositivo",null);
        Campo rapporto_dispositivo_codiceDispositivo = creaRegolaCodiceDispositivo(regoleCodiceDispositivo, "rapporto_dispositivo_codiceDispositivo",null);
        Campo rapporto_dispositivo_nomeDispositivo = creaRegolaNomeDispositivo(regoleNomeDispositivo, "rapporto_dispositivo_nomeDispositivo",null);
        Campo rapporto_dispositivo_CND = creaRegolaDispositivoCND(regoleDispositivoCND, "rapporto_dispositivo_CND",null);
        Campo rapporto_dispositivo_tipoIVD = creaRegolaTipoIVD(regoleTipoIVD, "rapporto_dispositivo_tipoIVD",null);
        Campo rapporto_dispositivo_denominazioneFabbricante = creaRegolaDenomFabbricante(regoleDenomFabbricante, "rapporto_dispositivo_denominazioneFabbricante",null);
        Campo rapporto_dispositivo_nazioneFabbricante = creaRegolaNazioneFabbricante(regoleNazioneFabbricante, "rapporto_dispositivo_nazioneFabbricante",null);
        Campo rapporto_dispositivo_descNazioneFabbricante = creaRegolaDescNazioneFabbricante(regoleDescNazioneFabbricante, "rapporto_dispositivo_descNazioneFabbricante",null);
        Campo rapporto_dispositivo_regioneFabbricante = creaRegolaRegioneFabbricante(regoleRegioneFabbricante, "rapporto_dispositivo_regioneFabbricante",null);
        Campo rapporto_dispositivo_descRegioneFabbricante = creaRegolaDescRegioneFabbricante(regoleDescRegioneFabbricante, "rapporto_dispositivo_descRegioneFabbricante",null);
        Campo rapporto_dispositivo_provinciaFabbricante = creaRegolaProvinciaFabbricante(regoleProvinciaFabbricante, "rapporto_dispositivo_provinciaFabbricante",null);
        Campo rapporto_dispositivo_descProvinciaFabbricante = creaRegolaDescProvinciaFabbricante(regoleDescProvinciaFabbricante, "rapporto_dispositivo_descRegioneFabbricante",null);
        Campo rapporto_dispositivo_comuneFabbricante = creaRegolaComuneFabbricante(regoleComuneFabbricante, "rapporto_dispositivo_comuneFabbricante",null);
        Campo rapporto_dispositivo_descComuneFabbricante = creaRegolaDescComuneFabbricante(regoleDescComuneFabbricante, "rapporto_dispositivo_descComuneFabbricante",null);
        Campo rapporto_dispositivo_localitaFabbricante = creaRegolaLocalitaFabbricante(regoleLocalitaFabbricante, "rapporto_dispositivo_localitaFabbricante",null);
        Campo rapporto_dispositivo_indirizzoFabbricante = creaRegolaIndirizzoFabbricante(regoleIndirizzoFabbricante, "rapporto_dispositivo_indirizzoFabbricante",null);
        Campo rapporto_dispositivo_denominazioneMandatario = creaRegolaDenominazioneMandatario(regoleDenominazioneMandatario, "rapporto_dispositivo_denominazioneMandatario",null);
        Campo rapporto_dispositivo_nazioneMandatario = creaRegolaNazioneMandatario(regoleNazioneMandatario, "rapporto_dispositivo_nazioneMandatario",null);
        Campo rapporto_dispositivo_descNazioneMandatario = creaRegolaDescNazioneMandatario(regoleDescNazioneMandatario, "rapporto_dispositivo_descNazioneMandatario",null);
        Campo rapporto_dispositivo_regioneMandatario = creaRegolaRegioneMandatario(regoleRegioneMandatario, "rapporto_dispositivo_regioneMandatario",null);
        Campo rapporto_dispositivo_descRegioneMandatario = creaRegolaDescRegioneMandatario(regoleDescRegioneMandatario, "rapporto_dispositivo_descRegioneMandatario",null);
        Campo rapporto_dispositivo_provinciaMandatario = creaRegolaProvinciaMandatario(regoleProvinciaMandatario, "rapporto_dispositivo_provinciaMandatario",null);
        Campo rapporto_dispositivo_descProvinciaMandatario = creaRegolaDescProvinciaMandatario(regoleDescProvinciaMandatario, "rapporto_dispositivo_descProvinciaMandatario",null);
        Campo rapporto_dispositivo_comuneMandatario = creaRegolaComuneMandatario(regoleComuneMandatario, "rapporto_dispositivo_comuneMandatario",null);
        Campo rapporto_dispositivo_descComuneMandatario = creaRegolaDescComuneMandatario(regoleDescComuneMandatario, "rapporto_dispositivo_descComuneMandatario",null);
        Campo rapporto_dispositivo_localitaMandatario = creaRegolaLocalitaMandatario(regoleLocalitaMandatario, "rapporto_dispositivo_localitaMandatario",null);
        Campo rapporto_dispositivo_indirizzoMandatario = creaRegolaIndirizzoMandatario(regoleIndirizzoMandatario, "rapporto_dispositivo_indirizzoMandatario",null);
        Campo rapporto_compilatore_struttSanitaria = creaRegolaStruttSanitaria(regoleStruttSanitaria, "rapporto_compilatore_struttSanitaria",null);
        Campo rapporto_compilatore_codStruttSanitaria = creaRegolaCodStruttSanitaria(regoleCodStruttSanitaria, "rapporto_compilatore_codStruttSanitaria",null);
        Campo rapporto_evento_azioniIntraprese = creaRegolaAzioniIntraprese(regoleAzioniIntraprese, "rapporto_evento_azioniIntraprese",null);
        Campo rapporto_evento_textAzioniIntraprese = creaRegolaTextAzioniIntraprese(regoleTextAzioniIntraprese, "rapporto_evento_textAzioniIntraprese",null);
        Campo rapporto_luogoEpisodio_codiceStruttura = creaRegolaLuogoEpisodioCodStruttura(regoleLuogoEpisodioCodStruttura, "rapporto_luogoEpisodio_codiceStruttura",null);        
        Campo rapporto_luogoEpisodio_nomeStruttura = creaRegolaLuogoEpisodioNomeStruttura(regoleLuogoEpisodioNomeStruttura, "rapporto_luogoEpisodio_nomeStruttura",null);
        
        List<Campo> campi = regoleFlusso.getCampi();
        
        for (Iterator<Campo> i = campi.iterator(); i.hasNext();) {
            Campo itemCampo = i.next();
            
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_modelloDispositivo.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_modelloDispositivo.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_codiceFabbrDispositivo.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_codiceFabbrDispositivo.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_versioneSoftware.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_versioneSoftware.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_evento_utilizzo.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_evento_utilizzo.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_evento_descUtilizzo.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_evento_descUtilizzo.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_evento_altroUtilizzoDispo.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_evento_altroUtilizzoDispo.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_evento_altraConseguenza.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_evento_altraConseguenza.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_evento_luogoDisponibilitaDispositivo.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_evento_luogoDisponibilitaDispositivo.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_evento_altreAzioniIntraprese.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_evento_altreAzioniIntraprese.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_compilatore_nome.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_compilatore_nome.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_compilatore_cognome.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_compilatore_cognome.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_compilatore_qualificaCompilatore.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_compilatore_qualificaCompilatore.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_compilatore_telefonoCompilatore.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_compilatore_telefonoCompilatore.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_compilatore_faxCompilatore.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_compilatore_faxCompilatore.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_compilatore_emailCompilatore.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_compilatore_emailCompilatore.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_luogoEpisodio_telefono.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_luogoEpisodio_telefono.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_luogoEpisodio_fax.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_luogoEpisodio_fax.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_luogoEpisodio_email.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_luogoEpisodio_email.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_luogoEpisodio_codAziendaOspLocale.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_luogoEpisodio_codAziendaOspLocale.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_tipoDispositivo.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_tipoDispositivo.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_codiceDispositivo.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_codiceDispositivo.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_nomeDispositivo.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_nomeDispositivo.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_CND.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_CND.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_tipoIVD.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_tipoIVD.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_denominazioneFabbricante.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_denominazioneFabbricante.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_nazioneFabbricante.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_nazioneFabbricante.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_descNazioneFabbricante.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_descNazioneFabbricante.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_regioneFabbricante.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_regioneFabbricante.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_descRegioneFabbricante.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_descRegioneFabbricante.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_provinciaFabbricante.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_provinciaFabbricante.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_descProvinciaFabbricante.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_descProvinciaFabbricante.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_comuneFabbricante.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_comuneFabbricante.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_descComuneFabbricante.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_descComuneFabbricante.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_localitaFabbricante.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_localitaFabbricante.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_indirizzoFabbricante.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_indirizzoFabbricante.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_denominazioneMandatario.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_denominazioneMandatario.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_nazioneMandatario.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_nazioneMandatario.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_descNazioneMandatario.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_descNazioneMandatario.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_regioneMandatario.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_regioneMandatario.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_descRegioneMandatario.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_descRegioneMandatario.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_provinciaMandatario.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_provinciaMandatario.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_descProvinciaMandatario.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_descProvinciaMandatario.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_comuneMandatario.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_comuneMandatario.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_descComuneMandatario.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_descComuneMandatario.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_localitaMandatario.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_localitaMandatario.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_dispositivo_indirizzoMandatario.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_dispositivo_indirizzoMandatario.getRegole());
            	itemCampo.setRegole(lstRegole);
            } 
            if (itemCampo.getNomeCampo().equals(rapporto_compilatore_struttSanitaria.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_compilatore_struttSanitaria.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_compilatore_codStruttSanitaria.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_compilatore_codStruttSanitaria.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_evento_azioniIntraprese.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_evento_azioniIntraprese.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_evento_textAzioniIntraprese.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_evento_textAzioniIntraprese.getRegole());
            	itemCampo.setRegole(lstRegole);
            }            
            if (itemCampo.getNomeCampo().equals(rapporto_luogoEpisodio_codiceStruttura.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_luogoEpisodio_codiceStruttura.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            if (itemCampo.getNomeCampo().equals(rapporto_luogoEpisodio_nomeStruttura.getNomeCampo())){ 
            	List<RegolaGenerica>  lstRegole = itemCampo.getRegole();
            	if (lstRegole == null)
            		lstRegole = new ArrayList<RegolaGenerica>();
            	            	
            	lstRegole.addAll(lstRegole.size(), rapporto_luogoEpisodio_nomeStruttura.getRegole());
            	itemCampo.setRegole(lstRegole);
            }
            
            
        }
        
        regoleFlusso =  RegoleFlusso.builder()
				.withCampi(campi)
				.build();
        
        return regoleFlusso;
	}
	
	
	private static Campo creaRegolaModelloDispositivo (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "IVD");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_dispositivo_tipoDispositivo");

		RegolaObbligatorietaCondizionataMultiCampo regolaObbligatorietaCondizionataMultiCampo = new RegolaObbligatorietaCondizionataMultiCampo(
				"regolaObbligatorietaCondizionataMultiCampo", "1046",
				"Mancata valorizzazione di un campo ad obbligatorietà condizionata", parametri);

		// aggiungo regole
		regoleCampo.add(regolaObbligatorietaCondizionataMultiCampo);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaCodiceFabbrDisositivo (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_dispositivo_codiceDispositivo");

		RegolaObbligatorietaCondizionataValoreCampoVuoto regolaObbligatorietaCondizionataValoreCampoVuoto = new RegolaObbligatorietaCondizionataValoreCampoVuoto(
				"regolaObbligatorietaCondizionataValoreCampoVuoto", "1048",
				"Mancata valorizzazione di un campo ad obbligatorietà condizionata", parametri);

		// TODOs: fare codice 1049 -> controllo su db se codiceDispositivo è valorizzato
		
		// aggiungo regole
		regoleCampo.add(regolaObbligatorietaCondizionataValoreCampoVuoto);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaVersioneSoftware (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "IVD");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_dispositivo_tipoDispositivo");

		RegolaIncongruenzaCrossDueCampi regolaIncongruenzaCrossDueCampi = new RegolaIncongruenzaCrossDueCampi(
				"regolaIncongruenzaCrossDueCampi", "10701", null, parametri);

		// aggiungo regole
		regoleCampo.add(regolaIncongruenzaCrossDueCampi);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoUtilizzo (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "Y");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_evento_isDispUtilizzato");

		RegolaObbligatorietaCondizionataMultiCampo regolaObbligatorietaCondizionataMultiCampo = new RegolaObbligatorietaCondizionataMultiCampo(
				"regolaIncongruenzaCrossDueCampi", "1137", "Mancata valorizzazione di un campo ad obbligatorietà condizionata.", parametri);

		// seconda Regola
		parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "Y");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_evento_isDispUtilizzato");

		RegolaIncongruenzaCrossDueCampi regolaIncongruenzaCrossDueCampi = new RegolaIncongruenzaCrossDueCampi(
				"regolaIncongruenzaCrossDueCampi", "11371", null, parametri);

		
		
		// aggiungo regole
		regoleCampo.add(regolaObbligatorietaCondizionataMultiCampo);
		regoleCampo.add(regolaIncongruenzaCrossDueCampi);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoDescUtilizzo (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "Y");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_evento_isDispUtilizzato");

		RegolaObbligatorietaCondizionataMultiCampo regolaObbligatorietaCondizionataMultiCampo = new RegolaObbligatorietaCondizionataMultiCampo(
				"regolaIncongruenzaCrossDueCampi", "1139", "Mancata valorizzazione di un campo ad obbligatorietà condizionata.", parametri);

		// seconda Regola
		parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "Y");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_evento_isDispUtilizzato");

		RegolaIncongruenzaCrossDueCampi regolaIncongruenzaCrossDueCampi = new RegolaIncongruenzaCrossDueCampi(
				"regolaIncongruenzaCrossDueCampi", "11391", null, parametri);

				
				
		// aggiungo regole
		regoleCampo.add(regolaObbligatorietaCondizionataMultiCampo);
		regoleCampo.add(regolaIncongruenzaCrossDueCampi);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoAltroUtilizzoDispo (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "6");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_evento_utilizzo");

		RegolaObbligatorietaCondizionataMultiCampo regolaObbligatorietaCondizionataMultiCampo = new RegolaObbligatorietaCondizionataMultiCampo(
				"regolaIncongruenzaCrossDueCampi", "1142", "Mancata valorizzazione di un campo ad obbligatorietà condizionata.", parametri);

		// seconda Regola
		parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "6");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_evento_utilizzo");

		RegolaIncongruenzaCrossDueCampi regolaIncongruenzaCrossDueCampi = new RegolaIncongruenzaCrossDueCampi(
				"regolaIncongruenzaCrossDueCampi", "11421", null, parametri);

				
				
		// aggiungo regole
		regoleCampo.add(regolaObbligatorietaCondizionataMultiCampo);
		regoleCampo.add(regolaIncongruenzaCrossDueCampi);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoAltraConseguenza (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "8");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_evento_conseguenza");

		RegolaObbligatorietaCondizionataMultiCampo regolaObbligatorietaCondizionataMultiCampo = new RegolaObbligatorietaCondizionataMultiCampo(
				"regolaIncongruenzaCrossDueCampi", "1151", "Mancata valorizzazione di un campo ad obbligatorietà condizionata.", parametri);

		// seconda Regola
		parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "8");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_evento_conseguenza");

		RegolaIncongruenzaCrossDueCampi regolaIncongruenzaCrossDueCampi = new RegolaIncongruenzaCrossDueCampi(
				"regolaIncongruenzaCrossDueCampi", "11511", null, parametri);

				
				
		// aggiungo regole
		regoleCampo.add(regolaObbligatorietaCondizionataMultiCampo);
		regoleCampo.add(regolaIncongruenzaCrossDueCampi);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoLuogoDispoDispo (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "Y");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_evento_disponibilitaDispositivo");

		RegolaObbligatorietaCondizionataMultiCampo regolaObbligatorietaCondizionataMultiCampo = new RegolaObbligatorietaCondizionataMultiCampo(
				"regolaIncongruenzaCrossDueCampi", "1157", "Mancata valorizzazione di un campo ad obbligatorietà condizionata.", parametri);

		// seconda Regola
		parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "Y");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_evento_disponibilitaDispositivo");

		RegolaIncongruenzaCrossDueCampi regolaIncongruenzaCrossDueCampi = new RegolaIncongruenzaCrossDueCampi(
				"regolaIncongruenzaCrossDueCampi", "11571", null, parametri);

				
				
		// aggiungo regole
		regoleCampo.add(regolaObbligatorietaCondizionataMultiCampo);
		regoleCampo.add(regolaIncongruenzaCrossDueCampi);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoAltreAzioniIntraprese (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "Altro");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_evento_textAzioniIntraprese");

		RegolaObbligatorietaCondizionataMultiCampo regolaObbligatorietaCondizionataMultiCampo = new RegolaObbligatorietaCondizionataMultiCampo(
				"regolaIncongruenzaCrossDueCampi", "1162", "Mancata valorizzazione di un campo ad obbligatorietà condizionata.", parametri);

		// seconda Regola
		parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante", "Altro");
		parametri.getParametriMap().put("nomeCampoCondizionante", "rapporto_evento_textAzioniIntraprese");

		RegolaIncongruenzaCrossDueCampi regolaIncongruenzaCrossDueCampi = new RegolaIncongruenzaCrossDueCampi(
				"regolaIncongruenzaCrossDueCampi", "11621", null, parametri);

				
		// aggiungo regole
		regoleCampo.add(regolaObbligatorietaCondizionataMultiCampo);
		regoleCampo.add(regolaIncongruenzaCrossDueCampi);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoCompilatoreNome (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("campoValidatore", "rapporto_compilatore_ruolo");
		parametri.getParametriMap().put("parametroInput", "2");
		parametri.getParametriMap().put("campoUguaglianza", "rapporto_luogoEpisodio_nomeOperatore");

		RegolaUguaglianzaCrossCampoCondizionato regolaUguaglianzaCrossCampoCondizionato = new RegolaUguaglianzaCrossCampoCondizionato(
				"regolaUguaglianzaCrossCampoCondizionato", "1171", "Non appartenenza al dominio di riferimento", parametri);

		// seconda regola
		parametri = new Parametri();
		parametri.getParametriMap().put("campoValidatore", "rapporto_compilatore_ruolo");
		parametri.getParametriMap().put("parametroInput", "3");
		parametri.getParametriMap().put("campoUguaglianza", "rapporto_luogoEpisodio_nomeResponsabile");

		RegolaUguaglianzaCrossCampoCondizionato regolaUguaglianzaCrossCampoCondizionato2 = new RegolaUguaglianzaCrossCampoCondizionato(
				"regolaUguaglianzaCrossCampoCondizionato", "1171", "Non appartenenza al dominio di riferimento", parametri);
				
				
		// aggiungo regole
		regoleCampo.add(regolaUguaglianzaCrossCampoCondizionato);
		regoleCampo.add(regolaUguaglianzaCrossCampoCondizionato2);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoCompilatoreCognome (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("campoValidatore", "rapporto_compilatore_ruolo");
		parametri.getParametriMap().put("parametroInput", "2");
		parametri.getParametriMap().put("campoUguaglianza", "rapporto_luogoEpisodio_cognomeOperatore");

		RegolaUguaglianzaCrossCampoCondizionato regolaUguaglianzaCrossCampoCondizionato = new RegolaUguaglianzaCrossCampoCondizionato(
				"regolaUguaglianzaCrossCampoCondizionato", "1174", "Non appartenenza al dominio di riferimento", parametri);

		// seconda regola
		parametri = new Parametri();
		parametri.getParametriMap().put("campoValidatore", "rapporto_compilatore_ruolo");
		parametri.getParametriMap().put("parametroInput", "3");
		parametri.getParametriMap().put("campoUguaglianza", "rapporto_luogoEpisodio_cognomeResponsabile");

		RegolaUguaglianzaCrossCampoCondizionato regolaUguaglianzaCrossCampoCondizionato2 = new RegolaUguaglianzaCrossCampoCondizionato(
				"regolaUguaglianzaCrossCampoCondizionato", "1174", "Non appartenenza al dominio di riferimento", parametri);
				
				
		// aggiungo regole
		regoleCampo.add(regolaUguaglianzaCrossCampoCondizionato);
		regoleCampo.add(regolaUguaglianzaCrossCampoCondizionato2);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoCompilatoreQualificaComp (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("campoValidatore", "rapporto_compilatore_ruolo");
		parametri.getParametriMap().put("parametroInput", "2");
		parametri.getParametriMap().put("campoUguaglianza", "rapporto_luogoEpisodio_qualificaOperatore");

		RegolaUguaglianzaCrossCampoCondizionato regolaUguaglianzaCrossCampoCondizionato = new RegolaUguaglianzaCrossCampoCondizionato(
				"regolaUguaglianzaCrossCampoCondizionato", "1177", "Non appartenenza al dominio di riferimento", parametri);

		// aggiungo regole
		regoleCampo.add(regolaUguaglianzaCrossCampoCondizionato);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoCompilatoreTelefono (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("campoValidatore", "rapporto_compilatore_ruolo");
		parametri.getParametriMap().put("parametroInput", "2");
		parametri.getParametriMap().put("campoUguaglianza", "rapporto_luogoEpisodio_telefono");

		RegolaUguaglianzaCrossCampoCondizionato regolaUguaglianzaCrossCampoCondizionato = new RegolaUguaglianzaCrossCampoCondizionato(
				"regolaUguaglianzaCrossCampoCondizionato", "1185", "Non appartenenza al dominio di riferimento", parametri);
		
		
		// seconda regola
		parametri = new Parametri();
		parametri.getParametriMap().put("listaCampi", "rapporto_compilatore_telefonoCompilatore|rapporto_compilatore_faxCompilatore|rapporto_compilatore_emailCompilatore");
		
		RegolaObbligatorietaCondizionataAlmenoUnoCampi regolaObbligatorietaCondizionataAlmenoUnoCampi = new RegolaObbligatorietaCondizionataAlmenoUnoCampi(
				"regolaObbligatorietaCondizionataAlmenoUnoCampi", "1182", "Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato", parametri);
		

		// aggiungo regole
		regoleCampo.add(regolaUguaglianzaCrossCampoCondizionato);
		regoleCampo.add(regolaObbligatorietaCondizionataAlmenoUnoCampi);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoCompilatoreFax (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("campoValidatore", "rapporto_compilatore_ruolo");
		parametri.getParametriMap().put("parametroInput", "2");
		parametri.getParametriMap().put("campoUguaglianza", "rapporto_luogoEpisodio_fax");

		RegolaUguaglianzaCrossCampoCondizionato regolaUguaglianzaCrossCampoCondizionato = new RegolaUguaglianzaCrossCampoCondizionato(
				"regolaUguaglianzaCrossCampoCondizionato", "1189", "Non appartenenza al dominio di riferimento", parametri);
		
		// seconda regola
		parametri = new Parametri();
		parametri.getParametriMap().put("listaCampi", "rapporto_compilatore_telefonoCompilatore|rapporto_compilatore_faxCompilatore|rapporto_compilatore_emailCompilatore");
		
		RegolaObbligatorietaCondizionataAlmenoUnoCampi regolaObbligatorietaCondizionataAlmenoUnoCampi = new RegolaObbligatorietaCondizionataAlmenoUnoCampi(
				"regolaObbligatorietaCondizionataAlmenoUnoCampi", "1186", "Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato", parametri);
		
				
		// aggiungo regole
		regoleCampo.add(regolaUguaglianzaCrossCampoCondizionato);
		regoleCampo.add(regolaObbligatorietaCondizionataAlmenoUnoCampi);
		
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoCompilatoreEmail (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("campoValidatore", "rapporto_compilatore_ruolo");
		parametri.getParametriMap().put("parametroInput", "2");
		parametri.getParametriMap().put("campoUguaglianza", "rapporto_luogoEpisodio_email");

		RegolaUguaglianzaCrossCampoCondizionato regolaUguaglianzaCrossCampoCondizionato = new RegolaUguaglianzaCrossCampoCondizionato(
				"regolaUguaglianzaCrossCampoCondizionato", "1192", "Non appartenenza al dominio di riferimento", parametri);
		
		// seconda regola
		parametri = new Parametri();
		parametri.getParametriMap().put("listaCampi", "rapporto_compilatore_telefonoCompilatore|rapporto_compilatore_faxCompilatore|rapporto_compilatore_emailCompilatore");
		
		RegolaObbligatorietaCondizionataAlmenoUnoCampi regolaObbligatorietaCondizionataAlmenoUnoCampi = new RegolaObbligatorietaCondizionataAlmenoUnoCampi(
				"regolaObbligatorietaCondizionataAlmenoUnoCampi", "1190", "Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato", parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaUguaglianzaCrossCampoCondizionato);
		regoleCampo.add(regolaObbligatorietaCondizionataAlmenoUnoCampi);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	
	private static Campo creaRegolaEventoLuogoEpisodioTelefono (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("listaCampi", "rapporto_luogoEpisodio_telefono|rapporto_luogoEpisodio_fax|rapporto_luogoEpisodio_email");
		
		RegolaObbligatorietaCondizionataAlmenoUnoCampi regolaObbligatorietaCondizionataAlmenoUnoCampi = new RegolaObbligatorietaCondizionataAlmenoUnoCampi(
				"regolaObbligatorietaCondizionataAlmenoUnoCampi", "1014", "Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato", parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaObbligatorietaCondizionataAlmenoUnoCampi);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoLuogoEpisodioFax (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("listaCampi", "rapporto_luogoEpisodio_telefono|rapporto_luogoEpisodio_fax|rapporto_luogoEpisodio_email");
		
		RegolaObbligatorietaCondizionataAlmenoUnoCampi regolaObbligatorietaCondizionataAlmenoUnoCampi = new RegolaObbligatorietaCondizionataAlmenoUnoCampi(
				"regolaObbligatorietaCondizionataAlmenoUnoCampi", "1017", "Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato", parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaObbligatorietaCondizionataAlmenoUnoCampi);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaEventoLuogoEpisodioEmail (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// regola
		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("listaCampi", "rapporto_luogoEpisodio_telefono|rapporto_luogoEpisodio_fax|rapporto_luogoEpisodio_email");
		
		RegolaObbligatorietaCondizionataAlmenoUnoCampi regolaObbligatorietaCondizionataAlmenoUnoCampi = new RegolaObbligatorietaCondizionataAlmenoUnoCampi(
				"regolaObbligatorietaCondizionataAlmenoUnoCampi", "1020", "Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato", parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaObbligatorietaCondizionataAlmenoUnoCampi);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	
	private static Campo creaRegolaLuogoCodAziOspLocale (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigLuogoAziendaOspLocale regolaDispovigLuogoAziendaOspLocale = new RegolaDispovigLuogoAziendaOspLocale(
				"regolaDispovigLuogoAziendaOspLocale", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigLuogoAziendaOspLocale);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaTipoDispositivo (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {

		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigTipoDispositivo regolaDispovigTipoDispositivo = new RegolaDispovigTipoDispositivo(
				"regolaDispovigTipoDispositivo", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigTipoDispositivo);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaCodiceDispositivo (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigCodiceDispositivo regolaDispovigCodiceDispositivo = new RegolaDispovigCodiceDispositivo(
				"regolaDispovigCodiceDispositivo", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigCodiceDispositivo);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaNomeDispositivo (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigNomeDispositivo regolaDispovigNomeDispositivo = new RegolaDispovigNomeDispositivo(
				"regolaDispovigNomeDispositivo", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigNomeDispositivo);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaDispositivoCND (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigClassCND regolaDispovigClassCND = new RegolaDispovigClassCND(
				"regolaDispovigClassCND", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigClassCND);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaTipoIVD (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigTipoIVD regolaDispovigTipoIVD = new RegolaDispovigTipoIVD(
				"regolaDispovigTipoIVD", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigTipoIVD);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaDenomFabbricante (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigDenomFabbr regolaDispovigDenomFabbr = new RegolaDispovigDenomFabbr(
				"regolaDispovigDenomFabbr", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigDenomFabbr);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaNazioneFabbricante (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigNazioneFabbr regolaDispovigNazioneFabbr = new RegolaDispovigNazioneFabbr(
				"regolaDispovigNazioneFabbr", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigNazioneFabbr);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaDescNazioneFabbricante (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigDescNazioneFabbr regolaDispovigDescNazioneFabbr = new RegolaDispovigDescNazioneFabbr(
				"regolaDispovigDescNazioneFabbr", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigDescNazioneFabbr);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaRegioneFabbricante (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigRegioneFabbr regolaDispovigRegioneFabbr = new RegolaDispovigRegioneFabbr(
				"regolaDispovigRegioneFabbr", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigRegioneFabbr);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaDescRegioneFabbricante (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigDescRegioneFabbr regolaDispovigDescRegioneFabbr = new RegolaDispovigDescRegioneFabbr(
				"regolaDispovigDescRegioneFabbr", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigDescRegioneFabbr);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaProvinciaFabbricante (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigProvinciaFabbr regolaDispovigProvinciaFabbr = new RegolaDispovigProvinciaFabbr(
				"regolaDispovigProvinciaFabbr", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigProvinciaFabbr);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaDescProvinciaFabbricante (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigDescProvinciaFabbr regolaDispovigDescProvinciaFabbr = new RegolaDispovigDescProvinciaFabbr(
				"regolaDispovigDescProvinciaFabbr", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigDescProvinciaFabbr);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaComuneFabbricante (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigComuneFabbr regolaDispovigComuneFabbr = new RegolaDispovigComuneFabbr(
				"regolaDispovigComuneFabbr", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigComuneFabbr);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaDescComuneFabbricante (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigDescComuneFabbr regolaDispovigDescComuneFabbr = new RegolaDispovigDescComuneFabbr(
				"regolaDispovigDescComuneFabbr", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigDescComuneFabbr);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaLocalitaFabbricante (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigLocalitaFabbr regolaDispovigLocalitaFabbr = new RegolaDispovigLocalitaFabbr(
				"regolaDispovigLocalitaFabbr", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigLocalitaFabbr);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaIndirizzoFabbricante (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigIndirizzoFabbr regolaDispovigIndirizzoFabbr = new RegolaDispovigIndirizzoFabbr(
				"regolaDispovigIndirizzoFabbr", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigIndirizzoFabbr);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	
	private static Campo creaRegolaDenominazioneMandatario (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigDenomMandatario regolaDispovigDenomMandatario = new RegolaDispovigDenomMandatario(
				"regolaDispovigDenomMandatario", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigDenomMandatario);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaNazioneMandatario (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigNazioneMandatario regolaDispovigNazioneMandatario = new RegolaDispovigNazioneMandatario(
				"regolaDispovigNazioneMandatario", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigNazioneMandatario);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	
	private static Campo creaRegolaDescNazioneMandatario (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigDescNazioneMandatario regolaDispovigDescNazioneMandatario = new RegolaDispovigDescNazioneMandatario(
				"regolaDispovigDescNazioneMandatario", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigDescNazioneMandatario);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	
	private static Campo creaRegolaRegioneMandatario (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigRegioneMandatario regolaDispovigRegioneMandatario = new RegolaDispovigRegioneMandatario(
				"regolaDispovigRegioneMandatario", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigRegioneMandatario);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	
	private static Campo creaRegolaDescRegioneMandatario (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigDescRegioneMandatario regolaDispovigDescRegioneMandatario = new RegolaDispovigDescRegioneMandatario(
				"regolaDispovigDescRegioneMandatario", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigDescRegioneMandatario);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
		
	private static Campo creaRegolaProvinciaMandatario (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigProvinciaMandatario regolaDispovigProvinciaMandatario = new RegolaDispovigProvinciaMandatario(
				"regolaDispovigProvinciaMandatario", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigProvinciaMandatario);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	
	private static Campo creaRegolaDescProvinciaMandatario (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigDescProvinciaMandatario regolaDispovigDescProvinciaMandatario = new RegolaDispovigDescProvinciaMandatario(
				"regolaDispovigDescProvinciaMandatario", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigDescProvinciaMandatario);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	
	private static Campo creaRegolaComuneMandatario (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigComuneMandatario regolaDispovigComuneMandatario = new RegolaDispovigComuneMandatario(
				"regolaDispovigComuneMandatario", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigComuneMandatario);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaDescComuneMandatario (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigDescComuneMandatario regolaDispovigDescComuneMandatario = new RegolaDispovigDescComuneMandatario(
				"regolaDispovigDescComuneMandatario", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigDescComuneMandatario);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
		
	private static Campo creaRegolaLocalitaMandatario (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigLocalitaMandatario regolaDispovigLocalitaMandatario = new RegolaDispovigLocalitaMandatario(
				"regolaDispovigLocalitaMandatario", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigLocalitaMandatario);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaIndirizzoMandatario (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigIndirizzoMandatario regolaDispovigIndirizzoMandatario = new RegolaDispovigIndirizzoMandatario(
				"regolaDispovigIndirizzoMandatario", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigIndirizzoMandatario);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaStruttSanitaria (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigStruttSan regolaDispovigStruttSan = new RegolaDispovigStruttSan(
				"regolaDispovigStruttSan", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigStruttSan);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaCodStruttSanitaria (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		// prima regola anagrafica
		Parametri parametri = new Parametri();
		RegolaDispovigCodStruttSan regolaDispovigCodStruttSan = new RegolaDispovigCodStruttSan(
				"regolaDispovigCodStruttSan", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigCodStruttSan);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaAzioniIntraprese (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
				
		Parametri parametri = new Parametri();
		RegolaDispovigAzioniIntraprese regolaDispovigAzioniIntraprese = new RegolaDispovigAzioniIntraprese(
				"regolaDispovigAzioniIntraprese", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigAzioniIntraprese);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaTextAzioniIntraprese (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		Parametri parametri = new Parametri();
		RegolaDispovigTextAzioniIntraprese regolaDispovigTextAzioniIntraprese = new RegolaDispovigTextAzioniIntraprese(
				"regolaDispovigAzioniIntraprese", null, null, parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigTextAzioniIntraprese);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaLuogoEpisodioCodStruttura (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		Parametri parametri = new Parametri();
		RegolaDispovigLuogoIncidente regolaDispovigLuogoIncidente = new RegolaDispovigLuogoIncidente(
				"regolaDispovigLuogoIncidente", "1007", "Il valore inserito e controllato non è presente in anagrafica", parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigLuogoIncidente);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	private static Campo creaRegolaLuogoEpisodioNomeStruttura (List<RegolaGenerica>  regoleCampo, String nomeCampo, String codErr) {
		
		Parametri parametri = new Parametri();
		RegolaDispovigLuogoIncidente regolaDispovigLuogoIncidente = new RegolaDispovigLuogoIncidente(
				"regolaDispovigLuogoIncidente", "1009", "Il valore inserito e controllato non è presente in anagrafica", parametri);
		
		// aggiungo regole
		regoleCampo.add(regolaDispovigLuogoIncidente);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
	
	}
	
	
	
}
