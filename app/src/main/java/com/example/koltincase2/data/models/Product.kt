package com.example.koltincase2.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
class Product(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    val title: String,
    val price: String,
    val oldPrice: String,
    val discount: String,
    val imageUrl: String
)