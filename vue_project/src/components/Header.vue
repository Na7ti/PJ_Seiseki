<template>
  <header class="bg-gray-800 text-white p-4">
    <h1 class="text-xl">出席管理アプリ</h1>
    <nav>
      <ul class="flex space-x-4">
        <li><router-link to="/home">ホーム</router-link></li>
        <li><router-link to="/students">学生一覧</router-link></li>
        <li><router-link to="/account">アカウント</router-link></li>
        <li><router-link to="/login">ログイン</router-link></li>
      </ul>
    </nav>
  </header>
</template>

<script>
export default {
  name: 'AppHeader'
}
</script>

<style scoped>
/* Tailwind CSSを使用するため、特別なスタイルは不要です */
</style>
