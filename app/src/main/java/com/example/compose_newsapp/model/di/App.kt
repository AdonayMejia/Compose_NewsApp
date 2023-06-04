package com.example.compose_newsapp.model.di

import android.app.Application
import com.example.compose_newsapp.model.network.ApiServiceClient
import com.example.compose_newsapp.model.network.GuardianApiService
import com.example.compose_newsapp.model.network.GuardianApiImpl
import com.example.compose_newsapp.model.network.repository.GuardianRepository
import com.example.compose_newsapp.model.network.repository.GuardianRepositoryImpl
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
    single { ApiServiceClient().createClient()}
    single<GuardianApiService> { GuardianApiImpl(get()) }
    single<GuardianRepository> { GuardianRepositoryImpl(get() ) }
    viewModel { SearchViewModel(get()) }
}