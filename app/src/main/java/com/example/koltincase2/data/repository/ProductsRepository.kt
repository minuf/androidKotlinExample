package com.example.koltincase2.data.repository

import androidx.lifecycle.LiveData
import com.example.koltincase2.data.dao.ProductDao
import com.example.koltincase2.data.models.Product

class ProductsRepository(private val productDao: ProductDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allProducts: LiveData<List<Product>> = productDao.getAlphabetizedWords()

    suspend fun insert(product: Product) {
        productDao.insert(product)
    }
}