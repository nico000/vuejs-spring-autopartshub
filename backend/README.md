# [UPHF-TP Project] - AutoParts Hub

Ce projet a pour but de remplir les exigences du TP proposé par M. Persent.
Il a pour but de simuler un site d'achat de pièces auto.
En créant une API qui gère les pièces auto, en les stockant dans une base de données Mongo Db.

## Table des matières

- [Introduction](#introduction)
- [Prérequis](#prérequis)
- [Comment bien commencer](#comment-bien-commencer)
- [Variable d'environement](#variable-d-environement)
- [Déploiement](#déploiement)

## Introduction

Le projet doit être facilement déployable avec Docker.
Il doit permettre à l'utilisateur doit pouvoir ajouter des pièces à son panier.
On doit aussi pouvoir créer, modifier, récupérer et supprimer des pièces.

## Prérequis

Voici quelque pré-requit à respecter pour ne pas avoir de problème:

- Java Développement Kit (JDK) 21 ou ultérieur
- Maven build tool
- Docker & Docker Compose
- Et votre IDE ou votre éditeur de code favori.

## Comment bien commencer

1. Cloner ce répertoire:

````bash
$ git clone git@github.com:nico000/vuejs-spring-autopartshub.git
````

2. Lancer la BDD

Ce service a besoin d’une base de données Mongo pour fonctionner. 
Vous pouvez fournir votre base de données ou en démarrer une en utilisant le
projet docker-compose en exécutant:

````bash
$ docker-compose up
````

3. (Optionnel) Créer sa configuration local

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

4. Lancer l'application

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


## Déploiement

Ce projet peut être envoyé au dépôt d’images temporaires gratuit [ttl.sh](https://ttl.sh).

Il existe deux façons:

1. IC/CD

Une configuration CI/CD génère et envoie automatiquement l’image pour les branches suivantes:
- principal
- libération/*

L’image peut ensuite être tirée avec la commande suivante:

```bash
$ docker pull ttl.sh/ecp_autoparts_service:1h
```

Consultez le fichier [github action config file](.github/workflows/build_push_ttl.yml).

2. Scénario

Un [script shell](ttl_push.sh) est disponible pour pousser vers ttl.sh sans CI/CD.

Il peut être utilisé comme suit:

```bash
$ ./ttl_push.sh my_service_name
```

La balise résultante sera affichée après l’exécution du script.