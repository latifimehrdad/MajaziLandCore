package land.majazi.majazilandcore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import land.majazi.majazicore.manager.AuthorizationType
import land.majazi.majazicore.manager.ErrorType
import land.majazi.majazicore.manager.RemoteErrorEmitter
import land.majazi.majazilandcore.viewModel.CurrencyViewModel
import land.majazi.majazilandcore.viewModel.DashboardViewModel
import land.majazi.majazilandcore.viewModel.PasswordViewModel
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RemoteErrorEmitter {

    private val currencyViewModel : CurrencyViewModel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private val password: PasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        password.register().observe(this) {
            it?.message?.let { it1 -> Log.i("meri", it1)}
        }*/

/*        dashboardViewModel.getDashboard().observe(this) {
            Log.i("meri", it?.data?.isNeededTwoStep.toString())
        }*/

/*        currencyViewModel.getMainCurrency().observe(this) {
            it?.message?.let { it1 -> Log.i("meri", it1) }
        }*/

    }

    override fun unAuthorization(type: AuthorizationType, message: String?) {
        Log.i("meri", "errorType : $type - msg : $message")
    }


    override fun onError(errorType: ErrorType, message: String?) {
        Log.i("meri", "errorType : $errorType - msg : $message")
    }

}