package io.github.ronjunevaldoz.calendardemo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.github.ronjunevaldoz.kmp_calendar.Calendar
import io.github.ronjunevaldoz.kmp_calendar.CalendarDefaults
import io.github.ronjunevaldoz.kmp_calendar.CalendarSelection
import io.github.ronjunevaldoz.kmp_calendar.DefaultCalendarDayItem
import io.github.ronjunevaldoz.kmp_calendar.rememberCalendarState


@Composable
fun App() {
    var showCalendar by remember { mutableStateOf(false) }
    val state = rememberCalendarState(
        selection = CalendarSelection.Single
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            showCalendar = true
        }) {
            Text("Show calendar")
        }
        if (showCalendar) {
            Dialog(onDismissRequest = {
                showCalendar = false
            }) {
                Card(
                    modifier = Modifier,
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Calendar(
                        modifier = Modifier.padding(20.dp),
                        state = state,
                        calendarDay = { day, inRange, onDaySelected ->
                            DefaultCalendarDayItem(
                                modifier = Modifier
                                    .size(25.dp)
                                    .clickable {
                                        onDaySelected(day)
                                    },
                                inRange = inRange,
                                colors = CalendarDefaults.calendarColors(),
                                start = state.start,
                                end = state.end,
                                selectedDate = state.selected,
                                day = day,
                            )
                        }
                    )
                }
            }
        }
    }
}