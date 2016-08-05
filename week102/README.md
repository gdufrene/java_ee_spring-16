
Créer une servlet simple.

* Copiez ce répertoire dans "webapps" de tomcat et ouvrez une console à cet endroit.
* Compilez le source se trouvant dans WEB-INF/classes à ce même endroit,  
pour cela vous aurez besoin de la librairie "servlet-api.jar" disponible dans le répertoire "lib" de tomcat.
* Lancez ensuite tomcat et rendez-vous sur http://localhost:8080/week102/
* Essayez de saisir votre nom et prénom et validez le formulaire.
* Corrigez la servlet pour qu'elle fonctionne correctement.

Ajouter une librairie à votre application

* Remettez vous dans "week101" et créer une archive "jar" avec toutes les classes de ce projet.  
Il n'y a en fait que deux classes, vous utiliserez la commande "jar" pour créer votre librairie,  
vous pourrez l'appeler json-encoder.jar
* Déplacer cette librairie dans "WEB-INF/lib" du projet "week102" qui est déployé sur tomcat.


Packager et déployer votre application