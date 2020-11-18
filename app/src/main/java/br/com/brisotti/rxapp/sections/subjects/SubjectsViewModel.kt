package br.com.brisotti.rxapp.sections.subjects

import br.com.brisotti.rxapp.api.RetrofitProvider
import br.com.brisotti.rxapp.usercases.login.LoginUserCase
import br.com.brisotti.rxapp.usercases.subjects.SubjectsUserCase
import io.reactivex.Observable
import io.reactivex.functions.Function

class SubjectsViewModel {

    private val subjectsUserCase = SubjectsUserCase()

    fun getSubjects() : Observable<List<String>> {
        return subjectsUserCase.loadSubjects()
            .flatMap( Function { data -> Observable.just( data.map { it.title } ) })
    }

}