package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val meRepository: MeRepository) : ViewModel()  {
    lateinit var me: LiveData<Me>

    fun init() {
        me = meRepository.load();
    }

    fun exists(): Boolean {
        return meRepository.exists();
    }
}