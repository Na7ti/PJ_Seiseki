<template>
    <div class="container mx-auto p-6">
        <h1 class="text-2xl font-bold mb-6">アカウントページ</h1>

        <!-- アカウント情報セクション -->
        <div class="bg-white shadow rounded-lg p-6 mb-6">
            <h2 class="text-xl font-semibold mb-4">アカウント情報</h2>
            <div class="space-y-4">
                <div class="flex items-center">
                    <span class="w-32 text-gray-600">メールアドレス:</span>
                    <span>{{ userEmail }}</span>
                </div>
                <div class="flex items-center">
                    <span class="w-32 text-gray-600">ユーザー名:</span>
                    <span>{{ userName }}</span>
                </div>
                <div class="flex items-center">
                    <span class="w-32 text-gray-600">登録日:</span>
                    <span>{{ formatDate(registrationDate) }}</span>
                </div>
            </div>
        </div>

        <!-- ログアウトボタン -->
        <button
            @click="handleLogout"
            class="bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded transition duration-200"
        >
            ログアウト
        </button>
    </div>
</template>

<script>
export default {
    name: "AccountPage",
    data() {
        return {
            userEmail: "example@email.com", // 実際のユーザーメールアドレス
            userName: "ユーザー名", // 実際のユーザー名
            registrationDate: new Date("2024-01-01"), // 実際の登録日
        };
    },
    methods: {
        formatDate(date) {
            return new Intl.DateTimeFormat("ja-JP", {
                year: "numeric",
                month: "long",
                day: "numeric",
            }).format(date);
        },
        async handleLogout() {
            try {
                // ログアウト処理を実装
                // 例: await this.$store.dispatch('auth/logout');
                this.$router.push("/login");
            } catch (error) {
                console.error("ログアウトエラー:", error);
            }
        },
    },
};
</script>
