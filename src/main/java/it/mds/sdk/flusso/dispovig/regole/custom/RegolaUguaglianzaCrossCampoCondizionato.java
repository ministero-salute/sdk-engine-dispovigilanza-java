/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.regole.custom;

import java.lang.reflect.InvocationTargetException;
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
@XmlDiscriminatorValue("regolaUguaglianzaCrossCampoCondizionato")
public class RegolaUguaglianzaCrossCampoCondizionato extends RegolaGenerica {


    public RegolaUguaglianzaCrossCampoCondizionato(String nome, String codErrore, String desErrore, Parametri parametri) {
        super(nome, codErrore, desErrore, parametri);
    }

    /**
     * verifica un campo del DTO sia uguale al valore prefissato; qualora così fosse verifica che
     * il campo del tracciato sia uguale ad un altro campo del DTO
     *
     * @param nomeCampo         campo da validare
     * @param recordDtoGenerico DTO del record del flusso
     * @return lista di {@link Esito}
     */

    @Override
    public List<Esito> valida(String nomeCampo, RecordDtoGenerico recordDtoGenerico) {
        List<Esito> listaEsiti = new ArrayList<>();
        try {
        	
            String daValidare = String.valueOf(recordDtoGenerico.getCampo(nomeCampo));
            String validatore = this.getParametri().getParametriMap().get("campoValidatore");
            String parametroInput = this.getParametri().getParametriMap().get("parametroInput");
            String comparante = String.valueOf(recordDtoGenerico.getCampo(validatore));
            
            String campoUg = this.getParametri().getParametriMap().get("campoUguaglianza");
            String campoUguaglianza = String.valueOf(recordDtoGenerico.getCampo(campoUg));
           
            if (comparante.equals(parametroInput)) {
                if (daValidare.equals(campoUguaglianza)) {
                    listaEsiti.add(creaEsitoOk(nomeCampo));
                } else {
                    listaEsiti.add(creaEsitoKO(nomeCampo,this.getCodErrore(),this.getDesErrore()));
                            //.withDescrizione("Il campo : " + nomeCampo + " e campo : " + validatore + " non sono uguali")
                }
                return listaEsiti;

            }
            listaEsiti.add(creaEsitoOk(nomeCampo));
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            log.error("Impossibile validare la regola UguaglianzaCrossCampoValore per il campo " + nomeCampo, e);
            throw new ValidazioneImpossibileException("Impossibile validare la regola UguaglianzaCrossCampoValore per il campo " + nomeCampo);
        }
        return listaEsiti;
    }


}
