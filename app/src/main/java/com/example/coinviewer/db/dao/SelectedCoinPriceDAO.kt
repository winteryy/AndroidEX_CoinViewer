package com.example.coinviewer.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coinviewer.db.entity.SelectedCoinPriceEntity


@Dao
interface SelectedCoinPriceDAO {

    //getAllData
    @Query("SELECT * FROM selected_coin_price_table")
    fun getAllData(): List<SelectedCoinPriceEntity>

    //insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(selectedCoinPriceEntity: SelectedCoinPriceEntity)

    //getTargetData
    @Query("SELECT * FROM selected_coin_price_table WHERE coinName = :coinName")
    fun getOneCoinData(coinName: String): List<SelectedCoinPriceEntity>

}