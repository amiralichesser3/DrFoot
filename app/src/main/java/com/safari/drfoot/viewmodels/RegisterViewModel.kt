package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val meRepository: MeRepository,
                                            private val personRepository: PersonRepository,
                                            private val sectionRepository: SectionRepository,
                                            private val subSectionRepository: SubSectionRepository) : ViewModel()  {
    lateinit var me: LiveData<Me>

    fun init() {
        me = meRepository.load()
        if (!exists()) {
            save(Me(1, null, null, null, null, false))
        }
    }

    fun exists(): Boolean {
        return meRepository.exists();
    }

    fun save(me: Me?) {
        if (me == null) return
        AsyncTask.execute {
            meRepository.save(me)
        }
    }

    fun seedDatabase() {

    }
}