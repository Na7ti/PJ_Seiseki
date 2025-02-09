<template>
    <div class="container mx-auto p-6">
        <h1 class="text-2xl font-bold mb-6">出席登録</h1>

        <time-table :days="days" :periods="periods" @class-click="openModal" />

        <attendance-modal
            :show="showModal"
            :subject-name="selectedSubjectName"
            :dates="availableDates"
            :submitted-dates="submittedAttendances"
            :selected-subject="selectedSubject"
            @close="closeModal"
            @submit="submitAttendance"
        />

        <attendance-alert ref="alertComponent" />
    </div>
</template>

<script>
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
            showModal: false,
            selectedSubject: null,
            selectedSubjectName: "",
            submittedAttendances: new Set(),
            availableDates: this.generateDates(),
            days: [
                { id: 1, name: "月" },
                { id: 2, name: "火" },
                { id: 3, name: "水" },
                { id: 4, name: "木" },
                { id: 5, name: "金" },
            ],
            periods: [1, 2, 3, 4],
        };
    },
    methods: {
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
            this.$refs.alertComponent.addAlert(
                this.selectedSubjectName,
                this.formatDate(dateObj)
            );
            this.$forceUpdate();
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
    },
};
</script>
