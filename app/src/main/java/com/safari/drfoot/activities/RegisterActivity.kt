package com.safari.drfoot.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.safari.drfoot.R
import com.safari.drfoot.utility.InjectorActivity
import com.safari.drfoot.utility.Navigator
import com.safari.drfoot.viewmodels.RegisterViewModel

class RegisterActivity : InjectorActivity<RegisterViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        viewModel.init();
        if (viewModel.exists()) {
            Navigator.withouthBundle().changeActivity(this@RegisterActivity, MainActivity::class.java, true)
            return;
        }
    }
}
