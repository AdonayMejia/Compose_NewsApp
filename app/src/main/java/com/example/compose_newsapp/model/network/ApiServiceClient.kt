package com.example.compose_newsapp.model.network

import com.example.compose_newsapp.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiServiceClient {

      fun createClient() : HttpClient {
//         return HttpClient(OkHttp){
//             install(DefaultRequest){
//                 host = "Base_Url"
//                 url {
//                     protocol = URLProtocol.HTTPS
//                 }
//                 contentType(ContentType.Application.Json)
//             }
//             install(ContentNegotiation){
//                 json(Json{
//                     prettyPrint = true
//                     isLenient = true
//                     ignoreUnknownKeys = true
//                 })
//             }
//         }
        return HttpClient(OkHttp){
            install(ContentNegotiation){
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
                )
            }
            install(HttpTimeout){
                requestTimeoutMillis = TIMEOUT
                connectTimeoutMillis = TIMEOUT
                socketTimeoutMillis = TIMEOUT
            }
            install(DefaultRequest){
                host = BuildConfig.GUARDIAN_API_BASE_URL
                url{
                    protocol = URLProtocol.HTTPS
                    parameters.append("key", BuildConfig.GUARDIAN_API_KEY)
                }
                contentType(ContentType.Application.Json)
            }
        }
    }

    companion object{
        private const val TIMEOUT = 60_000L
    }

//    override suspend fun searchArticles(query: String): GuardianApiResponse {
//        val url = "https://content.guardianapis.com/search?q=$query&api-key=b6930d40-9cc1-4a77-8f0c-f190605f00e8"
//        return client.get(url).body()
//    }
//
//    suspend fun testFunction(){
//        val guardian = ApiServiceClient()
//        val query = "sport"
//        val response = guardian.searchArticles(query)
//
//        println("Status: ${response.response.status}")
//        println("Total: ${response.response.total}")
//        println("Result:")
//        response.response.results.forEach { news ->
//            println("Title: ${news.webTitle}")
//            println("Url: ${news.webUrl}")
//        }
//
//    }
}














