package com.example.coinviewer.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.coinviewer.db.entity.InterestCoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InterestCoinDAO {

    //getAllData
    @Query("SELECT * FROM interest_coin_table")
    fun getAllData(): Flow<List<InterestCoinEntity>>

    //insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(interestCoinEntity: InterestCoinEntity)

    //update
    @Update
    fun update(interestCoinEntity: InterestCoinEntity)

    //getSelectedCoinList
    @Query("SELECT * FROM interest_coin_table WHERE selected = :selected")
    fun getSelectedData(selected: Boolean = true): List<InterestCoinEntity>
}
