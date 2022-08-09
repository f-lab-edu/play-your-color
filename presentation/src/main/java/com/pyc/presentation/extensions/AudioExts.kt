package com.pyc.presentation.extensions

import java.util.concurrent.TimeUnit


fun Long.toFormattedDuration() : String  = try {

    val hours = TimeUnit.MILLISECONDS.toHours(this)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(this)

    if(minutes < 60) {
        String.format(
            "%02d:%02d",
            minutes,
            seconds - TimeUnit.MINUTES.toSeconds(minutes)
        )
    } else {
        String.format(
            "%02d:%02d:%02d",
            hours,
            minutes - TimeUnit.HOURS.toMinutes(hours),
            seconds - TimeUnit.MINUTES.toSeconds(minutes)
        )
    }

} catch (e: Exception) {
    e.printStackTrace()
    "00:00"
}