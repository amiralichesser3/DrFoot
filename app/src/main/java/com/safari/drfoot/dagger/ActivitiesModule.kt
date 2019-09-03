package com.safari.drfoot.dagger

import com.safari.drfoot.activities.GameLevelActivity
import com.safari.drfoot.activities.MainActivity
import com.safari.drfoot.activities.ScenarioActivity
import com.safari.drfoot.activities.Splash
import com.safari.drfoot.dagger.FragmentBuildersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun contributeSplash(): Splash

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun contributeGameLevelActivity(): GameLevelActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun contributeScenarioActivity(): ScenarioActivity
}