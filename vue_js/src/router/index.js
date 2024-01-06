import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import panier from "../views/panier.vue";
import pagePaiement from "@/views/pagePaiement.vue";
import pageMerci from "@/views/pageMerci.vue";
import adminDash from "@/views/adminDash.vue";
import ajouterPiece from "@/views/ajouterPiece.vue";
import modifierProduit from "@/views/modifierProduit.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/panier',
      name: 'panier',
      component: panier
    },
    {
      path: '/achat',
      name: 'paiement',
      component: pagePaiement
    },
    {
      path: '/merci',
      name: 'merci',
      component: pageMerci
    },
    {
      path: '/admin',
      name: 'dashAdmin',
      component: adminDash
    },
    {
      path: '/ajouter',
      name: 'ajouterProduit',
      component: ajouterPiece
    },
    {
      path: '/modifier/:id',
      name: 'modifierProduit',
      component: modifierProduit
    },
  ]
})

export default router
