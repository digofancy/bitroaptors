package csokas.dominik.bitraptors.retrofit

import csokas.dominik.bitraptors.models.event.Event
import csokas.dominik.bitraptors.utilities.EntityMapper
import csokas.dominik.bitraptors.utilities.getEventType
import javax.inject.Inject

class EventNetworkMapper  @Inject constructor() : EntityMapper<EventNetworkEntity, Event> {
    override fun mapFromEntity(entity: EventNetworkEntity): Event {
        return Event(
            id =  entity.id,
            name =  entity.name,
            location =  entity.location,
            type =   entity.type,
            day =   entity.day,
            month =   entity.month,
            year =   entity.year,
            startTime =   entity.startTime,
            endTime =   entity.endTime
        )
    }

    override fun mapToEntity(domainModel: Event): EventNetworkEntity {
        return EventNetworkEntity(
            id = domainModel.id,
            name = domainModel.name,
            location = domainModel.location,
            type = domainModel.type,
            day = domainModel.day,
            month = domainModel.month,
            year = domainModel.year,
            startTime = domainModel.startTime,
            endTime = domainModel.endTime
        )
    }

    fun mapFromEntityList(entities: List<EventNetworkEntity>): List<Event> {
        return entities.map { mapFromEntity(it) }
    }

}