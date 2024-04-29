package com.sagrawal.newsapp.data.di.module

import android.content.Context
import com.sagrawal.nativelib.Provider.getApiKey
import com.sagrawal.nativelib.Provider.getBaseUrl
import com.sagrawal.newsapp.data.BuildConfig
import com.sagrawal.newsapp.data.api.ApiKeyInterceptor
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

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(@NetworkApiKey apiKey: String): ApiKeyInterceptor =
        ApiKeyInterceptor(apiKey)

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor):
            OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(@ApplicationContext context: Context) =
        getBaseUrl(BuildConfig.DEBUG, context)

    @Provides
    @Singleton
    @NetworkApiKey
    fun provideNetworkKey(@ApplicationContext context: Context) =
        getApiKey(BuildConfig.DEBUG, context)

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

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
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

}