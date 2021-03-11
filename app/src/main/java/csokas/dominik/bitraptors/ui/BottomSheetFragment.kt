package csokas.dominik.bitraptors.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendarview.model.CalendarDay
import csokas.dominik.bitraptors.R
import csokas.dominik.bitraptors.models.event.Event
import csokas.dominik.bitraptors.utilities.visible

class BottomSheetFragment(private val events: List<Event>,private val day : CalendarDay, private val clickListener: BottomSheetClickListener):Fragment(){
    private lateinit var innerView: View
    private lateinit var mainRecycleViewAdapter: MainRecycleViewAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        innerView = inflater.inflate( R.layout.fragment_bottom_sheet, container, false)
        setUpRecyclerView()
        setUpBottomNavigation()
        return innerView
    }


    private fun setUpRecyclerView(){
        val recyclerView = innerView.findViewById<View>(R.id.llContainer) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(super.getContext()))
        recyclerView.setAdapter(MainRecycleViewAdapter())

        if(!this::mainRecycleViewAdapter.isInitialized){
            mainRecycleViewAdapter = MainRecycleViewAdapter()
        }
        recyclerView.adapter= mainRecycleViewAdapter

        displayEventCards()
        displayDay()
    }

    private fun displayEventCards(){
        if(events.isNotEmpty()) {
            mainRecycleViewAdapter.setUpEvents(events)
        }
        else{
            val noEventText = innerView.findViewById<TextView>(R.id.tvNoEvent)
            noEventText.visible()
            noEventText.text = "A mai napra nincs program"
        }
    }

    private fun displayDay(){
        val tvDay = innerView.findViewById<TextView>(R.id.tvDay)
        tvDay.text = "${day.date.month.name.toLowerCase().capitalize()} ${day.day.toString()}"
    }

    private fun setUpBottomNavigation(){
        val leftArrow = innerView.findViewById<ImageView>(R.id.ivLeftArrow)
        leftArrow.setOnClickListener { clickListener.clickedPreviousDay() }
        val previousDay = innerView.findViewById<TextView>(R.id.tvPrevious)
        previousDay.text = "Tegnap"
        previousDay.setOnClickListener { clickListener.clickedPreviousDay() }

        val rightArrow = innerView.findViewById<ImageView>(R.id.ivRightArrow)
        rightArrow.setOnClickListener { clickListener.clickedNextDay() }
        val nextDay = innerView.findViewById<TextView>(R.id.tvNext)
        nextDay.text = "Holnap"
        nextDay.setOnClickListener { clickListener.clickedNextDay() }

    }

    interface BottomSheetClickListener{
        fun clickedNextDay()
        fun clickedPreviousDay()
    }
}