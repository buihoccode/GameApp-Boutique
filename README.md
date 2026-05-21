# README - Projet GameApp

## Section 1 — Comment lancer le projet

### Pré-requis
* **Java**: JDK 21+
* **Node.js**: v20+
* **MySQL**: Serveur actif (via MAMP, port 8889)

### Commandes de lancement
1. **Backend**: Dans le dossier `GameApp`, lancez le serveur avec : 
   `./mvnw spring-boot:run`
2. **Frontend**: Dans le dossier `frontend`, lancez le serveur de développement :
   `npm run dev`
3. **Configuration Admin**: Lors du premier lancement, créez un compte via `/api/users/register`. Pour obtenir les droits `ADMIN`, exécutez manuellement cette requête SQL dans phpMyAdmin : 
   `INSERT INTO user_roles (user_id, role_id) VALUES (1, (SELECT id FROM role WHERE role='ADMIN'));`

---

## Section 2 — Paramètres de votre fiche
* **Domaine**: Jeux Vidéo.
* **Champ signature**: `titre`.
* **Formule prixTotal**: `prix * quantite`.
* **Thème UI**: Moderne et épuré (Slate/Blue).

---

## Section 3 — Choix techniques

* **Q1 (BCrypt)**: J'ai choisi un coût de 12. C'est le standard actuel : assez lent pour décourager les attaques par force brute sans être trop coûteux en ressources serveur. Un coût de 4 serait trop rapide (vulnérable), et 15 rendrait le serveur inutilisable sous la charge.
* **Q2 (Wrapper Generic)**: Utiliser `ApiResponse<T>` permet une uniformisation des réponses API. Cela simplifie la gestion côté frontend, car le client sait toujours qu'il recevra une structure `success`, `message` et `data`, indépendamment de l'objet contenu.
* **Q3 (Axios Interceptor)**: L'interceptor centralise l'ajout du header `Authorization`. Avec un `fetch`, il faudrait l'ajouter manuellement dans chaque appel. L'interceptor évite l'oubli de cette logique et assure que chaque requête sortante est sécurisée sans duplication de code.
* **Q4 (invalidateQueries)**: Invalider le cache force TanStack Query à marquer les données comme "périmées" (`stale`). Cela garantit que la prochaine lecture sera une requête fraîche vers le serveur, évitant les incohérences entre l'UI et la base de données après une modification.

---

## Section 4 — Analyse critique

* **Prop A (Désactiver @Valid)**: 
  1. Rend la BDD vulnérable aux données corrompues. 
  2. Déplace toute la charge de validation sur le frontend, ce qui est une faille de sécurité. 
  3. Complexifie le débogage car les erreurs surviennent au niveau de la BDD et non lors de la réception de la requête.
* **Prop B (Sécurité Frontend)**: 
  1. La sécurité ne doit jamais reposer uniquement sur le frontend. 
  2. N'importe qui peut appeler l'API DELETE via Postman/cURL sans passer par l'interface. 
  3. C'est une faille de sécurité critique permettant une suppression non autorisée.
* **Prop C (Désactiver réponse 401)**: 
  1. L'application ne pourra jamais gérer correctement l'expiration du token. 
  2. L'utilisateur restera sur une page sans comprendre pourquoi ses actions échouent. 
  3. Impossible de rediriger automatiquement vers la page de login, dégradant l'expérience utilisateur.

---

## Section 5 — Difficultés rencontrées
1. **Connexion réseau avec MAMP**: MAMP bloquait les connexions TCP. Résolu en forçant l'usage de `127.0.0.1` et en configurant le port `8889` spécifiquement dans `application.properties`.
2. **Conflits de versions React/Vitest**: Conflit entre React 19 et les bibliothèques de tests. Résolu par l'utilisation de `npm install --legacy-peer-deps`.

---

## Section 6 — Captures d'écran Swagger
*Captures disponibles dans le dossier `/screenshots/` à la racine.*