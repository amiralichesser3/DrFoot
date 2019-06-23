package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.safari.drfoot.entities.Person
import com.safari.drfoot.entities.PersonGameLevel
import com.safari.drfoot.repositories.PersonGameLevelRepository
import com.safari.drfoot.repositories.PersonRepository
import javax.inject.Inject

class GameLevelActivityViewModel @Inject constructor(private val personRepo: PersonRepository, private val personGameLevelRepo: PersonGameLevelRepository) : ViewModel()  {
    lateinit var personGameLevels: LiveData<List<PersonGameLevel>>

    fun init(gameLevelId: Int) {
        personGameLevels = personGameLevelRepo.load(gameLevelId)
    }

    fun loadPeople(ids: List<Int>): LiveData<List<Person>> {
        return personRepo.load(ids)
    }
}