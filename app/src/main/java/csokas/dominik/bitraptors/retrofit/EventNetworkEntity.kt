package csokas.dominik.bitraptors.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EventNetworkEntity(

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("location")
    @Expose
    var location: String,

    @SerializedName("type")
    @Expose
    var type: String,

    @SerializedName("day")
    @Expose
    var day: Int,

    @SerializedName("month")
    @Expose
    var month: Int,

    @SerializedName("year")
    @Expose
    var year: Int,

    @SerializedName("startTime")
    @Expose
    var startTime: String,

    @SerializedName("endTime")
    @Expose
    var endTime: String
)
