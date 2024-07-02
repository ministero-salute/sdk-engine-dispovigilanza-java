/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.regole.custom.adhoc;

import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
@XmlDiscriminatorValue("RegolaDispovigStruttSan")
public class RegolaDispovigStruttSan extends RegolaGenerica {
	
	public RegolaDispovigStruttSan(String nome, String codErrore, String desErrore, Parametri parametri) {
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
			
			String rapporto_compilatore_ruolo = (recordDtoGenerico
					.getCampo("rapporto_compilatore_ruolo") != null
							? (String) recordDtoGenerico.getCampo("rapporto_compilatore_ruolo")
							: null);
			
			String rapporto_compilatore_struttSanitaria = (recordDtoGenerico
					.getCampo("rapporto_compilatore_struttSanitaria") != null
							? (String) recordDtoGenerico.getCampo("rapporto_compilatore_struttSanitaria")
							: null);
			
			String rapporto_luogoEpisodio_nomeStruttura = (recordDtoGenerico
					.getCampo("rapporto_luogoEpisodio_nomeStruttura") != null
							? (String) recordDtoGenerico.getCampo("rapporto_luogoEpisodio_nomeStruttura")
							: null);
			
			String rapporto_luogoEpisodio_aziendaOspLocale = (recordDtoGenerico
					.getCampo("rapporto_luogoEpisodio_aziendaOspLocale") != null
							? (String) recordDtoGenerico.getCampo("rapporto_luogoEpisodio_aziendaOspLocale")
							: null);
			
			String codErr = "1179";
			String desErr = "Ruolo 1 il campo rapporto_compilatore_struttSanitaria deve essere uguale a rapporto_luogoEpisodio_nomeStruttura";
			
			String codErr1 = "1179";
			String desErr1 = "Ruolo 2 o 3 il campo rapporto_compilatore_struttSanitaria deve essere uguale a rapporto_luogoEpisodio_aziendaOspLocale";
			/*
			 * se fabbricante italiano allora deve essere valorizzato
			 */
			if ("1".equalsIgnoreCase(rapporto_compilatore_ruolo)) {
				if (!StringUtils.isBlank(rapporto_compilatore_struttSanitaria) && !rapporto_compilatore_struttSanitaria.equalsIgnoreCase(rapporto_luogoEpisodio_nomeStruttura)) {	
					listaEsiti.add(creaEsitoKO(nomeCampo, codErr, desErr));
					return listaEsiti;
				}							
			}else if("2".equalsIgnoreCase(rapporto_compilatore_ruolo) || "3".equalsIgnoreCase(rapporto_compilatore_ruolo)) {
				if (!StringUtils.isBlank(rapporto_compilatore_struttSanitaria) && !rapporto_compilatore_struttSanitaria.equalsIgnoreCase(rapporto_luogoEpisodio_aziendaOspLocale)) {	
					listaEsiti.add(creaEsitoKO(nomeCampo, codErr1, desErr1));
					return listaEsiti;
				}
			}
			
			listaEsiti.add(creaEsitoOk(nomeCampo));
			
		} catch (InvocationTargetException | NoSuchMethodException |IllegalAccessException  e) {
				log.error("Non è possibile validare la regola RegolaDispovigStruttSan " + nomeCampo, e);
			throw new ValidazioneImpossibileException("Non è possibile validare la regola RegolaDispovigStruttSan " + nomeCampo );
		}catch (DateTimeParseException dataE) {
			listaEsiti.add(creaEsitoKO(nomeCampo,this.getCodErrore(),"Formato anno non corretto"));
		}
		return listaEsiti;
	}

}
