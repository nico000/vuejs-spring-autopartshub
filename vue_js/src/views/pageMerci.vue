<template>
  <div class="min-h-screen bg-gray-100">
    <header class="fixed w-full z-10 top-0 left-0 bg-white shadow py-3">
      <div class="flex justify-between items-center max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <img @click="LogoAccueil" src="../assets/logo.png" alt="Logo de l'entreprise" class="h-10 w-auto rounded-xl">
        <div class="relative">
          <button class="flex items-center text-gray-800 hover:text-gray-600">
            <img @click="goPanier" src="../assets/cart.png" alt="Panier" class="h-6 w-6 md:h-8 md:w-8" />
            <span class="absolute top-0 right-0 transform translate-x-1/2 -translate-y-1/2 inline-flex items-center justify-center px-2 py-1 text-xs font-bold leading-none text-red-100 bg-red-600 rounded-full">0</span>
          </button>
        </div>
      </div>
    </header>
    <main class="pt-16">
      <div class="max-w-2xl mx-auto py-6 sm:px-6 lg:max-w-7xl lg:px-8">
        <div class="bg-white p-6 rounded-lg shadow-lg text-center">
          <h2 class="text-2xl font-bold mb-4">Merci pour votre commande {{ nom }} !</h2>
          <p class="mb-2">Votre commande a été traitée avec succès.</p>
          <p>Numéro de commande : <span class="font-semibold">{{ orderNumber }}</span></p>
          <p>Montant total : <span class="font-semibold">{{ totalAmount }} €</span></p>
          <div class="mt-6">
            <router-link to="/" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
              Retour à l'accueil
            </router-link>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import router from "@/router/index.js";

export default {
  data() {
    return {
      orderNumber: '',
      totalAmount: '0',
      nom: ''
    };
  },
  mounted() {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const commande = JSON.parse(localStorage.getItem('commande')) || [];
    let totalGen = 0;

    cart.forEach(item => {
      totalGen += item.total;
    });

    this.totalAmount = totalGen;
    this.nom = commande.prenom + " " + commande.nom;

    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    let numCommande = '';
    for (let i = 0; i < 10; i++) {
      numCommande += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    this.orderNumber = numCommande;

    localStorage.removeItem('cart');
    localStorage.removeItem('commande');
  },
  setup() {
    const LogoAccueil = () => {
      router.push('/');
    }

    const goPanier = () => {
      router.push('/panier');
    }

    return { goPanier, LogoAccueil }
  },
}
</script>
