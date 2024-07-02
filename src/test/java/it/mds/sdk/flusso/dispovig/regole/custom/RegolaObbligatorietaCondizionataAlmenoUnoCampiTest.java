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
class RegolaObbligatorietaCondizionataAlmenoUnoCampiTest {
    @Mock
    RecordDtoGenerico recordMockito;
    RegolaObbligatorietaCondizionataAlmenoUnoCampi regola;
    Parametri parametriTest;
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        Map<String, String> parametri = new HashMap<>();
        parametri.put("listaCampi","campo1|campo2");
        parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        regola = spy(new RegolaObbligatorietaCondizionataAlmenoUnoCampi("TEST", "TEST", "TEST", parametriTest));
    }

    @Test
    void validaCostruttore() {
        RegolaObbligatorietaCondizionataAlmenoUnoCampi regola = new RegolaObbligatorietaCondizionataAlmenoUnoCampi();
        assertTrue(regola instanceof RegolaObbligatorietaCondizionataAlmenoUnoCampi);
    }

    @Test
    void validaOK() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("listaCampi", "rapporto_compilatore_telefonoCompilatore|rapporto_compilatore_faxCompilatore|rapporto_compilatore_emailCompilatore");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaObbligatorietaCondizionataAlmenoUnoCampi regola = new RegolaObbligatorietaCondizionataAlmenoUnoCampi("RegolaObbligatorietaAlternataCrossCampo", "9999", "descrizioneErrore",parametriTest);

        Mockito.when(recordMockito.getCampo("rapporto_compilatore_telefonoCompilatore")).thenReturn("1");
        //Mockito.when(recordMockito.getCampo("rapporto_compilatore_faxCompilatore")).thenReturn("2");
        //Mockito.when(recordMockito.getCampo("rapporto_compilatore_emailCompilatore")).thenReturn("3");


        List<Esito> result = regola.valida("campoRecord", recordMockito);
        for (Esito e : result) {
            assertTrue(e.isValoreEsito());
        }
    }

    @Test
    void validaOK2() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("listaCampi", "rapporto_compilatore_telefonoCompilatore|rapporto_compilatore_faxCompilatore|rapporto_compilatore_emailCompilatore");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaObbligatorietaCondizionataAlmenoUnoCampi regola = new RegolaObbligatorietaCondizionataAlmenoUnoCampi("RegolaObbligatorietaAlternataCrossCampo", "9999", "descrizioneErrore",parametriTest);

        Mockito.when(recordMockito.getCampo("rapporto_compilatore_telefonoCompilatore")).thenReturn("");
        Mockito.when(recordMockito.getCampo("rapporto_compilatore_faxCompilatore")).thenReturn("2");
        //Mockito.when(recordMockito.getCampo("rapporto_compilatore_emailCompilatore")).thenReturn("3");


        List<Esito> result = regola.valida("campoRecord", recordMockito);
        for (Esito e : result) {
            assertTrue(e.isValoreEsito());
        }
    }

    @Test
    void validaOK3() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        parametri.put("listaCampi", "rapporto_compilatore_telefonoCompilatore|rapporto_compilatore_faxCompilatore|rapporto_compilatore_emailCompilatore");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaObbligatorietaCondizionataAlmenoUnoCampi regola = new RegolaObbligatorietaCondizionataAlmenoUnoCampi("RegolaObbligatorietaAlternataCrossCampo", "9999", "descrizioneErrore",parametriTest);

        Mockito.when(recordMockito.getCampo("rapporto_compilatore_telefonoCompilatore")).thenReturn("");
        Mockito.when(recordMockito.getCampo("rapporto_compilatore_faxCompilatore")).thenReturn("");
        Mockito.when(recordMockito.getCampo("rapporto_compilatore_emailCompilatore")).thenReturn("3");


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