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
class RegolaIncongruenzaCrossDueCampiTest {

    @Mock
    RecordDtoGenerico recordMockito;
    RegolaIncongruenzaCrossDueCampi regola;
    Parametri parametriTest;
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        Map<String, String> parametri = new HashMap<>();
        parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        regola = spy(new RegolaIncongruenzaCrossDueCampi("TEST", "TEST", "TEST", parametriTest));
    }

    @Test
    void validaCostruttore() {
        RegolaIncongruenzaCrossDueCampi regola = new RegolaIncongruenzaCrossDueCampi();
        assertTrue(regola instanceof RegolaIncongruenzaCrossDueCampi);
    }
    @Test
    void validaKO() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("operatoreLogico", "and");
        parametri.put("nomeCampoCondizionante", "campo");
        parametri.put("nomeCampoCondizionante2", "campo2");

        parametri.put("parametroCampoCondizionante","val1");
        parametri.put("parametroCampoCondizionante2","val1");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaIncongruenzaCrossDueCampi regola = new RegolaIncongruenzaCrossDueCampi("RegolaIncongruenzaCrossDueCampi", "9999", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("campo")).thenReturn("valore1");
        Mockito.when(recordMockito.getCampo("campo2")).thenReturn("valore2");
        //Mockito.when(recordMockito.getCampo("campoRecord")).thenReturn("valore2");

        List<Esito> result = regola.valida("campoRecord", recordMockito);
        for (Esito e : result) {
            assertFalse(e.isValoreEsito());
            assertEquals("9999", e.getErroriValidazione().get(0).getCodice());
        }
    }
    @Test
    void validaKO2() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("operatoreLogico", "or");
        parametri.put("nomeCampoCondizionante", "campo");
        parametri.put("nomeCampoCondizionante2", "campo2");

        parametri.put("parametroCampoCondizionante","valore2");
        parametri.put("parametroCampoCondizionante2","valore1");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaIncongruenzaCrossDueCampi regola = new RegolaIncongruenzaCrossDueCampi("RegolaIncongruenzaCrossDueCampi", "9999", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("campo")).thenReturn("valore1");
        Mockito.when(recordMockito.getCampo("campo2")).thenReturn("valore2");
        //Mockito.when(recordMockito.getCampo("campoRecord")).thenReturn("valore2");

        List<Esito> result = regola.valida("campoRecord", recordMockito);
        for (Esito e : result) {
            assertFalse(e.isValoreEsito());
            assertEquals("9999", e.getErroriValidazione().get(0).getCodice());
        }
    }
    @Test
    void validaKO3() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("operatoreLogico", "or");
        parametri.put("nomeCampoCondizionante", "campo");
        parametri.put("nomeCampoCondizionante2", "campo2");

        parametri.put("parametroCampoCondizionante","valore2");
        parametri.put("parametroCampoCondizionante2","valore1");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaIncongruenzaCrossDueCampi regola = new RegolaIncongruenzaCrossDueCampi("RegolaIncongruenzaCrossDueCampi", "9999", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("campo")).thenReturn("valore1");
        Mockito.when(recordMockito.getCampo("campo2")).thenReturn("");
        Mockito.when(recordMockito.getCampo("campoRecord")).thenReturn("valore2");

        List<Esito> result = regola.valida("campoRecord", recordMockito);
        for (Esito e : result) {
            assertFalse(e.isValoreEsito());
            assertEquals("9999", e.getErroriValidazione().get(0).getCodice());
        }
    }
    @Test
    void validaKOException() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Mockito.when(recordMockito.getCampo(any())).thenThrow(new IllegalAccessException());
        assertThrows(ValidazioneImpossibileException.class,()->regola.valida("codStruttura", recordMockito));
    }
}