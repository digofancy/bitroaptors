package csokas.dominik.bitraptors.repository

import csokas.dominik.bitraptors.models.event.Event
import csokas.dominik.bitraptors.network.FakeNetwork
import csokas.dominik.bitraptors.retrofit.EventNetworkMapper
import csokas.dominik.bitraptors.retrofit.EventRetrofit
import csokas.dominik.bitraptors.room.EventCacheMapper
import csokas.dominik.bitraptors.room.EventDao
import csokas.dominik.bitraptors.utilities.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class EventRepository constructor(private val eventDao: EventDao, private val eventRetrofit: EventRetrofit, private val cacheMapper: EventCacheMapper, private val networkMapper: EventNetworkMapper) {

    suspend fun  getEvent() : Flow<DataState<List<Event>>> = flow{
        emit(DataState.Loading)
        try {
            val networkEvents = FakeNetwork.getInstance().getEvent() //eventRetrofit.getEvent()
            val events = networkMapper.mapFromEntityList(networkEvents)
            for( event in events){
                eventDao.insert(cacheMapper.mapToEntity(event))
            }
            val cachedEvents = eventDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedEvents)))
        }
        catch (e :Exception){
            emit(DataState.Error(e))
        }
    }
}