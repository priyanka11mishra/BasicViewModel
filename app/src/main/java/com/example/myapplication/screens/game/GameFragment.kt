package com.example.myapplication.screens.game

import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_game,container,false)


        viewModel=ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding.gameViewModel=viewModel
        binding.setLifecycleOwner(this)



       /* viewModel.score.observe(this, Observer { newScore->
            binding.scoreText.text = newScore.toString()
        })*/
        /*viewModel.word.observe(this, Observer { newWord->
            binding.wordText.text=newWord

        })*/

        /*viewModel.currentTime.observe(this, Observer { newTime ->
            binding.timerText.text = DateUtils.formatElapsedTime(newTime)

        })*/

        viewModel.gamefinish.observe(this, Observer{hasfinished ->
            if (hasfinished)
            {
                val currentScore = viewModel.score.value ?: 0
                val action = GameFragmentDirections.actionGameFragment2ToScoreFragment2(currentScore)
                findNavController().navigate(action)
                viewModel.gamefinish

            }


        })

        return binding.root
        //return inflater.inflate(R.layout.fragment_game, container, false)
    }






}
