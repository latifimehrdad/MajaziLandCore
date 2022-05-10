package land.majazi.majazilandcore.repo

import android.util.Log
import land.majazi.majazicore.manager.api.ProgressRequestBodyManager
import land.majazi.majazicore.manager.api.RemoteErrorEmitter
import land.majazi.majazicore.manager.api.apiCall
import land.majazi.majazilandcore.ApiInterface
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class NationalCodeRepo @Inject constructor(private val apiInterface: ApiInterface): ProgressRequestBodyManager.UploadCallBack {
    @Inject lateinit var emitter: RemoteErrorEmitter
    fun uploadNationalCode(file: File) = apiCall(emitter){
        val type = "NationalCardImg".toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val national = "3962623914".toRequestBody("multipart/form-data".toMediaTypeOrNull())
//        val fileBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val fileBody = ProgressRequestBodyManager(file, "image", this)
        val image = MultipartBody.Part.createFormData("File", file.name, fileBody)
        val token = "Bearer eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwidHlwIjoiSldUIn0.hHGSvGgA1ceHgHK2WUJIfTDT309-6ptWRbE5gOSr6g5gLGOKF9_2bg.NjGagWGvfaB9COWS_atBZg.ubYdKOpdyMXOieYWOzL0M4pWaC9mWiJ41v_WMUmAPwADsXvwAj2rOYvPWLfsmJSWY9ShxooPGcT52xYYyQXFrZIct8c3mVlyAV0ex9cO2aPsYQGKw1HoxrDueaaVieRAIUdycfmVqyxgA00YDnG29tKiORlgjgsbGcQL0mgSZcMbn3dTh5IkGpnuo-ktXOun2Q25CG9uBTd99mJPxK_3JKqkOrdskg_0eDvvR2rjk9QSfHs_nS7TmzwedmRheJ0AwcMA3nr5_KJSDyWa6nUKO9b9yw-PWieu1c61xy2E3GeGhARnp6GvOw0b_B0qHircN_u0Qld1My6itwqNNLvSz_T775hUZDsUGdAea0aH4X2BZKbUSxKeFIVIN1K-Sxfrn-iSit_WDiOqlKrlHXwYaTYksUReIdY8-zeHMo3-GSa_gnwwr9R9GpwIi2K-75SBMNeMzFtFfSJd4cELWrCmntLjN6hvjqK5Q7TRXy1LHmW3jV_o-Je-iqXG1_09shVAuYJhs-UFqRB2lkzs0-Y1xpDJoZ-wlr9Tvf1APmmJ-ckJM16sCrjqI5J6ANd0onXDRX_csqAyJLZ_55cNyVCgefDLtNYx3hm2-OoPjbUC3GoYgV_BekOmAxCwtnr_LBSxH7_GgLPPj3EoDNZYuh1LiQi6PNWsE_0Nsr9c6nP9RZstpVnag4dy4zrFjliuTshCL5J_MCV3CXSMxyXARuvM0gByBmtctb8VY1qBHNddSlAiP7WN1diHM27wWLWWv6_oMRYwTf_YsL8H305_q510z_rhrSFn6mBd4f39gIhGvaxNalD_etlrupynjQJD_zW9Wtp3LVOBREXjmD3B-5Jg_du_Oyy11uMKi52Pwq2jw2hMqA1XgoMgdjKZvEqbnXw5fZPGAZbCGe6NjHyMhyARvkP32RdPG2uSPWTHZ4sb7XfERppdgbO6GIub_I01KDhQoBrp45ot7jS7bqFK-TKaiLMW-BgLlD1gjwQTp3hvfpzwTC2a5-OMQZTusv2Uzo32lm_Mdya71K-8rYu5GNumfam_KR6oStz9WWOh57oXSDWYrA6rY2Hl0OfXVB0LHWyqznzgiRNebSNYYJkkutjTjm41BY9dltMTeK-IQZgKD8I0m-2PJgbOydcNFQaous0GI1AfSL07FYRY21Bhf6DUqRkjdJRuHX1KqRNdA3CKGA8gjDVidGuWg22KaSfecug6iIf3SOcqcAbGY_scIv0FtT7l0MiZXyj3wKEWfQ6YfG0.x3Rb-8tTut3dDRSfapHoMw"
        apiInterface.uploadNationalCode(image, type, national, token)
    }

    override fun onUploadPercent(percent: Int) {
        Log.i("meri", "percent : $percent")
    }
}