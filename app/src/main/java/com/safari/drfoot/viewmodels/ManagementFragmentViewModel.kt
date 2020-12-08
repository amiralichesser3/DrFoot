package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class ManagementFragmentViewModel @Inject constructor(private val answerRepository: AnswerRepository,
                                                      private val currentStateRepository: CurrentStateRepository, private val cpsppRepository: CpsppRepository) : ViewModel()  {
    lateinit var diagnosisAnswers: LiveData<List<Answer>>
    lateinit var currentState: LiveData<CurrentState>
    lateinit var cpss: LiveData<CoinPerSectionPerPerson>

    fun init() {
        currentState = currentStateRepository.load()
        val syncCurrentState = currentStateRepository.loadSync()
        cpss = cpsppRepository.load(syncCurrentState.selectedPersonId, syncCurrentState.selectedSectionId)
        diagnosisAnswers = answerRepository.load(syncCurrentState.selectedPersonId, "Management", syncCurrentState.selectedSectionId)
    }

    fun saveCurrentState(currentState: CurrentState) {
        currentStateRepository.save(currentState)
    }

    fun saveCpss(cpss: CoinPerSectionPerPerson) {
        cpsppRepository.save(cpss)
    }
}