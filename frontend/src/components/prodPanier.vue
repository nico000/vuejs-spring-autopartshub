<template>
  <div class="flex items-center justify-between border-b pb-4 mb-4">
    <div class="flex items-center">
      <img :src="item.image" alt="item.name" class="h-16 w-16 rounded-full object-cover mr-4">
      <div>
        <h3 class="text-lg font-semibold">{{ item.name }}</h3>
        <p class="text-sm">{{ item.description }}</p>
      </div>
    </div>
    <div>
      <span class="text-lg font-bold">{{ item.price }} €</span>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    item: Object
  }
}
</script>
