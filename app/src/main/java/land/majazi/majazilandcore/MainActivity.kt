package land.majazi.majazilandcore

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import land.majazi.majazicore.manager.*
import land.majazi.majazicore.manager.hash.AESUtils
import land.majazi.majazicore.manager.hash.ShaUtils


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView1.setOnClickListener {
            val theme = ThemeManager(this)
            Log.i("meri", theme.getThemFromSharePreferences().toString())
            ThemeManager(this).changeApplicationTheme()
            Log.i("meri", theme.getThemFromSharePreferences().toString())
        }

    }
}