<template>
  <div class="min-h-screen bg-gray-100">
    <header class="bg-white shadow py-3 fixed w-full z-10 top-0 left-0">
      <div class="flex justify-between items-center max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <img src="../assets/logo.png" alt="Logo de l'entreprise" class="h-10 w-auto rounded-xl">
        <div class="relative">
          <button @click="goAdmin" class="bg-black text-white p-2 rounded flex items-center text-gray-800 hover:text-gray-600">
            Administrateur mode
          </button>
        </div>
        <div class="relative">
          <button @click="goPanier" class="flex items-center text-gray-800 hover:text-gray-600">
            <img src="../assets/cart.png" alt="Panier" class="h-6 w-6 md:h-8 md:w-8" />
            <span class="absolute top-0 right-0 transform translate-x-1/2 -translate-y-1/2 inline-flex items-center justify-center px-2 py-1 text-xs font-bold leading-none text-red-100 bg-red-600 rounded-full">{{ totalQuantity }}</span>
          </button>
        </div>
      </div>
    </header>
    <main class="pt-16">
      <div class="max-w-2xl mx-auto py-6 sm:px-6 lg:max-w-7xl lg:px-8">
        <div class="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-4">
          <produit v-for="product in produits" :key="product.id" :product="product" @updateCart="updateCart"/>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import produit from '../components/produit.vue'
import {useRouter} from "vue-router";
import {ref} from "vue";
import ApiPiece from "@/API/ApiPiece.js";

export default {
  components: {
    produit
  },
  data() {
    return {
      totalQuantity: 0,
      produits: [
        { id: 1, name: 'Produit 1', description: 'Description 1', price: '100', image: 'https://media.autodoc.de/360_photos/1156098/h-preview.jpg' },
        { id: 2, name: 'Produit 2', description: 'Description 2', price: '100', image: 'https://oscaro.media/catalog/images//oscjpg/zoom/30/new_16_09_2022_f026400433_1.jpg'},
        { id: 3, name: 'Produit 3', description: 'Description 3', price: '100', image: 'https://media.vroomly.com/public/thumbnails/interventions/vZCHt6JOq26r_700x700_mtdhGWCw.jpg'},
        { id: 4, name: 'Produit 4', description: 'Description 4', price: '100', image: 'https://www.piecesautonantes.com/308703-thickbox_default/optique-phare-avant-gauche-full-led-pure-vision-260601381r-renault-clio-4-iv-origine.jpg'},
        { id: 5, name: 'Produit 1', description: 'Description 1', price: '100', image: 'https://media.autodoc.de/360_photos/1156098/h-preview.jpg' },
        { id: 6, name: 'Produit 2', description: 'Description 2', price: '100', image: 'https://oscaro.media/catalog/images//oscjpg/zoom/30/new_16_09_2022_f026400433_1.jpg'},
        { id: 7, name: 'Produit 3', description: 'Description 3', price: '100', image: 'https://media.vroomly.com/public/thumbnails/interventions/vZCHt6JOq26r_700x700_mtdhGWCw.jpg'},
        { id: 8, name: 'Produit 4', description: 'Description 4', price: '100', image: 'https://www.piecesautonantes.com/308703-thickbox_default/optique-phare-avant-gauche-full-led-pure-vision-260601381r-renault-clio-4-iv-origine.jpg'},
        { id: 9, name: 'Produit 1', description: 'Description 1', price: '100', image: 'https://media.autodoc.de/360_photos/1156098/h-preview.jpg' },
        { id: 10, name: 'Produit 2', description: 'Description 2', price: '100', image: 'https://oscaro.media/catalog/images//oscjpg/zoom/30/new_16_09_2022_f026400433_1.jpg'},
        { id: 11, name: 'Produit 3', description: 'Description 3', price: '100', image: 'https://media.vroomly.com/public/thumbnails/interventions/vZCHt6JOq26r_700x700_mtdhGWCw.jpg'},
        { id: 12, name: 'Produit 4', description: 'Description 4', price: '100', image: 'https://www.piecesautonantes.com/308703-thickbox_default/optique-phare-avant-gauche-full-led-pure-vision-260601381r-renault-clio-4-iv-origine.jpg'},
      ]
    }
  },
  setup() {
    const router = useRouter();
    let produits = ref();
    const goPanier = () => {
      router.push('/panier');
    };
    const goAdmin = () => {
      router.push('/admin');
    };

    return {goPanier, produits , goAdmin};
  },
  methods: {
    updateCart() {
      let cart = JSON.parse(localStorage.getItem('cart')) || [];
      this.totalQuantity = cart.reduce((acc, item) => acc + item.quantity, 0);
    }
  },
  async mounted() {
    const response = await ApiPiece.getAllPiece();
    this.produits = response.data.pieces;
    this.updateCart();
  }
}
</script>