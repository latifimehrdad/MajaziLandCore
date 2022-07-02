package land.majazi.majazilandcore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fr_national.*
import land.majazi.majazicore.manager.MessageManager
import land.majazi.majazicore.views.fragments.SelectPhotoFragment
import land.majazi.majazilandcore.viewModel.NationalCodeViewModel
import javax.inject.Inject

@AndroidEntryPoint
class NationalFragment: SelectPhotoFragment() {

    @Inject
    lateinit var messageManager: MessageManager

    private val national: NationalCodeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fr_national, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageManager.showSnackBar(coordinator, "Shiiiiittt", "refresh", this.test("oh Yessss"))
/*        selectPhotoDialog(false) {
            national.uploadNationalCode(it).observe(viewLifecycleOwner) {itt ->
                itt?.message?.let { it1 -> Log.i("meri", it1)}
            }
        }*/
    }


    fun test(message: String) {
        Log.i("meri", message)
    }


}