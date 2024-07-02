/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.regole.custom.adhoc;

import java.lang.reflect.InvocationTargetException;
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
@XmlDiscriminatorValue("RegolaDispovigCodStruttSan")
public class RegolaDispovigCodStruttSan extends RegolaGenerica {
	
	public RegolaDispovigCodStruttSan(String nome, String codErrore, String desErrore, Parametri parametri) {
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
			
			String rapportoCompilatoreRuolo = (recordDtoGenerico
					.getCampo("rapporto_compilatore_ruolo") != null
							? (String) recordDtoGenerico.getCampo("rapporto_compilatore_ruolo")
							: null);
			
			String rapportoCompilatoreCodStruttSanitaria = (recordDtoGenerico
					.getCampo("rapporto_compilatore_codStruttSanitaria") != null
							? (String) recordDtoGenerico.getCampo("rapporto_compilatore_codStruttSanitaria")
							: null);
			
			String rapportoLuogoEpisodioCodiceStruttura = (recordDtoGenerico
					.getCampo("rapporto_luogoEpisodio_codiceStruttura") != null
							? (String) recordDtoGenerico.getCampo("rapporto_luogoEpisodio_codiceStruttura")
							: null);
			
			String rapportoLuogoEpisodioCodAziendaOspLocale = (recordDtoGenerico
					.getCampo("rapporto_luogoEpisodio_codAziendaOspLocale") != null
							? (String) recordDtoGenerico.getCampo("rapporto_luogoEpisodio_codAziendaOspLocale")
							: null);
			

			String codErr = "1181";
			String desErr = "Ruolo 1 il campo rapporto_compilatore_codStruttSanitaria deve essere uguale a rapporto_luogoEpisodio_codiceStruttura";
			
			String codErr1 = "1181";
			String desErr1 = "Ruolo 2 o 3 il campo rapporto_compilatore_codStruttSanitaria deve essere uguale a rapporto_luogoEpisodio_codAziendaOspLocale";
			/*
			 * se fabbricante italiano allora deve essere valorizzato
			 */
			if ("1".equalsIgnoreCase(rapportoCompilatoreRuolo)) {
				if (rapportoCompilatoreCodStruttSanitaria != null && !rapportoCompilatoreCodStruttSanitaria.equalsIgnoreCase(rapportoLuogoEpisodioCodiceStruttura)) {	
					listaEsiti.add(creaEsitoKO(nomeCampo, codErr, desErr));
					return listaEsiti;
				}							
			}else if("2".equalsIgnoreCase(rapportoCompilatoreRuolo) || "3".equalsIgnoreCase(rapportoCompilatoreRuolo)) {
				if (rapportoCompilatoreCodStruttSanitaria != null && !rapportoCompilatoreCodStruttSanitaria.equalsIgnoreCase(rapportoLuogoEpisodioCodAziendaOspLocale)) {	
					listaEsiti.add(creaEsitoKO(nomeCampo, codErr1, desErr1));
					return listaEsiti;
				}
			}
			
			
			
			listaEsiti.add(creaEsitoOk(nomeCampo));
			
		} catch (InvocationTargetException | NoSuchMethodException |IllegalAccessException  e) {
				log.error("Non è possibile validare la regola RegolaDispovigCodStruttSan " + nomeCampo, e);
			throw new ValidazioneImpossibileException("Non è possibile validare la regola RegolaDispovigCodStruttSan " + nomeCampo );
		}catch (DateTimeParseException dataE) {
			listaEsiti.add(creaEsitoKO(nomeCampo,this.getCodErrore(),"Formato anno non corretto"));
		}
		return listaEsiti;
	}

}
