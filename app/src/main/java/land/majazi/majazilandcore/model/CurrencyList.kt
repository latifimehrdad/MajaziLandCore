package land.majazi.majazilandcore.model

import land.majazi.majazicore.models.BaseResponseModel

data class CurrencyList(
    val data: MutableList<Currency>,
    override val message: String,
    override val isSuccess: Boolean,
    override val errors: MutableList<String>
) : BaseResponseModel()
