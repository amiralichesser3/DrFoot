package com.safari.drfoot.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.safari.drfoot.R
import com.safari.drfoot.activities.LeafSectionActivity
import com.safari.drfoot.entities.SubSection
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

        if (!subsection.image.isNullOrEmpty()) {
            Glide.with(context!!).load(subsection.image).into(imageView)
        }

        subsection.hint?.let {
            (activity as LeafSectionActivity).setHint(it)
        }

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
