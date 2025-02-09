<template>
    <transition-group
        tag="div"
        class="fixed bottom-4 right-4 flex flex-col-reverse gap-2 z-50"
        name="alerts"
    >
        <div
            v-for="alert in activeAlerts"
            :key="alert.id"
            class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded shadow-lg flex items-center"
            role="alert"
        >
            <div class="flex-1">
                <p class="font-bold">{{ alert.subject }}</p>
                <p>{{ alert.date }}の出席を登録しました</p>
            </div>
            <button
                @click="removeAlert(alert.id)"
                class="ml-4 text-green-700 hover:text-green-900"
            >
                <span class="text-2xl">&times;</span>
            </button>
        </div>
    </transition-group>
</template>

<script>
export default {
    name: "AttendanceAlert",
    data() {
        return {
            activeAlerts: [],
        };
    },
    methods: {
        addAlert(subject, date) {
            const newAlert = {
                id: Date.now(),
                subject,
                date,
            };
            this.activeAlerts.push(newAlert);

            // 5秒後に自動削除
            setTimeout(() => {
                this.removeAlert(newAlert.id);
            }, 5000);
        },
        removeAlert(alertId) {
            this.activeAlerts = this.activeAlerts.filter(
                (alert) => alert.id !== alertId
            );
        },
    },
};
</script>

<style scoped>
.alerts-move {
    transition: all 0.5s ease;
}

.alerts-enter-active {
    transition: all 0.5s ease;
}

.alerts-leave-active {
    transition: all 0.5s ease;
    position: absolute;
}

.alerts-enter-from {
    opacity: 0;
    transform: translateY(30px);
}

.alerts-leave-to {
    opacity: 0;
    transform: translateY(30px);
}
</style>
