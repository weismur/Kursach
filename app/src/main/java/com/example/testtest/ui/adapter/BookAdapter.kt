package com.example.simplereader.ui.adapter.BookAdapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_test.ui.Entity

import com.example.testtest.R
import com.example.testtest.OnBookClickListener


class BookAdapter (private val results: List<Entity>, private val onBookClickListener: OnBookClickListener) :
    RecyclerView.Adapter<BookAdapter.ResultViewHolder>() {

    override fun getItemCount() = results.size

    class ResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bName: TextView = view.findViewById(R.id.book_title)
        val aName: TextView = view.findViewById(R.id.book_author)
        val image: ImageView = view.findViewById(R.id.book_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bName?.text = results[position].bname
        holder.aName?.text = results[position].aname
        holder.image?.setImageResource(R.drawable.empty_book)

        holder.itemView.setOnClickListener {
            onBookClickListener.OnBookItemClicked(position)
        }



    }


}