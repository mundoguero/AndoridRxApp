package br.com.brisotti.rxapp

import br.com.brisotti.rxapp.sections.login.LoginViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LoginViewModelUnitTest {
    lateinit var viewModel: LoginViewModel

    @Before
    fun before() {
        viewModel = LoginViewModel()
    }

    @Test
    fun fieldValidation_isValid() {
        assert(viewModel.fieldsValidation("user", "pass"))
    }

    @Test
    fun fieldValidationWithEmptyUsername_isNotValid() {
        assertFalse(viewModel.fieldsValidation("", "pass"))
    }

    @Test
    fun fieldValidationWithEmptyPassword_isNotValid() {
        assertFalse(viewModel.fieldsValidation("user", ""))
    }
}