package land.majazi.majazilandcore.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import land.majazi.majazilandcore.repo.PasswordRepo
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor(var repo: PasswordRepo): ViewModel(){
    fun register() = repo.register()
}