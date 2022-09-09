package com.example.fragmentdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentHandlerViewModel: ViewModel() {
    private val _sharedDataItem = MutableLiveData<String>()
    val shareDataItem = _sharedDataItem

    private val _firstFragmentNextButtonClicked = SingleActionLiveData<ButtonClicked>()
    val firstFragmentNextButtonClicked = _firstFragmentNextButtonClicked

    private val _secondFragmentPreviousButtonClicked = SingleActionLiveData<ButtonClicked>()
    val secondFragmentPreviousButtonClicked = _secondFragmentPreviousButtonClicked

    fun setSharedDataItem(shareDataItem: String) {
        _sharedDataItem.value = shareDataItem
    }

    fun onNextButtonClicked() {
        _firstFragmentNextButtonClicked.setValueData(ButtonClicked(true))
    }

    fun onPreviousButtonClicked() {
        _secondFragmentPreviousButtonClicked.setValueData(ButtonClicked(true))
    }
}

data class ButtonClicked(val isClicked: Boolean)