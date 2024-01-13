
# Autoparts hub - back 

## Lancement du backend + mongo avec les images docker

## 1. Lancer mongo

Ce service a besoin d’une base de données Mongo pour fonctionner. 
Vous pouvez fournir votre base de données ou en démarrer une en utilisant le
projet docker-compose en exécutant:

```bash
./backend # docker-compose up
```

## 2. (Optionnel) Créer sa configuration local

Vous pouvez remplacer l’application par défaut en créant votre propre fichier de configuration. 
Vous pouvez également personnaliser utilisant des variables d'environement ([Variable d'environement](#variable-d-environement))

Exemple:
*application-locale.yml*

````yml
spring:
    data:
        mongodb:
        uri: ${DB_URL:mongodb://localhost:27017}
        database: ${DB_NAME:autoparts_service_db}
        auto-index-creation: true
````

## 3. Lancer l'application

Vous pouvez exécuter l’application à l’aide de la commande suivante:

```bash
$ mvn spring-boot:run
```

Si vous souhaitez utiliser la configuration personnalisée créée ci-dessus, utilisez cette commande:

```bash
$ mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Variable d environement

Les variables d’environnement suivantes peuvent être personnalisées:

| Nom      | Type    | Valeur par défaut         | Description                                                        |
|----------|---------|---------------------------|--------------------------------------------------------------------|
| PORT     | Integer | 8080                      | Le port sur lequel le serveur doit démarrer                        |
| BASE_URL | String  | /                         | Le chemin de base pour le service                                  |
| DB_URL   | String  | mongodb://localhost:27017 | L’url de la base de données mongo à utiliser                       |
| DB_NAME  | String  | autoparts_service_db      | Le nom de la base de données qui doit être utilisé par le service  |


## Lancer spring boot par un dockerfile

Pour lancer le service il faut en premier vérifier si un dossier .jar existe dans le repertoire target sinon rentrer les commandes suivantes : 

```bash
./backend # maven clean
./backend # maven install
```

Puis on construit l'image :
```docker
docker build -t autoparts-back .     
```

Lancer l'image créer :
```docker
docker run -p 8080:8080 --rm autoparts-back 
```