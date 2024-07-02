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
@XmlDiscriminatorValue("RegolaDispovigClassCND")
public class RegolaDispovigClassCND extends RegolaGenerica {
	
	public RegolaDispovigClassCND(String nome, String codErrore, String desErrore, Parametri parametri) {
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
			
			String rapporto_dispositivo_CND = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_CND") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_CND")
							: null);
			String rapporto_dispositivo_desCND = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_desCND") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_desCND")
							: null);			
			String rapporto_dispositivo_codiceDispositivo = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_codiceDispositivo") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_codiceDispositivo")
							: null);

			List<String> listaDispositiviMedici = Utilities.getValoriAnagrafica("ANAG_DISPOVIG_DISP_MEDICI",rapporto_dispositivo_codiceDispositivo);
			List<String> listaClassificazioneCND = Utilities.getValoriAnagrafica("ANAG_DISPOVIG_CND_DISPO",rapporto_dispositivo_codiceDispositivo + "#" + rapporto_dispositivo_CND + "#" + rapporto_dispositivo_desCND);
			List<String> listaTipologiaCND = Utilities.getValoriAnagrafica("ANAG_DISPOVIG_CND_TIPOLOGIA",rapporto_dispositivo_CND + "#" + rapporto_dispositivo_desCND);
			
			String codErr = "1053";
			String desErr = "Non appartenenza al dominio di riferimento";

			if (!listaDispositiviMedici.contains(rapporto_dispositivo_codiceDispositivo)) {
				if (!listaTipologiaCND
						.contains(rapporto_dispositivo_CND + "#" + rapporto_dispositivo_desCND)) {
					listaEsiti.add(creaEsitoKO(nomeCampo, codErr, desErr));
					return listaEsiti;
				}
			}else {
				if (!listaClassificazioneCND
						.contains(rapporto_dispositivo_codiceDispositivo + "#" + rapporto_dispositivo_CND + "#" + rapporto_dispositivo_desCND)) {
					listaEsiti.add(creaEsitoKO(nomeCampo, codErr, desErr));
					return listaEsiti;
				}
			}
			
			listaEsiti.add(creaEsitoOk(nomeCampo));
			
		} catch (InvocationTargetException | NoSuchMethodException |IllegalAccessException |RegistryNotFoundException|MalformedRegistryException  e) {
				log.error("Non è possibile validare la regola RegolaDispovigClassCND " + nomeCampo, e);
			throw new ValidazioneImpossibileException("Non è possibile validare la regola RegolaDispovigClassCND " + nomeCampo );
		}catch (DateTimeParseException dataE) {
			listaEsiti.add(creaEsitoKO(nomeCampo,this.getCodErrore(),"Formato anno non corretto"));
		}
		return listaEsiti;
	}

}
