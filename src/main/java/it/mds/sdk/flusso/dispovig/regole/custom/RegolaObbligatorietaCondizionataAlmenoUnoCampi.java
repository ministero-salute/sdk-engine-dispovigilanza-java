/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.regole.custom;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
@XmlDiscriminatorValue("regolaObbligatorietaCondizionataAlmenoUnoCampi")
public class RegolaObbligatorietaCondizionataAlmenoUnoCampi extends RegolaGenerica {

    public RegolaObbligatorietaCondizionataAlmenoUnoCampi(String nome, String codErrore, String desErrore, Parametri parametri) {
        super(nome, codErrore, desErrore, parametri);
    }

    /**
     * Verifica che almeno un campo su n. sia valorizzato 
     *
     * @param nomeCampo         campo da validare
     * @param recordDtoGenerico DTO del record del flusso
     * @return lista di {@link it.mds.sdk.gestoreesiti.modelli.Esito}
     */

    @Override
    public List<Esito> valida(String nomeCampo, RecordDtoGenerico recordDtoGenerico) {
        List<Esito> listaEsiti = new ArrayList<>();
        int numeroCampiVuoti = 0;
        try {

            List<String> listaCampi = Arrays.stream(this.getParametri().getParametriMap().
                    get("listaCampi").split("\\|")).collect(Collectors.toList());

            for (String campo : listaCampi) {
                String valoreCampo = (recordDtoGenerico.getCampo(campo) != null
    							? (String) recordDtoGenerico.getCampo(campo)
    							: null);
                if ((valoreCampo != null) && (!valoreCampo.isBlank()) ) {
                    listaEsiti.add(creaEsitoOk(nomeCampo));
                    return listaEsiti;
                }
                if (valoreCampo == null || valoreCampo.isBlank()) {
                    numeroCampiVuoti++;
                }
            }
            if (numeroCampiVuoti == listaCampi.size()) 
            	listaEsiti.add(creaEsitoKO(nomeCampo, this.getCodErrore(), this.getDesErrore()));                
            
            

        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            log.error("Impossibile validare la regola RegolaObbligatorietaCondizionataAlmenoUnoCampi per il campo " + nomeCampo, e);
            throw new ValidazioneImpossibileException("Impossibile validare la regola RegolaObbligatorietaCondizionataAlmenoUnoCampi per il campo " + nomeCampo);
        }
        return listaEsiti;

    }
}

