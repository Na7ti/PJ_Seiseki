<template>
    <header class="bg-gray-800 text-white p-4">
        <h1 class="text-xl">出席管理アプリ</h1>
        <div>
            <div><router-link to="/home">ホーム</router-link></div>
            <div><router-link to="/students">学生一覧</router-link></div>
            <div><router-link to="/account">アカウント</router-link></div>
        </div>
    </header>
</template>

<script>
export default {
    name: "AppHeader",
};
</script>

<style scoped>
div {
    display: inline;
    margin-right: 10px;
}
</style>
