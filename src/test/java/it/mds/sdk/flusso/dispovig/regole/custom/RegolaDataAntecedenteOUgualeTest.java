/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.regole.custom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.mds.sdk.libreriaregole.exception.ValidazioneImpossibileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import it.mds.sdk.gestoreesiti.modelli.Esito;
import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
import it.mds.sdk.libreriaregole.regole.beans.Parametri;

@ExtendWith(MockitoExtension.class)
class RegolaDataAntecedenteOUgualeTest {

    @Mock
    RecordDtoGenerico recordMockito;
    RegolaDataAntecedenteOUguale regola;
    Parametri parametriTest;
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        Map<String, String> parametri = new HashMap<>();
        parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        regola = spy(new RegolaDataAntecedenteOUguale("TEST", "TEST", "TEST", parametriTest));
    }

    @Test
    void validaCostruttore() {
        RegolaDataAntecedenteOUguale regola = new RegolaDataAntecedenteOUguale();
        assertTrue(regola instanceof RegolaDataAntecedenteOUguale);
    }

    @Test
    void validaKO() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();
        parametri.put("nomeCampoValidatore", "assistitoDimissioneData");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaDataAntecedenteOUguale regola = new RegolaDataAntecedenteOUguale("RegolaDataAntecedenteOUguale", "1003", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("dataRecord")).thenReturn("2080-01-25");
        Mockito.when(recordMockito.getCampo("assistitoDimissioneData")).thenReturn("2000-01-25");
        List<Esito> result = regola.valida("dataRecord", recordMockito);
        for (Esito e : result) {
            assertFalse(e.isValoreEsito());
            assertEquals("1003", e.getErroriValidazione().get(0).getCodice());
        }
    }
    @Test
    void validaOK() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("nomeCampoValidatore", "assistitoDimissioneData");
        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaDataAntecedenteOUguale regola = new RegolaDataAntecedenteOUguale("RegolaDataAntecedenteOUguale", "1003", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("dataRecord")).thenReturn(null);
        Mockito.when(recordMockito.getCampo("dataRecord")).thenReturn("2000-01-25");
        List<Esito> result = regola.valida("dataRecord", recordMockito);
        for (Esito e : result) {
            assertTrue(e.isValoreEsito());
        }
    }
    @Test
    void validaOKDataDaComparareisNotAfter() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();
        parametri.put("nomeCampoValidatore", "assistitoDimissioneData");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaDataAntecedenteOUguale regola = new RegolaDataAntecedenteOUguale("RegolaDataAntecedenteOUguale", "1003", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("dataRecord")).thenReturn("2000-01-25");
        Mockito.when(recordMockito.getCampo("assistitoDimissioneData")).thenReturn("2080-01-25");
        List<Esito> result = regola.valida("dataRecord", recordMockito);
        for (Esito e : result) {
            assertTrue(e.isValoreEsito());
        }
    }
    @Test
    void validaKOException() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Mockito.when(recordMockito.getCampo(any())).thenThrow(new IllegalAccessException());
        assertThrows(ValidazioneImpossibileException.class,()->regola.valida("codStruttura", recordMockito));
    }
}