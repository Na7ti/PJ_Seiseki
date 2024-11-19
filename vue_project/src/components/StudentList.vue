<template>
	<div>
	  <h2>ユーザー一覧</h2>
	  <ul>
		<li v-for="user in users" :key="user.id">{{ user.name }}</li>
	  </ul>
	</div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
	data() {
	  return {
		users: []
	  }
	},
	mounted() {
	  this.fetchUsers();
	},
	methods: {
	  async fetchUsers() {
		const response = await axios.get('http://localhost:8080/api/students');
		this.users = response.data;
	  }
	}
  }
  </script>
