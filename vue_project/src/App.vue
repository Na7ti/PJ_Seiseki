<template>
    <div id="app">
        <AppHeader />
        <!-- ログインしている場合のみヘッダーを表示 -->
        <!-- <AppHeader v-if="isLoggedIn" /> -->
        <router-view />
    </div>
</template>

<script>
import AppHeader from "./components/Header.vue";

export default {
    name: "App",
    components: {
        AppHeader,
    },
    data() {
        return {
            isLoggedIn: false, // ログイン状態を管理
        };
    },
    created() {
        this.isLoggedIn = !!localStorage.getItem("token"); // ログイン状態をチェック
    },
};
</script>

<style>
/* modern-normalizeをインポート */
@import "https://cdnjs.cloudflare.com/ajax/libs/modern-normalize/3.0.1/modern-normalize.css";

/* アプリケーション全体に適用されるグローバルスタイル */
#app {
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
        "Helvetica Neue", Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
}
</style>
