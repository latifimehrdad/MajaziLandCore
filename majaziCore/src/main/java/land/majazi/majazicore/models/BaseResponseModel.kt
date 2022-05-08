package land.majazi.majazicore.models

data class BaseResponseModel(
    override val message: String,
    override val isSuccess: Boolean,
    override val errors: MutableList<String>
) : BaseResponseAbstractModel()