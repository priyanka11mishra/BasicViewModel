package com.example.myapplication.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.CharacterIterator.DONE


class GameViewModel :ViewModel()

{

    companion object{
        private const val done=0L
        private const val sec=1000L
        private const val counttimer=300000L
    }

    private val timer:CountDownTimer
     private val _word=MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _gamefinish=MutableLiveData<Boolean>()
    val gamefinish: LiveData<Boolean>
        get() = _gamefinish

    private val _score= MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    val CurrentTimeString=Transformations.map(currentTime) { time-> DateUtils.formatElapsedTime(time)}

    private lateinit var wordlist: MutableList<String>
    init {
        resetlist()
        nextword()
        _score.value=0
        _gamefinish.value=false

        timer=object :CountDownTimer(counttimer, sec){
            override fun onFinish() {
                _currentTime.value = done
                _gamefinish.value = true
            }

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / sec)
            }

        }
        timer.start()

    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    private fun resetlist() {
        wordlist = mutableListOf(
            "War",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "Kabir Singh",
            "calendar",
            "sad",
            "desk",
            "Bharat",
            "Mission Mangal",
            "railway",
            "zebra",
            "jelly",
            "Gully Boy",
            "crow",
            "Dabangg 3",
            "Housefull 4",
            "roll",
            "Total Dhamaal"
        )

        wordlist.shuffle()
    }

     fun onskip() {
        _score.value=(score.value)?.minus(1)
        nextword()
    }

     fun oncorrect() {
        _score.value=(score.value)?.plus(1)
        nextword()
    }

    private fun nextword() {
        if (wordlist.isEmpty()) {
            resetlist()
            //_gamefinish.value=true
        }
            _word.value = wordlist.removeAt(0)


    }

}