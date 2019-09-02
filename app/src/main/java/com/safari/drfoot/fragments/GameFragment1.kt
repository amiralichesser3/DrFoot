package com.safari.drfoot.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.safari.drfoot.R
import kotlinx.android.synthetic.main.fragment_game_fragment2.*
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment1.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class GameFragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FragmentPagerItemAdapter(
            childFragmentManager, FragmentPagerItems.with(context)
                .add("Demographics", DemographicsFragment::class.java)
                .add("Presenting Complain", ComplainFragment::class.java)
                .add("History", HistoryFragment::class.java)
                .add("Examination", ExaminationFragment::class.java)
                .add("Investigation", InvestigationFragment::class.java)
                .add("Next Stage", NextStageFragment::class.java)
                .create()
        )

        viewpager.adapter = adapter
        viewpagertab.setViewPager(viewpager)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            GameFragment1().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}
