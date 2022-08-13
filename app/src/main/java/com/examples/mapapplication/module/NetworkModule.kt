package com.examples.mapapplication.module

import com.examples.mapapplication.service.FoodTruckApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL =
        "https://data.sfgov.org/resource/jjew-r69b.json/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val logging =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    private val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(logging)
    }

    //    var gson: Gson? = GsonBuilder()
//        .create()
//        .addConverterFactory(GsonConverterFactory.create(gson))
    // retrofitService <- Repository <- vm
    @Singleton
    @Provides
    fun provideRetrofitService(): FoodTruckApiService {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .build()
            .create(FoodTruckApiService::class.java)
    }
}