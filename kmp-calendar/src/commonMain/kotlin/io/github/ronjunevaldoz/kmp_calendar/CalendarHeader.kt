package io.github.ronjunevaldoz.kmp_calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import io.github.ronjunevaldoz.kmp_calendar.CalendarColors
import io.github.ronjunevaldoz.kmp_calendar.CalendarState

@Composable
fun CalendarHeader(colors: CalendarColors, state: CalendarState, onMonthSelected: () -> Unit) {
    val textColor by colors.contentColor(enabled = true)
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {
                // check first if prev is allowed
                state.prevDate()
            }) {
            Icon(
                Icons.Default.KeyboardArrowLeft,
                contentDescription = "Prev",
                tint = textColor
            )
        }
        Text(
            text = state.currentDate.calendarTitle,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            modifier = Modifier.clickable {
                onMonthSelected()
            }
        )
        IconButton(
            onClick = {
                state.nextDate()
            }) {
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = "Next",
                tint = textColor
            )
        }
    }
}