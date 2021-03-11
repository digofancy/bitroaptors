package csokas.dominik.bitraptors.ui

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.ViewContainer
import csokas.dominik.bitraptors.R
import csokas.dominik.bitraptors.databinding.ItemCalendarDayBinding

class DayViewContainer(view: View, listener: DayViewContainerClickListener ): ViewContainer(view){
    val clRoot = view.findViewById<ConstraintLayout>(R.id.clRoot)
    val tvDayCounter = view.findViewById<TextView>(R.id.tvDayCounter)
    val llPointHolder = view.findViewById<LinearLayout>(R.id.llPointHolder)
    private lateinit var day: CalendarDay

    init {
        view.setOnClickListener {
            if(this::day.isInitialized) {
                listener.dayViewContainerClicked(day, this)
            }
        }
    }

    fun setDay(day : CalendarDay){
        this.day = day
    }

    fun getDay(): CalendarDay?{
        if(this::day.isInitialized){
            return day
        }
        return null
    }

    interface DayViewContainerClickListener{
        fun dayViewContainerClicked(day: CalendarDay, dayContainer: DayViewContainer)
    }
}