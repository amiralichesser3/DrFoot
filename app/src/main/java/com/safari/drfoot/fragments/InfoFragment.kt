package com.safari.drfoot.fragments

import android.app.Activity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.safari.drfoot.R
import com.safari.drfoot.activities.LeafSectionActivity
import com.safari.drfoot.adapters.SectionAdapter
import com.safari.drfoot.adapters.SubSectionAdapter
import com.safari.drfoot.entities.SubSection
import com.safari.drfoot.utilities.contracts.MyCallback
import com.safari.drfoot.utility.InjectorFragment
import com.safari.drfoot.utility.SharedPreferencesHelper
import com.safari.drfoot.utility.Toaster
import com.safari.drfoot.viewmodels.LeafSectionActivityViewModel
import com.safari.drfoot.viewmodels.LeafSectionFragmentViewModel
import com.safari.drfoot.viewmodels.SubSectionFragmentViewModel
import kotlinx.android.synthetic.main.fragment_person.*
import kotlinx.android.synthetic.main.recycler_gamelevels.*

class InfoFragment : Fragment() {

    lateinit var subsection: SubSection

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            subsection = it.getParcelable("subsection")!!
        }

        textView.setText(subsection.text)
    }

    companion object {
        @JvmStatic
        fun newInstance(subsection: SubSection) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("subsection", subsection)
                }
            }
    }

}
