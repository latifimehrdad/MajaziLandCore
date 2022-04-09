package land.majazi.majazicore.manager

import android.annotation.SuppressLint
import android.graphics.BlurMaskFilter
import android.view.View
import android.widget.TextView

class BlurryManager {

    //______________________________________________________________________________________________ blurryTextView
    @SuppressLint("SetTextI18n")
    fun blurryTextView(textView: TextView){
        textView.text = "0000000000"
        textView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val radius : Float = textView.text.length.toFloat()
        val filter = BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL)
        textView.paint.maskFilter = filter
    }
    //______________________________________________________________________________________________ blurryTextView


}