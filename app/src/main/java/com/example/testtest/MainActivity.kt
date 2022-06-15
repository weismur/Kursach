package com.example.testtest

import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.preference.PreferenceManager
import com.example.testtest.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.container
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var mTTS:TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var FilePath = intent.getStringExtra("id")
        val textView = findViewById<TextView>(R.id.Place_for_book)

        val sampleText: String = applicationContext.assets.open(FilePath+".txt").bufferedReader().use {
            it.readText()
        }
        textView.text = sampleText

        add_preferences()

        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR){

                var local = Locale("ru")
                mTTS.language = local
            }
        })

        var speakBtn = findViewById<Button>(R.id.startButton)
        var stopBtn = findViewById<Button>(R.id.stopButton)
        var textEt = findViewById<TextView>(R.id.Place_for_book)

        speakBtn.setOnClickListener {
            val toSpeak = textEt.text.toString()
            val startEnd = toSpeak[0].toString() + toSpeak[0].toString()
            val toSpeakqeue = toSpeak.split(startEnd).toMutableList()
            for (i in toSpeakqeue){
                if (i == ""){
                }
                else{
                    mTTS.speak(i, TextToSpeech.QUEUE_ADD, null)
                }
            }
        }

        stopBtn.setOnClickListener {
            if (mTTS.isSpeaking){
                mTTS.stop()}
            else{ }
        }
    }

    override fun onResume() {
        super.onResume()
        add_preferences()
    }

    override fun onPause() {
        if (mTTS.isSpeaking){
            mTTS.stop()
        }
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_developers -> {
                val intent = Intent(this, AboutProgram::class.java)
                startActivity(intent)
                return true
            }
            R.id.book_check -> {
                val intent = Intent(this, bookCheckActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_list -> {
                val intent = Intent(this, Random::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    fun add_preferences(){
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val color = prefs!!.getString("list", "")
        val size = prefs!!.getString("size","16")
        val font = prefs!!.getString("fonts","")
        val align = prefs!!.getString("align","")
        val textView = findViewById<TextView>(R.id.Place_for_book)
        val startbutton = findViewById<Button>(R.id.startButton)
        val stopbutton = findViewById<Button>(R.id.stopButton)


        if (color == "1") {
            container.setBackgroundColor(resources.getColor(R.color.theme1_background))
            textView.setTextColor(resources.getColor(R.color.theme1_textColor))
            startbutton.setBackgroundColor(resources.getColor(R.color.theme1_textColor))
            startbutton.setTextColor(resources.getColor(R.color.theme1_background))
            stopbutton.setBackgroundColor(resources.getColor(R.color.theme1_textColor))
            stopbutton.setTextColor(resources.getColor(R.color.theme1_background))

        }
        if (color == "2") {
            container.setBackgroundColor(resources.getColor(R.color.theme2_background))
            textView.setTextColor(resources.getColor(R.color.theme2_textColor))
            startbutton.setBackgroundColor(resources.getColor(R.color.theme2_textColor))
            startbutton.setTextColor(resources.getColor(R.color.theme2_background))
            stopbutton.setBackgroundColor(resources.getColor(R.color.theme2_textColor))
            stopbutton.setTextColor(resources.getColor(R.color.theme2_background))
        }
        if (color == "3") {
            container.setBackgroundColor(resources.getColor(R.color.theme3_background))
            textView.setTextColor(resources.getColor(R.color.theme3_textColor))
            startbutton.setBackgroundColor(resources.getColor(R.color.theme3_textColor))
            startbutton.setTextColor(resources.getColor(R.color.theme3_background))
            stopbutton.setBackgroundColor(resources.getColor(R.color.theme3_textColor))
            stopbutton.setTextColor(resources.getColor(R.color.theme3_background))
        }
        if (color == "4") {
            container.setBackgroundColor(resources.getColor(R.color.theme4_background))
            textView.setTextColor(resources.getColor(R.color.theme4_textColor))
            startbutton.setBackgroundColor(resources.getColor(R.color.theme4_textColor))
            startbutton.setTextColor(resources.getColor(R.color.theme4_background))
            stopbutton.setBackgroundColor(resources.getColor(R.color.theme4_textColor))
            stopbutton.setTextColor(resources.getColor(R.color.theme4_background))
        }

        if (size != null) {
            textView.textSize = size.toInt().toFloat()
        }

        if (font == "1") {
            textView.typeface = ResourcesCompat.getFont(this, R.font.roboto)
        }
        if (font == "2") {
            textView.typeface = ResourcesCompat.getFont(this, R.font.russoone)
        }
        if (font == "3") {
            textView.typeface = ResourcesCompat.getFont(this, R.font.comfortaa)
        }
        if (font == "4") {
            textView.typeface = ResourcesCompat.getFont(this, R.font.tinos)
        }

        if (align == "1") {
            textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER;

        }
        if (align == "2") {
            textView.textAlignment = TextView.TEXT_ALIGNMENT_TEXT_START;
        }
        if (align == "3") {
            textView.textAlignment = TextView.TEXT_ALIGNMENT_TEXT_END;
        }
    }
}