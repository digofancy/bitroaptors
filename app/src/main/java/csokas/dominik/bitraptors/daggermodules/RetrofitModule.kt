package csokas.dominik.bitraptors.daggermodules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import csokas.dominik.bitraptors.retrofit.EventRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder() : Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder().baseUrl("https://ql7zmw.nhely.hu/").addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideEventService(retrofit: Retrofit.Builder): EventRetrofit{
        return retrofit.build().create(EventRetrofit::class.java)
    }
}