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
@XmlDiscriminatorValue("RegolaDispovigDescRegioneMandatario")
public class RegolaDispovigDescRegioneMandatario extends RegolaGenerica {
	
	public RegolaDispovigDescRegioneMandatario(String nome, String codErrore, String desErrore, Parametri parametri) {
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
			
			String rapporto_dispositivo_nazioneFabbricante = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_nazioneFabbricante") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_nazioneFabbricante")
							: null);
			String rapporto_dispositivo_descNazioneFabbricante = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_descNazioneFabbricante") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_descNazioneFabbricante")
							: null);
			
			String rapporto_dispositivo_nazioneMandatario = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_nazioneMandatario") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_nazioneMandatario")
							: null);
			
			String rapporto_dispositivo_regioneMandatario = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_regioneMandatario") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_regioneMandatario")
							: null);
			String rapporto_dispositivo_descRegioneMandatario = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_descRegioneMandatario") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_descRegioneMandatario")
							: null);
			
			
			List<String> listaNaizoneNonUE = Utilities.getValoriAnagrafica("ANAG_DISPOVIG_NAZIONE_NON_EU",rapporto_dispositivo_descNazioneFabbricante+"#"+rapporto_dispositivo_nazioneFabbricante);
			
			String codErr = "1111";
			String desErr = "Mancata valorizzazione di un campo ad obbligatorietà condizionata";

			String codErr1 = "1113";
			String desErr1 = "Non appartenenza al dominio di riferimento";
			/*
			 * se fabbricante italiano allora deve essere valorizzato
			 */
			if ("IT".equalsIgnoreCase(rapporto_dispositivo_nazioneMandatario) &&
					listaNaizoneNonUE.contains(rapporto_dispositivo_descNazioneFabbricante+"#"+rapporto_dispositivo_nazioneFabbricante)) {
				if (StringUtils.isBlank(rapporto_dispositivo_descRegioneMandatario)) {	
					listaEsiti.add(creaEsitoKO(nomeCampo, codErr, desErr));
					return listaEsiti;
				}
				List<String> listaRegioniConDesc = Utilities.getValoriAnagrafica("ANAG_DISPOVIG_REGIONE",rapporto_dispositivo_regioneMandatario+"#"+rapporto_dispositivo_descRegioneMandatario);
				
				if (!listaRegioniConDesc.contains(rapporto_dispositivo_regioneMandatario+"#"+rapporto_dispositivo_descRegioneMandatario)) {
					listaEsiti.add(creaEsitoKO(nomeCampo, codErr1, desErr1));
					return listaEsiti;
				}
			}
			
			
			
			listaEsiti.add(creaEsitoOk(nomeCampo));
			
		} catch (InvocationTargetException | NoSuchMethodException |IllegalAccessException |RegistryNotFoundException|MalformedRegistryException  e) {
				log.error("Non è possibile validare la regola RegolaDispovigDescRegioneMandatario " + nomeCampo, e);
			throw new ValidazioneImpossibileException("Non è possibile validare la regola RegolaDispovigDescRegioneMandatario " + nomeCampo );
		}catch (DateTimeParseException dataE) {
			listaEsiti.add(creaEsitoKO(nomeCampo,this.getCodErrore(),"Formato anno non corretto"));
		}
		return listaEsiti;
	}

}
