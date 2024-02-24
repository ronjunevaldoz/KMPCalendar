package io.github.ronjunevaldoz.kmp_calendar

import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn


val today: LocalDate
    get() {
        return Clock.System.todayIn(TimeZone.currentSystemDefault())
    }

val calendarWeekOrder = listOf(
    DayOfWeek.SUNDAY,
    DayOfWeek.MONDAY,
    DayOfWeek.TUESDAY,
    DayOfWeek.WEDNESDAY,
    DayOfWeek.THURSDAY,
    DayOfWeek.FRIDAY,
    DayOfWeek.SATURDAY,
)

val DayOfWeek.short: String
    get() = when (this) {
        DayOfWeek.SUNDAY -> "Sun"
        DayOfWeek.MONDAY -> "Mon"
        DayOfWeek.TUESDAY -> "Tue"
        DayOfWeek.WEDNESDAY -> "Wed"
        DayOfWeek.THURSDAY -> "Thu"
        DayOfWeek.FRIDAY -> "Fri"
        DayOfWeek.SATURDAY -> "Sat"
        else -> TODO("not implemented")
    }

val LocalDate.calendarLabel: String
    get() = "${dayOfWeek.short}, ${monthNumber.monthShortName} ${dayOfMonth}, $year"
val LocalDate.calendarTitle: String
    get() = "${
        month.name.lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    } $year"

val months =
    listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
val Int.monthShortName: String
    get() = if (this in 1..12) {
        months[this - 1]
    } else {
        throw IllegalArgumentException("Invalid month number: $this")
    }