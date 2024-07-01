|** |<p>**PROGETTO SDK**</p><p>Specifiche funzionali</p>||
| :- | :-: | -: |
||||


![](img/Aspose.Words.73a420d9-2fea-4ebc-9cd4-be228af382ca.001.jpeg)

**Progetto SDK**


**Area: DISPOVIGILANCE**

**Flusso Logico: Rapporto Operatore Segnalazione Incidente**

Specifiche Funzionali












**Indice**

[1.**	**Introduzione	4****](#_Toc125123778)**

[1.1	Obiettivi del documento	4](#_Toc125123779)

[1.2	Acronimi	5](#_Toc125123780)

[**2.**	**Architettura SDK	6****](#_Toc125123781)

[2.1	Architettura funzionale	6](#_Toc125123782)

[2.2	Architettura di integrazione	7](#_Toc125123783)

[**3.**	**Funzionamento della soluzione SDK	9****](#_Toc125123784)

[3.1	Tracciato input a SDK	9](#_Toc125123785)

[3.2 Controlli di validazione del dato (business rules)	30](#_Toc125123786)

[3.3 Flussi di Output per alimentazione MdS	69](#_Toc125123787)

[3.4 Scarti di processamento	71](#_Toc125123788)

[3.5 Informazioni dell’esecuzione	71](#_Toc125123789)




**Storia del documento** 


|Versione doc.|Data Revisione|Redatto da|Approvato da|Descrizione revisione|Cambi evidenziati|
| :- | :- | :- | :- | :- | :- |
|1.0|04/03/2022|<p>Giorgio Gherardi</p><p>Raffaele Donadio</p>||Prima stesura||
|2.0|01/08/2021|Vittoria Ricci||<p>Aggiornamento Business Rules contenute nel file Excel;</p><p>Aggiunta campi al sistema di logging;</p>||
|3.0|28/11/2022|Vittoria Ricci||Aggiornamento Business Rules e Tracciato contenuti nei file Excel.||
|4.0|12/01/2023|Vittoria Ricci||Aggiornamento Tracciato contenuto nel file Excel.||
|5.0|20/01/2023|Vittoria Ricci||Aggiornamento informazioni dell’esecuzione||

|SDK\_HLD\_DISPOVIG\_Dispovigilance\_v5.0.docx||Pag. 10 di 113|
| :- | :- | -: |

||<p>**PROGETTO SDK**</p><p>Specifiche funzionali</p>||
| :- | :-: | -: |
||||


1. # **Introduzione**

1. ## ***Obiettivi del documento***

Il Ministero della Salute (MdS) metterà a disposizione degli Enti da cui riceve dati applicazioni SDK specifiche per flusso logico e tecnologie applicative (Java, PHP e C#) per verifica preventiva (in casa Ente) della qualità del dato prodotto.

![](img/Aspose.Words.73a420d9-2fea-4ebc-9cd4-be228af382ca.002.png)

Nel presente documento sono fornite la struttura e la sintassi dei tracciati previsti dalla soluzione SDK per avviare il proprio processo elaborativo, nonché i relativi schemi xsd di convalida e i controlli di merito sulla qualità, completezza e coerenza dei dati. 

Gli obiettivi del documento sono:

- fornire una descrizione funzionale chiara e consistente dei tracciati di input a SDK;
- fornire le regole funzionali per la verifica di qualità, completezza e coerenza dei dati;

In generale, la soluzione SDK è costituita da 2 diversi moduli applicativi (Access Layer e Validation Engine) per abilitare

- l’interoperabilità con il contesto tecnologico dell’Ente in cui la soluzione sarà installata;
- la validazione del dato ed il suo successivo invio verso il MdS.

La figura che segue descrive la soluzione funzionale ed i relativi benefici attesi.

![](img/Aspose.Words.73a420d9-2fea-4ebc-9cd4-be228af382ca.003.png)

1. ## ***Acronimi***

Nella tabella riportata di seguito sono elencati tutti gli acronimi e le definizioni adottate nel presente documento. 


|**#**|**Acronimo / Riferimento**|**Definizione**|
| - | - | - |
|1|NSIS|Nuovo Sistema Informativo Sanitario|
|2|SDK|Software Development Kit|
|3|SSN|Sistema Sanitario Nazionale|
|4|RO|Rapporto Operatore Sanitario|

1. # **Architettura SDK**
   1. ## ***Architettura funzionale***

Di seguito una rappresentazione architetturale del processo di gestione e trasferimento dei flussi dall’ente verso l’area MdS attraverso l’utilizzo dell’applicativo SDK, e il corrispondente diagramma di sequenza.

![](img/Aspose.Words.73a420d9-2fea-4ebc-9cd4-be228af382ca.004.jpeg)

![A picture containing graphical user interface

![]((img/Aspose.Words.73a420d9-2fea-4ebc-9cd4-be228af382ca.005.png)


1. L’utente dell’ente caricherà in una apposita directory (es. /sdk/input/) il flusso sorgente.  L’utente avvierà l’SDK passando in input una serie di parametri descritti in dettaglio al paragrafo 3.1
1. La componente Access Layer estrae dalla directory indicata il file e ne salva una copia sulla cartella flussi elaborati (es. /sdk/run/). Per ogni elaborazione, SDK genererà un identificativo (ID\_RUN) a fini di logging
1. I record del flusso verranno sottoposti alle logiche di validazione e controllo definite nel Validation Engine. Nel processare il dato, Validation Engine acquisirà da MdS eventuali anagrafiche di validazione del dato stesso.
1. Generazione del file degli scarti contenente tutti i record in scarto con evidenza degli errori riscontrati. I file di scarto saranno memorizzati in cartelle ad hoc (es. /sdk/esiti).
1. Tutti i record che passeranno i controlli verranno inseriti in un file xml copiato in apposita cartella (es /sdk/xml\_output), il quale verrà eventualmente trasferito a MdS utilizzando la procedura messa a disposizione dal MdS. A fronte di un’acquisizione, il MdS fornirà a SDK un identificativo (ID\_UPLOAD) che sarà usato da SDK sia per fini di logging che di recupero del File Unico degli Scarti.
1. A conclusione del processo di verifica dei flussi, il Validation Engine eseguirà le seguenti azioni:
1. Aggiornamento file contentente il riepilogo dell’esito dell’elaborazione del Validation Engine e del ritorno dell’esito da parte di MdS. I file contenente l’esito dell’elaborazione saranno memorizzati in cartelle ad hoc (es. /sdk/run). 
1. Consolidamento del file di log applicativo dell’elaborazione dell’SDK in cui sono disponibili una serie di informazioni tecniche (Es. StackTrace di eventuali errori).
1. Copia del file generato al punto 5, se correttamente inviato al MdS, in apposita cartella (es. /sdk/sent).

Ad ogni step del precedente elenco e a partire dal punto 2, l’SDK aggiornerà di volta in volta il file contenente l’esito dell’elaborazione.

**Nota: l’SDK elaborerà un solo file di input per esecuzione.**

In generale, le classi di controllo previste su Validation Engine sono:

- Controlli FORMALI (es. correttezza dei formati e struttura record)
- Controlli SINTATTICI (es. check correttezza del Codice Fiscale)
- Controlli di OBBLIGATORIETÀ DEL DATO (es. Codice Prestazione su flusso…)
- Controlli STRUTTURE FILE (es. header/footer ove applicabile)
- Controlli di COERENZA CROSS RECORD 
- Controlli di corrispondenza dei dati trasmessi con le anagrafiche di riferimento;
- Controlli di esistenza di chiavi duplicate nel file trasmesso rispetto alle chiavi logiche individuate per ogni tracciato.

Si sottolinea che la soluzione SDK non implementa controlli che prevedono la congruità del dato input con la base date storica (es non viene verificato se per un nuovo inserimento la chiave del record non sia già presente sulla struttura dati MdS).

1. ## ***Architettura di integrazione***

La figura sottostante mostra l’architettura di integrazione della soluzione SDK con il MdS. Si evidenzia in particolare che

- Tutti i dati scambiati fra SDK e MdS saranno veicolati tramite Porta di Interoperabilità (PDI);
- Il MdS esporrà servizi (API) per il download di dati anagrafici;
- SDK provvederà ad inviare vs MdS l’output (record validati) delle proprie elaborazioni. A fronte di tale invio, il MdS provvederà a generare un identificativo di avvenuta acquisizione del dato che SDK memorizzerà a fini di logging.


![](img/Aspose.Words.73a420d9-2fea-4ebc-9cd4-be228af382ca.006.png)


1. # **Funzionamento della soluzione SDK**

Di seguito la descrizione del processo di inoltro delle segnalazioni da parte dell’operatore sanitario al sistema nazionale Dispovigilance.

![](img/Aspose.Words.73a420d9-2fea-4ebc-9cd4-be228af382ca.007.png)

Vengono di seguito descritti i dettagli di funzionamento della soluzione suddivisa nella parti in cui essa si compone. Ogni esecuzione sarà associata ad un progressivo (ID\_RUN) a fini di logging.


1. ## ***Tracciato input a SDK***

Il flusso di input avrà formato **csv** e una naming convention libera a discrezione dell’utente che carica il flusso senza alcun vincolo di nomenclatura specifica (es nome\_file.csv).

Il separatore per il file csv sarà il carattere tra doppi apici : “~“.

In fase di caricamento del file verrano impostati i seguenti parametri che andranno in input al SDK in fase di processamento del file:

|**NOME PARAMETRO**|**DESCRIZIONE**|**LUNGHEZZA**|**DOMINIO VALORI**|
| :- | :- | :- | :- |
|ID CLIENT|Identificativo univoco della transazione che fa richiesta all'SDK|100|Non definito|
|NOME FILE INPUT|Nome del file per il quale si richiede il processamento lato SDK|256|Non definito|
|ANNO RIFERIMENTO|Stringa numerica rappresentante l’anno di riferimento per cui si intende inviare la fornitura|4|Anno (Es. 2022)|
|PERIODO RIFERIMENTO|Stringa alfanumerica rappresentante il periodo per il quale si intende inviare la fornitura; |3|**13** (rappresenta un alias per il quale MDS usa come data di competenza l’anno e il mese di riferimento del record piuttosto che il parametro periodo di riferimento passato in input alla procedura InvioFlussi)|
|TIPO TRASMISSIONE |Indica se la trassmissione dei dati verso MDS avverrà il modalità full (F) o record per record (R). Per questo flusso la valorizzazione del parametro sarà impostata di default a F|1|F/R|
|FINALITA’ ELABORAZIONE|Indica se i flussi in output prodotti dal SDK verranno inviati verso MDS (Produzione) oppure se rimarranno all’interno del SDK e il processamento vale solo come test del flusso (Test)|1|Produzione/Test|
|CODICE REGIONE|Individua la Regione a cui afferisce la struttura. Il codice da utilizzare è quello a tre caratteri definito con DM 17 settembre 1986, pubblicato nella Gazzetta Ufficiale n.240 del 15 ottobre 1986, e successive modifiche, utilizzato anche nei modelli per le rilevazioni delle attività gestionali ed economiche delle Aziende unità sanitarie locali.|3|Es. 010|

Nella tabella seguente sono indicati i dettagli dei campi di business del tracciato di input atteso.	
|SDK\_HLD\_DISPOVIG\_Dispovigilance\_v5.0.docx||Pag. 110 di 113|
| :- | :- | -: |

||<p>**PROGETTO SDK**</p><p>Specifiche funzionali</p>||
| :- | :-: | -: |
||||



|`		`**Posizione nel File**|**Nome elemento padre**|**Nome campo**|**Key**|**Descrizione**|**Tipo** |**Obbligatorietà**|**Informazioni di Dominio**|**Lunghezza campo**|**Tracciato di Output**|
| :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
|0|rapporto|codRapportoWeb|KEY|Codice univoco identificativo del rapporto|AN|OBB|I primi 6 caratteri devono corrispondere al codice della struttura competente dove si è verificato l'indicente; i restanti caratteri per identificare univocamente il rapporto operatore|30|Segnalazione Incidente|
|1|rapporto\_luogoEpisodio|numRapporto| |Identifica la segnalazione nella struttura sanitaria in cui si è verificato l'incidente|AN|OBB|Testo libero per indicare la segnalazione nella struttura|120|Segnalazione Incidente|
|2|rapporto\_luogoEpisodio|rapportoRelativoA| |Stringa statica|AN|OBB|Valori Ammessi:<br>Incidente|10|Segnalazione Incidente|
|3|rapporto\_luogoEpisodio|codiceStruttura| |Indica il codice della struttura in cui si è verificato l'incidente|AN|OBB|Valore censito sulle anagrafiche dei "Luoghi incidente" fornite dal MdS.|8|Segnalazione Incidente|
|4|rapporto\_luogoEpisodio|nomeStruttura| |Indica la denominazione della struttura in cui si è verificato l'incidente|AN|OBB|Valore censito sulle anagrafiche dei "Luoghi incidente" fornite dal MdS.|40|Segnalazione Incidente|
|5|rapporto\_luogoEpisodio|annoValiditaStruttTerrit| |Anno di validità della descrizione della struttura|N|FAC|Testo numerico per indicare l'anno|4|Segnalazione Incidente|
|6|rapporto\_luogoEpisodio|repartoStruttura| |Indica il reparto della struttura in cui si è verificato l'incidente|AN|FAC|Testo libero per indicare il reparto della struttura|100|Segnalazione Incidente|
|7|rapporto\_luogoEpisodio|telefono| |Numero di telefono della struttura|AN|NBB|Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato. il campo deve contenere solo numeri.|20|Segnalazione Incidente|
|8|rapporto\_luogoEpisodio|fax| |Numero di fax della struttura|AN|NBB|Almeno uno dei campi Telef.ono, Fax, Email deve essere valorizzato. il campo deve contenere solo numeri.|20|Segnalazione Incidente|
|9|rapporto\_luogoEpisodio|email| |Indirizzo email della struttura|AN|NBB|Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato.<br>Il campo deve essere un indirizzo email valido.|60|Segnalazione Incidente|
|10|rapporto\_luogoEpisodio|dataEpisodio| |Data in cui si è verificato l'incidente|D|OBB|Formato: GG/MM/AAAA<br>La data non può essere superiore alla data odierna|10|Segnalazione Incidente|
|11|rapporto\_luogoEpisodio|codAziendaOspLocale| |Indica il codice della struttura competente per territorio|AN|OBB|Valore censito sulle anagrafiche delle "Strutture competenti" fornite dal MdS, associato alla struttura dove è avvenuto l'incidente.|6|Segnalazione Incidente|
|12|rapporto\_luogoEpisodio|aziendaOspLocale| |Indica la denominazione della struttura competente per territorio|AN|OBB|Valore censito sulle anagrafiche delle "Strutture competenti" fornite dal MdS, associato alla struttura dove è avvenuto l'incidente.|40|Segnalazione Incidente|
|13|rapporto\_luogoEpisodio|nomeOperatore| |Indica il nome di chi ha rilevato l'incidente|AN|OBB|Testo libero per indicare il nome dell'operatore|50|Segnalazione Incidente|
|14|rapporto\_luogoEpisodio|cognomeOperatore| |Indica il cognome di chi ha rilevato l'incidente|AN|OBB|Testo libero per indicare il cognome dell'operatore|50|Segnalazione Incidente|
|15|rapporto\_luogoEpisodio|qualificaOperatore| |Indica la qualifica di chi ha rilevato l'incidente|AN|OBB|Testo libero per indicare la qualifica dell'operatore|100|Segnalazione Incidente|
|16|rapporto\_luogoEpisodio|nomeResponsabile| |Indica il nome del responsabile della vigilanza|AN|OBB|Testo libero per indicare il nome del responsabile|50|Segnalazione Incidente|
|17|rapporto\_luogoEpisodio|cognomeResponsabile| |Indica il cognome del responsabile della vigilanza|AN|OBB|Testo libero per indicare il cognome del responsabile|50|Segnalazione Incidente|
|18|rapporto\_dispositivo|tipoDispositivo| |Indica la tipologia del dispositivo coinvolto nell'incidente|AN|OBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore presente sull'anagrafica dispositivi fornita dal MdS; se non è valorizzato i valori Ammessi sono:<br>MDD = Di Classe<br>IDV = In vitro|3|Segnalazione Incidente|
|19|rapporto\_dispositivo|codiceDispositivo| |Indica il numero di repertorio del dispositivo|N|FAC|Valore censito sull'anagrafica dei dispositivi fornita dal MdS.|10|Segnalazione Incidente|
|20|rapporto\_dispositivo|nomeDispositivo| |Indica il nome commerciale del dispositivo|AN|OBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore presente sull'anagrafica dispositivi fornita dal MdS; se non è valorizzato <CodiceDispositivo> il campo può contenere qualsiasi testo.|200|Segnalazione Incidente|
|21|rapporto\_dispositivo|modelloDispositivo| |Indica il modello del dispositivo|AN|NBB|Obbligatorio solo se <tipoDispositivo>=IVD. Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore presente sull'anagrafica dispositivi fornita dal MdS; se non è valorizzato <CodiceDispositivo> il campo può contenere qualsiasi testo.|300|Segnalazione Incidente|
|22|rapporto\_dispositivo|codiceFabbrDispositivo| |Indica il numero di codice del dispositivo|AN|OBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore presente sull'anagrafica dispositivi fornita dal MdS; se non è valorizzato <CodiceDispositivo> il campo può contenere qualsiasi testo.|300|Segnalazione Incidente|
|23|rapporto\_dispositivo|numeroLotto| |Indica il numero di lotto o di serie del dispositivo|AN|FAC|Testo libero per indicare il numero del lotto|300|Segnalazione Incidente|
|24|rapporto\_dispositivo|dataScadenza| |Indica la data di scadenza del dispositivo|AN|FAC|Formato: GG/MM/AAAA|10|Segnalazione Incidente|
|25|rapporto\_dispositivo|CND| |Codice Classificazione CND del dispositivo|AN|OBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore presente sull'anagrafica dispositivi fornita dal MdS; se non è valorizzato <CodiceDispositivo> il campo deve essere censito sull'anagrafica CND fornita dal MdS.|20|Segnalazione Incidente|
|26|rapporto\_dispositivo|desCND| |Descrizione Classificazione CND del dispositivo|AN|OBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore corrispondente al codice presente sull'anagrafica dispositivi fornita dal MdS; se non è valorizzato <CodiceDispositivo> il campo deve essere censito sull'anagrafica CND fornita dal MdS e coerente con il campo <CND>.|700|Segnalazione Incidente|
|27|rapporto\_dispositivo|GMDN| |Codice Classificazione GMDN del dispositivo|AN|FAC|Lasciare vuoto|20|Segnalazione Incidente|
|28|rapporto\_dispositivo|desGMDN| |Descrizione Classificazione GMDN del dispositivo|AN|FAC|Lasciare vuoto|4000|Segnalazione Incidente|
|29|rapporto\_dispositivo|EDMA| |Codice Classificazione EDMA del dispositivo|AN|FAC|Lasciare vuoto|30|Segnalazione Incidente|
|30|rapporto\_dispositivo|desEDMA| |Descrizione Classificazione EDMA del dispositivo|AN|FAC|Lasciare vuoto|700|Segnalazione Incidente|
|31|rapporto\_dispositivo|tipoIVD| |Indica il codice della classe del dispositivo|AN|OBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore presente sull'anagrafica dispositivi fornita dal MdS; se non è valorizzato <CodiceDispositivo> il campo deve essere selezionato tra i valori ammessi e presenti nella colonna "Codice Classe" della tabella “Valori dominio classe”|10|Segnalazione Incidente|
|32|rapporto\_dispositivo|descTipoIVD| |Indica la descrizione della classe del dispositivo|AN|OBB|[Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore presente sull'anagrafica dispositivi fornita dal MdS; se non è valorizzato <CodiceDispositivo> il campo deve essere selezionato tra i valori ammessi e presenti nella colonna "Descrizione Classe" della tabella “Valori dominio classe”](#Valori_Dominio_Classe!B1)|100|Segnalazione Incidente|
|33|rapporto\_dispositivo|valutazionePrestazioni| | |AN|FAC|Lasciare vuoto| |Segnalazione Incidente|
|34|rapporto\_dispositivo|utilizzo| |Indica se il dispositivo è monouso o pluriuso|AN|FAC|Valori Ammessi:<br>M = Monouso<br>P = Pluriuso|1|Segnalazione Incidente|
|35|rapporto\_dispositivo|versioneSoftware| |Indica la versione del software del dispositivo|AN|FAC|Ammesso solo, facoltativamente, se <TipoDispositivo> = IVD|100|Segnalazione Incidente|
|36|rapporto\_dispositivo|denominazioneFabbricante| |Indica la denominazione del fabbricante del dispositivo|AN|OBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo può contenere qualsiasi testo.|100|Segnalazione Incidente|
|37|rapporto\_dispositivo|nazioneFabbricante| |Indica il codice della nazione del fabbricante del dispositivo|AN|OBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica nazione. La codifica da utilizzare è quella Alpha2 (a due lettere) prevista dalla normativa ISO 3166-2.|2|Segnalazione Incidente|
|38|rapporto\_dispositivo|descNazioneFabbricante| |Indica la nazione del fabbricante del dispositivo|AN|OBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica nazione coerente con <nazioneFabbricante>.|30|Segnalazione Incidente|
|39|rapporto\_dispositivo|regioneFabbricante| |Indica il codice della regione del fabbricante del dispositivo|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica regioni e deve essere coerente con la descrizione regione. Da valorizzare solo in caso di fabbricante italiano.|3|Segnalazione Incidente|
|40|rapporto\_dispositivo|descRegioneFabbricante| |Indica la regione del fabbricante del dispositivo|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica regioni e deve essere coerente con il codice regione. Da valorizzare solo in caso di fabbricante italiano.|40|Segnalazione Incidente|
|41|rapporto\_dispositivo|provinciaFabbricante| |Indica il codice della provincia del fabbricante del dispositivo|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica province e deve essere coerente con la regione e con la descrizione della provincia. Da valorizzare solo in caso di fabbricante italiano.|3|Segnalazione Incidente|
|42|rapporto\_dispositivo|descProvinciaFabbricante| |Indica la provincia del fabbricante del dispositivo|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica province e deve essere coerente con la regione e con il codice della provincia. Da valorizzare solo in caso di fabbricante italiano.|20|Segnalazione Incidente|
|43|rapporto\_dispositivo|comuneFabbricante| |Indica il codice del comune del fabbricante del dispositivo|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica comuni e deve essere coerente con la provincia e con la descrizione del comune. Da valorizzare solo in caso di fabbricante italiano.|3|Segnalazione Incidente|
|44|rapporto\_dispositivo|descComuneFabbricante| |Indica il comune del fabbricante del dispositivo|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica comuni e deve essere coerente con la provincia e con il codice del comune. Da valorizzare solo in caso di fabbricante italiano.|50|Segnalazione Incidente|
|45|rapporto\_dispositivo|localitaFabbricante| |Indica la località dove si trova il fabbricante|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo può contenere qualsiasi testo. Da valorizzare solo in caso di fabbricante estero.|100|Segnalazione Incidente|
|46|rapporto\_dispositivo|indirizzoFabbricante| |Indirizzo del fabbricante|AN|OBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo può contenere qualsiasi testo.|100|Segnalazione Incidente|
|47|rapporto\_dispositivo|denominazioneMandatario| |Indica la denominazione del mandatario del dispositivo|AN|NBB|Valorizzare obbligatoriamente solo se la Nazione del fabbricante è extra UE. Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo può contenere qualsiasi testo|100|Segnalazione Incidente|
|48|rapporto\_dispositivo|nazioneMandatario| |Indica la nazione del mandatario del dispositivo|AN|NBB|Valorizzare obbligatoriamente solo se la Nazione del fabbricante è extra UE. Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica nazione. La codifica da utilizzare è quella Alpha2 (a due lettere) prevista dalla normativa ISO 3166-2.|2|Segnalazione Incidente|
|49|rapporto\_dispositivo|descNazioneMandatario| |Indica la nazione del mandatario del dispositivo|AN|NBB|Valorizzare obbligatoriamente solo se la Nazione del fabbricante è extra UE. Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica nazione.|30|Segnalazione Incidente|
|50|rapporto\_dispositivo|regioneMandatario| |Indica il codice della regione del mandatario del dispositivo|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica regioni e deve essere coerente con la descrizione regione. Da valorizzare solo in caso di mandatario italiano.|3|Segnalazione Incidente|
|51|rapporto\_dispositivo|descRegioneMandatario| |Indica la regione del mandatario del dispositivo|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica regioni e deve essere coerente con il codice regione. Da valorizzare solo in caso di mandatario italiano.|40|Segnalazione Incidente|
|52|rapporto\_dispositivo|provinciaMandatario| |Indica il codice della provincia del mandatario del dispositivo|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica province e deve essere coerente con la regione e con la descrizione della provincia. Da valorizzare solo in caso di mandatario italiano.|3|Segnalazione Incidente|
|53|rapporto\_dispositivo|descProvinciaMandatario| |Indica la provincia del mandatario del dispositivo|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica province e deve essere coerente con la regione e con il codice della provincia. Da valorizzare solo in caso di mandatario italiano.|20|Segnalazione Incidente|
|54|rapporto\_dispositivo|comuneMandatario| |Indica il codice del comune del mandatario del dispositivo|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica comuni e deve essere coerente con la provincia e con la descrizione del comune. Da valorizzare solo in caso di mandatario italiano.|3|Segnalazione Incidente|
|55|rapporto\_dispositivo|descComuneMandatario| |Indica il comune del mandatario del dispositivo|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica comuni e deve essere coerente con la provincia e con il codice del comune. Da valorizzare solo in caso di mandatario italiano.|50|Segnalazione Incidente|
|56|rapporto\_dispositivo|localitaMandatario| |Indica la località dove si trova il mandatario|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo può contenere qualsiasi testo. Da valorizzare solo in caso di mandatario estero.|100|Segnalazione Incidente|
|57|rapporto\_dispositivo|indirizzoMandatario| |Indirizzo del mandatario|AN|NBB|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo può contenere qualsiasi testo.|100|Segnalazione Incidente|
|58|rapporto\_evento|offeso| |Indica chi è rimasto coinvolto nell'incidente|AN|OBB|Valori Ammessi:<br>P = Paziente<br>O = Operatore|1|Segnalazione Incidente|
|59|rapporto\_evento|etaPaziente| |Se nell'incidente è coinvolto il paziente indica la sua età|N|FAC|Informazioni presenti solo se l'episodio ha coinvolto il paziente|3|Segnalazione Incidente|
|60|rapporto\_evento|inizPaziente| |Se nell'incidente è coinvolto il paziente indica le sue iniziali|AN|FAC|Informazioni presenti solo se l'episodio ha coinvolto il paziente|3|Segnalazione Incidente|
|61|rapporto\_evento|dataImpianto| |Indica la data dell'impianto del dispositivo|D|FAC|Formato: GG/MM/AAAA<br>La data non può essere superiore alla data odierna|10|Segnalazione Incidente|
|62|rapporto\_evento|isDispUtilizzato| |Indica se il dispositivo è stato utilizzato|AN|OBB|Valori Ammessi:<br>Y = Si<br>N = Problema evidenziato prima dell'uso|1|Segnalazione Incidente|
|63|rapporto\_evento|utilizzo| |Indica se il dispositivo è stato utilizzato|N|NBB|Da valorizzare obbligatoriamente solo se il campo <DispUtilizzato> è uguale a Y altrimenti lasciare vuoto.<br>Valori Ammessi:<br>` `1 = Primo utilizzo<br>` `2= Riutilizzo dispositivo monouso<br>` `3 = Riutilizzo dispositivo riutilizzabile<br>` `4 = Revisionato/Rinnovato<br>` `5 = Problema evidenziato prima dell'uso<br>` `6 = Altro|1|Segnalazione Incidente|
|64|rapporto\_evento|descUtilizzo| |Descrizione utilizzo dispositivo|AN|NBB|Da valorizzare obbligatoriamente solo se il campo <DispUtilizzato> è uguale a Y altrimenti lasciare vuoto.<br>Riportare la descrizione corrispondente del campo <Utilizzo>:<br>` `1 = Primo utilizzo<br>` `2= Riutilizzo dispositivo monouso<br>` `3 = Riutilizzo dispositivo riutilizzabile<br>` `4 = Revisionato/Rinnovato<br>` `5 = Problema evidenziato prima dell'uso<br>` `6 = Altro|40|Segnalazione Incidente|
|65|rapporto\_evento|altroUtilizzoDispo| |Descrizione altro utilizzo dispositivo|AN|NBB|Da valorizzare obbligatoriamente solo se il campo <Utilizzo> è uguale a 6 altrimenti lasciare vuoto.<br>Campo a testo libero|40|Segnalazione Incidente|
|66|rapporto\_evento|classeIncidente| |Indica la gravità dell'incidente|N|OBB|Valori Ammessi:<br>` `1 = Decesso<br>` `2 = Inaspettato peggioramento, serio pericolo<br>` `3 = Tutti gli altri incidenti da segnalare|1|Segnalazione Incidente|
|67|rapporto\_evento|descClasseIncidente| |Descrizione classe incidente|AN|OBB|Riportare la descrizione corrispondente del campo <classeIncidente>|100|Segnalazione Incidente|
|68|rapporto\_evento|descrizioneIncidente| |Descrizione dell'incidente|AN|OBB|Testo libero per indicare la descrizione dell'incidente|4000|Segnalazione Incidente|
|69|rapporto\_evento|conseguenza| |Codice delle conseguenze dell'incidente|N|OBB|Valori Ammessi:<br>` `1 = Decesso<br>` `2 = Intervento chirurgico<br>` `3 = Intervento specifico<br>` `4=  Ospedalizzazione<br>` `5 = Prolungamento di stato di malattia dopo dimissione ospedaliera<br>` `6 = Menomazione di una funzione corporea<br>` `7 = Nessuna Conseguenza<br>` `8 = Altro|1|Segnalazione Incidente|
|70|rapporto\_evento|descConseguenza| |Descrizione delle conseguenze dell'incidente|AN|OBB|Riportare la descrizione corrispondente del campo <conseguenza>|100|Segnalazione Incidente|
|71|rapporto\_evento|altraConseguenza| |Descrizione azioni intrapese a seguito dell'incidente|AN|NBB|Obbligatorio solo se Conseguenza dell'incidente è "Altro"|4000|Segnalazione Incidente|
|72|rapporto\_evento|numPezzi| |Indica il numero di dispositivi coinvolti|N|OBB|Testo numerico per indicare il numero dei dispositivi coinvolti|3|Segnalazione Incidente|
|73|rapporto\_evento|disponibilitaDispositivo| |Indica la disponibilità del dispositivo coinvolto nell'incidente|AN|OBB|Valori Ammessi:<br>` `S<br>` `N|1|Segnalazione Incidente|
|74|rapporto\_evento|luogoDisponibilitaDispositivo| |Se il dispositivo è disponibile indica il luogo in cui si trova|AN|NBB|Informazioni obbligatorie solo se il dispositivo è disponibile|4000|Segnalazione Incidente|
|75|rapporto\_evento|azioniIntraprese| |Codici azioni intraprese a seguito dell'incidente|AN|OBB|Il campo può contenere i seguenti codici concatenati, separati dal carattere ";".<br>Valori Ammessi:<br>` `IFD = Informativa al fabbricante/distributore<br>` `IDSDG = Informazione alla Direzione sanitaria/Direzione generale<br>` `CRV = Comunicazione al responsabile della vigilanza<br>` `Altro|20|Segnalazione Incidente|
|76|rapporto\_evento|textAzioniIntraprese| | |AN|OBB|Il campo può contenere le seguenti descrizione concatenate, separate dal carattere ",". Deve essere congruente con <azioniIntraprese><br>Valori Ammessi:<br>Informativa al fabbricante/distributore<br>Informazione alla Direzione sanitaria/Direzione generale<br>Comunicazione al responsabile della vigilanza<br>Altro|200|Segnalazione Incidente|
|77|rapporto\_evento|altreAzioniIntraprese| |Descrizione azioni intraprese a seguito dell'incidente|AN|NBB|Obbligatorio solo se Azioni intraprese dall'operatore è "Altro" altrimenti lasciare vuoto|4000|Segnalazione Incidente|
|78|rapporto\_evento|altreInformazioni| |Ulteriori informazioni relative all'incidente|AN|FAC|Testo libero per indicare le altre informazioni|4000|Segnalazione Incidente|
|79|rapporto\_evento|dataRapporto| |Data della segnalazione|D|OBB|Formato: GG/MM/AAAA<br>Data odierna|10|Segnalazione Incidente|
|80|rapporto\_compilatore|ruolo| |Indica il ruolo del compilatore della segnalazione|N|OBB|Valori Ammessi:<br>1-Legale rappresentante della struttura<br>2-Operatore Sanitario<br>3-Responsabile della vigilanza|1|Segnalazione Incidente|
|81|rapporto\_compilatore|descRuolo| |Descrizione Ruolo|AN| |Descrizione <ruolo>.<br>Valori ammessi: <br>"Legale rappresentante della struttura"<br>"Operatore Sanitario"<br>"Responsabile della vigilanza"|100|Segnalazione Incidente|
|82|rapporto\_compilatore|nome| |Nome del compilatore|AN|OBB|Se ruolo 1 valorizzare con testo libero; se ruolo 2 valorizzare con <nomeOperatore>; se ruolo 3 valorizzare con  <nomeResponsabile>|300|Segnalazione Incidente|
|83|rapporto\_compilatore|cognome| |Cognome del compilatore|AN|OBB|Se ruolo 1 valorizzare con testo libero; se ruolo 2 valorizzare con <cognomeOperatore>; se ruolo 3 valorizzare con  <cognomeResponsabile>|300|Segnalazione Incidente|
|84|rapporto\_compilatore|qualificaCompilatore| |Qualifica del compilatore|AN|OBB|Se ruolo 1 o 3 valorizzare con testo libero; se ruolo 2 valorizzare con <qualificaOperatore>|100|Segnalazione Incidente|
|85|rapporto\_compilatore|struttSanitaria| |Indica la struttura di appartenenza del compilatore|AN|OBB|Se ruolo 1 valore censito sulle anagrafiche dei "Luoghi incidente" fornite dal MdS.; se ruolo 2 o 3 valorizzare con <nomeStruttura>|40|Segnalazione Incidente|
|86|rapporto\_compilatore|codStruttSanitaria| |Indica la struttura di appartenenza del compilatore|AN|OBB|Se ruolo 1 valore censito sulle anagrafiche dei "Luoghi incidente" fornite dal MdS.; se ruolo 2 o 3 valorizzare con <codiceStruttura>|8|Segnalazione Incidente|
|87|rapporto\_compilatore|telefonoCompilatore| |Indica il numero di telefono della struttura|N|NBB|Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato. il campo deve contenere solo numeri.<br>Se ruolo 1 o 3 valorizzare con testo libero; se ruolo 2 valorizzare con  <telefono>|40|Segnalazione Incidente|
|88|rapporto\_compilatore|faxCompilatore| |Indica il numero di fax della struttura|N|NBB|Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato. il campo deve contenere solo numeri.<br>Se ruolo 1 o 3 valorizzare con testo libero; se ruolo 2 valorizzare con  <fax>|40|Segnalazione Incidente|
|89|rapporto\_compilatore|emailCompilatore| |Indica l'indirizzo email della struttura|AN|NBB|Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato. Il campo deve contenere un indirizzo email valido.<br>Se ruolo 1 o 3 valorizzare con testo libero; se ruolo 2 valorizzare con  <email>|60|Segnalazione Incidente|


La colonna Posizione nel file la quale rappresenta l’ordinamento delle colonne del tracciato di input da caricare all’SDK.

La seguente tabella contiene i valori del dominio della classe:


|**Codice Classe**|**Descrizione Classe**|**Descrizione riferimento normativo**|
| :-: | :-: | :-: |
|1|ALL. II ELENCO A|D.L.vo 332/2000|
|2|ALL. II ELENCO B|D.L.vo 332/2000|
|R1|CLASS I|Reg. UE 2017/745|
|R2A|CLASS IIA|Reg. UE 2017/745|
|R2B|CLASS IIB|Reg. UE 2017/745|
|R3|CLASS III|Reg. UE 2017/745|
|RA|Classe A| |
|RB|Classe B| |
|RC|Classe C| |
|RD|Classe D| |
|IM|CLASSE I CON FUNZIONI DI MISURA|D.L.vo 46/97|
|I|CLASSE I NON STERILE E SENZA FUNZIONI DI MISURA|D.L.vo 46/97|
|IS|CLASSE I STERILE|D.L.vo 46/97|
|ISM|CLASSE I STERILE CON FUNZIONI DI MISURA|D.L.vo 46/97|
|IIA|CLASSE IIA|D.L.vo 46/97|
|IIB|CLASSE IIB|D.L.vo 46/97|
|III|CLASSE III|D.L.vo 46/97|
|3|DISPOSITIVI AUTODIAGNOSTICI|D.L.vo 332/2000|
|4|GENERALI|D.L.vo 332/2000|
|IA|IMPIANTABILI ATTIVI|D.L.vo 507/92|
|99I|ND AIMD| |
|99V|ND IVD| |
|99M|ND MDD| |

## ***3.2 Controlli di validazione del dato (business rules)***

Acquisito il dato di input, il Validation Engine di SDK procederà ad implementare i controlli descritti nel file che segue. Al verificarsi anche di un solo errore, tra quelli descritti il record oggetto di controllo sarà inserito tra gli scarti con il codice di errore riportato nella tabella seguente.


|**CAMPO**|**TIPOLOGIA CONTROLLO BR**|**CODICE ERRORE**|**DESCRIZIONE ERRORE**|**DESCRIZIONE ALGORITMO**|**TABELLA ANAGRAFICA**|**CAMPI di COERENZA**|**SCARTI/ANOMALIE**|
| :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
|rapporto\_codRapportoWeb|OBBLIGATORIETA' DEL DATO|1000|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_codRapportoWeb|SINTATTICI|1001|Lunghezza diversa da quella attesa|La lunghezza è diversa da 30 caratteri se valorizzato| | |Scarti|
|rapporto\_luogoEpisodio\_numRapporto|OBBLIGATORIETA' DEL DATO|1002|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_numRapporto|SINTATTICI|1003|Lunghezza diversa da quella attesa|La lunghezza è diversa da 120 caratteri se valorizzato| | |Scarti|
|rapporto\_luogoEpisodio\_rapportoRelativoA|OBBLIGATORIETA' DEL DATO|1004|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_rapportoRelativoA| |1005|Non appartenenza al dominio di riferimento|Valori diversi da quelli ammessi : Incidente| | |Scarti|
|rapporto\_luogoEpisodio\_codiceStruttura|OBBLIGATORIETA' DEL DATO|1006|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_codiceStruttura| |1007|Non appartenenza al dominio di riferimento|Il valore inserito e controllato non è presente in anagrafica |Anagrafica Strutture Competenti| |Scarti|
|rapporto\_luogoEpisodio\_nomeStruttura|OBBLIGATORIETA' DEL DATO|1008|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_nomeStruttura|SINTATTICI|1009|Non appartenenza al dominio di riferimento|Il valore inserito e controllato non è presente in anagrafica o non è coerente con il <CodiceStruttura>|Anagrafica Strutture Competenti| |Scarti|
|rapporto\_luogoEpisodio\_annoValiditaStruttTerrit|SINTATTICI|1010|Lunghezza diversa da quella attesa|La lunghezza è diversa da 4 caratteri se valorizzato| | |Scarti|
|rapporto\_luogoEpisodio\_annoValiditaStruttTerrit|SINTATTICI|1011|Datatype errato|Il campo deve essere numerico| | |Scarti|
|rapporto\_luogoEpisodio\_annoValiditaStruttTerrit| |1012|Non appartenenza al dominio di riferimento|Il valore inserito e controllato non è presente in anagrafica o non è coerente con il <CodiceStruttura>|Anagrafica Strutture Competenti| |Scarti|
|rapporto\_luogoEpisodio\_repartoStruttura|SINTATTICI|1013|Lunghezza diversa da quella attesa|La lunghezza è diversa da 100 caratteri se valorizzato| | |Scarti|
|rapporto\_luogoEpisodio\_telefono|OBBLIGATORIETA' DEL DATO|1014|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Campo non presente o campo presente ma non valorizzato. Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_telefono|SINTATTICI|1015|Lunghezza diversa da quella attesa|La lunghezza è diversa da 20 caratteri se valorizzato| | |Scarti|
|rapporto\_luogoEpisodio\_telefono|SINTATTICI|1016|Datatype errato|Il campo deve contenere solo numeri| | |Scarti|
|rapporto\_luogoEpisodio\_fax|OBBLIGATORIETA' DEL DATO|1017|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Campo non presente o campo presente ma non valorizzato. Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_fax|SINTATTICI|1018|Lunghezza diversa da quella attesa|La lunghezza è diversa da 20 caratteri se valorizzato| | |Scarti|
|rapporto\_luogoEpisodio\_fax|SINTATTICI|1019|Datatype errato|Il campo deve contenere solo numeri| | |Scarti|
|rapporto\_luogoEpisodio\_email|OBBLIGATORIETA' DEL DATO|1020|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Campo non presente o campo presente ma non valorizzato. Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_email|SINTATTICI|1021|Lunghezza diversa da quella attesa|La lunghezza è diversa da 60 caratteri se valorizzato| | |Scarti|
|rapporto\_luogoEpisodio\_email|SINTATTICI|1022|Datatype errato|Il campo deve essere un indirizzo email valido| | |Scarti|
|rapporto\_luogoEpisodio\_dataEpisodio|OBBLIGATORIETA' DEL DATO|1023|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_dataEpisodio|SINTATTICI|1024|Datatype errato|Il campo deve essere valorizzato con il formato data  GG/MM/AAAA| | |Scarti|
|rapporto\_luogoEpisodio\_codAziendaOspLocale|OBBLIGATORIETA' DEL DATO|1025|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_codAziendaOspLocale| |1026|Non appartenenza al dominio di riferimento|Il valore inserito e controllato non è presente in anagrafica |Anagrafica Strutture Competenti| |Scarti|
|rapporto\_luogoEpisodio\_aziendaOspLocale|OBBLIGATORIETA' DEL DATO|1027|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_aziendaOspLocale|SINTATTICI|1028|Lunghezza diversa da quella attesa|La lunghezza è diversa da 40 caratteri se valorizzato| | |Scarti|
|rapporto\_luogoEpisodio\_aziendaOspLocale| |1029|Non appartenenza al dominio di riferimento|Il valore inserito e controllato non è presente in anagrafica o non è coerente con il <CodiceAziendaOspedaliera>|Anagrafica Strutture Competenti| |Scarti|
|rapporto\_luogoEpisodio\_nomeOperatore|OBBLIGATORIETA' DEL DATO|1030|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_nomeOperatore|SINTATTICI|1031|Lunghezza diversa da quella attesa|La lunghezza è diversa da 100 caratteri se valorizzato| | |Scarti|
|rapporto\_luogoEpisodio\_cognomeOperatore|OBBLIGATORIETA' DEL DATO|1032|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_cognomeOperatore|SINTATTICI|1033|Lunghezza diversa da quella attesa|La lunghezza è diversa da 50 caratteri se valorizzato| | |Scarti|
|rapporto\_luogoEpisodio\_qualificaOperatore|OBBLIGATORIETA' DEL DATO|1034|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_qualificaOperatore|SINTATTICI|1035|Lunghezza diversa da quella attesa|La lunghezza è diversa da 100 caratteri se valorizzato| | |Scarti|
|rapporto\_luogoEpisodio\_nomeResponsabile|OBBLIGATORIETA' DEL DATO|1036|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_nomeResponsabile|SINTATTICI|1037|Lunghezza diversa da quella attesa|La lunghezza è diversa da 50 caratteri se valorizzato| | |Scarti|
|rapporto\_luogoEpisodio\_cognomeResponsabile|OBBLIGATORIETA' DEL DATO|1038|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_luogoEpisodio\_cognomeResponsabile|SINTATTICI|1039|Lunghezza diversa da quella attesa|La lunghezza è diversa da 50 caratteri se valorizzato| | |Scarti|
|rapporto\_dispositivo\_tipoDispositivo|OBBLIGATORIETA' DEL DATO|1040|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_dispositivo\_tipoDispositivo|SINTATTICI|1041|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il campo deve essere popolato con il valore presente sull'anagrafica dispositivi fornita dal MdS; se non è valorizzato i valori Ammessi sono:<br>MDD = Di Classe<br>IDV = In vitro| |Anagrafica Dispositivi Medici|Scarti|
|rapporto\_dispositivo\_<CodiceDispositivo>| |1042|Non appartenenza al dominio di riferimento|Il valore inserito non è presente in anagrafica |Anagrafica Dispositivi Medici| |Scarti|
|rapporto\_dispositivo\_nomeDispositivo|OBBLIGATORIETA' DEL DATO|1043|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_dispositivo\_nomeDispositivo|SINTATTICI|1044|Lunghezza diversa da quella attesa|La lunghezza è diversa da 200 caratteri se valorizzato| | |Scarti|
|rapporto\_dispositivo\_nomeDispositivo| |1045|Non appartenenza al dominio di riferimento|Se è valorizzato <<CodiceDispositivo>> il sistema controlla se il valore inserito  è presente in anagrafica  ed è coerente con il <<CodiceDispositivo>>|Anagrafica Dispositivi Medici|Anagrafica Dispositivi Medici|Scarti|
|rapporto\_dispositivo\_modelloDispositivo|OBBLIGATORIETA' DEL DATO|1046|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Obbligatorio solo se TipoDispositivo=IVD. Campo non presente o campo presente ma non valorizzato.| |Tipo Dispositivo|Scarti|
|rapporto\_dispositivo\_modelloDispositivo|SINTATTICI|1047|Lunghezza diversa da quella attesa|La lunghezza è diversa da 300 caratteri se valorizzato| | |Scarti|
|rapporto\_dispositivo\_<CodiceFabbrDispositivo>|OBBLIGATORIETA' DEL DATO|1048|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|` `Il controllo si esegue solo se <<CodiceDispositivo>> è valorizzato. Campo non presente o campo presente ma non valorizzato.| |Codice Dispositivo|Scarti|
|rapporto\_dispositivo\_numeroLotto|SINTATTICI|1050|Lunghezza diversa da quella attesa|La lunghezza è diversa da 300 caratteri se valorizzato| | |Scarti|
|rapporto\_dispositivo\_dataScadenza|SINTATTICI|1051|Datatype errato|Il campo deve essere valorizzato con il formato data  GG/MM/AAAA| | |Scarti|
|rapporto\_dispositivo\_CND|OBBLIGATORIETA' DEL DATO|1052|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_dispositivo\_CND| |1053|Non appartenenza al dominio di riferimento|Se è valorizzato <<CodiceDispositivo>> il sistema controlla se il valore inserito è presente in anagrafica  dispositivi medici; altrimenti verifica se Il valore inserito  è presente in Classificazione CND|Classificazione CND del dispositivo. Anagrafica Dispositivi Medici|Se tipo IVD allora può selezionare solo codici che iniziano con la w<br>Se tipo è di Classe la W non si deve selezionare|Scarti|
|rapporto\_dispositivo\_desCND|OBBLIGATORIETA' DEL DATO|1054|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_dispositivo\_desCND|SINTATTICI|1055|Lunghezza diversa da quella attesa|La lunghezza è diversa da 700 caratteri se valorizzato| | |Scarti|
|rapporto\_dispositivo\_GMDN|SINTATTICI|1057|Lunghezza diversa da quella attesa|Il campo deve essere vuoto| | |Scarti|
|rapporto\_dispositivo\_desGMDN|SINTATTICI|1058|Lunghezza diversa da quella attesa|Il campo deve essere vuoto| | |Scarti|
|rapporto\_dispositivo\_EDMA|SINTATTICI|1059|Lunghezza diversa da quella attesa|Il campo deve essere vuoto| | |Scarti|
|rapporto\_dispositivo\_desEDMA|SINTATTICI|1060|Lunghezza diversa da quella attesa|Il campo deve essere vuoto| | |Scarti|
|rapporto\_dispositivo\_tipoIVD|OBBLIGATORIETA' DEL DATO|1061|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_dispositivo\_tipoIVD|SINTATTICI|1062|Lunghezza diversa da quella attesa|La lunghezza è diversa da 10 caratteri se valorizzato| | |Scarti|
|rapporto\_dispositivo\_tipoIVD| |1063|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore presente sull'anagrafica dispositivi fornita dal MdS; se non è valorizzato <CodiceDispositivo> il campo deve essere censito sull'anagrafica Classi fornita dal MdS.|` `Anagrafica Dispositivi Medici. Anagrafica classi| |Scarti|
|rapporto\_dispositivo\_descTipoIVD|OBBLIGATORIETA' DEL DATO|1064|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_dispositivo\_descTipoIVD|SINTATTICI|1065|Lunghezza diversa da quella attesa|La lunghezza è diversa da 10 caratteri se valorizzato| | |Scarti|
|rapporto\_dispositivo\_valutazionePrestazioni|SINTATTICI|1067|Lunghezza diversa da quella attesa|Il campo deve essere vuoto| | |Scarti|
|rapporto\_dispositivo\_utilizzo|OBBLIGATORIETA' DEL DATO|1068|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_dispositivo\_utilizzo| |1069|Non appartenenza al dominio di riferimento|Valori diversi da quelli ammessi : <br>M = Monouso<br>P = Pluriuso| | |Scarti|
|rapporto\_dispositivo\_versioneSoftware|SINTATTICI|1070|Lunghezza diversa da quella attesa|La lunghezza è diversa da 100 caratteri se valorizzato| | |Scarti|
|rapporto\_dispositivo\_denomi<NazioneFabbricante>|OBBLIGATORIETA' DEL DATO|1071|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_dispositivo\_denomi<NazioneFabbricante>|SINTATTICI|1072|Lunghezza diversa da quella attesa|La lunghezza è diversa da 100 caratteri se valorizzato| | |Scarti|
|rapporto\_dispositivo\_denomi<NazioneFabbricante>| |1073|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se il valore inserito è presente in anagrafica e coerente con   <CodiceFabbrDispositivo>; se non è valorizzato <CodiceDispositivo> deve essere censito su Anagrafica   |Anagrafica Dispositivi Medici<br>Anagrafica Azienda|`  `<CodiceFabbrDispositivo>|Scarti|
|rapporto\_dispositivo\_<NazioneFabbricante>|OBBLIGATORIETA' DEL DATO|1074|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_dispositivo\_<NazioneFabbricante>| |1075|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se il valore inserito è coerente con  <CodiceFabbrDispositivo>; se non è valorizzato <CodiceDispositivo> deve essere censito su Anagrafica Nazione    |Anagrafica Dispositivi Medici. Anagrafica Azienda.<br>Anagrafica Nazione|`  `<CodiceFabbrDispositivo>|Scarti|
|rapporto\_dispositivo\_desc<NazioneFabbricante>|OBBLIGATORIETA' DEL DATO|1076|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_dispositivo\_desc<NazioneFabbricante>|SINTATTICI|1077|Lunghezza diversa da quella attesa|La lunghezza è diversa da 30 caratteri se valorizzato| | |Scarti|
|rapporto\_dispositivo\_desc<NazioneFabbricante>| |1078|Non appartenenza al dominio di riferimento|Il sistema controlla se il dato è coerente con <NazioneFabbricante>|Anagrafica Dispositivi Medici.Anagrafica Azienda.<br>Anagrafica Nazione|` `<NazioneFabbricante>|Scarti|
|rapporto\_dispositivo\_regioneFabbricante|OBBLIGATORIETA' DEL DATO|1079|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|` `Da valorizzare solo in caso di fabbricante italiano.|Anagrafica Dispositivi Medici. Anagrafica Regione.Anagrafica Azienda|Nazione|Scarti|
|rapporto\_dispositivo\_regioneFabbricante| |1080|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al fabbricante del dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica regioni e deve essere coerente con la descrizione regione. |Angrafica Regioni| |Scarti|
|rapporto\_dispositivo\_descRegioneFabbricante|OBBLIGATORIETA' DEL DATO|1081|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare solo in caso di fabbricante italiano.| | |Scarti|
|rapporto\_dispositivo\_descRegioneFabbricante|SINTATTICI|1082|Lunghezza diversa da quella attesa|La lunghezza è diversa da 40 caratteri se valorizzato|Anagrafica Regione. Anagrafica Dispositivi Medici. Anagrafica Azienda|RegioneFabbricante|Scarti|
|rapporto\_dispositivo\_descRegioneFabbricante| |1083|Non appartenenza al dominio di riferimento|Il campo deve contenere un valore censito sull'anagrafica regioni e deve essere coerente con la RegioneFabbricante. |Angrafica Regioni| |Scarti|
|rapporto\_dispositivo\_provinciaFabbricante|OBBLIGATORIETA' DEL DATO|1084|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare solo in caso di fabbricante italiano.|Anagrafica Provincia. Anagrafica Dispositivi Medici. Anagrafica Azienda|Regione|Scarti|
|rapporto\_dispositivo\_provinciaFabbricante| |1085|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al fabbricante del dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica province e deve essere coerente con la regione e con la descrizione della provincia. |Anagrafica Provincia. Anagrafica Dispositivi Medici. Anagrafica Azienda| |Scarti|
|rapporto\_dispositivo\_descProvinciaFabbricante|OBBLIGATORIETA' DEL DATO|1086|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare solo in caso di fabbricante italiano.| | |Scarti|
|rapporto\_dispositivo\_descProvinciaFabbricante| |1087|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al fabbricante del dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica province e deve essere coerente con la regione e con ProvinciaFabbricante. Da valorizzare solo in caso di fabbricante italiano.|Anagrafica Provincia. Anagrafica Dispositivi Medici. Anagrafica Azienda| |Scarti|
|rapporto\_dispositivo\_descProvinciaFabbricante|SINTATTICI|1088|Lunghezza diversa da quella attesa|La lunghezza è diversa da 20 caratteri se valorizzato|Anagrafica Comune. Anagrafica Azienda. Anagrafica Dispositivi Medici.|Provincia|Scarti|
|rapporto\_dispositivo\_comuneFabbricante|OBBLIGATORIETA' DEL DATO|1089|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|` `Da valorizzare solo in caso di fabbricante italiano.| | |Scarti|
|rapporto\_dispositivo\_comuneFabbricante| |1090|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al al fabbricante del dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica comuni e deve essere coerente con la provincia e con la descrizione del comune. Da valorizzare solo in caso di fabbricante italiano.|Anagrafica Comuni italiani| |Scarti|
|rapporto\_dispositivo\_descComuneFabbricante|OBBLIGATORIETA' DEL DATO|1091|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare solo in caso di fabbricante italiano.|Anagrafica Comune. Anagrafica Dispositivi Medici. Anagrafica Azienda.|Provincia|Scarti|
|rapporto\_dispositivo\_descComuneFabbricante|SINTATTICI|1092|Lunghezza diversa da quella attesa|La lunghezza è diversa da 50 caratteri se valorizzato| | |Scarti|
|rapporto\_dispositivo\_descComuneFabbricante| |1093|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al fabbricante del dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica comuni e deve essere coerente con la provincia e con la descrizione del comune. Da valorizzare solo in caso di fabbricante italiano.|Anagrafica Comuni italiani| |Scarti|
|rapporto\_dispositivo\_localitaFabbricante|OBBLIGATORIETA' DEL DATO|1094|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare solo in caso di fabbricante estero.|Anagrafica Dispositivi Medici.  Anagrafica Azienda.| |Scarti|
|rapporto\_dispositivo\_localitaFabbricante|SINTATTICI|1095|Lunghezza diversa da quella attesa|La lunghezza è diversa da 100 caratteri se valorizzato| | |Scarti|
|rapporto\_dispositivo\_indirizzoFabbricante|SINTATTICI|1097|Lunghezza diversa da quella attesa|La lunghezza è diversa da 100 caratteri se valorizzato| | |Scarti|
|rapporto\_dispositivo\_denomi<<NazioneMandatario>>|OBBLIGATORIETA' DEL DATO|1099|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Valorizzare obbligatoriamente solo se la Nazione del fabbricante è extra UE. |Anagrafica Dispositivi Medici<br>Anagrafica Azienda| |Scarti|
|rapporto\_dispositivo\_denomi<<NazioneMandatario>>|OBBLIGATORIETA' DEL DATO|1100|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.|Anagrafica Dispositivi Medici. Anagrafica Azienda.<br>Anagrafica Nazione| |Scarti|
|rapporto\_dispositivo\_denomiNazioneMandatario| |1101|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al mandatario del dispositivo; se non è valorizzato <CodiceDispositivo> il campo può contenere qualsiasi testo|Anagrafica Nazioni| |Scarti|
|rapporto\_dispositivo\_<<NazioneMandatario>>|OBBLIGATORIETA' DEL DATO|1102|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Valorizzare obbligatoriamente solo se la Nazione del fabbricante è extra UE. |Anagrafica Dispositivi Medici. Anagrafica Azienda.<br>Anagrafica Nazione| |Scarti|
|rapporto\_dispositivo\_<<NazioneMandatario>>|OBBLIGATORIETA' DEL DATO|1103|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.|Anagrafica Dispositivi Medici. Anagrafica Azienda.<br>Anagrafica Nazione| |Scarti|
|rapporto\_dispositivo\_NazioneMandatario| |1104|Non appartenenza al dominio di riferimento|Valorizzare obbligatoriamente solo se la Nazione del fabbricante è extra UE. Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al mandatario del dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica nazione. La codifica da utilizzare è quella Alpha2 (a due lettere) prevista dalla normativa ISO 3166-2.|Anagrafica Nazioni| |Scarti|
|rapporto\_dispositivo\_desc<<NazioneMandatario>>|OBBLIGATORIETA' DEL DATO|1105|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Valorizzare obbligatoriamente solo se la Nazione del fabbricante è extra UE.|Anagrafica Dispositivi Medici. Anagrafica Azienda.<br>Anagrafica Nazione| |Scarti|
|rapporto\_dispositivo\_desc<<NazioneMandatario>>|SINTATTICI|1106|Lunghezza diversa da quella attesa|La lunghezza è diversa da 30 caratteri se valorizzato|Anagrafica Dispositivi Medici. Anagrafica Azienda.<br>Anagrafica Nazione| |Scarti|
|rapporto\_dispositivo\_descNazioneMandatario| |1107|Non appartenenza al dominio di riferimento|Valorizzare obbligatoriamente solo se la Nazione del fabbricante è extra UE. Il sistema controlla se il dato è coerente con <<NazioneMandatario>>.|Anagrafica Nazioni| |Scarti|
|rapporto\_dispositivo\_regioneMandatario|OBBLIGATORIETA' DEL DATO|1108|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare solo in caso di mandatario italiano e solo se la Nazione del fabbricante è extra UE.|Anagrafica Regione|Nazione|Scarti|
|rapporto\_dispositivo\_regioneMandatario| |1109|Non appartenenza al dominio di riferimento|Il valore inserito e controllato non è presente in anagrafica |Anagrafica Regioni| |Scarti|
|rapporto\_dispositivo\_regioneMandatario| |1110|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al amndatario del dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica regioni e deve essere coerente con la descrizione regione. Da valorizzare solo in caso di mandatario italiano.|Anagrafica Regioni| |Scarti|
|rapporto\_dispositivo\_descRegioneMandatario|OBBLIGATORIETA' DEL DATO|1111|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare solo in caso di mandatario italiano e solo se la Nazione del fabbricante è extra UE.| | |Scarti|
|rapporto\_dispositivo\_descRegioneMandatario|SINTATTICI|1112|Lunghezza diversa da quella attesa|La lunghezza è diversa da 40 caratteri se valorizzato|Anagrafica Dispositivi Medici. Anagrafica Azienda.<br>Anagrafica Nazione| |Scarti|
|rapporto\_dispositivo\_descRegioneMandatario| |1113|Non appartenenza al dominio di riferimento|Valorizzare obbligatoriamente solo se la Nazione del fabbricante è extra UE. Il sistema controlla se il dato è coerente con <<NazioneMandatario>>. Da valorizzare solo in caso di mandatario italiano.|Anagrafica Regioni| |Scarti|
|rapporto\_dispositivo\_provinciaMandatario|OBBLIGATORIETA' DEL DATO|1114|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare solo in caso di mandatario italiano e solo se la Nazione del fabbricante è extra UE.| | |Scarti|
|rapporto\_dispositivo\_provinciaMandatario| |1115|Non appartenenza al dominio di riferimento|Valorizzare obbligatoriamente solo se la Nazione del fabbricante è extra UE. Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica province e deve essere coerente con la regione e con la descrizione della provincia. Da valorizzare solo in caso di mandatario italiano.|Anagrafica Province| |Scarti|
|rapporto\_dispositivo\_descProvinciaMandatario|OBBLIGATORIETA' DEL DATO|1116|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare solo in caso di mandatario italiano e solo se la Nazione del fabbricante è extra UE.| | |Scarti|
|rapporto\_dispositivo\_descProvinciaMandatario|SINTATTICI|1117|Lunghezza diversa da quella attesa|La lunghezza è diversa da 20 caratteri se valorizzato|Anagrafica Dispositivi Medici. Anagrafica Azienda.<br>Anagrafica Nazione| |Scarti|
|rapporto\_dispositivo\_descProvinciaMandatario| |1118|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica province e deve essere coerente con la regione e con il codice della provincia. |Anagrafica Province|Provincia|Scarti|
|rapporto\_dispositivo\_comuneMandatario|OBBLIGATORIETA' DEL DATO|1119|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare solo in caso di mandatario italiano e solo se la Nazione del fabbricante è extra UE.|Anagrafica Dispositivi Medici. Anagrafica Azienda.<br>Anagrafica Nazione| |Scarti|
|rapporto\_dispositivo\_comuneMandatario| |1120|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il campo è popolato con il valore censito sulle anagrafiche fornite dal MdS, associato al dispositivo; se non è valorizzato <CodiceDispositivo> il campo deve contenere un valore censito sull'anagrafica comuni e deve essere coerente con la provincia e con la descrizione del comune. Da valorizzare solo in caso di mandatario italiano.<br>Il valore inserito e controllato non è presente in anagrafica |AnagraficaComuni italiani| |Scarti|
|rapporto\_dispositivo\_descComuneMandatario|OBBLIGATORIETA' DEL DATO|1121|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare solo in caso di mandatario italiano e solo se la Nazione del fabbricante è extra UE.| | |Scarti|
|rapporto\_dispositivo\_descComuneMandatario|SINTATTICI|1122|Lunghezza diversa da quella attesa|La lunghezza è diversa da 50 caratteri se valorizzato|Anagrafica Dispositivi Medici. Anagrafica Azienda.<br>Anagrafica Nazione| |Scarti|
|rapporto\_dispositivo\_descComuneMandatario| |1123|Non appartenenza al dominio di riferimento|Valorizzare obbligatoriamente solo se la Nazione del fabbricante è extra UE. Il sistema controlla se il dato è coerente con <<NazioneMandatario>>.  Da valorizzare solo in caso di mandatario italiano.|Anagrafica Comuni italiani| |Scarti|
|rapporto\_dispositivo\_localitaMandatario|OBBLIGATORIETA' DEL DATO|1124|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Valorizzare obbligatoriamente solo se la Nazione del fabbricante è extra UE e solo in caso di mandatario estero.| | |Scarti|
|rapporto\_dispositivo\_localitaMandatario|SINTATTICI|1125|Lunghezza diversa da quella attesa|La lunghezza è diversa da 100 caratteri se valorizzato|Anagrafica Dispositivi Medici. Anagrafica Azienda.<br>Anagrafica Nazione| |Scarti|
|rapporto\_dispositivo\_indirizzoMandatario|OBBLIGATORIETA' DEL DATO|1127|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Valorizzare obbligatoriamente solo se la Nazione del fabbricante è extra UE| | |Scarti|
|rapporto\_dispositivo\_indirizzoMandatario|SINTATTICI|1128|Lunghezza diversa da quella attesa|La lunghezza è diversa da 100 caratteri se valorizzato| | |Scarti|
|rapporto\_evento\_offeso|OBBLIGATORIETA' DEL DATO|1130|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_evento\_offeso| |1131|Non appartenenza al dominio di riferimento|Valori diversi da quelli ammessi : <br>P = Paziente<br>O = Operatore| | |Scarti|
|rapporto\_evento\_etaPaziente|SINTATTICI|1132|Lunghezza diversa da quella attesa|La lunghezza è diversa da 3 caratteri se valorizzato| | |Scarti|
|rapporto\_evento\_etaPaziente|SINTATTICI|1133|Datatype errato|Il campo deve essere numerico| | |Scarti|
|rapporto\_evento\_inizPaziente|SINTATTICI|1134|Lunghezza diversa da quella attesa|La lunghezza è diversa da 3 caratteri se valorizzato| | |Scarti|
|rapporto\_evento\_dataImpianto|SINTATTICI|1135|Datatype errato|Il campo deve essere valorizzato con il formato data  GG/MM/AAAA| | |Scarti|
|rapporto\_evento\_isDispUtilizzato| |1136|Non appartenenza al dominio di riferimento|Valori diversi da quelli ammessi : <br>` `Y<br>N<br>` `3 = Riutilizzo dispositivo riutilizzabile<br>` `4 = Revisionato/Rinnovato<br>6 =  Altro<br>` `5 = Problema evidenziato prima dell'uso| | |Scarti|
|rapporto\_dispositivo\_utilizzo|OBBLIGATORIETA' DEL DATO|1137|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare obbligatoriamente solo se il campo <DispUtilizzato> è uguale a Y altrimenti lasciare vuoto.| | |Scarti|
|rapporto\_dispositivo\_utilizzo| |1138|Non appartenenza al dominio di riferimento|Valori diversi da quelli ammessi : <br>` `1 = Primo utilizzo<br>` `2= Riutilizzo dispositivo monouso<br>` `3 = Riutilizzo dispositivo riutilizzabile<br>` `4 = Revisionato/Rinnovato<br>6 =  Altro<br>` `5 = Problema evidenziato prima dell'uso| | |Scarti|
|rapporto\_evento\_descUtilizzo|OBBLIGATORIETA' DEL DATO|1139|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare obbligatoriamente solo se il campo <DispUtilizzato> è uguale a Y altrimenti lasciare vuoto.| | |Scarti|
|rapporto\_evento\_descUtilizzo|SINTATTICI|1140|Lunghezza diversa da quella attesa|La lunghezza è diversa da 40 caratteri se valorizzato| | |Scarti|
|rapporto\_evento\_altroUtilizzoDispo|SINTATTICI|1141|Lunghezza diversa da quella attesa|La lunghezza è diversa da 40 caratteri se valorizzato| | |Scarti|
|rapporto\_evento\_altroUtilizzoDispo|OBBLIGATORIETA' DEL DATO|1142|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare obbligatoriamente solo se il campo <DispUtilizzato> è uguale a Y e <utilizzo> = 6 altrimenti lasciare vuoto.| | |Scarti|
|rapporto\_evento\_classeIncidente|OBBLIGATORIETA' DEL DATO|1143|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_evento\_classeIncidente| |1144|Non appartenenza al dominio di riferimento|Valori diversi da quelli ammessi : RE| | |Scarti|
|rapporto\_evento\_descClasseIncidente|SINTATTICI|1145|Lunghezza diversa da quella attesa|La lunghezza è diversa da 4000 caratteri se valorizzato| | |Scarti|
|rapporto\_evento\_descrizioneIncidente|OBBLIGATORIETA' DEL DATO|1146|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_evento\_descrizioneIncidente|SINTATTICI|1147|Lunghezza diversa da quella attesa|La lunghezza è diversa da 4000 caratteri se valorizzato| | |Scarti|
|rapporto\_evento\_conseguenza|OBBLIGATORIETA' DEL DATO|1148|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_evento\_conseguenza| |1149|Non appartenenza al dominio di riferimento|Valori diversi da quelli ammessi <br>` `1 = Decesso<br>` `2 = Intervento chirurgico<br>` `3 = Intervento specifico<br>` `4=  Ospedalizzazione<br>` `5 = Prolungamento di stato di malattia dopo dimissione ospedalier<br>` `6 = Menomazione di una funzione corporea<br>` `7 = Nessuna Conseguenza<br>` `Altro| | |Scarti|
|rapporto\_evento\_descConseguenza|SINTATTICI|1150|Lunghezza diversa da quella attesa|La lunghezza è diversa da 100 caratteri se valorizzato| | |Scarti|
|rapporto\_evento\_altraConseguenza|OBBLIGATORIETA' DEL DATO|1151|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare obbligatoriamente solo se il campo <conseguenza> è uguale a 8, altrimenti deve essere vuoto | | |Scarti|
|rapporto\_evento\_altraConseguenza|SINTATTICI|1152|Lunghezza diversa da quella attesa|La lunghezza è diversa da 4000 caratteri se valorizzato| | |Scarti|
|rapporto\_evento\_numPezzi|OBBLIGATORIETA' DEL DATO|1153|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_evento\_numPezzi|SINTATTICI|1154|Datatype errato|Il campo deve essere numerico| | |Scarti|
|rapporto\_evento\_disponibilitaDispositivo|OBBLIGATORIETA' DEL DATO|1155|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_evento\_disponibilitaDispositivo| |1156|Non appartenenza al dominio di riferimento|Valori diversi da quelli ammessi : <br>` `S<br>` `N| | |Scarti|
|rapporto\_evento\_luogoDisponibilitaDispositivo|OBBLIGATORIETA' DEL DATO|1157|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Da valorizzare obbligatoriamente solo se il campo <disponibilitaDispositivo> = Y , altrimenti deve essere vuoto | | |Scarti|
|rapporto\_evento\_luogoDisponibilitaDispositivo|SINTATTICI|1158|Lunghezza diversa da quella attesa|La lunghezza è diversa da 4000 caratteri se valorizzato| | |Scarti|
|rapporto\_evento\_azioniIntraprese|OBBLIGATORIETA' DEL DATO|1159|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_evento\_azioniIntraprese| |1160|Non appartenenza al dominio di riferimento|Valori diversi da quelli ammessi : <br>` `IFD = Informativa al fabbricante/distributore<br>` `IDSDG = Informazione alla Direzione sanitaria/Direzione generale<br>` `CRV = Comunicazione al responsabile della vigilanza<br>` `Altro| | |Scarti|
|rapporto\_evento\_textAzioniIntraprese|SINTATTICI|1161|Lunghezza diversa da quella attesa|La lunghezza è diversa da 14 caratteri se valorizzato| | |Scarti|
|rapporto\_evento\_altreAzioniIntraprese|OBBLIGATORIETA' DEL DATO|1162|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Obbligatorio solo se Azioni intraprese dall'operatore è "Altro" altrimenti lasciare vuoto| | |Scarti|
|rapporto\_evento\_altreInformazioni|SINTATTICI|1164|Lunghezza diversa da quella attesa|La lunghezza è diversa da 4000 caratteri se valorizzato| | |Scarti|
|rapporto\_evento\_dataRapporto|OBBLIGATORIETA' DEL DATO|1165|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_compilatore\_ruolo|OBBLIGATORIETA' DEL DATO|1166|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_compilatore\_ruolo| |1167|Non appartenenza al dominio di riferimento|Valori diversi da quelli ammessi : <br>1-Legale rappresentante della struttura<br>2-Operatore Sanitario<br>3-Responsabile della vigilanza| | |Scarti|
|rapporto\_compilatore\_descRuolo|SINTATTICI|1168|Lunghezza diversa da quella attesa|La lunghezza è diversa da 100 caratteri se valorizzato| | |Scarti|
|rapporto\_compilatore\_nome|OBBLIGATORIETA' DEL DATO|1169|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_compilatore\_nome|SINTATTICI|1170|Lunghezza diversa da quella attesa|La lunghezza è diversa da 300 caratteri se valorizzato| | |Scarti|
|rapporto\_compilatore\_nome| |1171|Non appartenenza al dominio di riferimento|Se ruolo = 1 valorizzare con testo libero; se ruolo = 2 valorizzare con <nomeOperatore>; se ruolo = 3 valorizzare con  <nomeResponsabile>| | |Scarti|
|rapporto\_compilatore\_cognome|OBBLIGATORIETA' DEL DATO|1172|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_compilatore\_cognome|SINTATTICI|1173|Lunghezza diversa da quella attesa|La lunghezza è diversa da 300 caratteri se valorizzato| | |Scarti|
|rapporto\_compilatore\_cognome| |1174|Non appartenenza al dominio di riferimento|Se ruolo = 1 valorizzare con testo libero; se ruolo = 2 valorizzare con <cognomeOperatore>; se ruolo = 3 valorizzare con  <cognomeResponsabile>| | |Scarti|
|rapporto\_compilatore\_qualificaCompilatore|OBBLIGATORIETA' DEL DATO|1175|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_compilatore\_qualificaCompilatore|SINTATTICI|1176|Lunghezza diversa da quella attesa|La lunghezza è diversa da 100 caratteri se valorizzato| | |Scarti|
|rapporto\_compilatore\_qualificaCompilatore| |1177|Non appartenenza al dominio di riferimento|Se ruolo = 1 o 3 valorizzare con testo libero; se ruolo = 2 valorizzare con <qualificaOperatore>| | |Scarti|
|rapporto\_compilatore\_struttSanitaria|SINTATTICI|1178|Lunghezza diversa da quella attesa|La lunghezza è diversa da 40 caratteri se valorizzato| | |Scarti|
|rapporto\_compilatore\_struttSanitaria| |1179|Non appartenenza al dominio di riferimento|Se ruolo 1  <nomeStruttura>; se ruolo 2 o 3 valorizzare con <aziendaOspLocale>|Anagrafica Strutture Competenti| |Scarti|
|rapporto\_compilatore\_codStruttSanitaria|OBBLIGATORIETA' DEL DATO|1180|Mancata valorizzazione di un campo obbligatorio|Campo non presente o campo presente ma non valorizzato.| | |Scarti|
|rapporto\_compilatore\_codStruttSanitaria| |1181|Non appartenenza al dominio di riferimento|Se ruolo 1  CodiceStruttura; se ruolo 2 o 3 valorizzare con CodAziendaOspLocale|Anagrafica Strutture Competenti| |Scarti|
|rapporto\_compilatore\_telefonoCompilatore|SINTATTICI|1183|Lunghezza diversa da quella attesa|La lunghezza è diversa da 40 caratteri se valorizzato| | |Scarti|
|rapporto\_compilatore\_telefonoCompilatore|SINTATTICI|1184|Datatype errato|Il campo deve contenere solo numeri| | |Scarti|
|rapporto\_compilatore\_telefonoCompilatore| |1185|Non appartenenza al dominio di riferimento|Se ruolo = 1 o 3 valorizzare con testo libero; se ruolo = 2 valorizzare con  <telefono>| | |Scarti|
|rapporto\_compilatore\_faxCompilatore|OBBLIGATORIETA' DEL DATO|1186|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Campo non presente o campo presente ma non valorizzato. Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato.| | |Scarti|
|rapporto\_compilatore\_faxCompilatore|SINTATTICI|1187|Lunghezza diversa da quella attesa|La lunghezza è diversa da 40 caratteri se valorizzato| | |Scarti|
|rapporto\_compilatore\_faxCompilatore|SINTATTICI|1188|Datatype errato|Il campo deve contenere solo numeri| | |Scarti|
|rapporto\_compilatore\_faxCompilatore| |1189|Non appartenenza al dominio di riferimento|Se ruolo = 1 o 3 valorizzare con testo libero; se ruolo = 2 valorizzare con  <fax>| | |Scarti|
|rapporto\_compilatore\_emailCompilatore|OBBLIGATORIETA' DEL DATO|1190|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Campo non presente o campo presente ma non valorizzato. Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato.| | |Scarti|
|rapporto\_compilatore\_emailCompilatore|SINTATTICI|1191|Lunghezza diversa da quella attesa|La lunghezza è diversa da 60 caratteri se valorizzato| | |Scarti|
|rapporto\_compilatore\_emailCompilatore| |1192|Non appartenenza al dominio di riferimento|Se ruolo = 1 o 3 valorizzare con testo libero; se ruolo = 2 valorizzare con  <emailCompilatore>| | |Scarti|
|rapporto\_luogoEpisodio\_annoValiditaStruttTerrit|OBBLIGATORIETA' DEL DATO|10101|Mancata valorizzazione di un campo obbligatorio|Campo obbligatorio| | |Scarti|
|rapporto\_dispositivo\_denomiNazioneMandatario| |10421|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se i valori di un set dati è presente sull'anagrafica dispositivi fornita dal MdS|Anagrafica Dispositivi Medici| |Scarti|
|rapporto\_dispositivo\_denomiNazioneFabbricante| |10421|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se i valori di un set dati è presente sull'anagrafica dispositivi fornita dal MdS|Anagrafica Dispositivi Medici| |Scarti|
|rapporto\_dispositivo\_tipoIVD| |10421|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se i valori di un set dati è presente sull'anagrafica dispositivi fornita dal MdS|Anagrafica Dispositivi Medici| |Scarti|
|rapporto\_dispositivo\_tipoDispositivo| |10421|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se i valori di un set dati è presente sull'anagrafica dispositivi fornita dal MdS|Anagrafica Dispositivi Medici| |Scarti|
|rapporto\_dispositivo\_nomeDispositivo| |10421|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se i valori di un set dati è presente sull'anagrafica dispositivi fornita dal MdS|Anagrafica Dispositivi Medici| |Scarti|
|rapporto\_dispositivo\_NazioneMandatario| |10421|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se i valori di un set dati è presente sull'anagrafica dispositivi fornita dal MdS|Anagrafica Dispositivi Medici| |Scarti|
|rapporto\_dispositivo\_NazioneFabbricante| |10421|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se i valori di un set dati è presente sull'anagrafica dispositivi fornita dal MdS|Anagrafica Dispositivi Medici| |Scarti|
|rapporto\_dispositivo\_descTipoIVD| |10421|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se i valori di un set dati è presente sull'anagrafica dispositivi fornita dal MdS|Anagrafica Dispositivi Medici| |Scarti|
|rapporto\_dispositivo\_desCND| |10421|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se i valori di un set dati è presente sull'anagrafica dispositivi fornita dal MdS|Anagrafica Dispositivi Medici| |Scarti|
|rapporto\_dispositivo\_CodiceFabbrDispositivo| |10421|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se i valori di un set dati è presente sull'anagrafica dispositivi fornita dal MdS|Anagrafica Dispositivi Medici| |Scarti|
|rapporto\_dispositivo\_CodiceDispositivo| |10421|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se i valori di un set dati è presente sull'anagrafica dispositivi fornita dal MdS|Anagrafica Dispositivi Medici| |Scarti|
|rapporto\_dispositivo\_CND| |10421|Non appartenenza al dominio di riferimento|Se è valorizzato <CodiceDispositivo> il sistema controlla se i valori di un set dati è presente sull'anagrafica dispositivi fornita dal MdS|Anagrafica Dispositivi Medici| |Scarti|
|rapporto\_dispositivo\_regioneFabbricante| |10791|Codice Regione non congruente con la Nazione del Fabbricante|Il campo deve essere valorizzato solo se la nazione è IT| | |Scarti|
|rapporto\_dispositivo\_provinciaFabbricante| |10841|Codice Provincia non congruente con la Nazione del Fabbricante|Il campo deve essere valorizzato solo se la nazione è IT| | |Scarti|
|rapporto\_dispositivo\_regioneMandatario| |11091|Codice Regione non congruente con la Nazione del Mandatario|Il campo deve essere valorizzato solo se la nazione è IT|Anagrafica Regione|Nazione|Scarti|
|rapporto\_dispositivo\_provinciaMandatario| |11141|Codice Provincia non congruente con la Nazione del Mandatario|Il campo deve essere valorizzato solo se la nazione è IT|Anagrafica Provincia|Regione|Scarti|
|rapporto\_evento\_isDispUtilizzato|OBBLIGATORIETA' DEL DATO|11361|Mancata valorizzazione di un campo obbligatorio|Campo obbligatorio| | |Scarti|
|rapporto\_dispositivo\_utilizzo| |11371|Incongruenza tra campo utilizzo e isDispUtilizzo|Se <DispUtilizzato> è diverso da Y e campo è diverso da null| | |Scarti|
|rapporto\_evento\_descClasseIncidente|OBBLIGATORIETA' DEL DATO|11451|Mancata valorizzazione di un campo obbligatorio|Campo obbligatorio| | |Scarti|
|rapporto\_evento\_descConseguenza|OBBLIGATORIETA' DEL DATO|11501|Mancata valorizzazione di un campo obbligatorio|Campo obbligatorio| | |Scarti|
|rapporto\_evento\_textAzioniIntraprese| |11611|Non appartenenza al dominio di riferimento|Il campo può contenere le seguenti descrizione concatenate, separate dal carattere ",". Deve essere congruente con <azioniIntraprese><br>Valori Ammessi:<br>Informativa al fabbricante/distributore<br>Informazione alla Direzione sanitaria/Direzione generale<br>Comunicazione al responsabile della vigilanza<br>Altro| | |Scarti|
|rapporto\_evento\_altreAzioniIntraprese| |11621|Incongruenza tra campo rapporto\_evento\_textAzioniIntraprese e altreAzioniIntraprese|Se <rapporto\_evento\_textAzioniIntraprese> è diverso da "Altro" e campo è diverso da null| | |Scarti|
|rapporto\_compilatore\_struttSanitaria|OBBLIGATORIETA' DEL DATO|11781|Mancata valorizzazione di un campo obbligatorio|Campo obbligatorio| | |Scarti|
|rapporto\_compilatore\_emailCompilatore|SINTATTICI|11911|Datatype errato|Il campo deve essere un indirizzo email valido| | |Scarti|
|rapporto\_compilatore\_telefonoCompilatore|OBBLIGATORIETA' DEL DATO|1182 - 1186 - 1190|Mancata valorizzazione di un campo ad obbligatorietà condizionata.|Campo non presente o campo presente ma non valorizzato. Almeno uno dei campi Telefono, Fax, Email deve essere valorizzato.| | |Scarti|
.




||<p>**PROGETTO SDK**</p><p>Specifiche funzionali</p>||
| :- | :-: | -: |
||||



Le Business Rule transazionali non vengono implementate nell’SDK.



I controlli applicativi saranno implementati a partire dall’acquisizione dei seguenti dati anagrafici disponibili in ambito MdS e retrievati con servizi ad hoc (Service Layer mediante PDI): 

- Anagrafiche Nazione, Regione, Provincia, Comune
- Tabella Codifica Istat
- Anagrafica Luoghi Incidente
- Anagrafica Strutture competenti
- Anagrafica Dispositivi Medici
- Classificazione CND del dispositivo
- Anagrafica Classi incidente
- Anagrafica Aziende

Il dato anagrafico sarà presente sottoforma di tabella composta da tre colonne:

- Valore (in cui è riportato il dato, nel caso di più valori, sarà usato il carattere # come separatore)
- Data inizio validità (rappresenta la data di inizio validità del campo Valore)

- Formato: AAAA-MM-DD
- Notazione inizio validità permanente: **1900-01-01**

- Data Fine Validità (rappresenta la data di fine validità del campo Valore)
  - Formato: AAAA-MM-DD
  - Notazione fine validità permanente: **9999-12-31**

Affinchè le Business Rule che usano il dato anagrafico per effettuare controlli, siano correttamente funzionanti occorre sempre controllare che la data di competenza (**Anno**, **Mese di riferimento** i quali rappresentano anno e mese di erogazione del farmaco) del record su cui si effettua il controllo, sia compreso tra le data di validità.  

Di seguito viene mostrato un caso limite di anagrafica in cui sono presenti delle sovrapposizioni temporali (verde) e contraddizioni di validità permanente/specifico range (in rosso) 


|ID|VALUE|VALID\_FROM|VALID\_TO|
| - | - | - | - |
|1|VALORE 1|1900-01-01|9999-12-31|
|2|VALORE 1|2015-01-01|2015-12-31|
|3|VALORE 1|2018-01-01|2023-12-31|
|4|VALORE 1|2022-01-01|2024-12-31|


Diremo che :  il dato presente sul tracciato di input è valido se e solo se:

∃ VALUE\_R = VALUE\_A “tale che” VALID\_FROM(senza giorno)<= DATA\_COMPETENZA <= VALID\_TO(senza giorno)

(Esiste almeno un valore compreso tra le date di validità)

Dove:

- ` `VALUE\_R (rappresenta i campi del tracciato di input coinvolti nei controlli della specifica BR)

- VALUE\_A (rappresenta i campi dell’anagrafica coinvolti nei controlli della specifica BR)

- VALID\_FROM/VALID\_TO (rapresentano le colonne dell’anagrafica)



## ***3.3 Flussi di Output per alimentazione MdS***

A valle delle verifiche effettuate dal Validation Engine, qualora il caricamento sia stato effettuato con il parametro Finalità Elaborazione impostato a Produzione, verranno inviati verso MdS tutti i record corretti secondo le regole di validazione impostate. 

Verrà richiamata la procedura messa a disposizione dal MdS alla quale verranno passati in input i seguenti parametri:


|**NOME PARAMETRO**|**VALORIZZAZIONE**|
| :- | :- |
|ANNO RIFERIMENTO|Parametro ANNO RIFERIMENTO in input a SDK|
|PERIODO RIFERIMENTO|Parametro PERIODO RIFERIMENTO in input a SDK |
|CATEGORIA FLUSSI|DISP|
|NOME FLUSSO|VIG|
|NOME FILE|Parametro popolato dall’SDK in fase di invio flusso con il nome file generato dal Validation Engine in fase di produzione file.|

Il flusso generato dall’SDK sarà presente sotto la cartella /sdk/xml\_output e dovrà essere salvato e inviato verso MdS rispettando la seguente nomenclatura:

- **SDK\_DISP\_VIG\_{ Periodo di riferimento }\_{ID\_RUN}.xml**

Dove :

- Periodo di Riferimento rappresenta il periodo con il quale viene lanciato l’SDK;
- ID\_RUN rappresenta l’identificativo univoco. 

A valle della presa in carico del dato da parte di MdS, SDK riceverà una response contenente le seguenti informazioni:

1. **codiceFornitura**: stringa numerica indicante l’identificativo univoco della fornitura inviata al servizio
1. **errorCode**: stringa alfanumerica di 256 caratteri rappresentante il codice identificativo dell’errore eventualmente riscontrato
1. **errorText**: stringa alfanumerica di 256 caratteri rappresentante il descrittivo dell’errore eventualmente riscontrato
1. Insieme delle seguenti triple, una per ogni file inviato:
1. **idUpload**: stringa numerica indicante l’identificativo univoco del singolo file ricevuto ed accettato dal MdS, e corrispondente al file inviato con la denominazione riportata nell’elemento “nomeFile” che segue
1. **esito**: stringa alfanumerica di 4 caratteri rappresentante l’esito dell’operazione (Vedi tabella sotto)
1. **nomeFile**: stringa alfanumerica di 256 caratteri rappresentante il nome dei file inviati.

Copia dei file inviati verso MdS il cui esito è positivo (ovvero risposta della procedura Invio Flussi con IF00) saranno trasferiti e memorizzati in una cartella ad hoc di SDK (es. /sdk/sent) rispettando la seguente naming: 

- SDK\_DISP\_VIG\_{Periodo di riferimento}\_{ID\_RUN}.xml

Dove :

- Periodo di Riferimento rappresenta il periodo con il quale viene lanciato l’SDK;
- ID\_RUN rappresenta l’identificativo univoco 

Di seguito la tabella di riepilogo dei codici degli esiti possibili dell’invio del file


|**ESITO**|**DESCRIZIONE**|
| :- | :- |
|AE00|Errore di autenticazione al servizio|
|IF00|Operazione completata con successo|
|IF01|Incongruenza tra CF utente firmatario e cf utente inviante|
|IF02|Firma digitale non valida|
|IF03|Firma digitale scaduta|
|IF04|Estensione non ammessa|
|IF05|Utente non abilitato all’invio per la Categoria Flusso indicata|
|IF06|Utente non abilitato all’invio per il Flusso indicata|
|IF07|Periodo non congurente con la Categoria Flusso indicata|
|IF08|Il file inviato è vuoto|
|IF09|Errore interno al servizio nella ricezione del file|
|IF10|Il numero di allegati segnalati nel body non corrisponde al numero di allegati riscontrati nella request|
|IF11|Il nome dell’allegato riportato nel body non è presente tra gli allegati della request (content-id)|
|IF12|Presenza di nomi file duplicati|
|IF13|Errore interno al servizio nella ricezione del file|
|IF14|Errore interno al servizio nella ricezione del file|
|IF15|Errore interno al servizio nella ricezione del file|
|IF99|Errore generico dell’operation|
## ***3.4 Scarti di processamento***

In una cartella dedicata (es. /sdk/esiti) verrà creato un file json contenente il dettaglio degli scarti riscontrati ad ogni esecuzione del processo SDK. 

Il naming del file sarà:  ESITO\_{ID\_RUN}.json 

Dove:

- ID\_RUN rappresenta l’identificativo univoco dell’elaborazione

Di seguito il tracciato del record da produrre.


|**CAMPO**|**DESCRIZIONE**|
| :- | :- |
|NUMERO RECORD|Numero del record del flusso input|
|RECORD PROCESSATO|Campi esterni rispetto al tracciato, che sono necessari per la validazione dello stesso.|
||Record su cui si è verificato uno scarto, riportato in maniera strutturata (nome\_campo-valore).|
|LISTA ESITI|<p>Lista di oggetti contenente l’esito di validazione per ciascun campo:</p><p>- Campo: nome campo su cui si è verificato uno scarto</p><p>- Valore Scarto: valore del campo su cui si è verificato uno scarto</p><p>- Valore Esito: esito di validazione del particolare campo</p><p>- Errori Validazione: contiene i campi Codice (della Business Rule) e Descrizione (della Business Rule)</p>|
## ***3.5 Informazioni dell’esecuzione***

In una cartella dedicata (es. /sdk/run) verrà creato un file contenente il dettaglio degli esiti riscontrati ad ogni esecuzione del processo SDK.

Il naming del file sarà:  

{ID\_RUN}.json

Dove:

- ID\_RUN rappresenta l’identificativo univoco dell’elaborazione

Di seguito il tracciato del record da produrre.


|**CAMPO**|**DESCRIZIONE**|
| :- | :- |
|ID RUN (chiave)|Identificativo univoco di ogni esecuzione del SDK|
|ID\_CLIENT|Identificativo Univoco della trasazione sorgente che richiede processamento lato SDK|
|ID UPLOAD (chiave)|<p>Identificativo di caricamento fornito da MdS.</p><p>Contiene elenco dei codice identificativi generati dal sistema (DVO).</p>|
|TIPO ELABORAZIONE|F (full)/R (per singolo record) - Impostato di default a F|
|MODALITA’ OPERATIVA|P (=produzione) /T (=test)|
|DATA INIZIO ESECUZIONE|Timestamp dell’ inizio del processamento|
|DATA FINE ESECUZIONE|Timestamp di completamento del processamento|
|STATO ESECUZIONE |<p>Esito dell’esecuzione dell’ SDK. </p><p>Possibili valori: </p><p>- IN ELABORAZIONE: Sdk in esecuzione;</p><p>- ELABORATA: Esecuzione completata con successo;</p><p>- KO: Esecuzione fallita: </p><p>- KO SPECIFICO: Esecuzione fallita per una fase/componente più rilevante della soluzione (es. ko\_gestione\_file, ko\_gestione\_validazione, ko\_invio\_ministero, etc.); </p><p>- KO GENERICO: un errore generico non controllato.</p>|
|FILE ASSOCIATI RUN|nome del file di input elaborato dall’SDK|
|NOME FLUSSO|{DISPOVI}, valore fisso che identifica lo specifico SDK in termini di categoria e nome flusso|
|NUMERO RECORD |Numero di record del flusso input|
|NUMERO RECORD ACCETTATI|Numero validi|
|NUMERO RECORD SCARTATI|Numero scarti|
|VERSION|Versione del SDK (Access Layer e Validation Engine)|
|TIMESTAMP CREAZIONE|Timestamp creazione della info run|
|API (\*DPM)|Rappresenta L’API utilizzata per il flusso DPM (non valorizzata per gli altri flussi)|
|IDENTIFICATIVO SOGGETTO ALIMENTANTE (\*DPM)|Chiave flusso DPM (non valorizzata per gli altri flussi)|
|TIPO ATTO (\*DPM)|Chiave flusso DPM (non valorizzata per gli altri flussi)|
|NUMERO ATTO (\*DPM)|Chiave flusso DPM (non valorizzata per gli altri flussi)|
|TIPO ESITO MDS (\*DPM)|Esito della response dell’API 2 (non valorizzata per gli altri flussi) |
|DATA RICEVUTA MDS (\*DPM)|Data della response dell’API 3 (non valorizzata per gli altri flussi)|
|CODICE REGIONE|Codice Regione del Mittente|
|ANNO RIFERIMENTO|Anno cui si riferiscono i dati del flusso|
|PERIODO DI RIFERIMENTO|Rappresenta il mese di riferimento dei dati del flusso (es. 12)|
|DESCRIZIONE STATO ESECUZIONE |Specifica il messaggio breve dell’errore, maggiori informazioni saranno presenti all’interno del log applicativo|
|NOME FILE OUTPUT MDS|Nome dei file di output inviati verso MdS|
|ESITO ACQUISIZIONE FLUSSO|Codice dell’esito del processo di acquisizione del flusso su MdS. Tale campo riflette la proprietà invioFlussiReturn/listaEsitiUpload/item/esito della response della procedura **invioFlussi**. (Es IF00)|
|CODICE ERRORE INVIO FLUSSI|Codice d’errore della procedura di invio. Tale campo riflette la proprietà InvioFlussiReturn/errorCode della response della procedura **invioFlussi**|
|TESTO ERRORE INVIO FLUSSI|Descrizione codice d’errore della procedura.Tale campo riflette la proprietà InvioFlussiReturn/ errorText della response della procedura **invioFlussi**|

Inoltre, a supporto dell’entità che rappresenta lo stato dell’esecuzione, sotto la cartella /sdk/log, saranno presenti anche i file di log applicativi (aggregati giornalmente) non strutturati, nei quali saranno presenti informazioni aggiuntive, ad esempio lo StackTrace (in caso di errori).

Il naming del file, se non modificata la politica di rolling (impostazioni) sarà: 

SDK \_DISP\_VIG.log.

**NOTA BENE**: 

**Per la trasmissione del flusso Dispovigilance è previsto che il parametro “soglia.invio.mds” nel file di configurazione “/sdk/properties” sia impostato con il valore 100 per permettere uno scarto totale del file di input anche in presenza di un solo record non corretto evitando in questo modo acquisizioni parziali e scongiurando inconsistenze dei dati nel Sistema.** 

## mantainer:
 Accenture SpA until January 2026