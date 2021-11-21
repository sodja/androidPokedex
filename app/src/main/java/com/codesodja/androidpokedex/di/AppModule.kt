package com.codesodja.androidpokedex.di

import com.codesodja.androidpokedex.data.remote.ApiInterface
import com.codesodja.androidpokedex.repository.PokedexRepository
import com.codesodja.androidpokedex.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

        @Singleton
        @Provides
        fun provideHttpClient(): OkHttpClient {
            return OkHttpClient
                .Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()
        }

        @Singleton
        @Provides
        fun provideConverterFactory(): GsonConverterFactory =
            GsonConverterFactory.create()

    @Singleton
    @Provides
    fun providerPokedexRepository(
        apiInterface: ApiInterface
    ) = PokedexRepository(apiInterface)

    @Singleton
    @Provides
    fun providerApiInterface(retrofit: Retrofit): ApiInterface = 
        retrofit.create(ApiInterface::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}