package csokas.dominik.bitraptors.ui

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.kizitonwose.calendarview.CalendarView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.model.InDateStyle
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import csokas.dominik.bitraptors.R
import csokas.dominik.bitraptors.databinding.ActivityMainBinding
import csokas.dominik.bitraptors.models.event.Event
import csokas.dominik.bitraptors.utilities.AppConstants
import csokas.dominik.bitraptors.utilities.DataState
import csokas.dominik.bitraptors.utilities.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_calendar_day.view.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    private lateinit var dayContainerClickListener: DayViewContainer.DayViewContainerClickListener
    private lateinit var focusedDay: DayViewContainer

    private lateinit var calendar : CalendarView
    private var oneWeekHeight: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        calendar = findViewById(R.id.cvCalendar)

        initText()
        setOnClickListeners()
        setupMotionLayoutTransitionListener()
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetEventsEvent)
    }

    private fun initText(){
        val title = findViewById<TextView>(R.id.tvTitle)
        title.text = "Naptár"
        val today = findViewById<TextView>(R.id.tvToday)
        today.text = "Ma"
    }

    private fun setOnClickListeners(){
        val today = findViewById<TextView>(R.id.tvToday)
        today.setOnClickListener { calendar.scrollToDate(LocalDate.now()) }
    }


    private  fun subscribeObservers(){
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<Event>> ->{
                    displayCalendar(dataState.data)
                }
                is DataState.Error -> {
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    //loading animation
                }
            }
        })
    }

    private fun displayError(message: String?){
        if (message!=null){
            binding.tvErrorMessage.text = message
        }
        else{
            binding.tvErrorMessage.text = "Ismeretlen hiba"
        }
        binding.tvErrorMessage.visible()
    }

    private fun displayCalendar(events: List<Event>){
        setUpDayContainerClickListener(events)

        calendar.dayBinder = object : DayBinder<DayViewContainer>{
            override fun create(view: View): DayViewContainer {
                return DayViewContainer(view, dayContainerClickListener)
            }

            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.setDay(day)
                container.tvDayCounter.text = day.date.dayOfMonth.toString()
                if(getCurrentDate() ==day.date){
                    container.tvDayCounter.setTextColor(applicationContext.getColor(R.color.colorFunMain))
                    focusedDay = container
                }
                if (day.owner == DayOwner.THIS_MONTH) {
                    container.clRoot.alpha = 1.0f
                } else {
                    container.clRoot.alpha = 0.5f
                }


                val dailyEvents = getEventsByDate(day, events)
                addEventsToDay(dailyEvents,container.llPointHolder)
            }
        }

        calendar.monthHeaderBinder = object :MonthHeaderFooterBinder<MonthViewContainer>{
            override fun create(view: View): MonthViewContainer {
                return MonthViewContainer(view)
            }

            override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                container.tvMonthName.text = "${month.year} ${month.yearMonth.month.name.toLowerCase().capitalize()}"
            }
        }

        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(1)
        val lastMonth = currentMonth.plusMonths(1)
        calendar.setup(firstMonth, lastMonth, AppConstants.getInstance().daysOfWeek.first())
        calendar.scrollToMonth(currentMonth)

        oneWeekHeight = calendar.daySize.height
    }

    private fun setUpDayContainerClickListener(events: List<Event>) {
        dayContainerClickListener = object: DayViewContainer.DayViewContainerClickListener{
            override fun dayViewContainerClicked(day: CalendarDay, dayContainer: DayViewContainer) {
                val dailyEvents = getEventsByDate(day, events)
                val frameLayout = findViewById<FrameLayout>(R.id.flBottomSheet)
                supportFragmentManager.beginTransaction().replace(frameLayout.id,BottomSheetFragment(dailyEvents, day, createBottomSheetClickListener())).commit()

                if(this@MainActivity::focusedDay.isInitialized){
                    focusedDay.clRoot.background = null
                }
                focusedDay = dayContainer
                dayContainer.clRoot.background = ContextCompat.getDrawable(applicationContext,R.drawable.bg_focused_day)
            }
        }
    }

    private fun createBottomSheetClickListener(): BottomSheetFragment.BottomSheetClickListener{
        val bottomSheetClickListener = object : BottomSheetFragment.BottomSheetClickListener{
            override fun clickedNextDay() {
                if(this@MainActivity::focusedDay.isInitialized){
                    Toast.makeText(this@MainActivity, "Következő nap", Toast.LENGTH_SHORT).show()
                }
            }
            override fun clickedPreviousDay() {
                Toast.makeText(this@MainActivity, "Előző nap", Toast.LENGTH_SHORT).show()
            }

        }
        return bottomSheetClickListener
    }

    private fun getEventsByDate(day:CalendarDay, events: List<Event>):List<Event>{
        val result = ArrayList<Event>()
        events.forEach { event ->
            if (event.year ==day.date.year && event.month == day.date.monthValue && event.day == day.day) {
                result.add(event)
            }
        }
        return result
    }

    private fun addEventsToDay(events: List<Event>, container: LinearLayout){
       if(container.llPointHolder.childCount ==0){
           events.reversed().forEach { event ->
               EventPointItem(event, this, container.llPointHolder)
           }
       }
    }

    private fun getCurrentDate(): LocalDate{
        val current = LocalDateTime.now().toLocalDate()
        return current
    }

    private fun setupMotionLayoutTransitionListener(){
        val motionLayout = findViewById<MotionLayout>(R.id.mlRoot)


        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener{
            override fun onTransitionTrigger(layout: MotionLayout?, triggerID: Int, positive: Boolean, progress: Float) {
                //Nem releváns rész
            }

            override fun onTransitionStarted(layout: MotionLayout?, startID: Int, endID: Int) {
            }

            override fun onTransitionChange(layout: MotionLayout?, startID: Int, endID: Int, progress: Float) {
                calendar.updateMonthConfiguration(
                        maxRowCount =calendar.height/oneWeekHeight
                )

                if(this@MainActivity::focusedDay.isInitialized){
                    calendar.scrollToDay(focusedDay.getDay()!!)
                    focusedDay.clRoot.background = ContextCompat.getDrawable(this@MainActivity,R.drawable.bg_focused_day)
                }
            }

            override fun onTransitionCompleted(layout: MotionLayout?, currentID: Int) {
                if (currentID == R.id.endState){
                    calendar.updateMonthConfiguration(
                            inDateStyle = InDateStyle.ALL_MONTHS,
                            maxRowCount = 1,
                            hasBoundaries = true
                    )
                }
                else{
                    calendar.updateMonthConfiguration(
                            inDateStyle = InDateStyle.ALL_MONTHS,
                            maxRowCount = 6,
                            hasBoundaries = true
                    )
                }
                if (this@MainActivity::focusedDay.isInitialized){
                    focusedDay.clRoot.background = ContextCompat.getDrawable(this@MainActivity, R.drawable.bg_focused_day)
                }
            }
        })
    }
}