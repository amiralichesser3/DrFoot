package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class InvestigationViewModel @Inject constructor(private val investigationRepository: InvestigationRepository) : ViewModel()  {
    lateinit var investigation: LiveData<Investigation>

    fun init(personId: Int?) {
        investigation = investigationRepository.load(personId!!)
    }
}