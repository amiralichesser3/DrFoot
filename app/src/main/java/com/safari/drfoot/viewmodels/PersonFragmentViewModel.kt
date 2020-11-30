package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.safari.drfoot.R
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class PersonFragmentViewModel @Inject constructor(private val personRepository: PersonRepository) : ViewModel()  {
    lateinit var people: LiveData<List<Person>>

    fun init() {
        people = personRepository.load()
    }
}