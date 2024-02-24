package io.github.ronjunevaldoz.kmp_calendar

import kotlinx.datetime.LocalDate


data class CalendarDay(
    val date: LocalDate,
    val isCurrentMonth: Boolean,
    val isToday: Boolean = false
)