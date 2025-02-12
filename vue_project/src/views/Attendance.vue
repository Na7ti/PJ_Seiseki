<template>
    <div class="container mx-auto p-6">
        <h1 class="text-2xl font-bold mb-6">出席登録</h1>

        <!-- 時間割テーブルコンポーネント -->
        <time-table :days="days" :periods="periods" @class-click="openModal" />

        <!-- 出席登録用モーダルコンポーネント -->
        <attendance-modal
            :show="showModal"
            :subject-name="selectedSubjectName"
            :dates="availableDates"
            :submitted-dates="submittedAttendances"
            :selected-subject="selectedSubject"
            @close="closeModal"
            @submit="submitAttendance"
        />

        <!-- 出席完了通知用アラートコンポーネント -->
        <attendance-alert ref="alertComponent" />
    </div>
</template>

<script>
// 必要なコンポーネントのインポート
import TimeTable from "@/components/TimeTable.vue";
import AttendanceModal from "@/components/AttendanceModal.vue";
import AttendanceAlert from "@/components/AttendanceAlert.vue";

export default {
    name: "AttendancePage",
    components: {
        TimeTable,
        AttendanceModal,
        AttendanceAlert,
    },
    data() {
        return {
            // モーダルの表示状態
            showModal: false,
            // 選択された科目の情報
            selectedSubject: null,
            selectedSubjectName: "",
            // 出席済みの記録を保持するSet
            submittedAttendances: new Set(),
            // 出席可能な日付のリスト
            availableDates: this.generateDates(),
            // 曜日の定義
            days: [
                { id: 1, name: "月" },
                { id: 2, name: "火" },
                { id: 3, name: "水" },
                { id: 4, name: "木" },
                { id: 5, name: "金" },
            ],
            // 時限の定義
            periods: [1, 2, 3, 4],
        };
    },
    methods: {
        // 科目選択時のモーダル表示処理
        openModal(subject) {
            this.selectedSubject = subject.id;
            this.selectedSubjectName = subject.name;
            this.showModal = true;
        },
        // モーダルを閉じる処理
        closeModal() {
            this.showModal = false;
        },
        // 出席登録の処理
        submitAttendance(dateObj) {
            // 出席記録のユニークキーを生成
            const attendanceKey = `${this.selectedSubject}-${dateObj.id}`;
            // 出席記録をSetに追加
            this.submittedAttendances.add(attendanceKey);
            // アラート表示
            this.$refs.alertComponent.addAlert(
                this.selectedSubjectName,
                this.formatDate(dateObj)
            );
            // 画面の再描画を強制
            this.$forceUpdate();
        },
        // 過去30日分の日付を生成
        generateDates() {
            const dates = [];
            const today = new Date();

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
        // 日付のフォーマット処理
        formatDate(dateObj) {
            const options = {
                year: "numeric",
                month: "long",
                day: "numeric",
                weekday: "long",
            };
            return dateObj.date.toLocaleDateString("ja-JP", options);
        },
    },
};
</script>
