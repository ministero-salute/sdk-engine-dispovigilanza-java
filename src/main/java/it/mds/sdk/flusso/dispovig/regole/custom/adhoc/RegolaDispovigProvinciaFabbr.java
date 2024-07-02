/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.regole.custom.adhoc;

import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
@XmlDiscriminatorValue("RegolaDispovigProvinciaFabbr")
public class RegolaDispovigProvinciaFabbr extends RegolaGenerica {
	
	public RegolaDispovigProvinciaFabbr(String nome, String codErrore, String desErrore, Parametri parametri) {
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
			
			String rapporto_dispositivo_nazioneFabbricante = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_nazioneFabbricante") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_nazioneFabbricante")
							: null);
			
			String rapporto_dispositivo_provinciaFabbricante = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_provinciaFabbricante") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_provinciaFabbricante")
							: null);
			String rapporto_dispositivo_descProvinciaFabbricante = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_descProvinciaFabbricante") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_descProvinciaFabbricante")
							: null);
			String rapporto_dispositivo_regioneFabbricante = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_regioneFabbricante") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_regioneFabbricante")
							: null);
			
			String codErr = "1084";
			String desErr = "Mancata valorizzazione di un campo ad obbligatorietà condizionata";

			String codErr1 = "1085";
			String desErr1 = "Non appartenenza al dominio di riferimento";
			
			String codErr3 = "10841";
			String desErr3 = "Codice Provincia non congruente con la Nazione del Fabbricante";
			/*
			 * se fabbricante italiano allora deve essere valorizzato
			 */
			if ("IT".equalsIgnoreCase(rapporto_dispositivo_nazioneFabbricante)) {
				if (StringUtils.isBlank(rapporto_dispositivo_provinciaFabbricante)) {	
					listaEsiti.add(creaEsitoKO(nomeCampo, codErr, desErr));
					return listaEsiti;
				}
				List<String> listaDispositiviMedici = Utilities.getValoriAnagrafica("ANAG_DISPOVIG_DISP_MEDICI",rapporto_dispositivo_codiceDispositivo);
				
				if (!listaDispositiviMedici.contains(rapporto_dispositivo_codiceDispositivo)) {
					List<String> listaProvinceConDescEReg = Utilities.getValoriAnagrafica("ANAG_DISPOVIG_PROVINCIA",rapporto_dispositivo_provinciaFabbricante + "#" + rapporto_dispositivo_descProvinciaFabbricante
							+ "#" + rapporto_dispositivo_regioneFabbricante);
					
					if (!listaProvinceConDescEReg.contains(
							rapporto_dispositivo_provinciaFabbricante + "#" + rapporto_dispositivo_descProvinciaFabbricante
									+ "#" + rapporto_dispositivo_regioneFabbricante)) {
						listaEsiti.add(creaEsitoKO(nomeCampo, codErr1, desErr1));
						return listaEsiti;
					}
				} else {
					/*
					 * TODO: codice dispositivo esiste - verificare che la provincia del fabbricante
					 * sia uguale a quello presente sul db rapporto_dispositivo_regioneFabbricante -
					 * rapporto_dispositivo_descRegioneFabbricante
					 */
				}
			}else {
				if (!StringUtils.isBlank(rapporto_dispositivo_provinciaFabbricante)) {	
					listaEsiti.add(creaEsitoKO(nomeCampo, codErr3, desErr3));
					return listaEsiti;
				}
			}
			
			
			
			listaEsiti.add(creaEsitoOk(nomeCampo));
			
		} catch (InvocationTargetException | NoSuchMethodException |IllegalAccessException |RegistryNotFoundException|MalformedRegistryException  e) {
				log.error("Non è possibile validare la regola RegolaDispovigProvinciaFabbr " + nomeCampo, e);
			throw new ValidazioneImpossibileException("Non è possibile validare la regola RegolaDispovigProvinciaFabbr " + nomeCampo );
		}catch (DateTimeParseException dataE) {
			listaEsiti.add(creaEsitoKO(nomeCampo,this.getCodErrore(),"Formato anno non corretto"));
		}
		return listaEsiti;
	}	

}
