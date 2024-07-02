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
@XmlDiscriminatorValue("RegolaDispovigDenomFabbr")
public class RegolaDispovigDenomFabbr extends RegolaGenerica {
	
	public RegolaDispovigDenomFabbr(String nome, String codErrore, String desErrore, Parametri parametri) {
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
			
			String rapporto_dispositivo_codiceDispositivo = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_codiceDispositivo") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_codiceDispositivo")
							: null);
			
			String rapporto_dispositivo_denominazioneFabbricante = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_denominazioneFabbricante") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_denominazioneFabbricante")
							: null);
			
			String rapporto_dispositivo_nazioneFabbricante = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_nazioneFabbricante") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_nazioneFabbricante")
							: null);
			
			List<String> listaDispositiviMedici = Utilities.getValoriAnagrafica("ANAG_DISPOVIG_DISP_MEDICI",rapporto_dispositivo_codiceDispositivo);
			
			String codErr = "1073";
			String desErr = "Non appartenenza al dominio di riferimento";

			if (listaDispositiviMedici.contains(rapporto_dispositivo_codiceDispositivo)) {
				/*
				 * TODOs: codice dispositivo esiste - verificare che il fabbricante sia uguale a
				 * quello presente sul db
				 */
				List<String> listaDenomFabbricante = Utilities.getValoriAnagrafica("ANAG_DISPOVIG_FABBRICANTE",rapporto_dispositivo_codiceDispositivo + "#" + rapporto_dispositivo_denominazioneFabbricante + "#"
						+ rapporto_dispositivo_nazioneFabbricante);
				
				if (!listaDenomFabbricante.contains(
						rapporto_dispositivo_codiceDispositivo + "#" + rapporto_dispositivo_denominazioneFabbricante + "#"
								+ rapporto_dispositivo_nazioneFabbricante)) {
					listaEsiti.add(creaEsitoKO(nomeCampo, codErr, desErr));
					return listaEsiti;
				}
			}
			
			listaEsiti.add(creaEsitoOk(nomeCampo));
			
		} catch (InvocationTargetException | NoSuchMethodException |IllegalAccessException |RegistryNotFoundException|MalformedRegistryException  e) {
				log.error("Non è possibile validare la regola RegolaDispovigDenomFabbr " + nomeCampo, e);
			throw new ValidazioneImpossibileException("Non è possibile validare la regola RegolaDispovigDenomFabbr " + nomeCampo );
		}catch (DateTimeParseException dataE) {
			listaEsiti.add(creaEsitoKO(nomeCampo,this.getCodErrore(),"Formato anno non corretto"));
		}
		return listaEsiti;
	}


}
