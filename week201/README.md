# Maven


## Les bases

* faire de ce répertoire un projet maven (créer le pom.xml)  
vous mettrez comme groupId : fr.eservices.__votreNom__  
pour l'artefactId : user 
* créer les répertoires standards maven (src/main/java, ...)
* Ecrire une classe contenant un main, y mettre un "Hello world"
* compiler avec maven
* exécuter en ligne de commande

## Mise sous gestion de source

* "nettoyer" votre répertoire en supprimant ce qui a été généré par maven (/target ...)
* initialiser un projet GIT
* ajouter le pom et vos sources
* commitez votre travail

## Les Dépendances

* Ajouter une dépendance vers org.xerial:sqlite-jdbc:3.14.2.1
* Vérifier que vous avez la commande "sqlite", sinon rendez-vous <a href="https://sqlite.org/download.html">sur cette page</a> pour en télécharger une version compatible avec votre système.
* créer une base avec sqlite3 data.db et une table :  
create table user( email varchar(80) primary key, firstname varchar(80), lastname varchar(80), pwd varchar(32) );
* insérer un utilisateur :  
insert into user values('test@test.com', 'testFirst', 'testLast', 'testPwd');
* dans votre classe principale, utiliser un objet SQLiteDataSource pour récupérer une connexion et exécuter une requête faisant un "select * from user;" et affiche toutes les lignes de la table.
* vous devriez aussi ajouter une dépendance vers junit::junit version 4.12, dans le scope test

## implémenter un DAO (Data Access Object)

* Créer une classe "User" avec nom, prénom, mail, password
* Créer une interface "UserDao" avec une méthode pour charger, sauvegarder, mettre à jour et supprimer un Utilisateur.
* Créer une classe UserDaoJDBC qui implémente UserDao et générer des méthodes vides.
* Créer un test unitaire qui valide que vous pouvez :
  * créer un utilisateur,
  * charger un utilisateur,
  * modifier cet utilisateur (le nom par exemple),
  * supprimer cet utilisateur.
* Lancer vos test, pour le moment rien ne passe, évidemment.
* Implémenter la création puis relancer vos test ...
* Idem sur chargement, modification et suppression
* Ajouter une méthode "boolean checkPassword(String email, String password)" sur UserDao
* Ajouter un utilisateur en base.
* Ajouter un test qui vérifie que que votre méthode checkPassword fonctionne.


## Vers l'inversion de dépendance ...

* Dans cet état, quel est le scope nécessaire à la dépendance mysql-connector-java ?
* Comment serait-il possible de laisser le choix du driver utilisé au runtime ?  
L'objectif serait le suivant : sans changer les sources, être capable de choisir la base de données, son type et les paramètres de connexion.



