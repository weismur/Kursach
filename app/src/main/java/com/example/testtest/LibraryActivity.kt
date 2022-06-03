package com.example.testtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.project_test.ui.AppDatabase
import com.example.project_test.ui.Entity
import com.example.project_test.ui.TestData
import com.example.simplereader.ui.adapter.BookAdapter.BookAdapter

import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.android.synthetic.main.fragment_library.*
import kotlinx.android.synthetic.main.fragment_library.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LibraryActivity : AppCompatActivity(), OnBookClickListener  {

    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "results.db"
        ).build()
    }

    lateinit var book_list: List<Entity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        //supportFragmentManager.beginTransaction().replace(R.id.container, LibraryFragment()).commit()

        db.resultsDao().get_author().observe(this, {
            if (it == null) {
                GlobalScope.launch {
                    db.clearAllTables()
                    for(theme in TestData.books) {
                        db.resultsDao().insert(theme)
                    }
                }
            }
        })

        //container.recycler.layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = LinearLayoutManager(this)

        db.resultsDao().getAll("RESULT DESC").observe(this,
            { results -> recycler.adapter = BookAdapter(results,this) }
        )

        db.resultsDao().getAll("RESULT DESC").observe(this,
            { book_list = it }
        )


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
        }
        return super.onOptionsItemSelected(item)
    }

    fun Go_to_Home(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun Go_to_Library(view: View) {
        Toast.makeText(this, "Вы уже на странице библиотеки", Toast.LENGTH_SHORT).show()
    }

    override fun OnBookItemClicked(position: Int) {
        super.OnBookItemClicked(position)

        var text = book_list[position].bpath
        Toast.makeText(this, "Book " + text , Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("id", text)
        startActivity(intent)

    }
}

