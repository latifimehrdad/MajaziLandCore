package land.majazi.majazicore.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_capture_video.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import land.majazi.majazicore.R
import land.majazi.majazicore.tools.camerarecorder.CameraRecorder
import land.majazi.majazicore.tools.camerarecorder.CameraRecorderBuilder
import land.majazi.majazicore.tools.camerarecorder.LensFacing
import land.majazi.majazicore.views.custom.SampleGLView

class CaptureVideoFragment : Fragment() {

    private var secondsLimited: Int = 25
    private var recording = false
    private lateinit var videoTextContent: String
    private lateinit var sampleGLView: SampleGLView
    private lateinit var cameraRecorder: CameraRecorder
    private lateinit var jobTimerElapse: Job
    private lateinit var lensFacing: LensFacing


    //______________________________________________________________________________________________ onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            secondsLimited = requireArguments().getInt("secondsLimited", 25)
            videoTextContent = requireArguments().getString("videoTextContent", "")
        }
    }
    //______________________________________________________________________________________________ onCreate


    //______________________________________________________________________________________________ onCreateView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_capture_video, container, false)
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*        recording = false
        imageViewRecord.setImageDrawable(resources.getDrawable(R.drawable.bg_video_record, null))
        textViewContent.text = videoTextContent
        linearLayoutFinish.visibility = View.INVISIBLE
        imageViewRecord.visibility = View.VISIBLE
        imageViewSwitch.visibility = View.VISIBLE
        textViewTime.visibility = View.INVISIBLE
        videoViewPlayback.visibility = View.GONE
        setClicks()
        setFrontCamera()*/
    }
    //______________________________________________________________________________________________ onViewCreated


    //______________________________________________________________________________________________ setClicks
    private fun setClicks() {
        imageViewRecord.setOnClickListener { }
        imageViewSwitch.setOnClickListener { }
        imageViewOk.setOnClickListener { }
        imageViewRetry.setOnClickListener { }
    }
    //______________________________________________________________________________________________ setClicks


    //______________________________________________________________________________________________ setFrontCamera
    private fun setFrontCamera() {
        refreshRecording()
        CoroutineScope(IO).launch {
            delay(500)
            lensFacing = LensFacing.FRONT
            withContext(Main) {
                initCamera()
            }
        }
    }
    //______________________________________________________________________________________________ setFrontCamera



    //______________________________________________________________________________________________ refreshRecording
    private fun refreshRecording() {
        imageViewRecord.visibility = View.INVISIBLE
        sampleGLView.onPause()
        cameraRecorder.stop()
        cameraRecorder.release()
        frameLayoutRecord.removeView(sampleGLView)
        jobTimerElapse.cancel()
    }
    //______________________________________________________________________________________________ refreshRecording



    //______________________________________________________________________________________________ initCamera
    private fun initCamera() {
        sampleGLView = SampleGLView(context)
        cameraRecorder = CameraRecorderBuilder(activity, sampleGLView)
            .lensFacing(lensFacing)
            .videoSize(720, 1280)
            .build()
        cameraRecorder.changeAutoFocus()
        frameLayoutRecord.addView(sampleGLView)
        imageViewRecord.visibility = View.VISIBLE
    }
    //______________________________________________________________________________________________ initCamera

}