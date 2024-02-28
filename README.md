
![Build](https://github.com/ronjunevaldoz/KMPCalendar/actions/workflows/gradle.yml/badge.svg)
![Publish](https://github.com/ronjunevaldoz/KMPCalendar/actions/workflows/deploy.yml/badge.svg)
![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.ronjunevaldoz/kmp-calendar?server=https%3A%2F%2Fs01.oss.sonatype.org)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.20-blue.svg?logo=kotlin)](http://kotlinlang.org)
![GitHub](https://img.shields.io/github/license/ronjunevaldoz/KMPCalendar)

# Kotlin Multiplatform Calendar

Jetpack compose multiplatform calendar

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
    calendarDay = { day, onSelect ->
        CalendarDayItem(day, onSelect)          
    },
    calendarMonth = {
        CalendarMonthView(state = state, colors = colors)
    },
    onSelectDay = {
        
    }
)
```
