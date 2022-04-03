# IN205_LibraryManager

L’objectif de ce projet est de vous faire développer une application web de gestion d’une bibliothèque : gestion des membres, des livres référencés, des emprunts. L’application est réalisée à l’aide de Java EE.

 # Commande d'exécution
 
 mvn clean install exec:java
 mvn tomcat7:run
 
 Le site se trouve à l'url : http://localhost:8080/TP3Ensta/dashboard
 
 # Quelques commentaires
 
 Lors de la création d'un nouveau livre : si nous avons un titre vide, le formulaire se réinitialise sans crée un nouveau livre.
 Lors de la création d'un nouveau membre : si nous avons prenom ou un nom vide , le formulaire se réinitialise sans crée un nouveau membre.
 
