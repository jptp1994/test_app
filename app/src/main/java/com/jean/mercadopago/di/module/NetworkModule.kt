package com.jean.mercadopago.di.module

import com.jean.mercadopago.data.models.NetworkModel
import com.jean.mercadopago.utils.GeneralConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
// have the configurations of network
class NetworkModule {
    @Singleton
    @Provides
    fun providesClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        return okHttpClient as OkHttpClient
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GeneralConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): NetworkModel {
        return retrofit.create(NetworkModel::class.java)
    }

    companion object {
        private const val REQUEST_TIMEOUT = 60
        private var okHttpClient: OkHttpClient? = null
    }
}