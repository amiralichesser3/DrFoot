package com.hafezie.barname.dagger

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.safari.drfoot.dagger.ViewModelFactory
import com.safari.drfoot.viewmodels.GameLevelActivityViewModel
import com.safari.drfoot.viewmodels.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GameLevelActivityViewModel::class)
    internal abstract fun bindGameLevelActivityViewModel(gameLevelActivityViewModel: GameLevelActivityViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}