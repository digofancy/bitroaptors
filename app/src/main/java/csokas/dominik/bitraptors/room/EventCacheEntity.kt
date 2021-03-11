package csokas.dominik.bitraptors.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "location")
    var location: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "day")
    var day: Int,

    @ColumnInfo(name = "month")
    var month: Int,

    @ColumnInfo(name = "year")
    var year: Int,

    @ColumnInfo(name = "startTime")
    var startTime: String,

    @ColumnInfo(name = "endTime")
    var endTime: String,
)