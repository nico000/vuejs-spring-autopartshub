<template>
  <div class="bg-white rounded-lg overflow-hidden shadow-md hover:shadow-lg transition-shadow duration-150 ease-in-out">
    <img :src="product.image" alt="product.name" class="w-full h-48 object-cover object-center"/>
    <div class="p-4">
      <h3 class="text-lg font-semibold text-gray-800">{{ product.name }}</h3>
      <p class="text-sm text-gray-600">{{ product.description }}</p>
      <div class="flex items-center justify-between mt-3">
        <span class="text-lg font-bold text-gray-900">{{ product.price }} €</span>
        <button @click="addToCart(product)" class="px-4 py-2 bg-blue-500 hover:bg-blue-700 text-white text-sm font-medium rounded transition-colors duration-150 ease-in-out">
          Ajouter
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    addToCart(product) {
      let cart = JSON.parse(localStorage.getItem('cart')) || [];
      let found = cart.find(p => p.id === product.id);

      if (found) {
        found.quantity++;
      } else {
        cart.push({ ...product, quantity: 1 });
      }

      cart.forEach(item => {
        item.total = item.price * item.quantity;
      });

      localStorage.setItem('cart', JSON.stringify(cart));
      this.$emit('updateCart');
    }
  },
  props: {
    product: Object
  }
}
</script>