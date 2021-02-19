package com.example.pokeapikotlin.di

import com.example.pokeapikotlin.network.ApiService
import com.example.pokeapikotlin.network.WebHookService
import com.example.pokeapikotlin.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return provideRetrofitInit().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitInitWebHook(): Retrofit{
        return Retrofit.Builder()
                .baseUrl(Utils.WEBHOOK_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideApiServiceWebHook(): WebHookService {
        return provideRetrofitInitWebHook().create(WebHookService::class.java)
    }

}