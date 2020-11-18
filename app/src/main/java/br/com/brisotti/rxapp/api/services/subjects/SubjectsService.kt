package br.com.brisotti.rxapp.api.services.subjects

import br.com.brisotti.rxapp.api.services.subjects.response.SubjectResponse
import br.com.brisotti.rxapp.api.services.subjects.response.SubjectsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface SubjectsService {
    @GET("subjects")
    fun subjects(): Observable<List<SubjectResponse>>
}