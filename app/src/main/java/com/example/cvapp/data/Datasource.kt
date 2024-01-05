package com.example.cvapp.data

import com.example.cvapp.model.Portfolio
import com.example.cvapp.R

class Datasource() {
    fun loadPortfolio(): List<Portfolio> {
        return listOf<Portfolio>(
            Portfolio(R.string.portfolio1, R.drawable.dog),
            Portfolio(R.string.portfolio2, R.drawable.dog2),
            Portfolio(R.string.portfolio3, R.drawable.logo3))
        }
}