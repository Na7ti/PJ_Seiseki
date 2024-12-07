<template>
    <div>
        <h2>学生一覧</h2>
        <ul>
            <li v-for="user in users" :key="user.id">{{ user.name }}</li>
        </ul>
    </div>
</template>

<script>
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080';

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
            try {
                const response = await axios.get('/api/students');
                this.users = response.data;
            } catch (error) {
                if (error.response) {
                    console.error('Error response:', error.response.data);
                } else if (error.request) {
                    console.error('Error request:', error.request);
                } else {
                    console.error('Error message:', error.message);
                }
            }
        }
    }
}
</script>
