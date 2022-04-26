package land.majazi.majazilandcore.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import land.majazi.majazilandcore.repo.CurrencyRepo
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(var repository: CurrencyRepo): ViewModel() {
    fun getCurrency() = repository.getCurrency()
    fun getMainCurrency() = repository.getMainCurrency()
}

