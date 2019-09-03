package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.safari.drfoot.entities.Demographic
import com.safari.drfoot.entities.History
import com.safari.drfoot.entities.Person
import com.safari.drfoot.entities.PersonGameLevel
import com.safari.drfoot.repositories.DemographicRepository
import com.safari.drfoot.repositories.HistoryRepository
import com.safari.drfoot.repositories.PersonGameLevelRepository
import com.safari.drfoot.repositories.PersonRepository
import javax.inject.Inject

class HistoryViewModel @Inject constructor(private val historyRepository: HistoryRepository) : ViewModel()  {
    lateinit var history: LiveData<History>

    fun init(personId: Int?) {
        history = historyRepository.load(personId!!)
    }
}