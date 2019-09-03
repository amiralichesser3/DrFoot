package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.safari.drfoot.entities.Demographic
import com.safari.drfoot.entities.Person
import com.safari.drfoot.entities.PersonGameLevel
import com.safari.drfoot.repositories.DemographicRepository
import com.safari.drfoot.repositories.PersonGameLevelRepository
import com.safari.drfoot.repositories.PersonRepository
import javax.inject.Inject

class DemographicsViewModel @Inject constructor(private val demographicRepository: DemographicRepository) : ViewModel()  {
    lateinit var demographic: LiveData<Demographic>

    fun init(personId: Int?) {
        demographic = demographicRepository.load(personId!!)
    }
}