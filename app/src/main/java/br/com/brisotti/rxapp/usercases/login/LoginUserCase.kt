package br.com.brisotti.rxapp.usercases.login

import br.com.brisotti.rxapp.api.RetrofitProvider
import br.com.brisotti.rxapp.usercases.login.model.LoginResponseData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginUserCase {

    private val loginService = RetrofitProvider.loginService()

    fun authenticate() : Observable<LoginResponseData> {
        return Observable.create<LoginResponseData>{ emitter ->
            loginService.auth()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                {response -> emitter.onNext(LoginResponseData(response.password)) },
                { t -> emitter.onError(t) }
            )
        }
    }

}