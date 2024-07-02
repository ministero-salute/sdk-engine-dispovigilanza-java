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
class RegolaIncongruenzaCrossDueCampiUguaglianzaTest {

    @Mock
    RecordDtoGenerico recordMockito;
    RegolaIncongruenzaCrossDueCampiUguaglianza regola;
    Parametri parametriTest;
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        Map<String, String> parametri = new HashMap<>();
        parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        regola = spy(new RegolaIncongruenzaCrossDueCampiUguaglianza("TEST", "TEST", "TEST", parametriTest));
    }

    @Test
    void validaCostruttore() {
        RegolaIncongruenzaCrossDueCampiUguaglianza regola = new RegolaIncongruenzaCrossDueCampiUguaglianza();
        assertTrue(regola instanceof RegolaIncongruenzaCrossDueCampiUguaglianza);
    }
    @Test
    void validaKO() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("parametroCampoCondizionante", "6|7");
        parametri.put("nomeCampoCondizionante", "assistitoDimissioneEsitoTrattamento");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaIncongruenzaCrossDueCampiUguaglianza regola = new RegolaIncongruenzaCrossDueCampiUguaglianza("RegolaIncongruenzaCrossDueCampiUguaglianza", "9999", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("campoRecord")).thenReturn("9");
        Mockito.when(recordMockito.getCampo("assistitoDimissioneEsitoTrattamento")).thenReturn("6");

        List<Esito> result = regola.valida("campoRecord", recordMockito);
        for (Esito e : result) {
            assertFalse(e.isValoreEsito());
            assertEquals("9999", e.getErroriValidazione().get(0).getCodice());
        }
    }

    @Test
    void validaOK() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("parametroCampoCondizionante", "6|7");
        parametri.put("nomeCampoCondizionante", "assistitoDimissioneEsitoTrattamento");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaIncongruenzaCrossDueCampiUguaglianza regola = new RegolaIncongruenzaCrossDueCampiUguaglianza("RegolaIncongruenzaCrossDueCampiUguaglianza", "9999", "descrizioneErrore",parametriTest);
        //Mockito.when(recordMockito.getCampo("campoRecord")).thenReturn("6");
        Mockito.when(recordMockito.getCampo("assistitoDimissioneEsitoTrattamento")).thenReturn("10");

        List<Esito> result = regola.valida("campoRecord", recordMockito);
        for (Esito e : result) {
            assertTrue(e.isValoreEsito());
        }
    }
    @Test
    void validaOK2() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("parametroCampoCondizionante", "6|7");
        parametri.put("nomeCampoCondizionante", "assistitoDimissioneEsitoTrattamento");
        parametri.put("nomeCampoCondizionante2", "assistitoDimissioneEsitoTrattamento2");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaIncongruenzaCrossDueCampiUguaglianza regola = new RegolaIncongruenzaCrossDueCampiUguaglianza("RegolaIncongruenzaCrossDueCampiUguaglianza", "9999", "descrizioneErrore",parametriTest);
        //Mockito.when(recordMockito.getCampo("campoRecord")).thenReturn("6");
        Mockito.when(recordMockito.getCampo("assistitoDimissioneEsitoTrattamento")).thenReturn("10");
        Mockito.when(recordMockito.getCampo("assistitoDimissioneEsitoTrattamento2")).thenReturn("10");

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