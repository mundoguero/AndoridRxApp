package br.com.brisotti.rxapp.sections.subjects

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.brisotti.rxapp.R
import br.com.brisotti.rxapp.sections.BaseActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_subjects.*

class SubjectsActivity : BaseActivity() {

    private val viewModel = SubjectsViewModel()
    private lateinit var adapter: SubjectsAdapter
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)

        adapter = SubjectsAdapter(ArrayList<String>())
        activitySubjectsRecyclerView.adapter = adapter
        activitySubjectsRecyclerView.layoutManager  = LinearLayoutManager(this)

        loadSubjects()
    }

    private fun loadSubjects() {
        showLoading()
        compositeDisposable.add(
            viewModel.getSubjects()
                .subscribeOn(Schedulers.io())
                .subscribe (
                    { updateSubjects(it) },
                    { error ->  error.message?.let { onError(it) } })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun updateSubjects(list: List<String>)  {
        hideLoading()
        adapter.updateList(list)
    }
}