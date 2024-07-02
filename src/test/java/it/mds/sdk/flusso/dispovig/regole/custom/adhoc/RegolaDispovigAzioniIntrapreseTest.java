/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.regole.custom.adhoc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import it.mds.sdk.gestoreesiti.modelli.Esito;
import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
import it.mds.sdk.libreriaregole.regole.beans.Parametri;
@ExtendWith(MockitoExtension.class)
class RegolaDispovigAzioniIntrapreseTest {
    @Mock
    RecordDtoGenerico recordMockito;

    @Test
    void validaCostruttore() {
        RegolaDispovigAzioniIntraprese regola = new RegolaDispovigAzioniIntraprese();
        assertTrue(regola instanceof RegolaDispovigAzioniIntraprese);
    }

    @Test
    void validaOK() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaDispovigAzioniIntraprese regola = new RegolaDispovigAzioniIntraprese("RegolaDispovigAzioniIntraprese", "9999", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("rapporto_evento_azioniIntraprese")).thenReturn("IFD");

        List<Esito> result = regola.valida("recordcampo", recordMockito);
        for (Esito e : result) {
            assertTrue(e.isValoreEsito());
        }
    }

    @Test
    void validaKO() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> parametri = new HashMap<String, String>();

        Parametri parametriTest = new Parametri();
        parametriTest.setParametriMap(parametri);

        RegolaDispovigAzioniIntraprese regola = new RegolaDispovigAzioniIntraprese("RegolaDispovigAzioniIntraprese", "9999", "descrizioneErrore",parametriTest);
        Mockito.when(recordMockito.getCampo("rapporto_evento_azioniIntraprese")).thenReturn("1");

        List<Esito> result = regola.valida("recordcampo", recordMockito);
        for (Esito e : result) {
            assertFalse(e.isValoreEsito());
        }
    }
}