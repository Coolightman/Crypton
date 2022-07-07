package com.coolightman.crypton.di

import android.content.Context
import com.coolightman.crypton.data.database.CryptoDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @ApplicationScope
    @Provides
    fun provideCryptoDatabase(context: Context) = CryptoDatabase.getDb(context)

    @ApplicationScope
    @Provides
    fun provideCoinInfoDao(db: CryptoDatabase) = db.coinInfoDao()
}