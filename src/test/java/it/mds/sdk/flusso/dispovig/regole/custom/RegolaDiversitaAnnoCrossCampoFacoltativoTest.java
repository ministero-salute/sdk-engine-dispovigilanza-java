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
class RegolaDiversitaAnnoCrossCampoFacoltativoTest {

    @Mock
    RecordDtoGenerico recordMockito;
    RegolaDiversitaAnnoCrossCampoFacoltativo regola;
    Parametri parametriTest;
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        Map<String, String> parametri = new HashMap<>();
        parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        regola = spy(new RegolaDiversitaAnnoCrossCampoFacoltativo("TEST", "TEST", "TEST", parametriTest));
    }

    @Test
    void validaCostruttore() {
        RegolaDiversitaAnnoCrossCampoFacoltativo regola = new RegolaDiversitaAnnoCrossCampoFacoltativo();
        assertTrue(regola instanceof RegolaDiversitaAnnoCrossCampoFacoltativo);
    }

    @Test
    void validaOK() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();
        parametri.put("valoreSuccessivo", "true");
        parametri.put("campoDipendente", "campoDip");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaDiversitaAnnoCrossCampoFacoltativo regola = new RegolaDiversitaAnnoCrossCampoFacoltativo("RegolaDiversitaAnnoCrossCampoFacoltativo", "9999", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("campoDip")).thenReturn("2022");
        Mockito.when(recordMockito.getCampo("anno")).thenReturn("2022");

        List<Esito> result = regola.valida("anno", recordMockito);
        for (Esito e : result) {
            assertTrue(e.isValoreEsito());

        }
    }
    @Test
    void validaKO() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();
        parametri.put("valoreSuccessivo", "true");
        parametri.put("campoDipendente", "campoDip");

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaDiversitaAnnoCrossCampoFacoltativo regola = new RegolaDiversitaAnnoCrossCampoFacoltativo("RegolaDiversitaAnnoCrossCampoFacoltativo", "9999", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("campoDip")).thenReturn("2022-12-12");
        Mockito.when(recordMockito.getCampo("anno")).thenReturn("2025-12-12");

        List<Esito> result = regola.valida("anno", recordMockito);
        for (Esito e : result) {
            assertFalse(e.isValoreEsito());

        }
    }
    @Test
    void validaKOException() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Mockito.when(recordMockito.getCampo(any())).thenThrow(new IllegalAccessException());
        assertThrows(ValidazioneImpossibileException.class,()->regola.valida("codStruttura", recordMockito));
    }
}