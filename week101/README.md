
* Compléter la méthode `toJson( String str )`

* Compiler JsonEncoder en ligne de commande de manière à mettre les classes dans le répertoire "bin"

* Lancer JsonEncoder en ligne de commande depuis le répertoire "week1"

* Télécharger ces librairies et les mettre dans "lib" :
  * https://github.com/junit-team/junit4/releases/download/r4.12/junit-4.12.jar
  * http://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

* Compiler JsonEncoderTest dans "bin", vous aurez besoin de junit-4.xx.jar dans le classpath,  
il faudra également mettre "src" dans le sourcepath car le test référence la classe JsonEncoder qui devra
être compiler en même temps.

* Lancer les Tests. Pour cela il faut exécuter la classe org.junit.runner.JUnitCore et passer en paramètre
le nom de la classe de tests à lancer : JsonEncoderTest.  
Il faudra mettre dans le classpath junit-4.xx.jar et hamcrest-core-1.3.jar.

