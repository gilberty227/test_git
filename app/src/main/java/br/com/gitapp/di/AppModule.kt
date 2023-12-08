package br.com.gitapp.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import br.com.gitapp.data.api.ApiGithub
import br.com.gitapp.domian.util.ConnectivityInterceptor
import br.com.gitapp.domian.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): ApiGithub =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiGithub::class.java)

    @Provides
    @Singleton
    fun provideCheckInternet(application: Application): ConnectivityInterceptor {
        return ConnectivityInterceptor(application)
    }





}