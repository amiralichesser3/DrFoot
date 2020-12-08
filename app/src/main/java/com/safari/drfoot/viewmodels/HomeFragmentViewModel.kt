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
                                                private val currentStateRepo: CurrentStateRepository) : ViewModel()  {
    lateinit var me: LiveData<Me>
    lateinit var rootSections: LiveData<List<Section>>
    lateinit var currentState: LiveData<CurrentState>

    fun init() {
        me = meRepo.load()
        rootSections = sectionRepo.loadRoots()
        currentState = currentStateRepo.load()
    }

    fun saveCurrentState(currentState: CurrentState) {
        currentStateRepo.save(currentState)
    }
}