package com.example.compose_newsapp.model.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiServiceClient {

    fun createClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = TIMEOUT
                connectTimeoutMillis = TIMEOUT
                socketTimeoutMillis = TIMEOUT
            }
        }
    }

    companion object {
        private const val TIMEOUT = 60_000L
    }
}














