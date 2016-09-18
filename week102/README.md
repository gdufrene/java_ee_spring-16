
# Créer une servlet simple.

* Copiez ce répertoire dans "webapps" de tomcat et ouvrez une console à cet endroit.
* Compilez le source se trouvant dans WEB-INF/classes à ce même endroit,  
pour cela vous aurez besoin de mettre la librairie "servlet-api.jar" dans le classpath au moment de la compilation. Ce jar est disponible dans le répertoire "lib" de tomcat.
* Lancez ensuite tomcat et rendez-vous sur http://localhost:8080/week102/
* Essayez de saisir votre nom et prénom et validez le formulaire.
* Corrigez la servlet pour qu'elle fonctionne correctement.

# Ajouter une librairie à une application

* Remettez vous dans "week101" et créer une archive "jar" avec toutes les classes de ce projet.  
Il n'y a en fait que deux classes, vous utiliserez la commande "jar" pour créer votre librairie,  
vous pourrez l'appeler json-encoder.jar
* Déplacez cette librairie dans "WEB-INF/lib" du projet "week102" qui est déployé sur tomcat.
* Modifiez la servlet "ListFiles" afin de retourner en json la liste des fichier d'un répertoire passé en paramètre.
* Redémarrez tomcat et testez

# Packager et déployer une application

* Arrêtez tomcat
* Placez vous dans "webapps"
* Créer un fichier "week102.war" à l'aide de la commande "jar" qui contient l'ensemble des fichiers contenu dans "week102"
* Déplacez votre répertoire week102 pour qu'il ne soit plus chargez par tomcat.
* Relancez tomcat et observez la console.
* Vous devriez voir une ligne "Déploiement de l'archive xxxxx/apache-tomcat-8.5.4/webapps/week102.war de l'application web"
* Essayez de nouveaux vos servlet, tout devrait fonctionner comme avant.  
Vous avez maintenant une archive qui peut se déployer dans n'importe quel serveur Java compatible.

