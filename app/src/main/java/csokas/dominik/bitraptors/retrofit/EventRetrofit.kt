package csokas.dominik.bitraptors.retrofit

import retrofit2.http.GET

interface EventRetrofit {

    @GET//("event")
    suspend fun  getEvent(): List<EventNetworkEntity>

}