package com.example.testtest

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pr33.Model.BookModelClass
import kotlinx.android.synthetic.main.activity_random.*
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset


import java.util.*

class Random : AppCompatActivity() {

    val bookList: ArrayList<BookModelClass> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val obj = JSONObject(getJSONFromAssets()!!)

        val usersArray = obj.getJSONArray("books")

        for (i in 0 until usersArray.length()) {
            val book = usersArray.getJSONObject(i)
            val name = book.getString("name")
            val author = book.getString("author")
            val annotation = book.getString("annotation")
            val userDetails = BookModelClass(name, author, annotation)
            bookList.add(userDetails)
        }

        book_Random_Button.setOnClickListener {
            var id = random()
            random_Book.text = bookList[id].book
            random_Author.text = bookList[id].author
            Annotation.text = bookList[id].annotation
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = assets.open("Books.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun random(): Int {
        val rnd = (0..50).random()
        return rnd
    }
}