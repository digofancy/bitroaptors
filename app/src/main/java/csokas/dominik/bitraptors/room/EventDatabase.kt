package csokas.dominik.bitraptors.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EventCacheEntity::class], version = 1)
abstract class EventDatabase: RoomDatabase(){

    abstract fun eventDao(): EventDao

    companion object {
        val DATABASE_NAME: String = "event_db"
    }
}