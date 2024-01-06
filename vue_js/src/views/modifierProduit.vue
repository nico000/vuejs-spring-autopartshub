<template>
  <div class="min-h-screen bg-gray-100">
    <header class="bg-white shadow py-3 text-center">
      <img src="../assets/logo.png" alt="Logo de l'entreprise" class="h-10 w-auto rounded-full mx-auto">
      <h1 class="text-2xl font-bold">Interface administrateur</h1>
    </header>
    <main class="pt-4">
      <div class="max-w-2xl mx-auto py-6 sm:px-6 lg:px-8">
        <div class="bg-white p-6 rounded-lg shadow-lg">
          <h2 class="text-2xl font-bold mb-4">Modifier une pièce</h2>
          <form @submit.prevent="submitForm">
            <div class="mb-4">
              <label class="block text-gray-700 text-sm font-bold mb-2" for="name">Nom</label>
              <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="name" type="text" placeholder="Nom" v-model="item.name">
            </div>

            <div class="mb-4">
              <label class="block text-gray-700 text-sm font-bold mb-2" for="description">Description</label>
              <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="description" type="text" placeholder="Description" v-model="item.description">
            </div>

            <div class="mb-4">
              <label class="block text-gray-700 text-sm font-bold mb-2" for="price">Prix</label>
              <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="price" type="number" step=".01" placeholder="Prix" v-model="item.price">
            </div>

            <div class="mb-6">
              <label class="block text-gray-700 text-sm font-bold mb-2" for="image">Image URL</label>
              <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="image" type="text" placeholder="URL de l'image" v-model="item.image">
            </div>

            <div class="flex items-center justify-center">
              <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit">
                Modifier
              </button>
            </div>
          </form>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import {useRoute} from "vue-router";
import ApiPiece from "@/API/ApiPiece.js";
export default {
  data() {
    return {
      item: {
        name: '',
        description: '',
        price: '',
        image: ''
      }
    };
  },
  async mounted() {
    const route = useRoute();
    const response = await ApiPiece.getPiece(route.params.id);
    const infos = response.data;

    this.name = infos.name;
    this.description = infos.description;
    this.price = infos.price;
    this.image = infos.image;
  },
  methods: {
    submitForm() {
      if (this.item.name !== '' && this.item.description !== '' && this.item.image !== '' && this.item.image !== '') {
        ApiPiece.updatePiece(useRoute().params.id, this.item);
      } else {
        alert('Tous les champs doivent être renseignés.');
      }
    }
  }
}
</script>