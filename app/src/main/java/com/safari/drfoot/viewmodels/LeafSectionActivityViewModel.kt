package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.safari.drfoot.R
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class LeafSectionActivityViewModel @Inject constructor(private val meRepo: MeRepository,
                                                       private val personRepo: PersonRepository,
                                                       private val currentStateRepository: CurrentStateRepository, private val cpssRepository: CpsppRepository) : ViewModel()  {
    lateinit var me: LiveData<Me>
    lateinit var currentState: LiveData<CurrentState>
    lateinit var cpss: LiveData<CoinPerSectionPerPerson>

    fun init() {
        me = meRepo.load()
        currentState = currentStateRepository.load()
        val currentStateSync = currentStateRepository.loadSync()
        cpss = cpssRepository.load(currentStateSync.selectedPersonId, currentStateSync.selectedSectionId)

    }

    fun seedCpss(currentState: CurrentState) {
        val cpssSync = loadCpssSync(currentState.selectedPersonId, currentState.selectedSectionId)
        if (cpssSync == null) {
            saveCpss(CoinPerSectionPerPerson(0, currentState.selectedPersonId, currentState.selectedSectionId, 0))
        }
    }

    fun loadPatientSync(patientId: Int): Person? {
        return personRepo.loadSync(patientId)
    }

    fun loadCpssSync(personId: Int, sectionId: Int): CoinPerSectionPerPerson? {
        return cpssRepository.loadSync(personId, sectionId)
    }

    fun saveCurrentState(currentState: CurrentState) {
        currentStateRepository.save(currentState)
    }

    fun saveCpss(cpss: CoinPerSectionPerPerson) {
        cpssRepository.save(cpss)
    }
}