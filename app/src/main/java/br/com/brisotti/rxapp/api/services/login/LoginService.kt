package br.com.brisotti.rxapp.api.services.login

import br.com.brisotti.rxapp.api.services.login.response.LoginResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface LoginService {
    @GET("passwords")
    fun auth(): Observable<LoginResponse>
}