package com.hafezie.barname.dagger

import com.safari.drfoot.fragments.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    internal abstract fun contributeDummyFragment(): DummyFragment

    @ContributesAndroidInjector
    internal abstract fun contributeGameFragment(): GameFragment1

    @ContributesAndroidInjector
    internal abstract fun contributeDemographicsFragment(): DemographicsFragment

    @ContributesAndroidInjector
    internal abstract fun contributeComplainFragment(): ComplainFragment

    @ContributesAndroidInjector
    internal abstract fun contributeExaminationFragment(): ExaminationFragment

    @ContributesAndroidInjector
    internal abstract fun contributeInvestigationFragment(): InvestigationFragment

    @ContributesAndroidInjector
    internal abstract fun contributeHistoryFragment(): HistoryFragment
}