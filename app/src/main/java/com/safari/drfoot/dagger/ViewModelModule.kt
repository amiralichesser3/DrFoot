package com.safari.drfoot.dagger

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.safari.drfoot.dagger.ViewModelFactory
import com.safari.drfoot.entities.Examination
import com.safari.drfoot.viewmodels.*
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
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    internal abstract fun bindGameViewModel(gameViewModel: GameViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(DemographicsViewModel::class)
    internal abstract fun bindDemographicsViewModel(demographicsViewModel: DemographicsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExaminationViewModel::class)
    internal abstract fun bindExaminationViewModel(examinationViewModel: ExaminationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    internal abstract fun bindHistoryViewModel(historyViewModel: HistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InvestigationViewModel::class)
    internal abstract fun bindInvestigationViewModel(investigationViewModel: InvestigationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel::class)
    internal abstract fun bindHomeFragmentViewModel(homeFragmentViewModel: HomeFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    internal abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LeafSectionActivityViewModel::class)
    internal abstract fun bindLeafViewModel(leafSectionActivityViewModel: LeafSectionActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PersonFragmentViewModel::class)
    internal abstract fun bindPersonFragmentViewModel(personFragmentViewModel: PersonFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LeafSectionFragmentViewModel::class)
    internal abstract fun bindLeafSectionFragmentViewModel(leafSectionFragmentViewModel: LeafSectionFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SubSectionFragmentViewModel::class)
    internal abstract fun bindSubSectionFragmentViewModel(subSectionFragmentViewModel: SubSectionFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DiagnosisFragmentViewModel::class)
    internal abstract fun bindDiagnosisFragmentViewModel(diagnosisFragmentViewModel: DiagnosisFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ManagementFragmentViewModel::class)
    internal abstract fun bindManagementFragmentViewModel(managementFragmentViewModel: ManagementFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FinishActivityViewModel::class)
    internal abstract fun bindFinishActivityViewModel(finishActivityViewModel: FinishActivityViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}