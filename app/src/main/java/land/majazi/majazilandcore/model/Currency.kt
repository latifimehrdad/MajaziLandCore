package land.majazi.majazilandcore.model

data class Currency(

    val isActiveSell: Boolean,
    val isActiveBuy: Boolean,
    val isDecimal: Boolean,
    val isFavorite: Boolean,
    val decimalPlace: Int,
    val id: Long,
    val buyPrice: Double,
    val sellPrice: Double,
    val usdPrice: Double,
    val volume_24h: Double,
    val percent_Change_1h: Double,
    val percent_Change_24h: Double,
    val percent_Change_7d: Double,
    val market_Cap: Double,
    val name: String,
    val symbol: String,
    val namePersian: String,
    val logo: String,
    val chart_24h: String,
    val chart_7d: String,
)
