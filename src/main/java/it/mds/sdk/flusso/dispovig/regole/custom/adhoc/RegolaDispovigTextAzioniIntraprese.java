/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.regole.custom.adhoc;

import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorValue;

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
@XmlDiscriminatorValue("RegolaDispovigTextAzioniIntraprese")
public class RegolaDispovigTextAzioniIntraprese extends RegolaGenerica {
	
	public RegolaDispovigTextAzioniIntraprese(String nome, String codErrore, String desErrore, Parametri parametri) {
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
			
			String rapporto_evento_textAzioniIntraprese = (recordDtoGenerico
					.getCampo("rapporto_evento_textAzioniIntraprese") != null
							? (String) recordDtoGenerico.getCampo("rapporto_evento_textAzioniIntraprese")
							: null);

			
			String codErr = "11611";
			String desErr = "Non appartenenza al dominio di riferimento";
			ArrayList<String> arrValAmmessi = new ArrayList<String>();
			arrValAmmessi.add("Informativa al fabbricante/distributore");arrValAmmessi.add("Informazione alla Direzione sanitaria/Direzione generale");
			arrValAmmessi.add("Comunicazione al responsabile della vigilanza");arrValAmmessi.add("Altro");
			
			/*
			 * valori ammessi: IFD;IDSDG;CRV;Altro
			 */
			if (!StringUtils.isBlank(rapporto_evento_textAzioniIntraprese)) {
				StringTokenizer st = new StringTokenizer(rapporto_evento_textAzioniIntraprese, ",");
				ArrayList<String> valoreCampo = new ArrayList<String>();
				Iterator it = st.asIterator();
				boolean ret = true;
				while (it.hasNext()) {
					String val = (String) it.next();
					if (!arrValAmmessi.contains(val.trim())) {
						listaEsiti.add(creaEsitoKO(nomeCampo, codErr, desErr));	
						return listaEsiti;
					}
				}
			}
				
			if (Utilities.verificaEsitoOK(listaEsiti)) {
				listaEsiti.clear();
				listaEsiti.add(creaEsitoOk(nomeCampo));
			}
					
		} catch (InvocationTargetException | NoSuchMethodException |IllegalAccessException  e) {
				log.error("Non è possibile validare la regola RegolaDispovigTextAzioniIntraprese " + nomeCampo, e);
			throw new ValidazioneImpossibileException("Non è possibile validare la regola RegolaDispovigTextAzioniIntraprese " + nomeCampo );
		}catch (DateTimeParseException dataE) {
			listaEsiti.add(creaEsitoKO(nomeCampo,this.getCodErrore(),"Formato anno non corretto"));
		}
		return listaEsiti;
	}


	

}
