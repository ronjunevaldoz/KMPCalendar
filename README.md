
![Build](https://github.com/ronjunevaldoz/KMPCalendar/actions/workflows/gradle.yml/badge.svg)
![Publish](https://github.com/ronjunevaldoz/KMPCalendar/actions/workflows/deploy.yml/badge.svg)
![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.ronjunevaldoz/kmp-calendar?server=https%3A%2F%2Fs01.oss.sonatype.org)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.20-blue.svg?logo=kotlin)](http://kotlinlang.org)
![GitHub](https://img.shields.io/github/license/ronjunevaldoz/KMPCalendar)

# Kotlin Multiplatform Calendar

Jetpack compose multiplatform calendar

## Screenshot
![image](https://github.com/ronjunevaldoz/KMPCalendar/assets/4947998/edc63524-4d26-4ecf-afac-4eedc97debfa)

## Installation
```kotlin
repositories { 
    mavenCentral()
    maven { url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots") }
}
```
## Dependency
```kotlin
implementation("io.github.ronjunevaldoz:kmp-calendar:0.0.1-SNAPSHOT") // common library
implementation("io.github.ronjunevaldoz:kmp-calendar-android:0.0.1-SNAPSHOT") // android library
```
```toml
[libraries]
calendar = {group = "io.github.ronjunevaldoz", name ="kmp-calendar-android", version.ref ="calendar"}
```
## Usage
```kotlin
Calendar(
    modifier = Modifier,
    state = rememberCalendarState(),
    colors = CalendarDefaults.calendarColors(),
    actionHeader = {  
        // Composable 
    },
    weekHeader = {   
        // DayWeek 
    } ,
    calendarDay = { day, inRange, onDaySelected ->
        DefaultCalendarDayItem(
            modifier = Modifier
                .size(30.dp)
                .clickableNoRipple {
                    onDaySelected(day)
                    airBnbSelector(day)
                },
            inRange = inRange,
            start = calendarState.start,
            end = calendarState.end,
            colors = CalendarDefaults.calendarColors(),
            selectedDate = calendarState.selected,
            day = day,
        )
    }
    calendarMonth = {
        CalendarMonthView(state = state, colors = colors)
    }, 
)
```
## Customize
```kotlin
val setCalendarDefaults = {
    if (calendarState.selection == CalendarSelection.Single) {
        calendarState.selected = // selected date 
    } else {
        calendarState.start = // selected start date 
        calendarState.end = // selected end date
    }
}

// Calendar Picker
Text(
    text = "Select start date",
    modifier = Modifier
        .clickable { 
            isDatePickerShow = true
            calendarState.cursor = CalendarCursor.StartDate
            setCalendarDefaults()
        }, 
)
Text(
    text = "Select end date",
    modifier = Modifier
        .clickable { 
            isDatePickerShow = true
            calendarState.cursor = CalendarCursor.EndDate
            setCalendarDefaults()
        }, 
)

// Calendar Dialog
Dialog(onDismissRequest = {
    isDatePickerShow = !isDatePickerShow
}, DialogProperties(usePlatformDefaultWidth = false)) {
    Card(
        modifier = Modifier
            .width(230.dp)
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp),
    ) {
        SetImmersiveScreen()
        CustomCalendar()
    }
}

// Custom Calendar
@Composable
fun CustomCalendar() {
    Calendar(
        modifier = Modifier.padding(vertical = 12.dp),
        state = calendarState,
        colors = CalendarDefaults.calendarColors(),
        weekHeader = {
            Text(text = "${it.name.first()}")
        },
        calendarDay = { day, inRange, onDaySelected ->
            DefaultCalendarDayItem(
                modifier = Modifier
                    .size(30.dp)
                    .clickableNoRipple {
                        onDaySelected(day)
                        airBnbSelector(day)
                    },
                inRange = inRange,
                start = calendarState.start,
                end = calendarState.end,
                colors = CalendarDefaults.calendarColors(),
                selectedDate = calendarState.selected,
                day = day,
            )
        }
    )
}
```
