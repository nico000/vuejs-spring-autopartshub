<template>
  <div class="min-h-screen bg-gray-100">
    <header class="fixed w-full z-10 top-0 left-0 bg-white shadow py-3">
      <div class="flex justify-between items-center max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <img @click="LogoAccueil" src="../assets/logo.png" alt="Logo de l'entreprise" class="h-10 w-auto rounded-xl">
        <div class="relative">
          <button class="flex items-center text-gray-800 hover:text-gray-600">
            <img src="../assets/cart.png" alt="Panier" class="h-6 w-6 md:h-8 md:w-8" />
            <span class="absolute top-0 right-0 transform translate-x-1/2 -translate-y-1/2 inline-flex items-center justify-center px-2 py-1 text-xs font-bold leading-none text-red-100 bg-red-600 rounded-full">{{ totalQuantity }}</span>
          </button>
        </div>
      </div>
    </header>
    <main class="pt-16">
      <div class="max-w-2xl mx-auto py-6 sm:px-6 lg:max-w-7xl lg:px-8">
        <div class="bg-white p-6 rounded-lg shadow-lg">
          <h2 class="text-2xl font-bold mb-4">Panier</h2>
          <div v-for="item in cartItems" :key="item.id" class="mb-4">
            <panier-article :item="item"/>
            <div class="flex justify-between text-sm text-gray-600">
              <span>Quantité : {{ item.quantity }}</span>
              <span>Sous-total : {{ item.total }} €</span>
            </div>
            <button @click="removeFromCart(item.id)" class="text-red-600 hover:text-red-800 mt-2">Supprimer</button>
          </div>
          <div class="text-center text-lg font-bold my-4">
            Total Général : {{ totalGeneral }} €
          </div>
          <div class="flex justify-center mt-6">
            <button
                @click="appuiBouton"
                :class="{ 'bg-blue-500 hover:bg-blue-700': cartItems.length, 'bg-gray-500 hover:bg-gray-700': !cartItems.length }"
                class="px-4 py-2 text-white font-bold rounded transition-colors duration-150 ease-in-out"
            >
              {{ cartItems.length ? 'Passer commande' : 'Faire mes achats' }}
            </button>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import panierArticle from '../components/prodPanier.vue';
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";

export default {
  components: {
    panierArticle
  },
  data() {
    return {
      totalQuantity: 0,
      totalGeneral: 0,
    }
  },
  setup() {
    const router = useRouter();
    const cartItems = ref(JSON.parse(localStorage.getItem('cart')) || []);
    const totalQuantity = ref(0);
    const totalGeneral = ref(0);

    const updateCart = () => {
      totalQuantity.value = cartItems.value.reduce((acc, item) => acc + item.quantity, 0);
      totalGeneral.value = cartItems.value.reduce((acc, item) => acc + item.total, 0);
    };

    const removeFromCart = (itemId) => {
      cartItems.value = cartItems.value.filter(item => item.id !== itemId);
      localStorage.setItem('cart', JSON.stringify(cartItems.value));
      updateCart();
    };

    const LogoAccueil = () => {
      router.push('/');
    };

    const appuiBouton = () => {
          if (cartItems.value.length) {
            router.push('/achat');
          } else {
            router.push('/');
          }
    };

    onMounted(updateCart);

    return { cartItems, totalQuantity, totalGeneral, removeFromCart, LogoAccueil, appuiBouton };
  },
}
</script>