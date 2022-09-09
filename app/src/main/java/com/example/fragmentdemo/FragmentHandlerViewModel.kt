package com.example.fragmentdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentHandlerViewModel: ViewModel() {
    private val _sharedDataItem = MutableLiveData<String>()
    var shareDataItem = _sharedDataItem

    fun setSharedDataItem(shareDataItem: String) {
        _sharedDataItem.value = shareDataItem
    }

    fun getSharedDataItem(): MutableLiveData<String> {
        return shareDataItem
    }
}