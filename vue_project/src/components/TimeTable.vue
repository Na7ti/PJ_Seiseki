<template>
    <div class="bg-white shadow-lg rounded-lg overflow-hidden">
        <table class="w-full">
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
            <tbody>
                <tr v-for="period in periods" :key="period">
                    <td class="p-3 border-b border-r bg-gray-50 text-center">
                        {{ period }}限
                    </td>
                    <td
                        v-for="day in days"
                        :key="day.id"
                        class="p-2 border-b border-r h-24"
                    >
                        <button
                            v-if="getClassForTimeSlot(day.id, period)"
                            @click="
                                onClassClick(
                                    getClassForTimeSlot(day.id, period)
                                )
                            "
                            :class="[
                                'w-full h-full p-2 rounded text-white text-left',
                                isToday(day.id)
                                    ? 'bg-red-500 hover:bg-red-600'
                                    : 'bg-blue-500 hover:bg-blue-600',
                            ]"
                        >
                            <div class="text-sm">
                                {{ getClassForTimeSlot(day.id, period).name }}
                                <!-- 時間数の表示 -->
                                <!-- <div class="text-xs opacity-80">
                                    {{
                                        getClassForTimeSlot(day.id, period)
                                            .hours
                                    }}時間
                                </div> -->
                            </div>
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import scheduleData from "@/data/schedule.json";

export default {
    name: "TimeTable",
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
    emits: ["class-click"],
    methods: {
        getClassForTimeSlot(dayId, period) {
            const dayMap = {
                1: "monday",
                2: "tuesday",
                3: "wednesday",
                4: "thursday",
                5: "friday",
            };

            const daySchedule = scheduleData.schedule[dayMap[dayId]];
            if (!daySchedule) return null;

            const startingClass = daySchedule.classes.find(
                (classData) => classData.period === period
            );
            if (startingClass) return startingClass;

            const continuingClass = daySchedule.classes.find((classData) => {
                const classStartPeriod = classData.period;
                const classEndPeriod = classData.period + (classData.hours - 1);
                return period > classStartPeriod && period <= classEndPeriod;
            });

            return continuingClass || null;
        },
        isToday(dayId) {
            return new Date().getDay() === dayId;
        },
        onClassClick(subject) {
            this.$emit("class-click", subject);
        },
    },
};
</script>
