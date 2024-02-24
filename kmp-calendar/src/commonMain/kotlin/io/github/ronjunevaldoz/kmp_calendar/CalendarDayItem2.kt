package io.github.ronjunevaldoz.kmp_calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CalendarDayItem2(
    colors: CalendarColors,
    hideTodayHighlight: Boolean,
    selectedDate: CalendarDay?,
    day: CalendarDay,
    onSelect: (CalendarDay) -> Unit
) {
    val primaryColor by colors.contentColor(enabled = true)
    val isDaySelected = selectedDate?.date == day.date
    val color: Color = when {
        day.isToday -> {
            Color.Black
        }

        isDaySelected -> {
            Color.White
        }

        day.isCurrentMonth -> {
            Color.Black
        }

        else -> {
            Color.LightGray
        }
    }

    Box(
        modifier = Modifier
            .size(30.dp)
            .clickable { onSelect(day) },
        contentAlignment = Alignment.Center
    ) {
        if (isDaySelected || (!hideTodayHighlight && day.isToday)) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(primaryColor, CircleShape)
            )
        }
        Text(
            text = "${day.date.dayOfMonth}",
            color = color,
            fontWeight = FontWeight.SemiBold
        )
    }
}