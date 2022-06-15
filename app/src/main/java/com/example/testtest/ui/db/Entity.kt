package com.example.project_test.ui

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "books")
data class Entity(
    @PrimaryKey(autoGenerate = true)

    var _id: Int = 0,
    @ColumnInfo(name = "author_name") var aname: String?,
    @ColumnInfo(name = "book_name") var bname: String?,
    @ColumnInfo(name = "image_path") var ipath: String?,
    @ColumnInfo(name = "book_path") var bpath: String?,
    @ColumnInfo(name = "checkBoock") var checkBoock: Boolean,
    )