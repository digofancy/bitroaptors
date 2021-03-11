package csokas.dominik.bitraptors.network

data class PracticeEventNetworkEntity(
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