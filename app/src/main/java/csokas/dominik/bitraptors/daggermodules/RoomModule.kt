package csokas.dominik.bitraptors.daggermodules

import android.content.Context
import androidx.room.Room
import csokas.dominik.bitraptors.room.EventDao
import csokas.dominik.bitraptors.room.EventDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideEventDatabase(@ApplicationContext context: Context): EventDatabase {
        return Room.databaseBuilder(context, EventDatabase::class.java, EventDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideEventDAO( eventDatabase: EventDatabase) : EventDao {
        return eventDatabase.eventDao()
    }
}