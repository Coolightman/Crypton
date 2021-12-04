package com.coolightman.crypton.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.coolightman.crypton.data.database.dao.CoinInfoDao
import com.coolightman.crypton.data.database.model.CoinInfoDbModel


@Database(
    version = 8,
    entities = [CoinInfoDbModel::class]
)
abstract class CryptoDatabase : RoomDatabase() {

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

    abstract fun coinInfoDao(): CoinInfoDao
}