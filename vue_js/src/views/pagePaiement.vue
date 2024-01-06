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
        <div class="bg-white p-6 rounded-lg shadow-lg">
          <h2 class="text-2xl font-bold mb-4">Passage de commande</h2>
          <form @submit.prevent="submitOrder">
            <div class="mb-4">
              <label class="block text-gray-700 text-sm font-bold mb-2" for="nom">Nom</label>
              <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="name" type="text" placeholder="Nom" v-model="order.nom">
            </div>

            <div class="mb-4">
              <label class="block text-gray-700 text-sm font-bold mb-2" for="prenom">Prénom</label>
              <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="surname" type="text" placeholder="Prénom" v-model="order.prenom">
            </div>

            <div class="mb-4">
              <label class="block text-gray-700 text-sm font-bold mb-2" for="address">Adresse postale</label>
              <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="address" type="text" placeholder="Adresse postale" v-model="order.address">
            </div>

            <div class="mb-4">
              <label class="block text-gray-700 text-sm font-bold mb-2" for="email">Adresse e-mail</label>
              <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="email" type="email" placeholder="Email" v-model="order.email">
            </div>

            <div class="mb-4">
              <label class="block text-gray-700 text-sm font-bold mb-2" for="card">Numéro de carte bancaire</label>
              <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="card" type="text" placeholder="xxxx xxxx xxxx xxxx" v-model="order.cardNumber" @input="formatCardNumber">
            </div>
            <div class="flex items-center justify-between">
              <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit">
                Valider la commande
              </button>
            </div>
          </form>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import router from "@/router/index.js";

export default {
  setup() {
    const LogoAccueil = () => {
      router.push('/');
    }

    const goPanier = () => {
      router.push('/panier');
    }

    return { goPanier, LogoAccueil };
  },
  data() {
    return {
      order: {
        nom: '',
        prenom: '',
        address: '',
        email: '',
        cardNumber: ''
      }
    };
  },
  methods: {
    formatCardNumber() {
      let cardNumber = this.order.cardNumber.split(' ').join('');
      if (cardNumber.length > 0) {
        cardNumber = cardNumber.match(new RegExp('.{1,4}', 'g')).join(' ');
      }
      this.order.cardNumber = cardNumber;
    },
    submitOrder() {
      if (!this.order.cardNumber.match(/(?:\d{4} ){3}\d{4}/)) {
        alert("Veuillez saisir un numéro de carte valide.");
      } else {
        if (this.order.nom !== '' && this.order.prenom !== '' && this.order.address !== '' && this.order.email !== '') {
          this.finaliseCommande();
        } else {
          alert('Tous les champs doivent être remplis.')
        }
      }
    },
    finaliseCommande() {
      localStorage.setItem('commande', JSON.stringify(this.order));
      router.push('/merci');
    }
  }
}
</script>
