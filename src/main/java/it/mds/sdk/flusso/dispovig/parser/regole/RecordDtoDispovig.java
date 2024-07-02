/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.parser.regole;

import com.opencsv.bean.CsvBindByPosition;

import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDtoDispovig extends RecordDtoGenerico {
	// rapporto_codRapportoWeb~rapporto_luogoEpisodio_numRapporto~rapporto_luogoEpisodio_rapportoRelativoA~rapporto_luogoEpisodio_codiceStruttura~rapporto_luogoEpisodio_nomeStruttura~rapporto_luogoEpisodio_annoValiditaStruttTerrit~rapporto_luogoEpisodio_repartoStruttura~rapporto_luogoEpisodio_telefono~rapporto_luogoEpisodio_fax~rapporto_luogoEpisodio_email~rapporto_luogoEpisodio_dataEpisodio~rapporto_luogoEpisodio_codAziendaOspLocale~rapporto_luogoEpisodio_aziendaOspLocale~rapporto_luogoEpisodio_nomeOperatore~rapporto_luogoEpisodio_cognomeOperatore~rapporto_luogoEpisodio_qualificaOperatore~rapporto_luogoEpisodio_nomeResponsabile~rapporto_luogoEpisodio_cognomeResponsabile~rapporto_dispositivo_tipoDispositivo~rapporto_dispositivo_codiceDispositivo~rapporto_dispositivo_nomeDispositivo~rapporto_dispositivo_modelloDispositivo~rapporto_dispositivo_codiceFabbrDispositivo~rapporto_dispositivo_numeroLotto~rapporto_dispositivo_dataScadenza~rapporto_dispositivo_CND~rapporto_dispositivo_desCND~rapporto_dispositivo_GMDN~rapporto_dispositivo_desGMDN~rapporto_dispositivo_EDMA~rapporto_dispositivo_desEDMA~rapporto_dispositivo_tipoIVD~rapporto_dispositivo_descTipoIVD~rapporto_dispositivo_valutazionePrestazioni~rapporto_dispositivo_utilizzo~rapporto_dispositivo_versioneSoftware~rapporto_dispositivo_denominazioneFabbricante~rapporto_dispositivo_nazioneFabbricante~rapporto_dispositivo_descNazioneFabbricante~rapporto_dispositivo_regioneFabbricante~rapporto_dispositivo_descRegioneFabbricante~rapporto_dispositivo_provinciaFabbricante~rapporto_dispositivo_descProvinciaFabbricante~rapporto_dispositivo_comuneFabbricante~rapporto_dispositivo_descComuneFabbricante~rapporto_dispositivo_localitaFabbricante~rapporto_dispositivo_indirizzoFabbricante~rapporto_dispositivo_denominazioneMandatario~rapporto_dispositivo_nazioneMandatario~rapporto_dispositivo_descNazioneMandatario~rapporto_dispositivo_regioneMandatario~rapporto_dispositivo_descRegioneMandatario~rapporto_dispositivo_provinciaMandatario~rapporto_dispositivo_descProvinciaMandatario~rapporto_dispositivo_comuneMandatario~rapporto_dispositivo_descComuneMandatario~rapporto_dispositivo_localitaMandatario~rapporto_dispositivo_indirizzoMandatario~rapporto_evento_offeso~rapporto_evento_etaPaziente~rapporto_evento_inizPaziente~rapporto_evento_dataImpianto~rapporto_evento_isDispUtilizzato~rapporto_evento_utilizzo~rapporto_evento_descUtilizzo~rapporto_evento_altroUtilizzoDispo~rapporto_evento_classeIncidente~rapporto_evento_descClasseIncidente~rapporto_evento_descrizioneIncidente~rapporto_evento_conseguenza~rapporto_evento_descConseguenza~rapporto_evento_altraConseguenza~rapporto_evento_numPezzi~rapporto_evento_disponibilitaDispositivo~rapporto_evento_luogoDisponibilitaDispositivo~rapporto_evento_azioniIntraprese~rapporto_evento_textAzioniIntraprese~rapporto_evento_altreAzioniIntraprese~rapporto_evento_altreInformazioni~rapporto_evento_dataRapporto~rapporto_compilatore_ruolo~rapporto_compilatore_descRuolo~rapporto_compilatore_nome~rapporto_compilatore_cognome~rapporto_compilatore_qualificaCompilatore~rapporto_compilatore_struttSanitaria~rapporto_compilatore_codStruttSanitaria~rapporto_compilatore_telefonoCompilatore~rapporto_compilatore_faxCompilatore~rapporto_compilatore_emailCompilatore

	
	@CsvBindByPosition(position = 0)
	private String rapporto_codRapportoWeb;
	@CsvBindByPosition(position = 1)
	private String rapporto_luogoEpisodio_numRapporto;
	@CsvBindByPosition(position = 2)
	private String rapporto_luogoEpisodio_rapportoRelativoA;
	@CsvBindByPosition(position = 3)
	private String rapporto_luogoEpisodio_codiceStruttura;
	@CsvBindByPosition(position = 4)
	private String rapporto_luogoEpisodio_nomeStruttura;
	@CsvBindByPosition(position = 5)
	private String rapporto_luogoEpisodio_annoValiditaStruttTerrit;
	@CsvBindByPosition(position = 6)
	private String rapporto_luogoEpisodio_repartoStruttura;
	@CsvBindByPosition(position = 7)
	private String rapporto_luogoEpisodio_telefono;
	@CsvBindByPosition(position = 8)
	private String rapporto_luogoEpisodio_fax;
	@CsvBindByPosition(position = 9)
	private String rapporto_luogoEpisodio_email;
	@CsvBindByPosition(position = 10)
	private String rapporto_luogoEpisodio_dataEpisodio;
	@CsvBindByPosition(position = 11)
	private String rapporto_luogoEpisodio_codAziendaOspLocale;
	@CsvBindByPosition(position = 12)
	private String rapporto_luogoEpisodio_aziendaOspLocale;
	@CsvBindByPosition(position = 13)
	private String rapporto_luogoEpisodio_nomeOperatore;
	@CsvBindByPosition(position = 14)
	private String rapporto_luogoEpisodio_cognomeOperatore;
	@CsvBindByPosition(position = 15)
	private String rapporto_luogoEpisodio_qualificaOperatore;
	@CsvBindByPosition(position = 16)
	private String rapporto_luogoEpisodio_nomeResponsabile;
	@CsvBindByPosition(position = 17)
	private String rapporto_luogoEpisodio_cognomeResponsabile;
	@CsvBindByPosition(position = 18)
	private String rapporto_dispositivo_tipoDispositivo;
	@CsvBindByPosition(position = 19)
	private String rapporto_dispositivo_codiceDispositivo;
	@CsvBindByPosition(position = 20)
	private String rapporto_dispositivo_nomeDispositivo;
	@CsvBindByPosition(position = 21)
	private String rapporto_dispositivo_modelloDispositivo;
	@CsvBindByPosition(position = 22)
	private String rapporto_dispositivo_codiceFabbrDispositivo;
	@CsvBindByPosition(position = 23)
	private String rapporto_dispositivo_numeroLotto;
	@CsvBindByPosition(position = 24)
	private String rapporto_dispositivo_dataScadenza;
	@CsvBindByPosition(position = 25)
	private String rapporto_dispositivo_CND;
	@CsvBindByPosition(position = 26)
	private String rapporto_dispositivo_desCND;
	@CsvBindByPosition(position = 27)
	private String rapporto_dispositivo_GMDN;
	@CsvBindByPosition(position = 28)
	private String rapporto_dispositivo_desGMDN;
	@CsvBindByPosition(position = 29)
	private String rapporto_dispositivo_EDMA;
	@CsvBindByPosition(position = 30)
	private String rapporto_dispositivo_desEDMA;
	@CsvBindByPosition(position = 31)
	private String rapporto_dispositivo_tipoIVD;
	@CsvBindByPosition(position = 32)
	private String rapporto_dispositivo_descTipoIVD;
	@CsvBindByPosition(position = 33)
	private String rapporto_dispositivo_valutazionePrestazioni;
	@CsvBindByPosition(position = 34)
	private String rapporto_dispositivo_utilizzo;
	@CsvBindByPosition(position = 35)
	private String rapporto_dispositivo_versioneSoftware;
	@CsvBindByPosition(position = 36)
	private String rapporto_dispositivo_denominazioneFabbricante;
	@CsvBindByPosition(position = 37)
	private String rapporto_dispositivo_nazioneFabbricante;
	@CsvBindByPosition(position = 38)
	private String rapporto_dispositivo_descNazioneFabbricante;
	@CsvBindByPosition(position = 39)
	private String rapporto_dispositivo_regioneFabbricante;
	@CsvBindByPosition(position = 40)
	private String rapporto_dispositivo_descRegioneFabbricante;
	@CsvBindByPosition(position = 41)
	private String rapporto_dispositivo_provinciaFabbricante;
	@CsvBindByPosition(position = 42)
	private String rapporto_dispositivo_descProvinciaFabbricante;
	@CsvBindByPosition(position = 43)
	private String rapporto_dispositivo_comuneFabbricante;
	@CsvBindByPosition(position = 44)
	private String rapporto_dispositivo_descComuneFabbricante;
	@CsvBindByPosition(position = 45)
	private String rapporto_dispositivo_localitaFabbricante;
	@CsvBindByPosition(position = 46)
	private String rapporto_dispositivo_indirizzoFabbricante;
	@CsvBindByPosition(position = 47)
	private String rapporto_dispositivo_denominazioneMandatario;
	@CsvBindByPosition(position = 48)
	private String rapporto_dispositivo_nazioneMandatario;
	@CsvBindByPosition(position = 49)
	private String rapporto_dispositivo_descNazioneMandatario;
	@CsvBindByPosition(position = 50)
	private String rapporto_dispositivo_regioneMandatario;
	@CsvBindByPosition(position = 51)
	private String rapporto_dispositivo_descRegioneMandatario;
	@CsvBindByPosition(position = 52)
	private String rapporto_dispositivo_provinciaMandatario;
	@CsvBindByPosition(position = 53)
	private String rapporto_dispositivo_descProvinciaMandatario;
	@CsvBindByPosition(position = 54)
	private String rapporto_dispositivo_comuneMandatario;
	@CsvBindByPosition(position = 55)
	private String rapporto_dispositivo_descComuneMandatario;
	@CsvBindByPosition(position = 56)
	private String rapporto_dispositivo_localitaMandatario;
	@CsvBindByPosition(position = 57)
	private String rapporto_dispositivo_indirizzoMandatario;
	@CsvBindByPosition(position = 58)
	private String rapporto_evento_offeso;
	@CsvBindByPosition(position = 59)
	private String rapporto_evento_etaPaziente;
	@CsvBindByPosition(position = 60)
	private String rapporto_evento_inizPaziente;
	@CsvBindByPosition(position = 61)
	private String rapporto_evento_dataImpianto;
	@CsvBindByPosition(position = 62)
	private String rapporto_evento_isDispUtilizzato;
	@CsvBindByPosition(position = 63)
	private String rapporto_evento_utilizzo;
	@CsvBindByPosition(position = 64)
	private String rapporto_evento_descUtilizzo;
	@CsvBindByPosition(position = 65)
	private String rapporto_evento_altroUtilizzoDispo;
	@CsvBindByPosition(position = 66)
	private String rapporto_evento_classeIncidente;
	@CsvBindByPosition(position = 67)
	private String rapporto_evento_descClasseIncidente;
	@CsvBindByPosition(position = 68)
	private String rapporto_evento_descrizioneIncidente;
	@CsvBindByPosition(position = 69)
	private String rapporto_evento_conseguenza;
	@CsvBindByPosition(position = 70)
	private String rapporto_evento_descConseguenza;
	@CsvBindByPosition(position = 71)
	private String rapporto_evento_altraConseguenza;
	@CsvBindByPosition(position = 72)
	private String rapporto_evento_numPezzi;
	@CsvBindByPosition(position = 73)
	private String rapporto_evento_disponibilitaDispositivo;
	@CsvBindByPosition(position = 74)
	private String rapporto_evento_luogoDisponibilitaDispositivo;
	@CsvBindByPosition(position = 75)
	private String rapporto_evento_azioniIntraprese;
	@CsvBindByPosition(position = 76)
	private String rapporto_evento_textAzioniIntraprese;
	@CsvBindByPosition(position = 77)
	private String rapporto_evento_altreAzioniIntraprese;
	@CsvBindByPosition(position = 78)
	private String rapporto_evento_altreInformazioni;
	@CsvBindByPosition(position = 79)
	private String rapporto_evento_dataRapporto;
	@CsvBindByPosition(position = 80)
	private String rapporto_compilatore_ruolo;
	@CsvBindByPosition(position = 81)
	private String rapporto_compilatore_descRuolo;
	@CsvBindByPosition(position = 82)
	private String rapporto_compilatore_nome;
	@CsvBindByPosition(position = 83)
	private String rapporto_compilatore_cognome;
	@CsvBindByPosition(position = 84)
	private String rapporto_compilatore_qualificaCompilatore;
	@CsvBindByPosition(position = 85)
	private String rapporto_compilatore_struttSanitaria;
	@CsvBindByPosition(position = 86)
	private String rapporto_compilatore_codStruttSanitaria;
	@CsvBindByPosition(position = 87)
	private String rapporto_compilatore_telefonoCompilatore;
	@CsvBindByPosition(position = 88)
	private String rapporto_compilatore_faxCompilatore;
	@CsvBindByPosition(position = 89)
	private String rapporto_compilatore_emailCompilatore;


}
