package com.example.coinviewer.repository

import com.example.coinviewer.network.Api
import com.example.coinviewer.network.RetrofitInstance

class NetworkRepository {

    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getCurrentCoinList() = client.getCurrentCoinList()

}