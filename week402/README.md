# Spring web MVC

Vous allez réaliser quelques contrôleurs simples pour appréhender les mécanismes de spring web-mvc.

## Un peu de configuration

* Ajouter une dépendance vers :

```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>${spring.version}</version>
</dependency>
```

La propriété spring.version est définie plus haut dans le pom.

Il faut exposer la servlet RequestDispatcher de spring, faisons le au moyen d'une classe qui étend WebApplicationInitializer. 

* Observer le contenu de WebAppInitializer, dé-commenter le code. 

Une classe "AppConfig" est enregistré dans le contexte de spring. 

* Ouvrez cette classe et ajouter 3 annotations à cette classe, pour le moment ne vous occupez pas du bean InternalResourceViewResolver.

Il faut maintenant exposer un contrôleur:

* ouvrez HelloController, dé-commentez le code 
* ajouter les annotations nécessaires. 
* Mappez le contrôleur sur l'URL "/hello" et la méthode sur l'URL "/world". 
* N'oubliez pas d'ajoutez une annotation sur le paramètre "name" pour le récupérer depuis la requête.

Lancez tomcat et rendez-vous sur http://localhost:8080/week402/app/hello/world?name=e-services

* Modifier la JSP /sample.jsp pour faire apparaitre votre message.
* Actualiser la page.

## Contrôleurs REST

Essayons maintenant d'implémenter un contrôleur REST.

* Ouvrez SimpleController et ajouter les annotation nécessaire pour exposer l'opération "add". Cette opération devra être accessible à travers cette adresse complète : http://localhost:8080/week402/app/simple/add
* Testez votre addition

Il est facile d'exposer du texte, mais nous souhaitons maintenant exposer l'opération "getTime" qui retourne un objet java.

* Ajouter une dépendance vers :

```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.8.5</version>
    <scope>runtime</scope>
</dependency>
```

* Annotez getTime pour l'exposer sur "/time"
* Testez votre contrôleur en appelant l'URL correspondante.

## Configurer le lien entre contrôleur et view

Il n'est pas souhaitable que la jsp soit accessible à travers l'url http://localhost:8080/sample.jsp

Généralement, les vues sont fournies par la ResquestDispatcher, et non directement par tomcat. Il existe différentes manière de masquer les vues, la plus simple étant de les mettre dans "WEB-INF". Ce répertoire ne sera en effet jamais mappé sur une url fournie par tomcat.

* Créer un répertoire "/WEB-INF/views"
* Copier votre jsp à l'intérieur de ce répertoire
* modifier AppConfig pour mettre à disposition un InternalResourceViewResolver
* configurer ce ViewResolver pour mettre en prefix "/WEB-INF/views/", et en suffixe ".jsp"

A partir de maintenant, tous les contrôleurs peuvent simplement retourné le nom de la jsp à afficher, sans leur extension. Ces JSP seront toutes rangées dans "/WEB-INF/views/". Vous pouvez y faire des sous-répertoire selon des groupes logiques, par exemple ranger toutes les vues des utilisateurs dans "users". Un contrôleur exposant la liste des utilisateurs retournerait donc "users/list" en supposant que vous avez une jsp : /WEB-INF/views/users/list.jsp

* Modifier le HelloControler pour que la jsp "sample.jsp" dans /WEB-INF/views s'affiche.


