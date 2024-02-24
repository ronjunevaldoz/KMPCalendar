package io.github.ronjunevaldoz.kmp_calendar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
    selectedDate: CalendarDay? = null,
    onDismiss: () -> Unit = {},
    onSelectDay: (CalendarDay) -> Unit = {},
    content: @Composable () -> Unit = {
        DefaultCalendar(selectedDate, onSelectDay = {
            onSelectDay(it)
            onDismiss()
        })
    }
) {
    Dialog(onDismissRequest = onDismiss, DialogProperties(usePlatformDefaultWidth = false)) {
        Card(
            modifier = Modifier
                .width(400.dp)
                .wrapContentHeight(),
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
fun DefaultCalendar(selectedDate: CalendarDay?, onSelectDay: (day: CalendarDay) -> Unit) {
    val state = rememberCalendarState()
    Calendar(
        modifier = Modifier.padding(vertical = 12.dp),
        state = state,
        colors = CalendarDefaults.calendarColors(),
        onSelectDay = {
            onSelectDay(it)
        },
        calendarDay = { day, onSelect ->
            CalendarDayItem2(
                colors = CalendarDefaults.calendarColors(),
                hideTodayHighlight = state.hideTodayHighlight,
                selectedDate = selectedDate,
                day = day,
                onSelect = onSelect
            )
        }
    )
}