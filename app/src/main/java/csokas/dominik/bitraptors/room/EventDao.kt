package csokas.dominik.bitraptors.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(eventEntity : EventCacheEntity): Long //A tábla sor indexe vagy -1 ha hibás

    @Query(value = "SELECT * From events")
    suspend fun get(): List<EventCacheEntity>
}