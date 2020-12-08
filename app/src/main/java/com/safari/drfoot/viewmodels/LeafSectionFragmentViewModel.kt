package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.safari.drfoot.R
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class LeafSectionFragmentViewModel @Inject constructor(private val sectionRepo: SectionRepository, private val currentStateRepository: CurrentStateRepository) : ViewModel()  {
    lateinit var leafSections: LiveData<List<Section>> 

    fun init(parentId: Int) {
        leafSections = sectionRepo.loadLeaves(parentId)
    }

}