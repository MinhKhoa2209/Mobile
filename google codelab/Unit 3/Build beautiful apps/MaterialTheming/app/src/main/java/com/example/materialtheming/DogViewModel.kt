package com.example.materialtheming
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class DogViewModel : ViewModel() {
    private val _dogList = MutableLiveData<List<Dog>>()
    val dogList: LiveData<List<Dog>> get() = _dogList

    init {
        _dogList.value = listOf(
            Dog("Golden Retriever", 3, R.drawable.golden_retriever),
            Dog("Husky", 2, R.drawable.husky),
            Dog("Bulldog", 4, R.drawable.bulldog)
        )
    }
}
