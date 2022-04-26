package land.majazi.majazicore.models

abstract class BaseResponseModel {
    abstract val message: String
    abstract val isSuccess: Boolean
    abstract val errors: MutableList<String>
}
