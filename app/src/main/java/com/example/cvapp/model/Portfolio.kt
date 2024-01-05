package com.example.cvapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Portfolio(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
