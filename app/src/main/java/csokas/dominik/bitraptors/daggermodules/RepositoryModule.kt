package csokas.dominik.bitraptors.daggermodules

import csokas.dominik.bitraptors.repository.EventRepository
import csokas.dominik.bitraptors.retrofit.EventNetworkMapper
import csokas.dominik.bitraptors.retrofit.EventRetrofit
import csokas.dominik.bitraptors.room.EventCacheMapper
import csokas.dominik.bitraptors.room.EventDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(eventDao: EventDao, retrofit: EventRetrofit, cacheMapper: EventCacheMapper, networkMapper: EventNetworkMapper) : EventRepository{
        return EventRepository(eventDao, retrofit, cacheMapper, networkMapper)
    }
}