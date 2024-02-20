package com.debug.tmdb.main.data.di

import android.util.Log
import com.debug.tmdb.main.data.repository.MovieRepository
import com.debug.tmdb.main.data.repository.MovieRepositoryImpl
import com.debug.tmdb.main.data.service.MovieService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    fun load() {
        loadKoinModules(movieModule() + networkModule())
    }

    private fun movieModule() : Module {
        return module {
            single<MovieRepository> { MovieRepositoryImpl(get()) }
        }
    }

    private fun networkModule() : Module {
        return module {
            single { createService(get(), get()) }

            single { GsonBuilder().create() }

            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it)
                }

                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
        }
    }

    private const val baseUrl = "https://api.themoviedb.org/3/"
    private const val OK_HTTP = "Ok http"

    private fun createService(
        factory: Gson,
        client: OkHttpClient
    ): MovieService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
            .create(MovieService::class.java)

    }

}