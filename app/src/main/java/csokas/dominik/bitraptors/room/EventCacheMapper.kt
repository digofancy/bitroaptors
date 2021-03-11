package csokas.dominik.bitraptors.room

import csokas.dominik.bitraptors.models.event.Event
import csokas.dominik.bitraptors.utilities.EntityMapper
import csokas.dominik.bitraptors.utilities.getEventType
import javax.inject.Inject


class EventCacheMapper  @Inject constructor() : EntityMapper<EventCacheEntity, Event> {
    override fun mapFromEntity(entity: EventCacheEntity): Event {
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

    override fun mapToEntity(domainModel: Event): EventCacheEntity {
        return EventCacheEntity(
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

    fun mapFromEntityList(entities: List<EventCacheEntity>): List<Event> {
        return entities.map { mapFromEntity(it) }
    }

}