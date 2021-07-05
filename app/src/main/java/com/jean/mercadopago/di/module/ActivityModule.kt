package com.jean.mercadopago.di.module

import com.jean.mercadopago.screens.home.view.HomeActivity
import com.jean.mercadopago.screens.splash.view.SplashActivity
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
// Have the activitys in the app inyect
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): HomeActivity
}
