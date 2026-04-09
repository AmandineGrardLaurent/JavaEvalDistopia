# 🎬 Distopia – Application de gestion de cinémas

Distopia est une application web développée avec **Spring Boot (2.5.3)** et **Thymeleaf**, permettant de gérer un réseau de cinémas, leurs films, séances et réservations.

---

# Stack technique

## Backend
- Java 8
- Spring Boot 2.5.3
- Spring MVC
- Spring Data JPA
- Hibernate
- Spring Validation

## Frontend
- Thymeleaf
- Thymeleaf Layout Dialect (Ultraq)
- TailwindCSS

## Base de données
- MariaDB (mariadb-java-client)

## Outils
- Lombok
- Maven

---

# Fonctionnalités

## Partie publique
- Affichage des cinémas
- Affichage des films
- Consultation des séances par film et cinéma
- Recherche de cinéma par mot-clé et filtrage par ville

## Réservations (utilisateur connecté)
- Réservation de places pour une séance
- Association automatique à l’utilisateur connecté
- Choix du nombre de places
- Historique des réservations

## Administration
- Gestion des films (CRUD)
- Gestion des cinémas (CRUD)
- Gestion des villes (CRUD)
- Gestion des séances (CRUD) (à venir)

---

# Rôles utilisateurs

## Visiteur
- Consultation des cinémas
- Consultation des films et séances
- Recherche de cinémas

## Utilisateur connecté
- Création de réservations
- Accès aux fonctionnalités personnalisées

## Administrateur
- Gestion complète :
  - films
  - cinémas
  - villes
  - séances

---

# Modèle de données

- User
- Movie
- Cinema
- City
- Screening
- Reservation

---

# Relations principales

- Un cinéma appartient à une ville
- Un film possède plusieurs séances
- Une séance (Screening) est liée à un film et un cinéma
- Une réservation est liée à :
  - un utilisateur
  - une séance

---

# Sécurité

- Authentification basée sur session HTTP
- Gestion des rôles (USER / ADMIN)
- Protection des routes via `AuthHelper`

---

# Réservation

Une réservation contient :
- utilisateur connecté
- séance sélectionnée
- nombre de places
- date de réservation

---

# Installation

## 1. Cloner le projet
```bash
git clone https://github.com/ton-repo/distopia.git
cd distopia
```

## 2. Configuration base de données (MariaDB)

Dans application.properties :

```bash
#Database
spring.datasource.url=jdbc:mariadb://localhost:3306/distopia
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

#Jpa-hibernate
spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDB53Dialect
```
## 3. Lancer l'application

```bash
./mvnw spring-boot:run
```

---

### Accès application
- URL : http://localhost:8080
- Connexion utilisateur : selon base de données
- Connexion admin : utilisateur avec rôle ADMIN


### Architecture
- Architecture MVC (Spring Boot)
- Templates Thymeleaf côté front
- Accès données via Spring Data JPA
- Logique métier dans les controllers (à refactor en services possible)
