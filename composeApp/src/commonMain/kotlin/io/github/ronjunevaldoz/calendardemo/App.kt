package io.github.ronjunevaldoz.calendardemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import io.github.ronjunevaldoz.kmp_calendar.Calendar


@Composable
fun App() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var showCalendar by remember { mutableStateOf(false) }
        Button(onClick = {
            showCalendar = true
        }) {
            Text("Show calendar")
        }
        if(showCalendar) {
            Dialog(onDismissRequest = {
                showCalendar = false
            }) {
                Calendar {

                }
            }
        }
    }
}