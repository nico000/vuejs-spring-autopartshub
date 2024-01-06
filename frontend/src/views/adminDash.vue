<template>
  <div v-if="isAuthenticated" class="min-h-screen bg-gray-100">
    <header class="bg-white shadow py-3 text-center">
      <img src="../assets/logo.png" alt="Logo de l'entreprise" class="h-10 w-auto rounded-full mx-auto">
      <h1 class="text-2xl font-bold">Interface administrateur</h1>
    </header>
    <main class="pt-4">
      <div class="max-w-6xl mx-auto py-6 sm:px-6 lg:px-8 text-center">
        <button @click="addProduit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mb-4 inline-block">
          Ajouter une pièce
        </button>
        <button @click="quitAdminMode" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded mb-4 inline-block ml-4">
          Quitter mode admin
        </button>
        <div class="bg-white p-6 rounded-lg shadow-lg mt-4">
          <ul>
            <li v-for="item in items" :key="item.id" class="flex justify-between items-center mb-2">
              <div class="flex items-center">
                <img :src="item.image" alt="item.name" class="h-20 w-20 rounded-full mr-4">
                <span>{{ item.name }}</span>
              </div>
              <div>
                <button @click="modifier(item.id)" class="text-blue-500 hover:text-blue-700 mr-2">
                  <svg class="h-6 w-6 inline" fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="1" viewBox="0 0 24 24" stroke="currentColor">
                    <polygon points="14 2 18 6 7 17 3 17 3 13 14 2"></polygon><line x1="3" y1="22" x2="21" y2="22"></line>
                  </svg>
                </button>
                <button @click="deleteArticle(item.id)" class="text-red-500 hover:text-red-700">
                  <svg class="h-6 w-6 inline" fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="1" viewBox="0 0 24 24" stroke="currentColor">
                    <polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line>
                  </svg>
                </button>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import router from "@/router/index.js";
import ApiPiece from "@/API/ApiPiece.js";

export default {
  data() {
    return {
      items: [
        { id: 1, name: 'Produit 1', description: 'Description 1', price: '100€', image: 'https://media.autodoc.de/360_photos/1156098/h-preview.jpg' },
        { id: 2, name: 'Produit 2', description: 'Description 2', price: '100€', image: 'https://oscaro.media/catalog/images//oscjpg/zoom/30/new_16_09_2022_f026400433_1.jpg'},
        { id: 3, name: 'Produit 3', description: 'Description 3', price: '100€', image: 'https://media.vroomly.com/public/thumbnails/interventions/vZCHt6JOq26r_700x700_mtdhGWCw.jpg'},
        { id: 4, name: 'Produit 4', description: 'Description 4', price: '100€', image: 'https://www.piecesautonantes.com/308703-thickbox_default/optique-phare-avant-gauche-full-led-pure-vision-260601381r-renault-clio-4-iv-origine.jpg'},
        { id: 5, name: 'Produit 1', description: 'Description 1', price: '100€', image: 'https://media.autodoc.de/360_photos/1156098/h-preview.jpg' },
        { id: 6, name: 'Produit 2', description: 'Description 2', price: '100€', image: 'https://oscaro.media/catalog/images//oscjpg/zoom/30/new_16_09_2022_f026400433_1.jpg'},
        { id: 7, name: 'Produit 3', description: 'Description 3', price: '100€', image: 'https://media.vroomly.com/public/thumbnails/interventions/vZCHt6JOq26r_700x700_mtdhGWCw.jpg'},
        { id: 8, name: 'Produit 4', description: 'Description 4', price: '100€', image: 'https://www.piecesautonantes.com/308703-thickbox_default/optique-phare-avant-gauche-full-led-pure-vision-260601381r-renault-clio-4-iv-origine.jpg'},
        { id: 9, name: 'Produit 1', description: 'Description 1', price: '100€', image: 'https://media.autodoc.de/360_photos/1156098/h-preview.jpg' },
        { id: 10, name: 'Produit 2', description: 'Description 2', price: '100€', image: 'https://oscaro.media/catalog/images//oscjpg/zoom/30/new_16_09_2022_f026400433_1.jpg'},
        { id: 11, name: 'Produit 3', description: 'Description 3', price: '100€', image: 'https://media.vroomly.com/public/thumbnails/interventions/vZCHt6JOq26r_700x700_mtdhGWCw.jpg'},
        { id: 12, name: 'Produit 4', description: 'Description 4', price: '100€', image: 'https://www.piecesautonantes.com/308703-thickbox_default/optique-phare-avant-gauche-full-led-pure-vision-260601381r-renault-clio-4-iv-origine.jpg'},
      ],
      isAuthenticated: false
    };
  },
  async mounted() {
    const response = await ApiPiece.getAllPiece();
    this.items = response.data.pieces;
    this.updateCart();
  },
  created() {
    this.authenticate();
  },
  methods: {
    authenticate() {
      const authLocal =  localStorage.getItem('auth');
      let password = '';
      if (authLocal !== 'true') {
        password = prompt("Veuillez entrer le mot de passe pour accéder à l'interface administrateur:");
      } else {
        password = 'admin';
      }

      if (password === 'admin') {
        this.isAuthenticated = true;
        localStorage.setItem('auth', 'true');
      } else {
        router.push('/');
      }
    },
    quitAdminMode() {
      localStorage.removeItem('auth');
      router.push('/');
    },
    addProduit() {
      router.push('/ajouter');
    },
    modifier(id) {
      router.push('/modifier/' + id);
    },
    deleteArticle(id) {
      ApiPiece.deletePiece(id);
      this.items = this.items.filter(item => item.id !== id);
    }
  }
}
</script>
