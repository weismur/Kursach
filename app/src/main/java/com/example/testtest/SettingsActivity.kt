package com.example.testtest

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingsActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }

    override fun onDestroy() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "align") {
            Toast.makeText(this, "Для смены выравнивания перезапустите приложение", Toast.LENGTH_LONG).show()
        }
        fun isNumeric(toCheck: String): Boolean {
            return toCheck.all { char -> char.isDigit() }
        }
        fun changeSize(){
            val prefs = PreferenceManager.getDefaultSharedPreferences(this)
            Toast.makeText(this, "Неверное значение", Toast.LENGTH_SHORT).show()
            val editor: SharedPreferences.Editor = prefs!!.edit()
            editor.putString("size", "16")
            editor.apply()
        }
        if (key == "size") {
            val prefs = PreferenceManager.getDefaultSharedPreferences(this)

            val size = prefs!!.getString("size","16")
            if (size != ""){
                if (size!!.all { char -> char.isDigit() }){
                    Log.d("TAG", size!!.all { char -> char.isDigit() }.toString())
                    if (size!!.toInt() in 10..32){
                    }else {
                        changeSize()
                    }
                }else {
                    changeSize()
                }
            }else {
                changeSize()
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}