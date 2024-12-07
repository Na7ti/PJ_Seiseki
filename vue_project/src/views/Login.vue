<template>
  <div class="container mx-auto">
    <h1 class="text-2xl font-bold">ログイン</h1>
    <form @submit.prevent="login" class="mt-4">
      <div class="mb-4">
        <label class="block text-gray-700">メールアドレス:</label>
        <input type="email" v-model="email" class="w-full px-3 py-2 border rounded" required>
      </div>
      <div class="mb-4">
        <label class="block text-gray-700">パスワード:</label>
        <input type="password" v-model="password" class="w-full px-3 py-2 border rounded" required>
      </div>
      <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">ログイン</button>
    </form>
    <p class="mt-4">
      アカウントをお持ちでない場合は
      <router-link to="/register" class="text-blue-500">こちら</router-link>
      から作成してください。
    </p>
  </div>
</template>

<script>

export default {
  name: 'LoginPage',
  data() {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    async login() {
      try {
        const response = await this.axios.post('http://localhost:8080/api/login', {
          email: this.email,
          password: this.password
        });
        // ログイン成功時の処理
        console.log(response.data);
      } catch (error) {
        // エラー処理
        console.error(error);
      }
    }
  }
}
</script>

<style scoped>
/* Tailwind CSSを使用するため、特別なスタイルは不要です */
</style>
