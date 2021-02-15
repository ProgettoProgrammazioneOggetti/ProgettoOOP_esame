# ProgettoOOP_esame:
progetto appello di Febbraio 2021 OOP
- [ProgettoOOP_esame](#progettooop_esame)
- [Start](#start)
- [Autori](#autori)


# Start:
Il progetto richiedeva di creare un API in grado di gestire gli eventi e spettaccoli americani attraverso un'altra API (https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/). Di seguito troverete sia la parte di progettazione dell'API che la spiegazione delle rotte e delle loro funzionalità.

## Progettazione:
La fase di progettazione dell'API si è basata sulla stesura di vari diagrammi atti a semplificare la fase di scrittura del codice. Per questo obiettivo è stato utilizzato l'UML, nello specifico il software StarUML. Di seguito verrano riportati i vari documenti di cui si compone la modellazione del software.
### Use Case Diagram:
![Use Case Diagram](https://github.com/ProgettoProgrammazioneOggetti/ProgettoOOP_esame/blob/main/UseCaseDiagram.png)

### Class Diagram:
![Class Diagram](https://github.com/ProgettoProgrammazioneOggetti/ProgettoOOP_esame/blob/main/ClassDiagram.png)

I vari diagrammi delle sequenza verrano presentati in corrispondenza delle rotte a cui fanno riferimento solo nel caso in cui siano di reale aiuto per una migliore comprensione del progetto.
## Rotte:
Di seguito verrano esplicitate e spiegate le varie rotte messe a disposizione dall'API. Per fare tutto ciò ci siamo appoggiati a vari software quali Eclipse come ambiente di sviluppo, Maven per la gestione di librerie e progetti e Spring Boot come framework per lo sviluppo di applicazioni Java.
### 1) Ricerca di un evento
`GET /search?name=FL&keyword=space,Saturn`

Questa rotta permette di cercare eventi localizzati in uno specifico Stato Americano e tramite delle parole chiave ricercabili all'interno del nome dell'evento e della sua descrizione. I parametri presenti sono i seguenti:
| Parametro | Tipo | Descrizione |
|---------- | ---- | ----------- |
| name      |String| Codice dello stato che si desidera cercare, se assente si prendono in considerazione tutti gli stati|
|keyword    |String|Insieme delle parole chiavi che devono essere presenti. Per una corretta formattazione, non ci devono essere spazi ma devono essere separate da una virgola|

Di seguito si ha il diagramma delle sequenze di questa rotta
![Search Sequence Diagram](https://github.com/ProgettoProgrammazioneOggetti/ProgettoOOP_esame/blob/main/searchSequence.png)

### 2) Ricerca di tutti gli eventi
`POST /events`

Questa rotta permette di cercare tutti gli eventi [filtrandoli](#Filtro) come spiegato successivamente. 
Di seguito si ha il diagramma delle sequenze di questa rotta
![Events Sequence Diagram](https://github.com/ProgettoProgrammazioneOggetti/ProgettoOOP_esame/blob/main/eventsSequence.png)

### 3) Ricerca eventi per genere
`POST /genevents`

Questa rotta permette di cercare tutti gli eventi raggruppandoli per genere e [filtrandoli](#Filtro) come spiegato successivamente. 
Di seguito si ha il diagramma delle sequenze di questa rotta
![Genre Sequence Diagram](https://github.com/ProgettoProgrammazioneOggetti/ProgettoOOP_esame/blob/main/genreSequence.png)

### 4) Calcolo di statistiche riguardo gli eventi
`POST /statistics`

Questa rotta permette di ottenere in output i mesi col maggior numero di eventi, quelli con meno e il valore medio per ogni stato presente nel filtro, [filtrando](#Filtro) il database anche per gli altri campi. 
Di seguito si ha il diagramma delle sequenze di questa rotta
![Statistics Sequence Diagram](https://github.com/ProgettoProgrammazioneOggetti/ProgettoOOP_esame/blob/main/statsSequence.png)

## Database
Nel momento in cui l'API viene attivata, automaticamente il programma genera un database tramite una richiesta all'API esterna e lo salva su un file interno all'API. Di seguito vedremo le rotte inerenti il database:
### 5) Ottenimento del database
Questa rotta permette di ottenere come output della chiamata all'API il database utilizzato dall'applicazione per le rotte 2), 3) e 4).
### 6) Modifica del database
Poichè il database viene salvato in locale, esso può essere liberamente modificato e sostituito dall'utente. Affinché il database sia utilizzabile dall'applicazione e non si incorra in errori continui dovuti alle modifiche, questa rotta permette di controllarne l'idoneità. In output si otterrà o un messaggio che conferma l'idoneità del database o un'errore che resetterà in automatico il database.  
### 7) Reset e aggiornamento del database
Questra rotta permette sia di resettare il database, nel caso in cui si siano fatte delle modifiche che però si vogliono cancellare, sia di aggiornarlo, quindi ottenere eventi inseriti dell'API esterna successivamente all'avvio dell'API.

## Filtro
Per un corretto utilizzo dell'API, il filtro deve essere un JSONObject con al suo interno una struttura simile a quella qui mostrata.
`[
]   `

La seguente tabella spiega i vari filtri applicabili e in quali modi:
| Campo | Tipologia | Contenuto | Descrizione |
| ----- | --------- | --------- | ----------- |
|state|\[$in\]|stateCode degli stati |Permette di filtrare gli eventi prendendo in considerazione solo gli eventi che avvengono negli stati inseriti nel filtro |
|genre|\[$in]|nomi dei generi|Permette di filtrare gli eventi prendendo in considerazione solo gli eventi dei generi inseriti nel filtro|
|keywords|\[$in\]|parole chiavi che devono essere presenti|Permette di filtrare gli eventi prendendo in considerazione solo gli eventi che devono contenere tutte le parole chiavi o nel titolo o nella descrizione|
|date|\[$gte\]|Data iniziale|Permette di filtrare gli eventi prendendo in considerazione solo gli eventi che avvengono nel 2021 dopo la data specificata. Il formato della data deve essere del stringhe del tipo "AAAA:MM:GG"|
|date|\[$bt\]|Data iniziale e data finale| Permette di filtrare gli eventi prendendo in considerazione solo gli eventi che avvengono tra le date inserite. Per un corretto funzionamento del filtro, bisogna prima inserire la data iniziale poi la data finale|

# Autori:
Maurizio Candela

Paolo Cappelletti
