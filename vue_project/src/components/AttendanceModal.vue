<template>
    <div
        v-if="show"
        class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center"
    >
        <div
            class="bg-white p-6 rounded-lg shadow-lg w-96 max-h-[80vh] flex flex-col"
        >
            <h2 class="text-xl font-bold mb-4">
                {{ subjectName }}
            </h2>
            <p class="mb-4">出席する日付を選択してください：</p>

            <div class="overflow-y-auto flex-1">
                <div class="space-y-2">
                    <div
                        v-for="date in dates"
                        :key="date.id"
                        class="flex items-center justify-between p-3 bg-gray-50 rounded hover:bg-gray-100"
                    >
                        <span>{{ formatDate(date) }}</span>
                        <button
                            v-if="!isAttendanceSubmitted(date)"
                            @click="onSubmit(date)"
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

            <div class="mt-4 pt-4 border-t flex justify-end">
                <button
                    @click="$emit('close')"
                    class="px-4 py-2 bg-gray-300 text-gray-700 rounded hover:bg-gray-400"
                >
                    閉じる
                </button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "AttendanceModal",
    props: {
        show: Boolean,
        subjectName: String,
        dates: Array,
        submittedDates: Set,
        selectedSubject: Number,
    },
    emits: ["close", "submit"],
    methods: {
        isAttendanceSubmitted(dateObj) {
            // dateObjからattendanceKeyを生成
            const attendanceKey = `${this.selectedSubject}-${dateObj.id}`;
            return this.submittedDates.has(attendanceKey);
        },
        onSubmit(date) {
            if (!this.isAttendanceSubmitted(date)) {
                this.$emit("submit", date);
            }
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
