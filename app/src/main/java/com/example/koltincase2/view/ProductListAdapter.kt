package com.example.koltincase2.view

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.koltincase2.R
import com.example.koltincase2.data.models.Product
import com.squareup.picasso.Picasso

class ProductListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ProductListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var products = emptyList<Product>()

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProductTitle: TextView = itemView.findViewById(R.id.tv_title)
        val ivProductImage: ImageView = itemView.findViewById(R.id.iv_product_image)
        val tvProductPrice: TextView = itemView.findViewById(R.id.tv_price)
        val tvProductOldPrice: TextView = itemView.findViewById(R.id.tv_old_price)
        val tvProductDiscount: TextView = itemView.findViewById(R.id.tv_discount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val product = products[position]
        Picasso.get().load(product.imageUrl).into(holder.ivProductImage)
        holder.tvProductTitle.text = product.title
        holder.tvProductPrice.text = product.price + " €"
        holder.tvProductDiscount.text = product.discount
        holder.tvProductOldPrice.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            text = product.oldPrice + " €"
        }
    }

    internal fun setProducts(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun getItemCount() = products.size
}