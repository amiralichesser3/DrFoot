package com.safari.drfoot.fragments


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.safari.drfoot.utility.InjectorFragment
import com.safari.drfoot.R
import kotlinx.android.synthetic.main.fragment_game_fragment2.*
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.safari.drfoot.viewmodels.GameViewModel

private const val PERSON_ID = "pid"
class GameFragment1 : InjectorFragment<GameViewModel>() {
    private var personId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            personId = it.getInt(PERSON_ID)
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
        viewModel.init(personId)
        val bundle = Bundle()
        bundle.putInt("pid", personId!!)
        val adapter = FragmentPagerItemAdapter(
            childFragmentManager, FragmentPagerItems.with(context)
                .add("Demographics", DemographicsFragment::class.java, bundle)
                .add("Presenting Complain", ComplainFragment::class.java, bundle)
                .add("History", HistoryFragment::class.java, bundle)
                .add("Examination", ExaminationFragment::class.java, bundle)
                .add("Investigation", InvestigationFragment::class.java, bundle)
                .add("Next Stage", NextStageFragment::class.java, bundle)
                .create()
        )

        viewpager.adapter = adapter
        viewpagertab.setViewPager(viewpager)
    }

    companion object {
        @JvmStatic
        fun newInstance(personId: Int) =
            GameFragment1().apply {
                arguments = Bundle().apply {
                    putInt(PERSON_ID, personId)
                }
            }
    }
}
