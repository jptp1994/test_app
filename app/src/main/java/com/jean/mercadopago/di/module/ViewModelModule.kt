package com.jean.mercadopago.di.module

import androidx.lifecycle.ViewModel
import com.jean.mercadopago.di.annotation.ViewModelKey
import com.jean.mercadopago.screens.home.viewmodel.HomeViewModel
import com.jean.mercadopago.screens.splash.viewmodel.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
// instance of viewmodel
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}
