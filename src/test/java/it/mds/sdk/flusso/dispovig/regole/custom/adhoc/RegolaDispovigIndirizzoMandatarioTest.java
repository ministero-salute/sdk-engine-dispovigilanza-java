/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.regole.custom.adhoc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import it.mds.sdk.anagrafiche.client.exceptions.MalformedRegistryException;
import it.mds.sdk.anagrafiche.client.exceptions.RegistryNotFoundException;
import it.mds.sdk.connettore.anagrafiche.gestore.anagrafica.GestoreAnagrafica;
import it.mds.sdk.connettore.anagrafiche.tabella.TabellaAnagrafica;
import it.mds.sdk.flusso.dispovig.parser.regole.Utilities;
import it.mds.sdk.gestoreesiti.modelli.Esito;
import it.mds.sdk.libreriaregole.dtos.CampiInputBean;
import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
import it.mds.sdk.libreriaregole.exception.ValidazioneImpossibileException;
import it.mds.sdk.libreriaregole.regole.beans.Parametri;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RegolaDispovigIndirizzoMandatarioTest {
    @Mock
    RecordDtoGenerico recordMockito;
    @Mock
    GestoreAnagrafica gestoreAnagrafica = Mockito.mock(GestoreAnagrafica.class);
    TabellaAnagrafica tabellaAnagrafica = Mockito.mock(TabellaAnagrafica.class);
    CampiInputBean campiInputBean = Mockito.mock(CampiInputBean.class);
    Parametri parametriTest;
    private static MockedStatic<Utilities> utilities;

    RegolaDispovigIndirizzoMandatario regola;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        utilities = mockStatic(Utilities.class);
        Map<String, String> parametri = new HashMap<>();
        // parametri.put("valoreDipendente", "1");
        // parametri.put("campoDipendente", "importoPosizioneAssistitoTicket");
        // parametri.put("nomeTabella", "ANAG_EMUR_ESENZIONI");
        parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        regola = spy(new RegolaDispovigIndirizzoMandatario("TEST", "TEST", "TEST", parametriTest));
    }
    
    @AfterEach
    void close(){
        utilities.close();
    }

    @Test
    void costruttoreVuoto() {
        RegolaDispovigIndirizzoMandatario regola = new RegolaDispovigIndirizzoMandatario();
        assertTrue(regola instanceof RegolaDispovigIndirizzoMandatario);
    }

    @Test
    void validaOK() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, MalformedRegistryException, RegistryNotFoundException {
        Mockito.when(recordMockito.getCampo("rapporto_dispositivo_codiceDispositivo")).thenReturn("1");
        Mockito.when(recordMockito.getCampo("rapporto_dispositivo_nazioneFabbricante")).thenReturn("1");
        Mockito.when(recordMockito.getCampo("rapporto_dispositivo_descNazioneFabbricante")).thenReturn("1");
        Mockito.when(recordMockito.getCampo("rapporto_dispositivo_indirizzoMandatario")).thenReturn("1");

        List<String> lista = new ArrayList<>();lista.add("001001");
        utilities.when(() -> Utilities.getValoriAnagrafica(eq("ANAG_DISPOVIG_NAZIONE_NON_EU"),anyString())).thenReturn(lista);
        
        List<Esito> result = regola.valida("recordCampo", recordMockito);
        for (Esito e : result) {
            assertTrue(e.isValoreEsito());
        }
    }
    @Test
    void validaOK2() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, MalformedRegistryException, RegistryNotFoundException {
        Mockito.when(recordMockito.getCampo("rapporto_dispositivo_codiceDispositivo")).thenReturn("1");
        Mockito.when(recordMockito.getCampo("rapporto_dispositivo_nazioneFabbricante")).thenReturn("IT");
        Mockito.when(recordMockito.getCampo("rapporto_dispositivo_descNazioneFabbricante")).thenReturn("1");
        Mockito.when(recordMockito.getCampo("rapporto_dispositivo_indirizzoMandatario")).thenReturn("1");

        List<String> lista = new ArrayList<>();lista.add("001001");
        utilities.when(() -> Utilities.getValoriAnagrafica(eq("ANAG_DISPOVIG_NAZIONE_NON_EU"),anyString())).thenReturn(lista);

        List<Esito> result = regola.valida("recordCampo", recordMockito);
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