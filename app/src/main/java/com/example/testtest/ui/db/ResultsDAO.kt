package com.example.project_test.ui

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ResultsDao {
    @Query("SELECT author_name FROM books ")
    fun get_author(): LiveData<String>
    @Query("SELECT book_name FROM books ")
    fun get_book(): LiveData<String>
    @Insert
    fun insert(vararg result: Entity)
    @Delete
    fun delete(result: Entity)
    @Update
    fun update(vararg result: Entity)
    @Query("SELECT * FROM books ORDER BY :order")
    fun getAll(order: String): LiveData<List<Entity>>
//    @Query("SELECT book_path FROM books WHERE _id = n ")
//    fun get_book_path(num: Int): LiveData<String>





}