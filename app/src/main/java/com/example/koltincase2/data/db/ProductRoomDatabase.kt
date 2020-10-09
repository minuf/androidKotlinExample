package com.example.koltincase2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.koltincase2.data.dao.ProductDao
import com.example.koltincase2.data.models.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Product::class), version = 1, exportSchema = false)
abstract class ProductRoomDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    private class ProductDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var productDao = database.productDao()

                    // Delete all content here.
                    productDao.deleteAll()

                    // Add sample words.
                    var product = Product(1, "Conga 5490", "579.00", "998.33", "42 %", "https://www.storececotec.com/8952-home_default/conga-5490.jpg")
                    productDao.insert(product)
                    product = Product(2, "Conga 990 Vital", "148.00", "315.00", "53 %", "https://www.storececotec.com/7073-home_default/conga-990-vital.jpg")
                    productDao.insert(product)
                    product = Product(3, "Conga Serie 1090 + WinDroid 870 Connected", "289.00", "481.67", "40 %", "https://www.storececotec.com/9627-home_default/conga-1090-windroid-870-connected.jpg")
                    productDao.insert(product)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ProductRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ProductRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductRoomDatabase::class.java,
                    "product_database"
                )
                    .addCallback(ProductDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}