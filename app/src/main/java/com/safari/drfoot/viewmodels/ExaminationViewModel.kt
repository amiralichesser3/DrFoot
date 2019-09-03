package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class ExaminationViewModel @Inject constructor(private val examinationRepository: ExaminationRepository) : ViewModel()  {
    lateinit var examination: LiveData<Examination>

    fun init(personId: Int?) {
        examination = examinationRepository.load(personId!!)
    }
}