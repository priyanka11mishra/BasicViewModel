package com.example.myapplication.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.myapplication.R

import com.example.myapplication.databinding.FragmentScoreBinding


/**
 * A simple [Fragment] subclass.
 */
class ScoreFragment : Fragment() {

    private lateinit var scoremodel:ScoreViewModel
    private lateinit var scorefactory:ScoreViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentScoreBinding =
            DataBindingUtil.inflate(inflater,R.layout.fragment_score,container,false)

        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
        scorefactory= ScoreViewModelFactory(scoreFragmentArgs.score)
        scoremodel=ViewModelProviders.of(this,scorefactory).get(ScoreViewModel::class.java)

        binding.scoreViewModel=scoremodel
        binding.setLifecycleOwner(this)


        /*scoremodel.score.observe(this, Observer { newScore ->
            binding.scoretext.text = newScore.toString()
        })*/



        scoremodel.eventPlayAgain.observe(this, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections.actionScoreFragment2ToGameFragment2())
                scoremodel.onPlayAgainComplete()
            }
        })

        return binding.root

    }



}
