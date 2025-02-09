<template>
    <div class="container mx-auto p-6">
        <h1 class="text-2xl font-bold mb-6">出席管理アプリ</h1>

        <div class="bg-white shadow-lg rounded-lg p-6">
            <h2 class="text-xl font-semibold mb-4">本日の授業</h2>

            <div v-if="todaysClasses.length > 0" class="space-y-4">
                <div
                    v-for="subject in todaysClasses"
                    :key="subject.id"
                    class="bg-blue-50 p-4 rounded-lg border border-blue-200"
                >
                    <div class="flex justify-between items-center">
                        <div>
                            <h3 class="font-medium text-blue-900">
                                {{ subject.name }}
                            </h3>
                            <p class="text-sm text-blue-500">
                                {{ subject.hours }}時間
                            </p>
                        </div>
                        <button
                            v-if="!isAttendanceSubmitted(subject.id)"
                            @click="submitAttendance(subject)"
                            class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition-colors"
                        >
                            出席する
                        </button>
                        <span
                            v-else
                            class="px-4 py-2 bg-gray-400 text-white rounded"
                        >
                            出席済み
                        </span>
                    </div>
                </div>
            </div>

            <div v-else class="text-center text-gray-500 py-8">
                本日の授業はありません
            </div>
        </div>

        <attendance-alert ref="alertComponent" />
    </div>
</template>

<script>
import AttendanceAlert from "@/components/AttendanceAlert.vue";
import scheduleData from "@/data/schedule.json";

export default {
    name: "HomePage",
    components: {
        AttendanceAlert,
    },
    data() {
        return {
            currentDate: new Date(),
            submittedAttendances: new Set(),
        };
    },
    computed: {
        todaysClasses() {
            const today = this.currentDate.getDay();
            const dayMap = {
                1: "monday",
                2: "tuesday",
                3: "wednesday",
                4: "thursday",
                5: "friday",
            };
            return scheduleData.schedule[dayMap[today]]?.classes || [];
        },
    },
    methods: {
        submitAttendance(subject) {
            this.submittedAttendances.add(subject.id);
            this.$refs.alertComponent.addAlert(
                subject.name,
                this.formatDate(this.currentDate)
            );
        },
        isAttendanceSubmitted(subjectId) {
            return this.submittedAttendances.has(subjectId);
        },
        closeAlert(alertId) {
            this.alerts = this.alerts.filter((alert) => alert.id !== alertId);
        },
        formatDate(date) {
            return date.toLocaleDateString("ja-JP", {
                year: "numeric",
                month: "long",
                day: "numeric",
                weekday: "long",
            });
        },
    },
};
</script>
