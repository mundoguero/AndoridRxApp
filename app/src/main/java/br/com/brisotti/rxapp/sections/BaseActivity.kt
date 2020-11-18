package br.com.brisotti.rxapp.sections

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.brisotti.rxapp.views.Loading

open class BaseActivity : AppCompatActivity()  {
    private var loading: Loading? = null

    fun showLoading() {
        if (loading == null){
            loading = Loading()
        }
        loading?.show(supportFragmentManager, Loading::class.java.name)
    }

    fun hideLoading() {
        loading?.dismiss()
        loading = null
    }

    fun onError(message: String) {
        hideLoading()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}