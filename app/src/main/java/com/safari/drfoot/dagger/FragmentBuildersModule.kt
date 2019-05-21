package com.hafezie.barname.dagger

import com.safari.drfoot.fragments.DummyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    internal abstract fun contributeDummyFragment(): DummyFragment
}