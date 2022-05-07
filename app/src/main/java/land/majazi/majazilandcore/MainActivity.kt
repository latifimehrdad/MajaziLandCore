package land.majazi.majazilandcore

import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import land.majazi.majazicore.manager.api.AuthorizationType
import land.majazi.majazicore.manager.api.ErrorType
import land.majazi.majazicore.manager.api.RemoteErrorEmitter
import land.majazi.majazicore.tools.PhotoSelectionDialog
import land.majazi.majazilandcore.viewModel.CurrencyViewModel
import land.majazi.majazilandcore.viewModel.DashboardViewModel
import land.majazi.majazilandcore.viewModel.PasswordViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RemoteErrorEmitter {

    private val currencyViewModel: CurrencyViewModel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private val password: PasswordViewModel by viewModels()
    private val tag = "okhttp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PhotoSelectionDialog(this).observe(this) { Log.i(tag, it.toString()) }


/*        password.register().observe(this) {
            it?.message?.let { it1 -> Log.i(tag, it1)}
        }*/

/*        dashboardViewModel.getDashboard().observe(this) {
            Log.i(tag, it?.data?.isNeededTwoStep.toString())
        }*/

/*        currencyViewModel.getMainCurrency().observe(this) {
            it?.message?.let { it1 -> Log.i(tag, it1) }
        }*/

/*        val file = File(this.cacheDir, "meri")
        if (file.exists())
            Log.i(tag, "exists")
        else {
            Log.i(tag, "Not exists")
            Log.i(tag, file.mkdir().toString())
        }*/


    }

    override fun unAuthorization(type: AuthorizationType, message: String?) {
        Log.i(tag, "unAuthorization : $type - msg : $message")
    }


    override fun onError(errorType: ErrorType, message: String?) {
        Log.i(tag, "errorType : $errorType - msg : $message")
    }



}