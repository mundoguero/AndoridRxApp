package br.com.brisotti.rxapp.api.services.subjects.response

data class SubjectsResponse (
    val subjects: List<SubjectResponse>
)

data class SubjectResponse (
    val id: String,
    val title: String
)