package com.example.koltincase2.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.koltincase2.data.models.Product

@Dao
interface ProductDao {

    @Query("SELECT * from products_table ORDER BY id ASC")
    fun getAlphabetizedWords(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Query("DELETE FROM products_table")
    suspend fun deleteAll()
}