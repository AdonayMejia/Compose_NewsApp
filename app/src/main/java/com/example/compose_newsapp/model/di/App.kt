package com.example.compose_newsapp.model.di

import android.app.Application
import androidx.room.Room
import com.example.compose_newsapp.model.datastore.DataStoreManager
import com.example.compose_newsapp.model.network.ApiServiceClient
import com.example.compose_newsapp.model.network.GuardianApiImpl
import com.example.compose_newsapp.model.network.GuardianApiService
import com.example.compose_newsapp.model.network.repository.GuardianRepository
import com.example.compose_newsapp.model.network.repository.GuardianRepositoryImpl
import com.example.compose_newsapp.model.room.NewsDatabase
import com.example.compose_newsapp.model.room.repository.NewsRepository
import com.example.compose_newsapp.model.room.repository.NewsRepositoryImpl
import com.example.compose_newsapp.ui.favoriteview.viewmodel.FavoriteViewModel
import com.example.compose_newsapp.ui.searchview.viewmodel.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(newsModule)
        }
    }
}

val newsModule = module {
    single { ApiServiceClient().createClient() }
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java, DATABASE_NAME
        )
            .build()
    }
    single { get<NewsDatabase>().newsDao() }
    single<GuardianApiService> { GuardianApiImpl(get()) }
    single<GuardianRepository> { GuardianRepositoryImpl(get()) }
    single<NewsRepository> { NewsRepositoryImpl(get()) }
    single { DataStoreManager(androidContext()) }
    viewModel { SearchViewModel(get(),get()) }
    viewModel { FavoriteViewModel(get()) }
}

private const val DATABASE_NAME = "MovieDataBase"