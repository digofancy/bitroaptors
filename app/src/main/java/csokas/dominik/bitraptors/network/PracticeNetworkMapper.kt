package csokas.dominik.bitraptors.network

import csokas.dominik.bitraptors.models.event.Event
import csokas.dominik.bitraptors.utilities.EntityMapper
import csokas.dominik.bitraptors.utilities.getEventType
import javax.inject.Inject

class PracticeNetworkMapper @Inject constructor() : EntityMapper<PracticeEventNetworkEntity, Event> {
    override fun mapFromEntity(entityPractice: PracticeEventNetworkEntity): Event {
        return Event(
              id =  entityPractice.id,
              name =  entityPractice.name,
              location =  entityPractice.location,
              type =   entityPractice.type,
              day =   entityPractice.day,
              month =   entityPractice.month,
              year =   entityPractice.year,
              startTime =   entityPractice.startTime,
              endTime =   entityPractice.endTime
        )
    }

    override fun mapToEntity(domainModel: Event): PracticeEventNetworkEntity {
            return PracticeEventNetworkEntity(
                 id =    domainModel.id,
                 name =    domainModel.name,
                 location =    domainModel.location,
                 type =   domainModel.type,
                 day =    domainModel.day,
                 month =    domainModel.month,
                 year =    domainModel.year,
                 startTime =    domainModel.startTime,
                 endTime =    domainModel.endTime
            )
    }

    fun mapFromEntityList(entityPractices: List<PracticeEventNetworkEntity>): List<Event> {
        return entityPractices.map { mapFromEntity(it) }
    }
}