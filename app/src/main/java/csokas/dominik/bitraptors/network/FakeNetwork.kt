package csokas.dominik.bitraptors.network

import csokas.dominik.bitraptors.retrofit.EventNetworkEntity

class FakeNetwork private constructor(){

    private val listOfEvents= ArrayList<EventNetworkEntity>()

    companion object{
        private lateinit var instance: FakeNetwork
            fun getInstance(): FakeNetwork {
                if(!this::instance.isInitialized){
                instance = FakeNetwork()
                }
                return instance
            }
    }

    init {
        for (index in 0 until 100){
            listOfEvents.add(EventNetworkEntity(
                    index,
                    generateEventName(index),
                    generateLocation(index),
                    generateType(index),
                    index/5+1,
                    3,
                    2021,
                     generateStartHour(index),
                    generateEndHour(index)
                )
            )
        }

    }

    private fun generateEventName(index : Int): String{
        return "A(z) ${index+1}. esemény a hónapban"
    }

    private fun generateLocation(index: Int) : String{
        return when(index%5){
            0 -> { "Hősöktere" }
            1 -> { "Online"}
            2,3 ->{ "Corvinus"}
            4 ->{ "KakasKocsma" }
            else ->{ "Ismeretlen"}
        }
    }

    private fun generateType(index: Int): String{
        return when(index%3) {
            0 -> { "Business" }
            1 -> { "Tech" }
            2 -> { "Fun" }
            else -> { "Ismeretlen" }
        }
    }

    private fun generateStartHour(index : Int): String{
        var baseHour = 8*60
        baseHour = baseHour + index%10*75
        val responseHours = if(baseHour/60<10) "0${baseHour/60}" else "${baseHour/60}"
        val responseMinutes = if(baseHour%60<10) "0${baseHour%60}" else "${baseHour%60}"
        val response = "${responseHours}:${responseMinutes}"
        return response
    }

    private fun generateEndHour(index: Int): String{
        var baseHour = 10*60
        baseHour = baseHour + index%10*75
        val responseHours = if(baseHour/60<10) "0${baseHour/60}" else "${baseHour/60}"
        val responseMinutes = if(baseHour%60<10) "0${baseHour%60}" else "${baseHour%60}"
        val response = "${responseHours}:${responseMinutes}"
        return response
    }

    fun getEvent(): List<EventNetworkEntity> {
        return listOfEvents.toList()
    }
}