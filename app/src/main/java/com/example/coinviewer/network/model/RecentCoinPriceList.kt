package com.example.coinviewer.network.model

import com.example.coinviewer.dataModel.RecentPriceData

data class RecentCoinPriceList (

    val status: String,
    val data: List<RecentPriceData>

)