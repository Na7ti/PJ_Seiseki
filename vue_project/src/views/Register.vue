<template>
  <div class="container mx-auto">
    <h1 class="text-2xl font-bold">アカウント作成</h1>
    <form @submit.prevent="register" class="mt-4">
      <div class="mb-4">
        <label class="block text-gray-700">メールアドレス:</label>
        <input type="email" v-model="email" class="w-full px-3 py-2 border rounded" required>
      </div>
      <div class="mb-4">
        <label class="block text-gray-700">パスワード:</label>
        <input type="password" v-model="password" class="w-full px-3 py-2 border rounded" required>
      </div>
      <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">登録</button>
    </form>
  </div>
</template>

<script>

export default {
  name: 'RegisterPage',
  data() {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    async register() {
      try {
        const response = await this.axios.post('http://localhost:8080/api/register', {
          email: this.email,
          password: this.password
        });
        // 登録成功時の処理
        console.log(response.data);
      } catch (error) {
        // エラー処理
        console.error(error);
      }
    }
  }
}
</script>

