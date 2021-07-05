package com.jean.mercadopago.screens.common

import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

//Have general configurations for the app activities
open class BaseActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var baseViewModel: BaseViewModel? = null

    fun setBaseViewModel(baseViewModel: BaseViewModel) {
        this.baseViewModel = baseViewModel
    }

    fun setNavigation(baseViewModel: BaseViewModel) {
        this.baseViewModel = baseViewModel
    }



}