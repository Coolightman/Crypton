package com.coolightman.crypton

import android.app.Application
import com.coolightman.crypton.di.DaggerApplicationComponent

class CryptoApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}