package br.com.brisotti.rxapp.api

import br.com.brisotti.rxapp.api.services.login.LoginService
import br.com.brisotti.rxapp.api.services.subjects.SubjectsService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitProvider {

    private val baseURL = "http://my-json-server.typicode.com/mundoguero/fakeAPI/"
    private val retrofit: Retrofit

    init {
        val httpClient = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(httpLoggingInterceptor)
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val requestBuilder: Request.Builder = chain.request().newBuilder()
                    requestBuilder.header("Content-Type", "application/json")
                    return chain.proceed(requestBuilder.build())
                }
            })
        retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()


    }

    companion object {
        fun loginService() : LoginService {
            return RetrofitProvider().retrofit.create(LoginService::class.java)
        }
        fun subjectsService() : SubjectsService {
            return RetrofitProvider().retrofit.create(SubjectsService::class.java)
        }
    }
}