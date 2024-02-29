package io.github.ronjunevaldoz.kmp_calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.kmp_calendar.CalendarColors
import io.github.ronjunevaldoz.kmp_calendar.CalendarState
import io.github.ronjunevaldoz.kmp_calendar.CalendarType
import io.github.ronjunevaldoz.kmp_calendar.monthShortName

@Composable
fun CalendarMonthView(state: CalendarState, colors: CalendarColors) {
    val currentDate = state.currentDate
    val textColor by colors.contentColor(enabled = true)
    Text(
        text = "${currentDate.year}",
        style = MaterialTheme.typography.titleLarge,
//        fontWeight = FontWeight.Bold,
        color = textColor,
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(6),
    ) {
        items(12) { index ->
            val monthNumber = index + 1
            Box(
                modifier = Modifier.size(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = monthNumber.monthShortName,
                    modifier = Modifier.clickable {
                        if (monthNumber > currentDate.monthNumber) {
                            val monthRange = currentDate.monthNumber until monthNumber
                            state.increaseMonth(monthRange.step)
                        } else if (monthNumber < currentDate.monthNumber) {
                            val monthRange = monthNumber until currentDate.monthNumber
                            state.decreaseMonth(monthRange.step)
                        }
                        // change calendar to day view
                        state.type = CalendarType.Day
                    },
//                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            }
        }
    }
}