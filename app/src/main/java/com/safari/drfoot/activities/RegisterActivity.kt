package com.safari.drfoot.activities

import android.os.Bundle
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.safari.drfoot.R
import com.safari.drfoot.fragments.SliderFragment1
import com.safari.drfoot.fragments.SliderFragment2
import com.safari.drfoot.fragments.SliderFragment3
import com.safari.drfoot.utility.InjectorActivity
import com.safari.drfoot.utility.Navigator
import com.safari.drfoot.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : InjectorActivity<RegisterViewModel>() {

    private var adapter: FragmentPagerItemAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        viewModel.init();
        if (viewModel.exists()) {
            Navigator.withouthBundle().changeActivity(this@RegisterActivity, MainActivity::class.java, true)
            return;
        }

        adapter = FragmentPagerItemAdapter(
            supportFragmentManager, FragmentPagerItems.with(this)
                .add("", SliderFragment1::class.java)
                .add("", SliderFragment2::class.java)
                .add("", SliderFragment3::class.java)
                .create()
        )
        viewpager.adapter = adapter
        viewpagertab.setViewPager(viewpager)
    }
}
