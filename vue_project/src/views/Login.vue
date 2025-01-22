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
                :disabled="isLoading"
            >
                {{ isLoading ? "ログイン中..." : "ログイン" }}
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
import axios from "axios";

export default {
    name: "LoginPage",
    data() {
        return {
            email: "",
            emailError: "",
            isLoading: false, // ローディング状態を管理するフラグ
        };
    },
    methods: {
        // メールアドレスのバリデーション
        validateEmail() {
            const re =
                /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@(([^<>()[\]\\.,;:\s@"]+\.)+[^<>()[\]\\.,;:\s@"]{2,})$/i;
            return re.test(String(this.email).toLowerCase());
        },
        async login() {
            if (this.isLoading) return;
            this.isLoading = true;
            this.emailError = "";

            try {
                // Spring Boot APIへのリクエスト
                const response = await axios.post(
                    "http://localhost:8080/api/login",
                    {
                        email: this.email,
                    },
                    {
                        headers: {
                            "Content-Type": "application/json",
                        },
                    }
                );

                // レスポンス処理
                if (response.data.success) {
                    // JWTトークンをセッションストレージに保存
                    sessionStorage.setItem("token", response.data.token);
                    this.$router.push("/home");
                } else {
                    this.emailError = response.data.message;
                }
            } catch (error) {
                // エラーハンドリング
                if (error.code === "ERR_NETWORK") {
                    this.emailError =
                        "サーバーに接続できません。サーバーが起動していることを確認してください。";
                } else if (error.response) {
                    this.emailError = `エラー (${error.response.status}): ${
                        error.response.data.message || "不明なエラー"
                    }`;
                } else if (error.request) {
                    this.emailError = "サーバーからの応答がありません。";
                } else {
                    this.emailError = "ログイン処理中にエラーが発生しました。";
                }
                console.error("ログインエラーの詳細:", error);
            } finally {
                this.isLoading = false;
            }
        },
    },
};
</script>
