package io.github.ronjunevaldoz.kmp_calendar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
@Composable
fun CalendarPicker(
    modifier: Modifier = Modifier,
    state: CalendarState = rememberCalendarState(),
    onDismiss: () -> Unit = {},
    onDaySelected: (CalendarDay) -> Unit = {},
    onRangeSelected: (start: CalendarDay?, end: CalendarDay?) -> Unit = {_,_->},
    content: @Composable () -> Unit = {
        DefaultCalendar(state, onSelectDay = {
            when (state.selection) {
                CalendarSelection.Single -> {
                    // dismiss immediately on selection
                    onDaySelected(it)
                    onDismiss()
                }

                CalendarSelection.Range -> {
                    val start = state.start
                    val end = state.end
                    onRangeSelected(start, end)
                }
            }
        })
    }
) {
    Dialog(onDismissRequest = onDismiss, DialogProperties(usePlatformDefaultWidth = false)) {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp),
        ) {
            content()
        }
    }
}

@Composable
fun DefaultCalendar(
    state: CalendarState,
    onSelectDay: (day: CalendarDay) -> Unit
) {
    Calendar(
        modifier = Modifier.padding(vertical = 12.dp),
        state = state,
        colors = CalendarDefaults.calendarColors(),
        onSelectDay = {
            onSelectDay(it)
        },
        calendarDay = { day, onDaySelected ->
            val start = state.start?.date
            val end = state.end?.date
            val inRange = if(start != null && end != null) {
                val range = start..end
                day.date in range
            } else {
                false
            }
            DefaultCalendarDayItem(
                inRange = inRange,
                start = state.start,
                end = state.end,
                colors = CalendarDefaults.calendarColors(),
                hideTodayHighlight = state.hideTodayHighlight,
                selectedDate = state.selected,
                day = day,
                onSelect = onDaySelected
            )
        }
    )
}