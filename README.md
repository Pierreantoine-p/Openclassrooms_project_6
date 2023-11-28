# PayMyBuddy
<p>modèle physique de données </p>
<img src="https://github.com/Pierreantoine-p/Openclassrooms_project_6/blob/develop/src/main/resources/Structure/StructureBdd.png"/>

<p>diagramme de classe</p>
<img src ="https://github.com/Pierreantoine-p/Openclassrooms_project_6/blob/develop/src/main/resources/Structure/ModeleDeClasse.png"/>

Ce fichier README contient des instructions pour lancer et utiliser le serveur backend du projet Pay my buddy utilisant Spring Boot.
Prérequis

Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre système :

    Java Development Kit (JDK) (version 11 ou supérieure recommandée)
    Maven (outil de gestion de projet pour Java)
    IDE (comme IntelliJ IDEA ou Eclipse) pour une facilité de développement

Installation et exécution

    Clonez ce dépôt sur votre machine en utilisant la commande suivante :

    bash

git clone git@github.com:Pierreantoine-p/Openclassrooms_project_6.git

Ouvrez le projet dans votre IDE préféré.

Construisez le projet avec Maven pour télécharger les dépendances et générer le fichier JAR exécutable :

bash

mvn clean install

Lancez l'application Spring Boot :

    Recherchez et exécutez la classe principale NomDuProjetApplication (qui a l'annotation @SpringBootApplication) dans votre IDE.
    Ou via la ligne de commande :


    Une fois démarré, le serveur sera accessible à l'adresse : http://localhost:8080/.

Endpoints API

Les endpoints exposés par l'API peuvent être trouvés dans la documentation de l'API ou dans les classes de contrôleurs (@RestController) du projet. Pour tester les endpoints, vous pouvez utiliser des outils comme Postman ou cURL.
Configuration

Les configurations du serveur et de l'application peuvent être modifiées dans les fichiers de configuration Spring (application.properties ou application.yml) situés dans le répertoire src/main/resources.
