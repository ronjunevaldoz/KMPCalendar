package io.github.ronjunevaldoz.kmp_calendar

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
fun CalendarHeader(dayOfWeek: DayOfWeek) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            8.dp
        ),
    ) {
        Text(
            text = dayOfWeek.short,
            fontSize = 10.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
    }
}