
# JDBC + JSP


## Premier projet web avec maven et JDBC

Un pom a été préparé pour vous, jetez-y un coup d'oeil.

Un ensemble de propriétés permettent de définir la compatibilité des sources et classes générées. Ces propriétés sont lues par les plugins maven au moment de la compilations.  

Le packaging du projet et défini comme "war", maven s'attend donc à trouver un répertoire src/main/webapp pour constituer tout ce qui sera ranger à côté de vos classes. 

La propriété failOnMissingWebXml permet d'indiquer que nous ne considérons pas le fait de ne pas avoir de fichier web.xml comme une Erreur. Les nouvelles versions des spécifications servlet et les nouveaux serveurs web java ne nécessitent plus forcement d'avoir ce fichier.

Les propriétés sont aussi accessible en les entourant de ${...}.  
Maven expose les variables d'environnement dans ${env.xxxx}. La variable USER est donc ici utilisée pour compléter le nom du groupId.  
Vous pouvez la remplacer par votre nom, ou autre, si le contenu de la variable USER ne vous convient pas.

La librairie servlet-api étant fournie avec tomcat, son scope peut être positionnée à "provided".

Une dépendance vers user-api est définie, toutes les dépendances transitives sont livrées avec le war, dans WEB-INF/lib, lorsque maven package l'artefact.

## Identification

Il faudra sans doute modifier votre DataSource pour charger le fichier "data.db" ailleurs que dans le répertoire courant, interprété comme "le répertoire dans lequel vous êtes au moment de lancer tomcat". Ce pourrait ne pas etre pratique d'avoir ce fichier dans le "/bin" de tomcat, et ce ne semble pas être une super idée de le laisser dans le "/WEB-INF" de votre contexte.

à partir du fichier html fourni dans src/main/webapps/login.html, faire une jsp "login.jsp".

Compléter la servlet AuthControl que vous brancherez sur l'URL "/auth". Cette servlet tentera d'identifier l'utilisateur.

Utilisez les paramètre "email" et "password" pour valider ou non l'authentification.

Depuis une servlet, il est possible de déléguer la suite d'un traitement à une jsp avec le code :
<pre>
  req.getRequestDispatcher("login.jsp").forward(req, resp);
</pre>

La racine du requestDispatcher est le répertoire du contexte tomcat.  
Il est généralement judicieux de mettre les vues de notre application dans /WEB-INF car ce répertoire n'est pas accessible directement. Ainsi on ne peut pas accéder à une jsp en tapant sont url, on doit passer par une Servlet qui délègue l'affichage. On pourra y contrôler que l'utilisateur a bien le droit d'y accéder. Pour simplifier ce TP les jsp restent accessible par leur url.

Le message d'erreur doit être dans une div dont la classe css est "alert alert-danger" voir <a href="http://getbootstrap.com/components/#alerts">cet extrait</a> de documentation.  
Pour passer une variable (ou un paramètre java) à votre servlet (le message d'erreur par exemple) vous pouvez utiliser la méthode "<a href="https://docs.oracle.com/javaee/7/api/javax/servlet/ServletRequest.html#setAttribute%28java.lang.String,%20java.lang.Object%29">setAttribute</a>" de l'objet request. Dans votre JSP vous pourrez récupérer cette variable avec la méthode associée "<a href="https://docs.oracle.com/javaee/7/api/javax/servlet/ServletRequest.html#getAttribute%28java.lang.String%29">getAttribute</a>".

__Attention__ : sendRedirect ou forward n'arrête pas le traitement de la méthode java. Si vous souhaitez ne pas continuer le traitement de l'opération vous devez explicitement faire un "return" après cette opération. 

Le formulaire d'identification pourra ressembler à cela :  
(assets/login.png)

## Inscription

Selon le même principe, créer une servlet register.jsp

Les champs du formulaire seront nommés :

* firstname
* lastname
* email
* password

Pour réaliser un formulaire selon les conventions de bootstrap consulter <a href="http://getbootstrap.com/css/#forms">cette page</a>. Et particulièrement <a href="http://getbootstrap.com/css/#forms-control-validation">cette partie</a> concernant les champs en erreur.

Une servlet branchée sur "/signin" ajoutera l'utilisateur, si il manque un paramètre vous placerez le champ manquant en erreur (class has-error)

Si tout est Ok, enregistrer le nouvel utilisateur et redirigez le vers une page de confirmation.

Le formulaire d'inscription pourra ressembler à cela :  
(assets/register.png)

