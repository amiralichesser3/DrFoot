package com.safari.drfoot.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.safari.drfoot.utility.InjectorFragment
import com.safari.drfoot.R
import com.safari.drfoot.activities.ScenarioActivity
import com.safari.drfoot.viewmodels.GameLevelActivityViewModel
import com.safari.drfoot.viewmodels.GameViewModel
import kotlinx.android.synthetic.main.fragment_game_fragment3.*

private const val PERSON_ID = "pid"
class GameFragment2 : InjectorFragment<GameLevelActivityViewModel>(), View.OnClickListener {
    override fun onClick(view: View?) {
        if (view!!.id == R.id.revascularizationButton || view.id == R.id.dressingButton) {
            handleSuccess(view)
        } else if(view.id == R.id.finishButton) {
            handleEnd();
        } else  {
            handleLoss(view)
        }
    }

    private fun handleEnd() {
        (activity as ScenarioActivity).handleEnd()
    }

    private fun handleSuccess(view: View) {
        view.isEnabled = false
        YoYo.with(Techniques.Tada).playOn(view)
        (activity as ScenarioActivity).handleSuccess()
    }

    private fun handleLoss(view: View) {
        YoYo.with(Techniques.Flash).playOn(view)
        (activity as ScenarioActivity).handleLoss()
    }

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
        return inflater.inflate(R.layout.fragment_game_fragment3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GameLevelActivityViewModel::class.java)
        var people = arrayListOf<Int>()
        people.add(personId!!)
        viewModel.loadPeople(people).observe(this, Observer {
            Glide.with(context!!).load(it!![0].imageLocal).into(imageView)
        })

        debridementButton.setOnClickListener(this)
        patientEducationButton.setOnClickListener(this)
        dressingButton.setOnClickListener(this)
        antibioticsButton.setOnClickListener(this)
        revascularizationButton.setOnClickListener(this)
        finishButton.setOnClickListener(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(personId: Int) =
            GameFragment2().apply {
                arguments = Bundle().apply {
                    putInt(PERSON_ID, personId)
                }
            }
    }
}
