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
@XmlDiscriminatorValue("RegolaDispovigTipoDispositivo")
public class RegolaDispovigTipoDispositivo extends RegolaGenerica {
	
	public RegolaDispovigTipoDispositivo(String nome, String codErrore, String desErrore, Parametri parametri) {
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
			
			String rapporto_dispositivo_codiceDispositivo = (recordDtoGenerico
					.getCampo("rapporto_dispositivo_codiceDispositivo") != null
							? (String) recordDtoGenerico.getCampo("rapporto_dispositivo_codiceDispositivo")
							: null);
			
			
			/*
			 * Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore presente sull'anagrafica 
			 * dispositivi fornita dal MdS; se non è valorizzato i valori Ammessi sono:
			 * 				MDD = Di Classe
			 * 				IDV = In vitro
			 */
			
			
			String codErr = "1041";
			String desErr = "Non appartenenza al dominio di riferimento";

			if (!StringUtils.isBlank(rapporto_dispositivo_codiceDispositivo)) {
				List<String> listaDispositiviMedici = Utilities.getValoriAnagrafica("ANAG_DISPOVIG_DISP_MEDICI",rapporto_dispositivo_codiceDispositivo);

				if (listaDispositiviMedici.contains(rapporto_dispositivo_codiceDispositivo)) {
					// TODO: FB - qui dovrei verificare se il tipo rapporto è uguale a quello del codice dispositivo presente sul db
					// per ora metto lo stesso controllo MDD e IVD - si deve trovare il campo sul DB
					if (!"MDD".equalsIgnoreCase(valoreCampo) && !"IVD".equalsIgnoreCase(valoreCampo)) {
						listaEsiti.add(creaEsitoKO(nomeCampo,codErr,desErr));
						return listaEsiti;
					}
				}else {
					if (!"MDD".equalsIgnoreCase(valoreCampo) && !"IVD".equalsIgnoreCase(valoreCampo)) {
						listaEsiti.add(creaEsitoKO(nomeCampo,codErr,desErr));
						return listaEsiti;
					}					
				}
			}
			
			listaEsiti.add(creaEsitoOk(nomeCampo));
			
			
			
			
		
			
		} catch (InvocationTargetException | NoSuchMethodException |IllegalAccessException |RegistryNotFoundException|MalformedRegistryException  e) {
				log.error("Non è possibile validare la regola RegolaDispovigTipoDispositivo " + nomeCampo, e);
			throw new ValidazioneImpossibileException("Non è possibile validare la regola RegolaDispovigTipoDispositivo " + nomeCampo );
		}catch (DateTimeParseException dataE) {
			listaEsiti.add(creaEsitoKO(nomeCampo,this.getCodErrore(),"Formato anno non corretto"));
		}
		return listaEsiti;
	}
}
