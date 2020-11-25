package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.safari.drfoot.R
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(private val meRepo: MeRepository,
                                                private val sectionRepo: SectionRepository,
                                                private val subSectionRepo: SubSectionRepository) : ViewModel()  {
    lateinit var me: LiveData<Me>
    lateinit var rootSections: LiveData<List<Section>>

    fun init() {
        me = meRepo.load()
        rootSections = sectionRepo.loadRoots()
    }
}