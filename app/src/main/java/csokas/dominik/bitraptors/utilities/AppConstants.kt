package csokas.dominik.bitraptors.utilities

import csokas.dominik.bitraptors.R
import java.time.DayOfWeek

class AppConstants private constructor(){

    companion object {
        private lateinit var instance : AppConstants
        fun getInstance(): AppConstants{
            if(!this::instance.isInitialized){
                instance = AppConstants()
            }
            return instance
        }
    }

    val daysOfWeek = arrayOf(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY
    )

    val TECH = "TECH"
    val BUSINESS = "BUSINESS"
    val FUN = "FUN"
}