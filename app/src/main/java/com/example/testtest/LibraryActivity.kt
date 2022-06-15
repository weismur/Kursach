package com.example.testtest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.project_test.ui.AppDatabase
import com.example.project_test.ui.Entity
import com.example.project_test.ui.TestData
import com.example.simplereader.ui.adapter.BookAdapter.BookAdapter
import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LibraryActivity : AppCompatActivity(), OnBookClickListener  {
    val contex = this
    lateinit var adapter: BookAdapter
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

        recycler.layoutManager = LinearLayoutManager(this)

        db.resultsDao().getAll("RESULT DESC").observe(this,
            { book_list = it
                adapter = BookAdapter(book_list, contex)
                recycler.adapter = adapter}
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
            R.id.action_search -> {
                val searchView: SearchView = item.getActionView() as SearchView
                searchView.imeOptions = EditorInfo.IME_ACTION_DONE

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        //adapter.getFilter().filter(newText)
                        db.resultsDao().getBook("%"+ "$newText" +"%").observe(contex,
                            {
                                adapter.getFilter(it)
                                book_list = it
                            })
                        return false
                    }
                })
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun OnBookItemClicked(position: Int) {
        super.OnBookItemClicked(position)
        var text = book_list[position].bpath
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("id", text)
        startActivity(intent)

    }
}

