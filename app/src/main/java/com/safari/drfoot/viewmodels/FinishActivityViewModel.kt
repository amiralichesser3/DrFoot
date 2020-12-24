package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.safari.drfoot.R
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class FinishActivityViewModel @Inject constructor(private val cpsppRepository: CpsppRepository,
                                                  private val currentStateRepository: CurrentStateRepository,
                                                  private val personRepository: PersonRepository) : ViewModel()  {
    lateinit var cpss: CoinPerSectionPerPerson
    lateinit var currentState: CurrentState
    lateinit var person: LiveData<Person>
    fun init() {
        currentState = currentStateRepository.loadSync()
       cpss = cpsppRepository.loadSync(currentState.selectedPersonId, currentState.selectedPersonId)!!
        person = personRepository.load(currentState.selectedPersonId)
    }
}