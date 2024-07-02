/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.regole.custom;

import it.mds.sdk.gestoreesiti.modelli.Esito;
import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
import it.mds.sdk.libreriaregole.exception.ValidazioneImpossibileException;
import it.mds.sdk.libreriaregole.regole.beans.Parametri;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class RegolaObbligatorietaCondizionataMultiCampoDiversoTest {
    @Mock
    RecordDtoGenerico recordMockito;
    RegolaObbligatorietaCondizionataMultiCampoDiverso regola;
    Parametri parametriTest;
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        Map<String, String> parametri = new HashMap<>();
        parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        regola = spy(new RegolaObbligatorietaCondizionataMultiCampoDiverso("TEST", "TEST", "TEST", parametriTest));
    }

    @Test
    void validaCostruttore() {
        RegolaObbligatorietaCondizionataMultiCampoDiverso regola = new RegolaObbligatorietaCondizionataMultiCampoDiverso();
        assertTrue(regola instanceof RegolaObbligatorietaCondizionataMultiCampoDiverso);
    }

    @Test
    void validaOK() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("parametroCampoCondizionante", "1");
        parametri.put("nomeCampoCondizionante", "importoPosizioneAssistitoTicket");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaObbligatorietaCondizionataMultiCampoDiverso regola = new RegolaObbligatorietaCondizionataMultiCampoDiverso("RegolaObbligatorietaCondizionataMultiCampoDiverso", "9999", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("campoRecord")).thenReturn(null);
        Mockito.when(recordMockito.getCampo("importoPosizioneAssistitoTicket")).thenReturn("1");

        List<Esito> result = regola.valida("campoRecord", recordMockito);
        for (Esito e : result) {
            assertTrue(e.isValoreEsito());
            //assertEquals("9999", e.getErroriValidazione().get(0).getCodice());
        }
    }

    @Test
    void validaOK2() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("parametroCampoCondizionante", "1");
        parametri.put("nomeCampoCondizionante", "campo1");
        parametri.put("nomeCampoCondizionante2", "campo2");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaObbligatorietaCondizionataMultiCampoDiverso regola = new RegolaObbligatorietaCondizionataMultiCampoDiverso("RegolaObbligatorietaCondizionataMultiCampoDiverso", "9999", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("campoRecord")).thenReturn(null);
        Mockito.when(recordMockito.getCampo("campo1")).thenReturn("1");
        Mockito.when(recordMockito.getCampo("campo2")).thenReturn("1");

        List<Esito> result = regola.valida("campoRecord", recordMockito);
        for (Esito e : result) {
            assertTrue(e.isValoreEsito());
            //assertEquals("9999", e.getErroriValidazione().get(0).getCodice());
        }
    }
    @Test
    void validaKOException() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Mockito.when(recordMockito.getCampo(any())).thenThrow(new IllegalAccessException());
        assertThrows(ValidazioneImpossibileException.class,()->regola.valida("codStruttura", recordMockito));
    }
}