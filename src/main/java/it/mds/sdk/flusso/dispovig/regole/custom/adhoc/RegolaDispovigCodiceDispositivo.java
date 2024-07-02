/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.regole.custom.adhoc;

import java.lang.reflect.InvocationTargetException;
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
@XmlDiscriminatorValue("RegolaDispovigCodiceDispositivo")
public class RegolaDispovigCodiceDispositivo extends RegolaGenerica {
	
	public RegolaDispovigCodiceDispositivo(String nome, String codErrore, String desErrore, Parametri parametri) {
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
			
			String valoreCampo = (recordDtoGenerico.getCampo(nomeCampo) != null ? (String)recordDtoGenerico.getCampo(nomeCampo) : null);
			
			String rapporto_dispositivo_tipoDispositivo = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_tipoDispositivo") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_tipoDispositivo")
							: "");
			String rapporto_dispositivo_codiceDispositivo = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_codiceDispositivo") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_codiceDispositivo")
							: "");
			String rapporto_dispositivo_nomeDispositivo = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_nomeDispositivo") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_nomeDispositivo")
							: "");
			String rapporto_dispositivo_codiceFabbrDispositivo = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_codiceFabbrDispositivo") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_codiceFabbrDispositivo")
							: "");
			String rapporto_dispositivo_CND = (recordDtoGenerico.getCampo("rapporto_dispositivo_CND") != null
					? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_CND")
					: "");
			String rapporto_dispositivo_desCND = (recordDtoGenerico.getCampo("rapporto_dispositivo_desCND") != null
					? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_desCND")
					: "");
			String rapporto_dispositivo_tipoIVD = (recordDtoGenerico.getCampo("rapporto_dispositivo_tipoIVD") != null
					? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_tipoIVD")
					: "");
			String rapporto_dispositivo_descTipoIVD = (recordDtoGenerico.getCampo("rapporto_dispositivo_descTipoIVD") != null
					? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_descTipoIVD")
					: "");
			String rapporto_dispositivo_denominazioneFabbricante = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_denominazioneFabbricante") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_denominazioneFabbricante")
							: "");
			String rapporto_dispositivo_nazioneFabbricante = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_nazioneFabbricante") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_nazioneFabbricante")
							: "");
			String rapporto_dispositivo_denominazioneMandatario = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_denominazioneMandatario") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_denominazioneMandatario")
							: "");
			String rapporto_dispositivo_nazioneMandatario = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_nazioneMandatario") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_nazioneMandatario")
							: "");
		
			
			List<String> listaDispositiviMedici = Utilities.getValoriAnagrafica("ANAG_DISPOVIG_DISP_MEDICI",valoreCampo);
			List<String> listaDispositiviMediciGlobal = Utilities.getValoriAnagrafica("ANAG_DISPOVIG_DISP_MEDICI_GLOBAL",rapporto_dispositivo_tipoDispositivo + "#" +
					rapporto_dispositivo_codiceDispositivo + "#" +
					rapporto_dispositivo_nomeDispositivo + "#" +
					rapporto_dispositivo_codiceFabbrDispositivo + "#" +
					rapporto_dispositivo_CND + "#" +
					rapporto_dispositivo_desCND + "#" +
					rapporto_dispositivo_tipoIVD + " - " + rapporto_dispositivo_descTipoIVD + "#" +
					rapporto_dispositivo_denominazioneFabbricante + "#" +
					rapporto_dispositivo_nazioneFabbricante + "#" +
					rapporto_dispositivo_denominazioneMandatario + "#" +
					rapporto_dispositivo_nazioneMandatario + "#");
			
			String codErr = "1042";
			String desErr = "Non appartenenza al dominio di riferimento";

			String codErr2 = "10421";
			String desErr2 = "Se il codiceDispositivo è presente in base dati allora uno dei seguenti campi non è valorizzato in modo coerente: "
					+ "rapporto_dispositivo_tipoDispositivo - "
					+ "rapporto_dispositivo_codiceDispositivo - "
					+ "rapporto_dispositivo_nomeDispositivo - "
					+ "rapporto_dispositivo_codiceFabbrDispositivo - "
					+ "rapporto_dispositivo_CND - "
					+ "rapporto_dispositivo_desCND - "
					+ "rapporto_dispositivo_tipoIVD - "
					+ "rapporto_dispositivo_denominazioneFabbricante - "
					+ "rapporto_dispositivo_nazioneFabbricante - "
					+ "rapporto_dispositivo_denominazioneMandatario - "
					+ "rapporto_dispositivo_nazioneMandatario";
			
				if (!listaDispositiviMedici.contains(valoreCampo)) {					
					listaEsiti.add(creaEsitoKO(nomeCampo,codErr,desErr));	
					return listaEsiti;
				}else {
					// TODO: CONTROLLO GLOBALE SCRITTO - VERIFICARE
					if (!listaDispositiviMediciGlobal.contains(rapporto_dispositivo_tipoDispositivo + "#" +
							rapporto_dispositivo_codiceDispositivo + "#" +
							rapporto_dispositivo_nomeDispositivo + "#" +
							rapporto_dispositivo_codiceFabbrDispositivo + "#" +
							rapporto_dispositivo_CND + "#" +
							rapporto_dispositivo_desCND + "#" +
							rapporto_dispositivo_tipoIVD + " - " + rapporto_dispositivo_descTipoIVD + "#" +
							rapporto_dispositivo_denominazioneFabbricante + "#" +
							rapporto_dispositivo_nazioneFabbricante + "#" +
							rapporto_dispositivo_denominazioneMandatario + "#" +
							rapporto_dispositivo_nazioneMandatario + "#")) {					
						listaEsiti.add(creaEsitoKO(nomeCampo,codErr2,desErr2));	
						return listaEsiti;
					}					
				}
			
			listaEsiti.add(creaEsitoOk(nomeCampo));
			
		} catch (InvocationTargetException | NoSuchMethodException |IllegalAccessException |RegistryNotFoundException|MalformedRegistryException  e) {
				log.error("Non è possibile validare la regola RegolaDispovigCodiceDispositivo " + nomeCampo, e);
			throw new ValidazioneImpossibileException("Non è possibile validare la regola RegolaDispovigCodiceDispositivo " + nomeCampo );
		}
		return listaEsiti;
	}

}
