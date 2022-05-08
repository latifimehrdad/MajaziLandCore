package land.majazi.majazilandcore.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import land.majazi.majazilandcore.repo.NationalCodeRepo
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NationalCodeViewModel @Inject constructor(var repo: NationalCodeRepo): ViewModel() {
    fun uploadNationalCode(file: File) = repo.uploadNationalCode(file)
}