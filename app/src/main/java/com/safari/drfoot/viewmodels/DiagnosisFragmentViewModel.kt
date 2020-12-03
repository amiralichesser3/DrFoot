package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class DiagnosisFragmentViewModel @Inject constructor(private val answerRepository: AnswerRepository) : ViewModel()  {
    lateinit var diagnosisAnswers: LiveData<List<Answer>>

    fun init(userId: Int, sectionId: Int) {
        diagnosisAnswers = answerRepository.load(userId, "Diagnosis", sectionId)
    }
}