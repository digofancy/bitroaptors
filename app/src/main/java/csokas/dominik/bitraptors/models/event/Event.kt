package csokas.dominik.bitraptors.models.event

data class Event(
    val id : Int,
    val name: String,
    val location: String,
    val type: String,
    val day: Int,
    val month: Int,
    val year: Int,
    val startTime: String,
    val endTime: String
)