package com.coolightman.crypton.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coolightman.crypton.model.data.CoinInfo
import com.coolightman.crypton.model.data.CoinPriceInfo
import com.coolightman.crypton.model.db.dao.CoinPriceInfoDao


@Database(
    version = 4,
    entities = [CoinPriceInfo::class]
)
@TypeConverters(Converters::class)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun coinPriceInfoDao(): CoinPriceInfoDao

    companion object {
        private var db: CryptoDatabase? = null
        private const val DB_NAME = "Crypton.db"
        private val LOCK = Any()

        fun getDb(context: Context): CryptoDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CryptoDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
                db = instance
                return instance
            }
        }
    }
}