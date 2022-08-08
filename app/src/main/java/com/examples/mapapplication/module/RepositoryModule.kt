package com.examples.mapapplication.module

import com.examples.mapapplication.service.FoodTruckApiService
import com.examples.mapapplication.service.FoodTruckRepository
import com.examples.mapapplication.service.FoodTruckRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // retrofitService <- Repository <- vm
    @Singleton
    @Provides
    fun provideRepository(
        foodTruckApiService: FoodTruckApiService
    ): FoodTruckRepository{
         return FoodTruckRepositoryImpl(foodTruckApiService)
    }
}