# Spécifications fonctionnelles : Application de gestion de cinémas

---

# 1. Acteurs du système

### Visiteur (VISITOR)
Utilisateur non connecté.  
Il peut consulter les informations publiques de l’application.

### Utilisateur connecté (USER_CONNECTED)
Utilisateur authentifié.  
Il hérite des droits du visiteur et peut effectuer des réservations.

### Administrateur (ADMIN)
Utilisateur connecté avec des droits avancés.  
Il peut gérer les données de l’application (CRUD).

---

# 2. Authentification

### Se connecter
**Acteurs :** Visiteur, utilisateur connecté, administrateur  

**Description :**
Permet à un utilisateur de s’authentifier afin d’accéder aux fonctionnalités réservées.

**Règles :**
- Un utilisateur doit être connecté pour réserver ou accéder à l’administration  
- Les administrateurs doivent être authentifiés pour accéder aux fonctionnalités de gestion  

---

# 3. Consultation (Affichage public)

### Afficher tous les cinémas
**Acteur :** Visiteur

**Description :**
Le système affiche la liste de tous les cinémas disponibles.

**Données affichées :**
- Nom du cinéma  
- Adresse  
- Ville  
- Prix par place  

---

### Afficher tous les films
**Acteur :** Visiteur  

**Description :**
Affiche la liste des films disponibles dans le système.

---

### Afficher les séances d’un film
**Acteur :** Visiteur  

**Description :**
Permet de consulter les horaires des projections d’un film.

**Données affichées :**
- Film associé  
- Date et heure de séance  
- Cinéma associé  

---

### Rechercher un cinéma
**Acteur :** Visiteur  

**Description :**
Permet de rechercher un cinéma par mot-clé.

**Fonctionnalités :**
- Recherche par nom  
- Filtrage par ville  

---

# 4. Réservation

### Faire une réservation
**Acteur :** Utilisateur connecté  

**Description :**
Permet à un utilisateur connecté de réserver une ou plusieurs places pour une séance.

**Données nécessaires :**
- Utilisateur connecté (automatique)  
- Séance sélectionnée  
- Nombre de places  

**Règles :**
- L’utilisateur doit être authentifié  
- Une réservation est liée à :
  - un utilisateur  
  - une séance  
- Le prix est calculé selon :
  - prix du cinéma × nombre de places  

---

# 5. Administration

### Gérer les films
**Acteur :** Administrateur  

**Description :**
Permet la gestion complète des films.

**Actions :**
- Créer un film  
- Modifier un film  
- Supprimer un film  

---

### Gérer les cinémas
**Acteur :** Administrateur  

**Description :**
Permet la gestion des cinémas.

**Actions :**
- Ajouter un cinéma  
- Modifier un cinéma  
- Supprimer un cinéma  

---

### Gérer les villes
**Acteur :** Administrateur  

**Description :**
Gestion des villes liées aux cinémas.

**Actions :**
- Ajouter une ville  
- Modifier une ville  
- Supprimer une ville  

---

### Gérer les séances
**Acteur :** Administrateur  

**Description :**
Gestion des séances (screenings).

**Actions :**
- Créer une séance  
- Modifier une séance  
- Supprimer une séance  
