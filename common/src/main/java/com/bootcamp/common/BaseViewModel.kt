package com.bootcamp.common

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavDirections
import com.bootcamp.ext.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val navigationtEvent = SingleLiveEvent<NavDirections>()
    val popBackStackEvent = SingleLiveEvent<Any>()
    var parent: BaseViewModel? = null

    fun navigate(nav: NavDirections) {
        navigationtEvent.postValue(nav)
    }

    fun getString(id: Int) =
        getApplication<Application>().getString(id)

    fun popBackStack() {
        popBackStackEvent.postValue(Any())
    }

}