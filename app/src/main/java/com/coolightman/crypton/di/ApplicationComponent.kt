package com.coolightman.crypton.di

import android.content.Context
import com.coolightman.crypton.presentation.fragment.CoinDetailFragment
import com.coolightman.crypton.presentation.fragment.CoinsFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ViewModelModule::class,
        RepositoryBindModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)
@ApplicationScope
interface ApplicationComponent {

    fun inject(coinsFragment: CoinsFragment)
    fun inject(coinDetailFragment: CoinDetailFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}