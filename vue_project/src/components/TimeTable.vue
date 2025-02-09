<template>
    <!-- 時間割テーブルのメインコンテナ -->
    <div class="bg-white shadow-lg rounded-lg overflow-hidden">
        <table class="w-full">
            <!-- テーブルヘッダー（曜日の表示） -->
            <thead>
                <tr>
                    <th class="p-3 border-b border-r bg-gray-100"></th>
                    <th
                        v-for="day in days"
                        :key="day.id"
                        class="p-3 border-b bg-gray-100 w-1/5"
                    >
                        {{ day.name }}
                    </th>
                </tr>
            </thead>
            <!-- テーブルボディ（時限と授業の表示） -->
            <tbody>
                <!-- 時限ごとの行 -->
                <tr v-for="period in periods" :key="period">
                    <!-- 時限表示のセル -->
                    <td class="p-3 border-b border-r bg-gray-50 text-center">
                        {{ period }}限
                    </td>
                    <!-- 各曜日の授業セル -->
                    <td
                        v-for="day in days"
                        :key="day.id"
                        class="p-2 border-b border-r h-24"
                    >
                        <!-- 授業がある場合のボタン表示 -->
                        <button
                            v-if="getClassForTimeSlot(day.id, period)"
                            @click="
                                onClassClick(
                                    getClassForTimeSlot(day.id, period)
                                )
                            "
                            :class="[
                                'w-full h-full p-2 rounded text-white text-left',
                                // 今日の授業は赤色、それ以外は青色で表示
                                isToday(day.id)
                                    ? 'bg-red-500 hover:bg-red-600'
                                    : 'bg-blue-500 hover:bg-blue-600',
                            ]"
                        >
                            <!-- 授業情報の表示 -->
                            <div class="text-sm">
                                {{ getClassForTimeSlot(day.id, period).name }}
                            </div>
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
// スケジュールデータのインポート
import scheduleData from "@/data/schedule.json";

export default {
    name: "TimeTable",
    // 親コンポーネントから受け取るプロパティ
    props: {
        days: {
            type: Array,
            required: true,
        },
        periods: {
            type: Array,
            required: true,
        },
    },
    // 親コンポーネントに発行するイベント
    emits: ["class-click"],
    methods: {
        // 指定された曜日と時限の授業を取得
        getClassForTimeSlot(dayId, period) {
            // 曜日IDと曜日名のマッピング
            const dayMap = {
                1: "monday",
                2: "tuesday",
                3: "wednesday",
                4: "thursday",
                5: "friday",
            };

            // schedule.jsonから該当する曜日のスケジュールを取得
            const daySchedule = scheduleData.schedule[dayMap[dayId]];
            if (!daySchedule) return null;

            // 指定された時限で開始する授業を検索
            const startingClass = daySchedule.classes.find(
                (classData) => classData.period === period
            );
            if (startingClass) return startingClass;

            // 前の時限から継続している授業を検索
            const continuingClass = daySchedule.classes.find((classData) => {
                const classStartPeriod = classData.period;
                const classEndPeriod = classData.period + (classData.hours - 1);
                return period > classStartPeriod && period <= classEndPeriod;
            });

            return continuingClass || null;
        },
        // 指定された曜日が今日かどうかをチェック
        isToday(dayId) {
            return new Date().getDay() === dayId;
        },
        // 授業クリック時のイベント発行
        onClassClick(subject) {
            this.$emit("class-click", subject);
        },
    },
};
</script>
