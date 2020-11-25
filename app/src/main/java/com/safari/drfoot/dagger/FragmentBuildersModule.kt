package com.safari.drfoot.dagger

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
    internal abstract fun contributeGame2Fragment(): GameFragment2

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

    @ContributesAndroidInjector
    internal abstract fun contributeSliderFragment1(): SliderFragment1

    @ContributesAndroidInjector
    internal abstract fun contributeSliderFragment2(): SliderFragment2

    @ContributesAndroidInjector
    internal abstract fun contributeSliderFragment3(): SliderFragment3

    @ContributesAndroidInjector
    internal abstract fun contributeSliderFragment4(): SliderFragment4

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment
}