package com.example.unscrambleapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val wordList = listOf("banana", "orange", "grapes", "apple", "mango", "peach", "strawberry", "watermelon","melon", "chilli")
    private var currentIndex = 0
    private var score = 0

    val userInput = MutableLiveData<String>("")

    private val _scrambledWord = MutableLiveData<String>()
    val scrambledWord: LiveData<String> get() = _scrambledWord

    private val _wordCount = MutableLiveData(1)
    val wordCount: LiveData<Int> get() = _wordCount

    private val _score = MutableLiveData(0)
    val scoreLiveData: LiveData<Int> get() = _score

    private val _gameFinished = MutableLiveData<Boolean>()
    val gameFinished: LiveData<Boolean> get() = _gameFinished

    private lateinit var currentWord: String

    init {
        loadNextWord()
    }

    private fun loadNextWord() {
        if (currentIndex >= wordList.size) {
            _gameFinished.value = true
            return
        }

        currentWord = wordList[currentIndex]
        _scrambledWord.value = currentWord.toList().shuffled().joinToString("")
        _wordCount.value = currentIndex + 1
        currentIndex++
    }

    fun onSubmit() {
        if (userInput.value.equals(currentWord, ignoreCase = true)) {
            score += 10
            _score.value = score
            loadNextWord()
        }
        userInput.value = ""
    }

    fun onSkip() {
        loadNextWord()
        userInput.value = ""
    }
}
