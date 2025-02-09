<template>
    <div class="container mx-auto p-6">
        <h1 class="text-2xl font-bold mb-6">出席登録</h1>

        <div class="flex flex-col w-full">
            <button
                v-for="subject in subjects"
                :key="subject.id"
                @click="openModal(subject)"
                :class="[
                    'w-full p-4 text-white text-left border-b border-blue-400 first:rounded-t-lg last:rounded-b-lg last:border-none transition-colors',
                    hasClassToday(subject)
                        ? 'bg-red-500 hover:bg-red-600'
                        : 'bg-blue-500 hover:bg-blue-600',
                ]"
            >
                <div class="flex justify-between items-center">
                    <div>
                        <h3 class="font-medium">{{ subject.name }}</h3>
                        <p class="text-sm text-white/80">
                            {{ subject.description }}
                        </p>
                        <p class="text-sm">週{{ subject.totalHours }}時間</p>
                    </div>
                    <div class="text-sm" v-if="hasClassToday(subject)">
                        本日の授業
                    </div>
                </div>
            </button>
        </div>

        <!-- モーダル -->
        <div
            v-if="showModal"
            class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center"
        >
            <div
                class="bg-white p-6 rounded-lg shadow-lg w-96 max-h-[80vh] flex flex-col"
            >
                <h2 class="text-xl font-bold mb-4">
                    {{ selectedSubjectName }}
                </h2>
                <p class="mb-4">出席する日付を選択してください：</p>

                <!-- スクロール可能な日付リスト -->
                <div class="overflow-y-auto flex-1">
                    <div class="space-y-2">
                        <div
                            v-for="date in availableDates"
                            :key="date.id"
                            class="flex items-center justify-between p-3 bg-gray-50 rounded hover:bg-gray-100"
                        >
                            <span>{{ formatDate(date) }}</span>
                            <button
                                v-if="!isAttendanceSubmitted(date)"
                                @click="submitAttendance(date)"
                                class="px-3 py-1 bg-green-500 text-white rounded hover:bg-green-600"
                            >
                                出席
                            </button>
                            <span
                                v-else
                                class="px-3 py-1 bg-gray-400 text-white rounded"
                            >
                                出席済み
                            </span>
                        </div>
                    </div>
                </div>

                <!-- フッター -->
                <div class="mt-4 pt-4 border-t flex justify-end">
                    <button
                        @click="closeModal"
                        class="px-4 py-2 bg-gray-300 text-gray-700 rounded hover:bg-gray-400"
                    >
                        閉じる
                    </button>
                </div>
            </div>
        </div>

        <!-- アラートコンポーネント -->
        <attendance-alert :alerts="alerts" @close="closeAlert" />
        <attendance-alert ref="alertComponent" />
    </div>
</template>

<script>
import AttendanceAlert from "@/components/AttendanceAlert.vue";
import subjectData from "@/data/subject.json";

export default {
    name: "AttendancePage",
    components: {
        AttendanceAlert,
    },
    data() {
        return {
            showModal: false,
            selectedSubject: null,
            selectedSubjectName: "",
            submittedAttendances: new Set(),
            availableDates: this.generateDates(),
            subjects: subjectData.subjects,
            alerts: [],
        };
    },
    methods: {
        hasClassToday(subject) {
            const today = new Date().getDay();
            return subject.schedules.some((schedule) => schedule.day === today);
        },
        openModal(subject) {
            this.selectedSubject = subject.id;
            this.selectedSubjectName = subject.name;
            this.showModal = true;
        },
        closeModal() {
            this.showModal = false;
        },
        submitAttendance(dateObj) {
            const attendanceKey = `${this.selectedSubject}-${dateObj.id}`;
            this.submittedAttendances.add(attendanceKey);

            // アラートコンポーネントのaddAlertメソッドを呼び出し
            this.$refs.alertComponent.addAlert(
                this.selectedSubjectName,
                this.formatDate(dateObj)
            );
        },
        closeAlert(alertId) {
            this.alerts = this.alerts.filter((alert) => alert.id !== alertId);
        },
        generateDates() {
            const dates = [];
            const today = new Date();

            // 今日から過去30日分の日付を生成
            for (let i = 0; i < 30; i++) {
                const date = new Date(today);
                date.setDate(today.getDate() - i);
                dates.push({
                    id: i + 1,
                    date: date,
                });
            }
            return dates;
        },
        formatDate(dateObj) {
            const options = {
                year: "numeric",
                month: "long",
                day: "numeric",
                weekday: "long",
            };
            return dateObj.date.toLocaleDateString("ja-JP", options);
        },
        isAttendanceSubmitted(dateObj) {
            const attendanceKey = `${this.selectedSubject}-${dateObj.id}`;
            return this.submittedAttendances.has(attendanceKey);
        },
    },
};
</script>
