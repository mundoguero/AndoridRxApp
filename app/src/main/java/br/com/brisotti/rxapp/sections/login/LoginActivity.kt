package br.com.brisotti.rxapp.sections.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.brisotti.rxapp.R
import br.com.brisotti.rxapp.sections.BaseActivity
import br.com.brisotti.rxapp.sections.subjects.SubjectsActivity
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    private val viewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        activityLoginUsernameEditText.addTextChangedListener(viewModel.usernameWatcher())
        activityLoginPasswordEditText.addTextChangedListener(viewModel.passwordWatcher())
        addSubscribers()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }

    private fun addSubscribers() {
        viewModel.compositeDisposable.add(
            viewModel.buttonEnabled
                .subscribeOn(Schedulers.io())
                .subscribe { enabled ->
                    setLoginButton(enabled)
                }
            )
    }

    private fun setLoginButton(enabled: Boolean) {
        activityLoginLoginButton.isEnabled = enabled
        activityLoginLoginButton.alpha = if (enabled) 1.0f else 0.5f
    }

    fun onLoginTapped(view: View) {
        showLoading()
        viewModel.compositeDisposable.add(
            viewModel.login()
                .subscribeOn(Schedulers.io())
                .subscribe (
                    { handleLogin(it) },
                    { error ->  error.message?.let { onError(it) } })
        )
    }

    private fun handleLogin(success: Boolean) {
        hideLoading()
        if (success) {
            val intent = Intent(this, SubjectsActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            activityLoginPasswordEditText.error =
                getString(R.string.activity_login_invalid_password_message)
        }
    }


}