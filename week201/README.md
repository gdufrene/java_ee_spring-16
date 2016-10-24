# Maven / JDBC

Nous allons tester JDBC avec une base minimaliste nommée "sqlite".
sqlite est fourni avec votre distribution linux, et souvent avec Mac OS x. Sous windows vous pouvez télécharger cette archive contenant une version pré-compilée.
C'est une base de données relationnelle "classique" qui a au moins trois avantages :

* c'est simple
* c'est efficace
* çà "parle" le SQL :)

Vérifier que vous avez la commande "sqlite", sinon rendez-vous <a href="https://sqlite.org/download.html">sur cette page</a> pour en télécharger une version compatible avec votre système.

sqLite stocke l'ensemble des tables dans un seul fichier, ce qui permet de transporter la BDD assez facilement. La création d'une base de données se fait simplement en tapant : 

sqlite3 data.db

Vous avez ensuite à votre disposition un interpréteur SQL pour créer vos tables.

Pour ce TP nous utiliserons le schéma suivant :

create table user(
  email     varchar(100) primary key,
  lastname  varchar(80),
  firstname varchar(80),
  pwd       varchar(45)
);

Ajouté aussi cet enregistrement :

insert into user values( "mark.zuckerberg@facebook.com", "Zuckerberg", "Marck", "f4c3b00k");

Pour quitter l'interpréteur utilisez [CTRL]+D ou la commande ".quit"

## Initialiser le projet

Vous allez créer le fichier pom qui convient pour faire de ce projet un artefact maven.  
Les paramètre à utiliser :

* groupId : fr.eservices.__votreNom__  
* artefactId : user-data

Ajouter ces dépendances :

* org.xerial : sqlite-jdbc : 3.14.2.1
* junit : junit : 4.12

Compiler avec maven et lancer la classe principale "App" en ligne de commande.


## Mise sous gestion de source

* "nettoyer" votre répertoire en supprimant ce qui a été généré par maven (/target ...)
* initialiser un projet GIT
* ajouter le pom et vos sources, et faire un commit

Vous devriez ajouter un fichier .gitignore contenant au moins :

.project  
.settings  
*.class  
/target  
*.db  

## Première connexion JDBC

* Dans votre classe principale, utilisez un objet SQLiteDataSource pour récupérer une connexion et exécuter une requête faisant un "select * from user;" 
* Affichez toutes les lignes de la table sur la console


## Implémenter un DAO (Data Access Object)

* Compléter l'objet UserJDBCDAO
* Essayez de faire passer les Tests unitaires de TestUserJDBC, sans en modifier le contenu !
* Vous devriez être en mesure de faire passer la plupart des tests de TestUserJDBC.
* Pour passer les tests checkPassword_Inject1 et checkPassword_Inject2 il faudra filtrer les paramètres, ou utiliser des PreparedStatement

## Installer une dépendance dans maven

* Récupérer le jar code-check et l'installer dans votre repository local maven
* Ajouter cet artefact comme dépendance dans votre pom, en scope test
* Lancer l'évaluation des tests avec maven

## Déployer votre artefact

Configurer votre projet pour déployer sur :

* ftp://172.28.1.99
* user : eservices
* password : (donné en TP)

