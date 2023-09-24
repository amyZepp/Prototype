package com.example.prototype.data

import com.example.prototype.R
import com.example.prototype.model.Product

class Datasource() {
    companion object {

        fun loadProducts(): List<Product> {
            return listOf<Product>(
                Product(R.string.lamp, R.drawable.lamp),
                Product(R.string.dishes, R.drawable.dishes),
                Product(R.string.bag, R.drawable.bag)
            )
        }
    }
}