package br.com.brisotti.rxapp.usercases.subjects

import br.com.brisotti.rxapp.api.RetrofitProvider
import br.com.brisotti.rxapp.usercases.subjects.model.SubjectResponseData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SubjectsUserCase {

    private val subjectsService = RetrofitProvider.subjectsService()

    fun loadSubjects() : Observable<List<SubjectResponseData>> {
        return Observable.create<List<SubjectResponseData>>{ emitter ->
            subjectsService.subjects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                {response -> emitter.onNext( response.map { SubjectResponseData(it.id, it.title) } ) },
                { t -> emitter.onError(t) }
            )
        }
    }

}