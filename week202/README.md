# Premier projet web avec maven et JDBC

Un pom a été préparé pour vous, jetez-y un coup d'oeil.

Un ensemble de propriétés permettent de définir la compatibilité des sources et classes générées. Ces propriétés sont lues par les plugins maven au moment de la compilations.  

Le packaging du projet et défini comme "war", maven s'attend donc à trouver un répertoire src/main/webapp pour constituer tout ce qui sera ranger à côté de vos classes. 

La propriété failOnMissingWebXml permet d'indiquer que nous ne considérons pas le fait de ne pas avoir de fichier web.xml comme une Erreur. Les nouvelles versions des spécifications servlet et les nouveaux serveurs web java ne nécessitent plus forcement d'avoir ce fichier.

Les propriétés sont aussi accessible en les entourant de ${...}.  
Maven expose les variables d'environnement dans ${env.xxxx}. La variable USER est donc ici utilisée pour compléter le nom du groupId.

La librairie servlet-api étant fournie avec tomcat, son scope peut être positionnée à "provided".

Une dépendance vers user-api est définie, toutes les dépendances transitives sont livrées avec le war, dans WEB-INF/lib, lorsque maven package l'artefact.

