/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.regole.custom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
class RegolaIncoerenzaCrossCampoUguaglianzaTest {

    @Mock
    RecordDtoGenerico recordMockito;
    RegolaIncoerenzaCrossCampoUguaglianza regola;
    Parametri parametriTest;
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        Map<String, String> parametri = new HashMap<>();
        parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        regola = spy(new RegolaIncoerenzaCrossCampoUguaglianza("TEST", "TEST", "TEST", parametriTest));
    }

    @Test
    void validaCostruttore() {
        RegolaIncoerenzaCrossCampoUguaglianza regola = new RegolaIncoerenzaCrossCampoUguaglianza();
        assertTrue(regola instanceof RegolaIncoerenzaCrossCampoUguaglianza);
    }

    @Test
    void validaKO() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("nomeCampoCoerente", "campo");
        parametri.put("valoreConfronto", "valore2");
        parametri.put("listaValoriCoerenti", "123");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaIncoerenzaCrossCampoUguaglianza regola = new RegolaIncoerenzaCrossCampoUguaglianza("RegolaIncoerenzaCrossCampoUguaglianza", "9999", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("campo")).thenReturn("valore1");
        Mockito.when(recordMockito.getCampo("campoRecord")).thenReturn("valore2");

        List<Esito> result = regola.valida("campoRecord", recordMockito);
        for (Esito e : result) {
            assertFalse(e.isValoreEsito());
            assertEquals("9999", e.getErroriValidazione().get(0).getCodice());
        }
    }
    @Test
    void validaOK() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("nomeCampoCoerente", "campo");
        parametri.put("valoreConfronto", "valore1");
        parametri.put("listaValoriCoerenti", "valore1");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaIncoerenzaCrossCampoUguaglianza regola = new RegolaIncoerenzaCrossCampoUguaglianza("RegolaIncoerenzaCrossCampoUguaglianza", "9999", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("campo")).thenReturn("valore1");
        Mockito.when(recordMockito.getCampo("campoRecord")).thenReturn("valore1");

        List<Esito> result = regola.valida("campoRecord", recordMockito);
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