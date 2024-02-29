package io.github.ronjunevaldoz.kmp_calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DefaultCalendarDayItem(
    modifier: Modifier = Modifier,
    inRange: Boolean,
    start: CalendarDay?,
    end: CalendarDay?,
    colors: CalendarColors,
    selectedDate: CalendarDay?,
    day: CalendarDay,
) {
    val primaryColor by colors.contentColor(enabled = true)
    val isDaySelected = selectedDate?.date == day.date
    val color: Color = when {
        isDaySelected -> {
            Color.White
        }

        day.isToday -> {
            primaryColor
        }

        day.isCurrentMonth -> {
            Color.Black
        }

        else -> {
            Color.LightGray
        }
    }

    val sameRange = start?.date == end?.date
    val startSelected = start?.date == day.date
    val endSelected = end?.date == day.date

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (isDaySelected) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(primaryColor, CircleShape)
            )
        }
        if (inRange) {
            val isPrimary = startSelected || endSelected
            val rangeShape = if (startSelected) {
                RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp)
            } else if (endSelected) {
                RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp)
            } else {
                RectangleShape
            }
            if (!sameRange) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(
                            primaryColor.copy(0.5f),
                            rangeShape
                        )
                )
            }
            if (isPrimary) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(
                            primaryColor,
                            CircleShape
                        )
                )
            }
            Text(
                text = "${day.date.dayOfMonth}",
                color = if (isPrimary) Color.White else color,
                fontWeight = FontWeight.SemiBold
            )
        } else {
            Text(
                text = "${day.date.dayOfMonth}",
                color = color,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}