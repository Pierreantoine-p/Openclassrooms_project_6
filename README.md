# PayMyBuddy
<p>modèle physique de données </p>
<img src="https://github.com/Pierreantoine-p/Openclassrooms_project_6/blob/develop/src/main/resources/Structure/StructureBdd.png"/>

<p>diagramme de classe</p>
<img src ="https://github.com/Pierreantoine-p/Openclassrooms_project_6/blob/develop/src/main/resources/Structure/ModeleDeClasse.png"/>

Ce fichier README contient des instructions pour lancer et utiliser le serveur backend du projet Pay my buddy utilisant Spring Boot.

<h2>Prérequis</h2>

Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre système :

    Java Development Kit (JDK) (version 11 ou supérieure recommandée)
    Maven (outil de gestion de projet pour Java)
    IDE (comme IntelliJ IDEA ou Eclipse) pour une facilité de développement
    MySQL Server installé localement ou accessible à distance

<h2>Configuration de la base de données</h2>

1. Assurez-vous que votre serveur MySQL est en cours d'exécution
2. Configurations de la base de données dans le projet Spring Boot :
   - Ouvrez le fichier src/main/resources/application.properties.
   - Ajoutez les détails de connexion à votre base de données MySQL 
4. Créez une base de données pour le projet en executant le code bdd.sql

<h2>Installation et exécution</h2>

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
    
<h2>Endpoints API</h2>

Les endpoints exposés par l'API peuvent être trouvés dans la documentation de l'API ou dans les classes de contrôleurs (@RestController) du projet. Pour tester les endpoints, vous pouvez utiliser des outils comme Postman ou cURL.

<h2>Configuration</h2>

Les configurations du serveur et de l'application peuvent être modifiées dans les fichiers de configuration Spring (application.properties ou application.yml) situés dans le répertoire src/main/resources.

<h2>Vérification de la couverture des tests</h2>

Pour vérifier la couverture des tests, suivez ces étapes simples :

1. Ouvrez une console/terminal.

2. Assurez-vous que Maven est installé sur votre machine. Sinon, vous pouvez le télécharger à partir de [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi) et suivre les instructions d'installation.

3. Accédez au répertoire racine du projet.

4. Exécutez la commande suivante pour générer les rapports de couverture des tests :
    ```bash
    mvn site
    ```

5. Une fois que la construction est terminée, accédez au répertoire `target/site` de votre projet.

6. Ouvrez le fichier `index.html` dans votre navigateur préféré.

7. Recherchez la section "Coverage" ou "Couverture des tests" pour visualiser les détails de la couverture des tests.

