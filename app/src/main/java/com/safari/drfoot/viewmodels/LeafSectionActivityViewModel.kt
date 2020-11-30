package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.safari.drfoot.R
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class LeafSectionActivityViewModel @Inject constructor(private val meRepo: MeRepository, private val personRepo: PersonRepository) : ViewModel()  {
    lateinit var me: LiveData<Me>
    lateinit var patient: LiveData<Person>

    fun init(patientId: Int) {
        me = meRepo.load()
        patient = personRepo.load(patientId)
    }
}