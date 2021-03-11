package csokas.dominik.bitraptors.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import csokas.dominik.bitraptors.R
import csokas.dominik.bitraptors.models.event.Event
import csokas.dominik.bitraptors.utilities.AppConstants

class EventPointItem (event: Event, context: Context, parent: LinearLayout): View(context){
    val dot : View

    init {
        dot = LayoutInflater.from(context).inflate(R.layout.item_event_point,parent)
        val color = when(event.type.toUpperCase()){
            AppConstants.getInstance().TECH->{
                context.getColor(R.color.colorTechMain)
            }
            AppConstants.getInstance().BUSINESS ->{
                context.getColor(R.color.colorBusinessMain)
            }
            AppConstants.getInstance().FUN ->{
                context.getColor(R.color.colorFunMain)
            }
            else ->{
                context.getColor(R.color.colorFunMain)
            }
        }
        dot.findViewById<CardView>(R.id.cardView).foreground.setTint(color)
    }
}