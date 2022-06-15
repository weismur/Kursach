package com.example.simplereader.ui.adapter.BookAdapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.project_test.ui.AppDatabase
import com.example.project_test.ui.Entity
import com.example.testtest.OnBookClickListener
import com.example.testtest.R
import kotlinx.android.synthetic.main.activity_book_check.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class BookCheckAdapter (private var results: List<Entity>, var context: Context) :
    RecyclerView.Adapter<BookCheckAdapter.ResultViewHolder>() {
    override fun getItemCount() = results.size
    val db by lazy {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "results.db"
        ).build()
    }
    class ResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val check: CheckBox = view.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.boock_check_item, parent, false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.check.text = results[position].bname
        holder.check.isChecked = results[position].checkBoock!!
        holder.check.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if(holder.check.isChecked){
                GlobalScope.launch { db.resultsDao().updateCheck(true, holder.check.text.toString()) }
            } else{
                GlobalScope.launch { db.resultsDao().updateCheck(false, holder.check.text.toString()) }
            }
        })
    }

}