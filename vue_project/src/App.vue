<template>
  <div id="app">
    <AppHeader />
    <router-view />
  </div>
</template>

<script>
import AppHeader from './components/Header.vue'

export default {
  name: 'App',
  components: {
    AppHeader
  }
}
</script>

<style>
/* 必要に応じてスタイルを追加 */
</style>
