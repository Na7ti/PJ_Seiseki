<template>
    <header class="bg-gray-800 text-white p-4">
        <div class="flex justify-between items-center">
            <div>
                <h1 class="text-3xl font-bold mb-4">出席管理アプリ</h1>
                <nav>
                    <ul class="flex space-x-6">
                        <li>
                            <router-link
                                to="/home"
                                class="hover:text-gray-300 px-3 py-2"
                            >
                                ホーム
                            </router-link>
                        </li>
                        <li>
                            <router-link
                                to="/attendance"
                                class="hover:text-gray-300 px-3 py-2"
                            >
                                科目一覧
                            </router-link>
                        </li>
                        <li>
                            <router-link
                                to="/students"
                                class="hover:text-gray-300 px-3 py-2"
                            >
                                学生一覧
                            </router-link>
                        </li>
                    </ul>
                </nav>
            </div>
            <div>
                <router-link
                    to="/account"
                    class="hover:text-gray-300 px-3 py-2"
                >
                    アカウント
                </router-link>
            </div>
        </div>
    </header>
</template>

<script>
export default {
    name: "AppHeader",
};
</script>
