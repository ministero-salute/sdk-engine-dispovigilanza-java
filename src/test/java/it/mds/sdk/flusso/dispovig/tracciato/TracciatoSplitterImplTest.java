/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.tracciato;

import static org.mockito.Mockito.mockStatic;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import it.mds.sdk.flusso.dispovig.parser.regole.RecordDtoDispovig;
import it.mds.sdk.flusso.dispovig.parser.regole.conf.ConfigurazioneFlussoDispovig;
import it.mds.sdk.flusso.dispovig.tracciato.bean.output.FlsDispovig;
import it.mds.sdk.flusso.dispovig.tracciato.bean.output.ObjectFactory;
import it.mds.sdk.gestorefile.GestoreFile;
import it.mds.sdk.gestorefile.factory.GestoreFileFactory;
import it.mds.sdk.libreriaregole.dtos.CampiInputBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT)
public class TracciatoSplitterImplTest {
    @InjectMocks
    @Spy
    private TracciatoSplitterImpl tracciatoSplitter;
    private ConfigurazioneFlussoDispovig configurazione = Mockito.mock(ConfigurazioneFlussoDispovig.class);
    private ObjectFactory objectFactory = Mockito.mock(ObjectFactory.class);
    private FlsDispovig flsProSoc = Mockito.mock(FlsDispovig.class);
//    private FlsDispovig.AziendaSanitariaRiferimento asl = Mockito.mock(FlsDispovig.AziendaSanitariaRiferimento.class);
    private ConfigurazioneFlussoDispovig.XmlOutput xmlOutput = Mockito.mock(ConfigurazioneFlussoDispovig.XmlOutput.class);
    private MockedStatic<GestoreFileFactory> gestore;
    private GestoreFile gestoreFile = Mockito.mock(GestoreFile.class);
//    private FlsDispovig.AziendaSanitariaRiferimento aziendaSanitariaRiferimento = Mockito.mock(FlsDispovig.AziendaSanitariaRiferimento.class);
//    private List<FlsDispovig.AziendaSanitariaRiferimento> aziendaSanitariaRiferimentoList = new ArrayList<>();
//    private FlsDispovig.AziendaSanitariaRiferimento.DSM dsm = Mockito.mock(FlsDispovig.AziendaSanitariaRiferimento.DSM.class);
//    private List<FlsDispovig.AziendaSanitariaRiferimento.DSM> listDsm = new ArrayList<>();
//    private FlsDispovig.AziendaSanitariaRiferimento.DSM.Assistito assistito = Mockito.mock(FlsDispovig.AziendaSanitariaRiferimento.DSM.Assistito.class);
    private RecordDtoDispovig r = new RecordDtoDispovig();
    List<RecordDtoDispovig> records = new ArrayList<>();

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        gestore = mockStatic(GestoreFileFactory.class);
        initMockedRecord(r);
        records.add(r);
    }

//    @Test
//    void dividiTracciatoTest() throws JAXBException, IOException, SAXException {
//
//        when(tracciatoSplitter.getConfigurazione()).thenReturn(configurazione);
//        when(objectFactory.createFlsDispovig()).thenReturn(flsProSoc);
////        when(flsProSoc.getAziendaSanitariaRiferimento()).thenReturn(List.of(asl));
//        when(configurazione.getXmlOutput()).thenReturn(xmlOutput);
//        when(xmlOutput.getPercorso()).thenReturn("percorso");
//        gestore.when(() -> GestoreFileFactory.getGestoreFile("XML")).thenReturn(gestoreFile);
//        doNothing().when(gestoreFile).scriviDto(any(), any(), any());
//        
//        
//        Assertions.assertEquals(
//                List.of(Path.of("percorsoSDK_PS1_PS1_Z04_999.xml")),
//                this.tracciatoSplitter.dividiTracciato(records, "999")
//        );
//
//    }

//    @Test
//    void dividiTracciatoTestOk2() throws JAXBException, IOException, SAXException {
//        when(tracciatoSplitter.getConfigurazione()).thenReturn(configurazione);
//        when(objectFactory.createFlsDispovig()).thenReturn(flsProSoc);
////        when(flsProSoc.getAziendaSanitariaRiferimento()).thenReturn(List.of(asl));
//
//        when(configurazione.getXmlOutput()).thenReturn(xmlOutput);
//        when(xmlOutput.getPercorso()).thenReturn("percorso");
//        gestore.when(() -> GestoreFileFactory.getGestoreFile("XML")).thenReturn(gestoreFile);
//        doNothing().when(gestoreFile).scriviDto(any(), any(), any());
//
////        doReturn(null).when(tracciatoSplitter).getCurrentAsl(any(), any());
////        doReturn(null).when(tracciatoSplitter).getCurrentDSM(any(), any());
//        Assertions.assertEquals(
//                List.of(Path.of("percorsoSDK_PS1_PS1_Z04_100.xml")),
//                this.tracciatoSplitter.dividiTracciato(records, "100")
//        );
//
//    }

//    @Test
//    void getCurrentDsmTest() {
//        var list = List.of(dsm);
//        when(asl.getDSM()).thenReturn(list);
//        var c = tracciatoSplitter.getCurrentDSM(asl, r);
//    }

//    @Test
//    void getCurrentAslTest() {
//        var list = List.of(asl);
//
//        when(flsProSoc.getAziendaSanitariaRiferimento()).thenReturn(list);
//        var c = tracciatoSplitter.getCurrentAsl(flsProSoc, r);
//    }

//    @Test
//    void creaPrestazioniSanitarieTest() {
//        var list = List.of(asl);
//        var c = tracciatoSplitter.creaTerritorialeContatti(records, null);
//    }

    @AfterEach
    void closeMocks() {
        gestore.close();
    }

    private void initMockedRecord(RecordDtoDispovig r) {
        CampiInputBean campiInputBean = new CampiInputBean();
        campiInputBean.setPeriodoRiferimentoInput("");
        campiInputBean.setAnnoRiferimentoInput("");
        campiInputBean.setCodiceRegioneInput("");
        r.setCampiInput(campiInputBean);
        r.setRapporto_compilatore_codStruttSanitaria("111111");
        records.add(r);
    }

}