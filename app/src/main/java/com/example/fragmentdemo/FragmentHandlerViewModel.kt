package com.example.fragmentdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentHandlerViewModel: ViewModel() {
    private val _dataForSecondFragment = MutableLiveData<String>()
    var dataForSecondFragment = _dataForSecondFragment

    private val _dataForFirstFragment = MutableLiveData<String>()
    var dataForFirstFragment = _dataForFirstFragment

    fun setDataForFirstFragment(data: String) {
        _dataForFirstFragment.value = data
    }

    fun setDataForSecondFragment(data: String) {
        _dataForSecondFragment.value = data
    }
}