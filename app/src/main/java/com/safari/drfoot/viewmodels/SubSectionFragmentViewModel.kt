package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.safari.drfoot.R
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class SubSectionFragmentViewModel @Inject constructor(private val subSectionRepository: SubSectionRepository) : ViewModel()  {
    lateinit var subsections: LiveData<List<SubSection>>

    fun init(parentId: Int) {
        subsections = subSectionRepository.load(parentId)
    }
}