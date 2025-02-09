<template>
    <div class="container mx-auto">
        <h1 class="text-2xl font-bold">ログイン</h1>
        <form @submit.prevent="login" class="mt-4">
            <div class="mb-4">
                <label class="block text-gray-700">メールアドレス:</label>
                <input
                    type="email"
                    v-model="email"
                    class="w-full px-3 py-2 border rounded"
                    required
                />
                <span v-if="emailError" class="text-red-500">{{
                    emailError
                }}</span>
                <!-- メールアドレスのエラーメッセージ -->
            </div>
            <button
                type="submit"
                class="bg-blue-500 text-white px-4 py-2 rounded"
            >
                ログイン
            </button>
        </form>
        <p class="mt-4">
            アカウントをお持ちでない場合は
            <router-link to="/register" class="text-blue-500"
                >こちら</router-link
            >
            から作成してください。
        </p>
    </div>
</template>

<script>
export default {
    name: "LoginPage",
    data() {
        return {
            email: "",
            emailError: "", // メールアドレスのエラーメッセージ
        };
    },
    methods: {
        validateEmail() {
            const re =
                /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@(([^<>()[\]\\.,;:\s@"]+\.)+[^<>()[\]\\.,;:\s@"]{2,})$/i;
            return re.test(String(this.email).toLowerCase());
        },
        async login() {
            this.emailError = ""; // エラーメッセージをリセット

            if (!this.validateEmail()) {
                this.emailError = "有効なメールアドレスを入力してください。";
                return;
            }

            try {
                const response = await this.axios.post(
                    "http://localhost:8080/api/login",
                    {
                        email: this.email,
                    }
                );
                // ログイン成功時の処理
                localStorage.setItem("token", response.data.token); // トークンを保存
                this.$router.push("/"); // ホームページにリダイレクト
            } catch (error) {
                // エラー処理
                console.error(error);
            }
        },
    },
};
</script>
