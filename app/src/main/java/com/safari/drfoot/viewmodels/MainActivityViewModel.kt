package com.safari.drfoot.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.safari.drfoot.R
import com.safari.drfoot.entities.GameLevel
import com.safari.drfoot.entities.Person
import com.safari.drfoot.entities.PersonGameLevel
import com.safari.drfoot.repositories.GameLevelRepository
import com.safari.drfoot.repositories.PersonGameLevelRepository
import com.safari.drfoot.repositories.PersonRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val gameLevelRepo: GameLevelRepository, private val personRepo: PersonRepository,
                                                private val personGameLevelRepo: PersonGameLevelRepository) : ViewModel()  {
    lateinit var gameLevels: LiveData<List<GameLevel>>

    fun init() {
        seedDatabase()
        gameLevels = gameLevelRepo.load()
    }

    private fun seedDatabase() {
        AsyncTask.execute {
            if (!gameLevelRepo.exists()) {
                val gameLevels = arrayListOf(GameLevel(1, "Prevention", false, "https://www.uottawa.ca/health/sites/www.uottawa.ca.health/files/icons/Icon---Chiropody---V2.png"),
                    GameLevel(2, "Examination", true, null),
                    GameLevel(3, "Detection", true, null),
                    GameLevel(4, "Healing", true, null),
                    GameLevel(5, "Amputated", true, null))
                gameLevelRepo.save(gameLevels)
            }

            if (!personRepo.exists()) {
                val people = arrayListOf(Person(1, "Muhammad", null, R.drawable.oldman,
                    "Demographic Info 1", "History Info 1", "Main Problem 1",
                    "Initial Examination Result 1", "Lab Results 1", false),
                    Person(2, "Zahra", null, R.drawable.oldwoman,
                        "Demographic Info 2", "History Info 2", "Main Problem 2",
                        "Initial Examination Result 2", "Lab Results 2", true),
                    Person(3, "Ali", null, R.drawable.youngman,
                        "Demographic Info 3", "History Info 3", "Main Problem 3",
                        "Initial Examination Result 3", "Lab Results 3", true),
                    Person(4, "Maryam", null, R.drawable.youngwoman,
                        "Demographic Info 4", "History Info 4", "Main Problem 4",
                        "Initial Examination Result 4", "Lab Results 4", true),
                    Person(5, "Sam", null, R.drawable.kid,
                        "Demographic Info 5", "History Info 5", "Main Problem 5",
                        "Initial Examination Result 5", "Lab Results 5", true))
                personRepo.save(people)
            }

            if (!personGameLevelRepo.exists()) {
                val personGameLevels = arrayListOf(PersonGameLevel(1, 1),
                    PersonGameLevel(2, 1), PersonGameLevel(3, 1),
                    PersonGameLevel(4, 1), PersonGameLevel(5, 1))
                personGameLevelRepo.save(personGameLevels)
            }
        }
    }
}