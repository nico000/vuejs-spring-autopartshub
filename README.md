# [UPHF-TP Project] - AutoParts Hub

Ce projet a pour but de remplir les exigences du TP proposé par M. Persent.
Il a pour but de simuler un site d'achat de pièces auto.
En créant une API qui gère les pièces auto, en les stockant dans une base de données Mongo Db.

---
## [Note importante] 

Je m'excuse pour un rendu si tardif en effet j'ai dû mal noté la date de rendu et j'ai cru que c'était le 15/01 , je l'ai découvert que deux jours avant le rendu que je n'avais pas la bonne date.
Etant donné que Mr Wojtyna Quentin était parti en vacance et ne revenait qu'a peine 2 jour avant le rendu , nous n'avons pas pu y travailler avant et donc nous avons du réaliser rapidement le projet.

Le jour du rendu mais deux collaborateurs avait terminé leurs tâches cependant il y avait différentes erreurs que j'ai du résoudre pour intégrer la partie front et back et donc je n'ai pas pu travailler sur la partie CI/CD et la démo.

J'ai donc fait le choix de le rendre en retard pour pouvoir le finaliser comme il le faut et je m'en excuse encore du retard et je vous demanderais s'il vous plait de ne pas pénaliser Quentin et Benjamin car c'est entiérement de ma faute que le devoir est rendu avec une semaine de retard.

Nicolas Laniau

---

## Table des matières

- [Introduction](#introduction)
- [Prérequis](#prérequis)
- [Comment bien commencer](#comment-bien-commencer)
- [Déploiement par docker compose](#Déploiement-docker-compose)
- [Déploiement par ttl](#Déploiement-ttl)
- [Swagger](#Swagger)

## Introduction

Le projet doit être facilement déployable avec Docker.
Il doit permettre à l'utilisateur doit pouvoir ajouter des pièces à son panier.
On doit aussi pouvoir créer, modifier, récupérer et supprimer des pièces.

## Prérequis

Voici quelque pré-requis à respecter pour ne pas avoir de problème:

- Java Développement Kit (JDK) 21 ou ultérieur
- Maven build tool
- Docker & Docker Compose
- Et votre IDE ou votre éditeur de code favori.

## Comment bien commencer

Cloner ce répertoire:

````bash
$ git clone https://github.com/nico000/vuejs-spring-autopartshub/tree/master
````

## Déploiement docker-compose 

Voir `DEMO`

## Déploiement ttl

Ce projet peut être envoyé au dépôt d’images temporaires gratuit [ttl.sh](https://ttl.sh).

### 1. Démarrage du script

Le démarrage s'effectue soit au push du projet ou par l'activation de github action 

---
Une configuration CI/CD génère et envoie automatiquement l’image pour les branches suivantes:
- master
---

Il va alors vérifier l'intégrité des fichiers avec le workflow (build_deploy.yml)
Puis il va construire à partir du docker compose les différentes images

### 2. Push sur ttl lors d'un déploiement

Il va ensuite push sur TTL en fonction des variable d'environnement : 
```
DEPLOY_BACKEND
DEPLOY_FRONTEND
DEPLOY_MONGODB
```

Consultez le fichier [github action config file](.github/workflows/build_deploy.yml).

### 3. Recupération des images 
 
 Pour récupérer les images on rentre les commandes suivante :
 
```
docker pull ttl.sh/autoparts-back:1h
docker pull ttl.sh/autoparts-front:1h
docker pull ttl.sh/autoparts-db:1h
```
### 4. Création d'un réseau
On créer ensuite un réseau pour les contenaires

```
docker network create demo_autoparts_network
```

### 5. Création et démarrage des contenaires 

On démarre ensuite les contenaire en précisant bien le port et le réseau.
A noté de bien mettre pour le nom "mongo" pour le contenaire de la base de donné sans ça la connexion ne se fera pas. 

```
docker run -d -p 27017:27017 --network=demo_autoparts_network --name=mongo ttl.sh/autoparts-db:1h

```
```
docker run -d -p 8080:80 --network=demo_autoparts_network --name=front ttl.sh/autoparts-front:1h
```
```
docker run -d -p 8081:8081 --network=demo_autoparts_network --name=back ttl.sh/autoparts-back:1h
```


### 6. Se rendre à l'adresse localhost:8080


## Swagger

Aller à l'adresse http://localhost:8081/swagger-ui/index.html pour le swagger  




