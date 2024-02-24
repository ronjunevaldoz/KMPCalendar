package io.github.ronjunevaldoz.kmp_calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.DayOfWeek

@Composable
fun CalendarDayItem(calendarDay: CalendarDay, onSelect: (CalendarDay) -> Unit) {
    var bg: Color
    bg = if (calendarDay.isCurrentMonth) Color.White else Color.LightGray

    var color: Color = if (calendarDay.isToday) {
        Color.Blue
    } else if (calendarDay.isCurrentMonth) {
        Color.Black
    } else {
        Color.White
    }

    if (calendarDay.date.dayOfWeek == DayOfWeek.SUNDAY || calendarDay.date.dayOfWeek == DayOfWeek.SATURDAY) {
        bg = Color.LightGray
        color = Color.Red
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = bg
        ),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onSelect(calendarDay)
            },
        elevation = CardDefaults.cardElevation(
            8.dp
        ),
    ) {
        Text(
            text = "${calendarDay.date.dayOfMonth}",
            fontSize = 12.sp,
            color = color,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
    }
}