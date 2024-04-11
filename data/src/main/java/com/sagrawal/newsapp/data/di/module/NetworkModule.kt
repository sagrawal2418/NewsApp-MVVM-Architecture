package com.sagrawal.newsapp.data.di.module

import android.content.Context
import com.sagrawal.newsapp.data.api.NetworkService
import com.sagrawal.newsapp.data.di.BaseUrl
import com.sagrawal.newsapp.data.di.NetworkApiKey
import com.sagrawal.newsapp.util.DefaultDispatcherProvider
import com.sagrawal.newsapp.util.DefaultNetworkHelper
import com.sagrawal.newsapp.util.DispatcherProvider
import com.sagrawal.newsapp.util.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    //TODO pick the value from build.config
    @NetworkApiKey
    @Provides
    fun provideApiKey(): String = "9f6482a584804376874b848980b7a044"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder().build()

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return DefaultNetworkHelper(context)
    }

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

}