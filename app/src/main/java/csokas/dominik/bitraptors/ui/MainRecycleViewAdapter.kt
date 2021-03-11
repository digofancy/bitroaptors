package csokas.dominik.bitraptors.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import csokas.dominik.bitraptors.R
import csokas.dominik.bitraptors.models.event.Event
import csokas.dominik.bitraptors.utilities.AppConstants
import csokas.dominik.bitraptors.utilities.visible
import kotlinx.android.synthetic.main.item_event_card.view.*

class MainRecycleViewAdapter() : RecyclerView.Adapter<MainRecycleViewAdapter.EventCardViewHolder>(){
    private val eventHolder = ArrayList<Event>()

    inner class EventCardViewHolder(itemView: View, private val context: Context): RecyclerView.ViewHolder(itemView){
        fun bindData(event: Event){
            itemView.tvStartHour.text = event.startTime
            itemView.tvEndHour.text = event.endTime
            itemView.tvEventTitle.text = event.name


            setUpLocation(event)
            setUpContentType(event)
        }

        private fun coloringFrontBar(event: Event){
            if(itemView.flColoredBar.background!=null){
                itemView.flColoredBar.background.setTint(getEventTypeMainColor(event.type))
            }
        }

        private fun setUpLocation(event:Event){
            if (event.location.isNotBlank() && event.location.toUpperCase() != "ONLINE"){
                itemView.ivArrow.visible()
                itemView.tvEventLocation.setOnClickListener {
                    Toast.makeText(context, "Navigálás a helyszínre", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                itemView.tvEventLocation.setOnClickListener {
                    Toast.makeText(context, "Online esemény", Toast.LENGTH_SHORT).show()
                }
            }
            itemView.tvEventLocation.text = event.location
        }

        private fun setUpContentType(event:Event){
            coloringFrontBar(event)

            if(itemView.tvEventType.background!=null){
                itemView.tvEventType.background.setTint(getEventTypeBackgroundColor(event.type))
            }
            itemView.tvEventType.text = event.type
            itemView.tvEventType.setTextColor(getEventTypeMainColor(event.type))
        }

        private fun getEventTypeMainColor(eventType: String) : Int {
            return when(eventType.toUpperCase()){
                AppConstants.getInstance().TECH ->{
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
        }

        private fun getEventTypeBackgroundColor(eventType: String): Int {
            return when(eventType.toUpperCase()){
                AppConstants.getInstance().TECH ->{
                    context.getColor(R.color.colorTechBackground)
                }
                AppConstants.getInstance().BUSINESS ->{
                    context.getColor(R.color.colorBusinessBackground)
                }
                AppConstants.getInstance().FUN ->{
                    context.getColor(R.color.colorFunBackground)
                }
                else ->{
                    context.getColor(R.color.colorFunBackground)
                }
            }
        }
    }

    fun setUpEvents(events: List<Event>){
        eventHolder.clear()
        events.forEach {
            eventHolder.add(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventCardViewHolder {
        return EventCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_event_card, parent, false), parent.context)
    }

    override fun getItemCount(): Int {
        return eventHolder.size
    }

    override fun onBindViewHolder(holder: EventCardViewHolder, position: Int) {
        holder.bindData(eventHolder[position])
    }

}