package com.example.coinviewer.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.coinviewer.db.dao.InterestCoinDAO
import com.example.coinviewer.db.dao.SelectedCoinPriceDAO
import com.example.coinviewer.db.entity.DataConverters
import com.example.coinviewer.db.entity.InterestCoinEntity
import com.example.coinviewer.db.entity.SelectedCoinPriceEntity

@Database(entities = [InterestCoinEntity::class, SelectedCoinPriceEntity::class], version = 2)
@TypeConverters(DataConverters::class)
abstract class CoinPriceDatabase: RoomDatabase() {

    abstract fun interestCoinDAO(): InterestCoinDAO
    abstract fun selectedCoinDAO(): SelectedCoinPriceDAO

    companion object {

        @Volatile
        private var INSTANCE: CoinPriceDatabase? = null

        fun getDatabase(
            context: Context
        ): CoinPriceDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinPriceDatabase::class.java,
                    "coin_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }

        }

    }

}