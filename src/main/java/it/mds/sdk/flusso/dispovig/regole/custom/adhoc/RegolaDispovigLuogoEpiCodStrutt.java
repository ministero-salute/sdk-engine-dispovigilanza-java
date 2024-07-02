/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.regole.custom.adhoc;

import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorValue;

import it.mds.sdk.anagrafiche.client.exceptions.MalformedRegistryException;
import it.mds.sdk.anagrafiche.client.exceptions.RegistryNotFoundException;
import it.mds.sdk.flusso.dispovig.parser.regole.Utilities;
import it.mds.sdk.gestoreesiti.modelli.Esito;
import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
import it.mds.sdk.libreriaregole.exception.ValidazioneImpossibileException;
import it.mds.sdk.libreriaregole.regole.beans.Parametri;
import it.mds.sdk.libreriaregole.regole.beans.RegolaGenerica;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@XmlDiscriminatorValue("RegolaDispovigLuogoAziendaOspLocale")
public class RegolaDispovigLuogoEpiCodStrutt extends RegolaGenerica {
	
	public RegolaDispovigLuogoEpiCodStrutt(String nome, String codErrore, String desErrore, Parametri parametri) {
		super(nome, codErrore, desErrore, parametri);
	}

	/**
	 *
	 * @param nomeCampo campo da validare
	 * @param recordDtoGenerico DTO del record del flusso
	 * @return lista di {@link it.mds.sdk.gestoreesiti.modelli.Esito}
	 */
	@Override
	public List<Esito> valida(String nomeCampo, RecordDtoGenerico recordDtoGenerico) {
		List<Esito> listaEsiti = new ArrayList<>();
		try {
			String rapporto_luogoEpisodio_codAziendaOspLocale = (recordDtoGenerico
					.getCampo("rapporto_luogoEpisodio_codAziendaOspLocale") != null
							? (String) recordDtoGenerico.getCampo("rapporto_luogoEpisodio_codAziendaOspLocale")
							: null);
			String rapporto_luogoEpisodio_aziendaOspLocale = (recordDtoGenerico
					.getCampo("rapporto_luogoEpisodio_aziendaOspLocale") != null
							? (String) recordDtoGenerico.getCampo("rapporto_luogoEpisodio_aziendaOspLocale")
							: null);
//			String rapporto_luogoEpisodio_annoValiditaStruttTerrit = (recordDtoGenerico
//					.getCampo("rapporto_luogoEpisodio_annoValiditaStruttTerrit") != null
//							? (String) recordDtoGenerico.getCampo("rapporto_luogoEpisodio_annoValiditaStruttTerrit")
//							: null);
			
			
			List<String> listaAnagStruttCompetenti = Utilities.getValoriAnagrafica("ANAG_DISPOVIG_STRUTT_COMPETENTI",rapporto_luogoEpisodio_aziendaOspLocale + "#" + rapporto_luogoEpisodio_codAziendaOspLocale);

			String codErr = "1026-1029";
			String desErr = "Non appartenenza al dominio di riferimento";

			if (listaAnagStruttCompetenti
					.contains(rapporto_luogoEpisodio_aziendaOspLocale + "#" + rapporto_luogoEpisodio_codAziendaOspLocale)) {
				
				listaEsiti.add(creaEsitoOk(nomeCampo));
			}else
				listaEsiti.add(creaEsitoKO(nomeCampo,codErr,desErr));
				
			
			
		
			
		} catch (InvocationTargetException | NoSuchMethodException |IllegalAccessException |RegistryNotFoundException|MalformedRegistryException  e) {
				log.error("Non è possibile validare la regola RegolaDispovigLuogoAziendaOspLocale " + nomeCampo, e);
			throw new ValidazioneImpossibileException("Non è possibile validare la regola RegolaDispovigLuogoAziendaOspLocale " + nomeCampo );
		}catch (DateTimeParseException dataE) {
			listaEsiti.add(creaEsitoKO(nomeCampo,this.getCodErrore(),"Formato anno non corretto"));
		}
		return listaEsiti;
	}

}
