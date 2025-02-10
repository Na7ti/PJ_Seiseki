<template>
    <div class="container mx-auto p-6">
        <h1 class="text-2xl font-bold mb-6">出席管理</h1>

        <!-- 本日の授業一覧を表示するカード -->
        <div class="bg-white shadow-lg rounded-lg p-6">
            <h2 class="text-xl font-semibold mb-4">本日の授業</h2>

            <!-- 本日の授業がある場合の表示 -->
            <div v-if="todaysClasses.length > 0" class="space-y-4">
                <!-- 各授業のカード -->
                <div
                    v-for="subject in todaysClasses"
                    :key="subject.id"
                    class="bg-blue-50 p-4 rounded-lg border border-blue-200"
                >
                    <div class="flex justify-between items-center">
                        <!-- 授業情報の表示部分 -->
                        <div>
                            <h3 class="font-medium text-blue-900">
                                {{ subject.name }}
                            </h3>
                            <p class="text-sm text-blue-500">
                                {{ subject.hours }}時間
                            </p>
                        </div>
                        <!-- 出席ボタン/出席済み表示の複数時限対応 -->
                        <div class="flex gap-2">
                            <template
                                v-for="period in getPeriods(subject)"
                                :key="period"
                            >
                                <button
                                    v-if="
                                        !isAttendanceSubmitted(
                                            subject.id,
                                            period
                                        )
                                    "
                                    @click="submitAttendance(subject, period)"
                                    class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition-colors"
                                >
                                    {{ period }}限目
                                </button>
                                <span
                                    v-else
                                    class="px-4 py-2 bg-gray-400 text-white rounded"
                                >
                                    {{ period }}限目済
                                </span>
                            </template>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 本日の授業がない場合の表示 -->
            <div v-else class="text-center text-gray-500 py-8">
                本日の授業はありません
            </div>
        </div>

        <!-- 出席完了通知用アラートコンポーネント -->
        <attendance-alert ref="alertComponent" />
    </div>
</template>

<script>
// 必要なコンポーネントとデータのインポート
import AttendanceAlert from "@/components/AttendanceAlert.vue";
import scheduleData from "@/data/schedule.json";

export default {
    name: "HomePage",
    components: {
        AttendanceAlert,
    },
    data() {
        return {
            // 現在の日付を管理
            currentDate: new Date(),
            // 出席済みの科目IDを保持するSet
            submittedAttendances: new Set(),
        };
    },
    computed: {
        // 本日の授業一覧を取得する算出プロパティ
        todaysClasses() {
            // 現在の曜日を取得（0: 日曜日, 1: 月曜日, ...）
            const today = 3;
            // 曜日の数値と文字列のマッピング
            const dayMap = {
                1: "monday",
                2: "tuesday",
                3: "wednesday",
                4: "thursday",
                5: "friday",
            };
            // schedule.jsonから本日の授業を取得（存在しない場合は空配列を返す）
            return scheduleData.schedule[dayMap[today]]?.classes || [];
        },
    },
    methods: {
        // 出席登録の処理（時限を追加）
        submitAttendance(subject, period) {
            // 出席記録のユニークキーを生成（時限を含める）
            const attendanceKey = `${subject.id}-${period}`;
            this.submittedAttendances.add(attendanceKey);

            // アラートコンポーネントを通じて通知を表示
            this.$refs.alertComponent.addAlert(
                `${subject.name} ${period}限目`,
                this.formatDate(this.currentDate)
            );
        },
        // 出席済みかどうかをチェック（時限を含める）
        isAttendanceSubmitted(subjectId, period) {
            const attendanceKey = `${subjectId}-${period}`;
            return this.submittedAttendances.has(attendanceKey);
        },
        // 科目の時限配列を生成
        getPeriods(subject) {
            const periods = [];
            const startPeriod = subject.period || 1;
            for (let i = 0; i < subject.hours; i++) {
                periods.push(startPeriod + i);
            }
            return periods;
        },
        // アラートを閉じる処理
        closeAlert(alertId) {
            this.alerts = this.alerts.filter((alert) => alert.id !== alertId);
        },
        // 日付のフォーマット処理
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
