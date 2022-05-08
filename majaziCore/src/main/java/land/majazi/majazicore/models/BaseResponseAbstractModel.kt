package land.majazi.majazicore.models

abstract class BaseResponseAbstractModel {
    abstract val message: String
    abstract val isSuccess: Boolean
    abstract val errors: MutableList<String>
}
