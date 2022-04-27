package land.majazi.majazilandcore.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import land.majazi.majazilandcore.repo.DashboardRepo
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(var repository: DashboardRepo): ViewModel() {
    fun getDashboard() = repository.getDashboard()
}