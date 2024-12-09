<template>
  <div class="container mx-auto">
    <h1 class="text-2xl font-bold">出席管理アプリ</h1>
    <StudentList />
  </div>
</template>

<script>
import StudentList from '../components/StudentList.vue';

export default {
  name: 'HomePage',
  components: {
    StudentList,
  }
}
</script>

<style scoped>
/* Tailwind CSSを使用するため、特別なスタイルは不要です */
</style>
