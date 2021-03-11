package csokas.dominik.bitraptors.ui

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendarview.ui.ViewContainer
import csokas.dominik.bitraptors.R

class MonthViewContainer(view: View) :ViewContainer(view){
    val tvMonthName = view.findViewById<TextView>(R.id.tvMonthName)
}