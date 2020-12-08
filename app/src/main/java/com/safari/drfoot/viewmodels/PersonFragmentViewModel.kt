package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.safari.drfoot.R
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class PersonFragmentViewModel @Inject constructor(private val personRepository: PersonRepository,
                                                  private val currentStateRepository: CurrentStateRepository,
                                                  private val cpsppRepository: CpsppRepository) : ViewModel()  {
    lateinit var people: LiveData<List<Person>>
    lateinit var currentState: LiveData<CurrentState>
    lateinit var cpssList: List<CoinPerSectionPerPerson>

    fun init() {
        people = personRepository.load()
        currentState = currentStateRepository.load()
        val personIds = personRepository.loadIds()
        cpssList = cpsppRepository.loadList(personIds)
    }

    fun saveCurrentState(currentState: CurrentState) {
        currentStateRepository.save(currentState)
    }
}