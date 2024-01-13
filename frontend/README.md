# AutoParts Hub - Front

Le programme a été dévellopé en vue3 vite

---
---

## Recommended IDE Setup

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur) + [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin).

## Customize configuration

See [Vite Configuration Reference](https://vitejs.dev/config/).

## Project Setup

```sh
npm install
```

### Compile and Hot-Reload for Development

```sh
npm run dev
```

### Compile and Minify for Production

```sh
npm run build
```

---
---

## Version avec dockerfile

Pour cette version on utilise un serveur nginx exposé sur le port 80

A noté que l'on utilise une version modifié du fichier nginx.conf pour acceder depuis l'adresse n'importe où sur le site

De plus on indique dans le fichier .env l'adresse pour accéder au back

Pour utiliser le dockerfile , il suffit juste de le build et de lancer l'image (ne pas oublier de setrouver dansle bon répertoire):

```docker
docker build -t autoparts-front .     
```
```docker
docker run -p 8080:8080 --rm autoparts-front 
```

