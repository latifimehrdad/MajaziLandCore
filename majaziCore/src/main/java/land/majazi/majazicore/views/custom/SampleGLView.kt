package land.majazi.majazicore.views.custom

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener

class SampleGLView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) :
    GLSurfaceView(context, attrs), OnTouchListener {
    private var touchListener: TouchListener? = null

    //______________________________________________________________________________________________ init
    init {
        setOnTouchListener(this)
    }
    //______________________________________________________________________________________________ init


    //______________________________________________________________________________________________ onTouch
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val actionMasked = event.actionMasked
        if (actionMasked != MotionEvent.ACTION_DOWN) {
            return false
        }
        if (touchListener != null) {
            touchListener!!.onTouch(event, v.width, v.height)
        }
        return false
    }
    //______________________________________________________________________________________________ onTouch


    //______________________________________________________________________________________________ setTouchListener
    fun setTouchListener(touchListener: TouchListener?) {
        this.touchListener = touchListener
    }
    //______________________________________________________________________________________________ setTouchListener


    //______________________________________________________________________________________________ TouchListener
    interface TouchListener {
        fun onTouch(event: MotionEvent?, width: Int, height: Int)
    }
    //______________________________________________________________________________________________ TouchListener


}