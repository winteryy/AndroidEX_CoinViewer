package com.example.coinviewer.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinviewer.dataModel.CurrentPrice
import com.example.coinviewer.dataModel.CurrentPriceResult
import com.example.coinviewer.dataStore.MyDataStore
import com.example.coinviewer.db.entity.InterestCoinEntity
import com.example.coinviewer.repository.DBRepository
import com.example.coinviewer.repository.NetworkRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class SelectViewModel: ViewModel() {

    private val networkRepository = NetworkRepository()
    private val dbRepository = DBRepository()

    private lateinit var currentPriceResultList: ArrayList<CurrentPriceResult>

    private val _currentPriceResult = MutableLiveData<List<CurrentPriceResult>>()
    val currentPriceResult: LiveData<List<CurrentPriceResult>>
        get() = _currentPriceResult

    private val _saved = MutableLiveData<String>()
    val saved: LiveData<String>
        get() = _saved

    fun getCurrentCoinList() = viewModelScope.launch {

        val result = networkRepository.getCurrentCoinList()

        currentPriceResultList = ArrayList()

        for(coin in result.data){

            try{
                val gson = Gson()
                val gsonToJson = gson.toJson(result.data[coin.key])
                val gsonFromJson = gson.fromJson(gsonToJson, CurrentPrice::class.java)

                val currentPriceResult = CurrentPriceResult(coin.key, gsonFromJson)

                currentPriceResultList.add(currentPriceResult)

            }catch(e: Exception){
                Timber.d(e.toString())
            }

        }

        _currentPriceResult.value = currentPriceResultList

    }

    fun setUpFirstFlag() = viewModelScope.launch {
        MyDataStore().setupFirstData()
    }

    fun saveSelectedCoinList(selectedCoinList: ArrayList<String>) = viewModelScope.launch(Dispatchers.IO) {

        for(coin in currentPriceResultList){
            Timber.d(coin.toString())

            val selected = selectedCoinList.contains(coin.coinName)

            val interestCoinEntity = InterestCoinEntity(
                0,
                coin.coinName,
                coin.coinInfo.opening_price,
                coin.coinInfo.closing_price,
                coin.coinInfo.min_price,
                coin.coinInfo.max_price,
                coin.coinInfo.units_traded,
                coin.coinInfo.acc_trade_value,
                coin.coinInfo.prev_closing_price,
                coin.coinInfo.units_traded,
                coin.coinInfo.acc_trade_value,
                coin.coinInfo.fluctate_24H,
                coin.coinInfo.fluctate_rate_24H,
                selected
            )

            interestCoinEntity.let {
                dbRepository.insertInterestCoinData(it)
            }
        }

        withContext(Dispatchers.Main){
            _saved.value = "done"
        }


    }


}