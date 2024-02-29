package io.github.ronjunevaldoz.kmp_calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
    content: @Composable () -> Unit = {
        Calendar(
            modifier = Modifier.padding(vertical = 12.dp),
            state = state,
            colors = CalendarDefaults.calendarColors(),
            calendarDay = { day, inRange, onDaySelected ->
                DefaultCalendarDayItem(
                    modifier = Modifier.size(30.dp).clickable { onDaySelected(day) },
                    inRange = inRange,
                    start = state.start,
                    end = state.end,
                    colors = CalendarDefaults.calendarColors(),
                    selectedDate = state.selected,
                    day = day
                )
            }
        )
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