package land.majazi.majazicore.manager

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate

class ThemeManager(val context: Context) {

    private val sharePreferencesKey = "themeSharedPreferences"

    //______________________________________________________________________________________________ saveThemeToSharePreferences
    private fun saveThemeToSharePreferences(theme: Int): Boolean {
        val sharedPreferences = SharedPreferencesManager(context).get()
        val editor = sharedPreferences.edit()
        editor.putInt(sharePreferencesKey, theme)
        editor.apply()
        return true
    }
    //______________________________________________________________________________________________ saveThemeToSharePreferences


    //______________________________________________________________________________________________ getThemFromSharePreferences
    fun getThemFromSharePreferences(): Int {
        val sharedPreferences = SharedPreferencesManager(context).get()
        return sharedPreferences.getInt(sharePreferencesKey, -1)
    }
    //______________________________________________________________________________________________ getThemFromSharePreferences


    //______________________________________________________________________________________________ applicationTheme
    fun applicationTheme(): Int {
        val theme = getThemFromSharePreferences()
        return if (theme != -1)
            theme
        else
            return context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    }
    //______________________________________________________________________________________________ applicationTheme


    //______________________________________________________________________________________________ changeApplicationTheme
    fun changeApplicationTheme() {
        when (getThemFromSharePreferences()) {
            Configuration.UI_MODE_NIGHT_YES -> {
                saveThemeToSharePreferences(Configuration.UI_MODE_NIGHT_NO)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                saveThemeToSharePreferences(Configuration.UI_MODE_NIGHT_YES)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                saveThemeToSharePreferences(Configuration.UI_MODE_NIGHT_NO)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
    //______________________________________________________________________________________________ changeApplicationTheme


}