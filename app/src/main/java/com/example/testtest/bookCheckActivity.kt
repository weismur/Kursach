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
import com.example.simplereader.ui.adapter.BookAdapter.BookCheckAdapter
import kotlinx.android.synthetic.main.activity_book_check.*
import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class bookCheckActivity : AppCompatActivity() {
    val contex = this
    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "results.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_check)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        lateinit var adapter: BookAdapter
        checkBoockRecycler.layoutManager = LinearLayoutManager(this)
        db.resultsDao().getAll("RESULT DESC").observe(this,
            { var adapter = BookCheckAdapter(it, this)
                checkBoockRecycler.adapter = adapter }
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}