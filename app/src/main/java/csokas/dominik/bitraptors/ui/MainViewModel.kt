package csokas.dominik.bitraptors.ui

import androidx.lifecycle.*
import csokas.dominik.bitraptors.models.event.Event
import csokas.dominik.bitraptors.repository.EventRepository
import csokas.dominik.bitraptors.utilities.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel                                                //Itt az @Assisted miért dob errort?
@Inject constructor(private val eventRepository: EventRepository, private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private  val _dataState: MutableLiveData<DataState<List<Event>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Event>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetEventsEvent ->{
                    eventRepository.getEvent().onEach { dataState ->
                        _dataState.value = dataState
                    }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None ->{
                    //Nothing to do here
                }
            }
        }
    }
}

sealed class  MainStateEvent{

    object GetEventsEvent : MainStateEvent()
    object None: MainStateEvent()
}