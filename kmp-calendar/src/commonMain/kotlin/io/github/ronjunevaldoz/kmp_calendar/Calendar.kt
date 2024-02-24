package io.github.ronjunevaldoz.kmp_calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.plus

enum class CalendarType {
    Day,
    Month
}

enum class CalendarSelection {
    Single,
    Range
}

interface CalendarState {
    var currentDate: LocalDate
    var type: CalendarType
    var selection: CalendarSelection
    var hideTodayHighlight: Boolean
    fun increaseMonth(month: Int)
    fun decreaseMonth(month: Int)
    fun nextDate()
    fun prevDate()
    fun generateCalendarGrid(year: Int, month: Int): List<CalendarDay>
}


@Immutable
class CalendarColors internal constructor(
    private val containerColor: Color,
    private val contentColor: Color,
    private val disabledContainerColor: Color,
    private val disabledContentColor: Color,
) {
    /**
     * Represents the container color for this card, depending on [enabled].
     *
     * @param enabled whether the card is enabled
     */
    @Composable
    internal fun containerColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) containerColor else disabledContainerColor)
    }

    @Composable
    internal fun contentColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
    }
}

object CalendarDefaults {
    @Composable
    fun calendarColors(
        containerColor: Color = Color.White,
        contentColor: Color = Color(0xFFBD8DDD),//contentColorFor(containerColor),
        disabledContainerColor: Color = Color.White,
        disabledContentColor: Color = Color.Gray//contentColorFor(containerColor)
    ): CalendarColors = CalendarColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
}

class MutableCalendarState(defaultDate: LocalDate = today) : CalendarState {
    override var currentDate: LocalDate by mutableStateOf(defaultDate)
    override var type: CalendarType by mutableStateOf(CalendarType.Day)
    override var selection: CalendarSelection by mutableStateOf(CalendarSelection.Single)
    override var hideTodayHighlight: Boolean by mutableStateOf(false)

    override fun increaseMonth(month: Int) {
        currentDate += if (currentDate.month == Month.JANUARY) {
            // move to last year if current month is january
            DatePeriod(months = month, years = 0)
        } else {
            DatePeriod(months = month)
        }
    }

    override fun decreaseMonth(month: Int) {
        currentDate -= if (currentDate.month == Month.JANUARY) {
            // move to last year if current month is january
            DatePeriod(months = month, years = 0)
        } else {
            DatePeriod(months = month)
        }
    }

    override fun nextDate() {
        currentDate += if (currentDate.month == Month.JANUARY) {
            // move to last year if current month is january
            DatePeriod(months = 1, years = 0)
        } else {
            DatePeriod(months = 1)
        }
    }

    override fun prevDate() {
        currentDate -= if (currentDate.month == Month.JANUARY) {
            // move to last year if current month is january
            DatePeriod(months = 1, years = 0)
        } else {
            DatePeriod(months = 1)
        }
    }

    override fun generateCalendarGrid(year: Int, month: Int): List<CalendarDay> {
        val startDate = LocalDate(year, month, dayOfMonth = 1)
        val endDate = startDate + DatePeriod(months = 1) - DatePeriod(days = 1)
        val today = today

        val daysInMonth = mutableListOf<CalendarDay>()

        // Fill in days for the previous month
        val daysInPreviousMonth = startDate.dayOfWeek.isoDayNumber
        for (day in daysInPreviousMonth downTo 1) {
            val previousMonthDate = startDate - DatePeriod(days = day)
            daysInMonth.add(CalendarDay(previousMonthDate, false))
        }

        // Fill in days for the current month
        for (day in startDate.dayOfMonth..endDate.dayOfMonth) {
            val currentDate = LocalDate(year, month, day)
            daysInMonth.add(CalendarDay(currentDate, true, currentDate == today))
        }

        // Fill in days for the next month
        var daysInNextMonth = 7 - endDate.dayOfWeek.isoDayNumber - 1

        daysInNextMonth = if (daysInNextMonth == -1) 6 else daysInNextMonth

        for (day in 1..daysInNextMonth) {
            val nextMonthDate = endDate + DatePeriod(days = day)
            daysInMonth.add(CalendarDay(nextMonthDate, false))
        }

        return daysInMonth
    }
}

@Composable
fun rememberCalendarState(): CalendarState {
    return remember { MutableCalendarState() }
}

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    state: CalendarState = rememberCalendarState(),
    colors: CalendarColors = CalendarDefaults.calendarColors(),
    actionHeader: @Composable (state: CalendarState) -> Unit = {
        CalendarHeader(state = state, colors = colors) {
            state.type = CalendarType.Month
        }
    },
    weekHeader: @Composable (dayWeek: DayOfWeek) -> Unit = {
        val textColor by colors.contentColor(enabled = true)
        Text(
            text = it.short,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    },
    calendarDay: @Composable (day: CalendarDay, onSelect: (day: CalendarDay) -> Unit) -> Unit = { day, onSelect ->
        CalendarDayItem(day, onSelect)
    },
    calendarMonth: @Composable () -> Unit = {
        CalendarMonthView(state = state, colors = colors)
    },
    onSelectDay: (
        CalendarDay
    ) -> Unit = {}
) {
    val currentDate = state.currentDate
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.type == CalendarType.Month) {
            calendarMonth()
        } else {
            actionHeader(state)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items(calendarWeekOrder) {
                    weekHeader(it)
                }
            }
            val grids = state.generateCalendarGrid(currentDate.year, currentDate.monthNumber)
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(grids) {
                    calendarDay(it, onSelectDay)
                }
            }
        }
    }
}