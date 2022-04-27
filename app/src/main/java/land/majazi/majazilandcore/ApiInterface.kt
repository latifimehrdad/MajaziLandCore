package land.majazi.majazilandcore

import land.majazi.majazicore.models.BaseResponseModel
import land.majazi.majazilandcore.model.CurrencyList
import land.majazi.majazilandcore.model.DashboardRequirementResponse
import retrofit2.http.*

interface ApiInterface {

    companion object {
        const val api = "/api"
        const val v1 = "$api/v1"
        const val version = "$api/v654654"
    }

    @GET("$v1/GetCurrencies")
    suspend fun getCurrency(@Header("Authorization") Authorization: String): CurrencyList

    @GET("$v1/GetDashboardRequirements")
    suspend fun getDashboard(@Header("Authorization")Authorization: String): DashboardRequirementResponse

    @FormUrlEncoded
    @POST("$v1/Register")
    suspend fun register(
        @Field("MobileNumber") MobileNumber: String,
        @Field("Password") Password: String,
        @Field("ConfirmPassword") ConfirmPassword: String): BaseResponseModel


    @GET("$version/Currencies/GetMainCurrencies")
    suspend fun getMainCurrency(@Header("Authorization")Authorization: String): CurrencyList
}