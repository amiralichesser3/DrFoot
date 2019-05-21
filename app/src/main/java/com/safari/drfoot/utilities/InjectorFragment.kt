package com.hafezie.barname.utility

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.safari.drfoot.dagger.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class InjectorFragment<T: ViewModel>: BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: T

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}