package land.majazi.majazilandcore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import land.majazi.majazicore.views.fragments.SelectPhotoFragment
import land.majazi.majazilandcore.viewModel.NationalCodeViewModel

@AndroidEntryPoint
class NationalFragment: SelectPhotoFragment() {

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
        selectPhotoDialog(false) {
            national.uploadNationalCode(it).observe(viewLifecycleOwner) {itt ->
                itt?.message?.let { it1 -> Log.i("meri", it1)}
            }
        }
    }




}