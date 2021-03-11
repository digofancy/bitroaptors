package csokas.dominik.bitraptors.utilities

import android.view.View
import csokas.dominik.bitraptors.models.event.EventType
import csokas.dominik.bitraptors.models.event.EventType.*

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun getEventType(value : String) : EventType {
    return when (value) {
        "Tech" -> {
            EventType.TECH
        }
        "Business" -> {
            BUSINESS
        }
        "Fun" -> {
            FUN
        }
        else -> {
            FUN
        }
    }
}
