

https://github.com/user-attachments/assets/10ad5b8c-c3ee-46ab-89fd-f5a3ef15ac2a

# Hospitality Booking Core

Backend RESTful sviluppato in **Java 21** e **Spring Boot** per la gestione centralizzata di strutture alberghiere, disponibilità camere e ciclo di vita delle prenotazioni su database relazionale **MySQL**.

## 🎥 Live Demo

<!-- TRASCINA QUI IL TUO VIDEO DEMO .MP4 DIRETTAMENTE SU GITHUB -->

## 🏗️ Architettura e Scelte Tecniche

Il progetto adotta un'architettura stratificata (*Layered Architecture*) progettata secondo le best practice enterprise:

* **Persistenza e Relazioni (`model` & `repository`)**: Entità JPA (`Hotel`, `Camera`, `Prenotazione`) collegate tramite relazioni `@ManyToOne` e `@OneToMany`, con query derivate su Spring Data JPA e gestione monetaria tramite `BigDecimal`.
* **Validazione e Contratti API (`dto`)**: Utilizzo di **Java Records** immutabili (`HotelRequestDTO`, `CameraRequestDTO`, `PrenotazioneRequestDTO`, `PrenotazioneResponseDTO`) protetti da **Jakarta Bean Validation** (`@NotBlank`, `@Email`, `@Positive`, `@Future`, `@FutureOrPresent`).
* **Logica di Business e Transazioni (`service`)**: Controllo univocità delle strutture, calcolo dinamico delle notti e del costo totale (`ChronoUnit.DAYS`), generazione di codici prenotazione univoci (`UUID`) e gestione atomica (`@Transactional`) dell'aggiornamento stato camera durante prenotazione e cancellazione.
* **Gestione Centralizzata Errori (`exception`)**: Intercettore globale basato su `@RestControllerAdvice` (`GlobalExceptionHandler`) per trasformare regole di business violate (`BusinessException`) ed errori di validazione in risposte JSON strutturate (`400 Bad Request`).
* **Test Unitari (`test`)**: Test isolati della logica di business con **JUnit 5** e **Mockito** (`Arrange / Act / Assert`).

## 🛠️ Stack Tecnologico

* **Linguaggio:** Java 21
* **Framework:** Spring Boot (Spring Web, Spring Data JPA, Validation)
* **Database:** MySQL 8
* **Testing:** JUnit 5, Mockito
* **Frontend Demo:** HTML5, CSS3, Vanilla JavaScript (`Fetch API`)
* **Build Tool:** Maven

## 📡 Endpoint Principali (REST API)

| Metodo | Endpoint | Descrizione |
| :--- | :--- | :--- |
| `POST` | `/api/hotel` | Registra una nuova struttura alberghiera (Back-Office) |
| `GET` | `/api/hotel` | Restituisce l'elenco delle strutture registrate |
| `POST` | `/api/camere` | Aggiunge una camera a un hotel esistente (Back-Office) |
| `GET` | `/api/camere/disponibili/{hotelId}` | Filtra le camere attualmente libere per una specifica struttura |
| `POST` | `/api/prenotazioni` | Crea una nuova prenotazione, calcola il totale e blocca la camera |
| `GET` | `/api/prenotazioni/{codice}` | Recupera i dettagli di una prenotazione tramite codice ricevuta |
| `PATCH` | `/api/prenotazioni/{codice}/cancella` | Annulla una prenotazione e ripristina la disponibilità della camera |

## 🚀 Come avviare il progetto in locale

1. Clona il repository:
   ```bash
   git clone https://github.com
   ```
2. Crea un database vuoto su MySQL chiamato `hospitality_db`.
3. Configura le tue credenziali locali in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hospitality_db?createDatabaseIfNotExist=true
   spring.datasource.username=TUO_UTENTE
   spring.datasource.password=tua_password_mySql
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Avvia l'applicazione con `./mvnw spring-boot:run` e apri `http://localhost:8080/` nel browser per utilizzare l'interfaccia demo.
