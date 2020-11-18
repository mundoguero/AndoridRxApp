package br.com.brisotti.rxapp.sections.login

import android.text.Editable
import android.text.TextWatcher
import br.com.brisotti.rxapp.usercases.login.LoginUserCase
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.subjects.BehaviorSubject

class LoginViewModel {
    val compositeDisposable = CompositeDisposable()
    private val usernameObservable: BehaviorSubject<String> = BehaviorSubject.createDefault("")
    private val passwordObservable: BehaviorSubject<String> = BehaviorSubject.createDefault("")
    val buttonEnabled = BehaviorSubject.createDefault(false)

    private val loginUserCase = LoginUserCase()

    init {
        compositeDisposable.add(
            Observable.combineLatest(usernameObservable, passwordObservable, BiFunction { username: String, password: String -> fieldsValidation(username, password) })
            .subscribe(
                { isValid: Boolean -> buttonEnabled.onNext(isValid) },
                { throwable -> print(throwable) }
            )
        )
    }
    fun fieldsValidation(username: String, password: String) : Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }

    fun dispose() {
        compositeDisposable.dispose()
    }

    fun usernameWatcher() : TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                usernameObservable.onNext(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }
    }

    fun passwordWatcher() : TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                passwordObservable.onNext(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }
    }

    fun login() : Observable<Boolean> {
        return loginUserCase.authenticate()
            .flatMap( Function { return@Function Observable.just(it.password == passwordObservable.value) })
    }

}