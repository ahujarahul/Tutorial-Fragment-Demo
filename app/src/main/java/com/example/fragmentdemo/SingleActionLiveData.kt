package com.example.fragmentdemo

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleActionLiveData<T>: MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) { data ->
            if (data != null) observer.onChanged(data)
            // We set the value to null straight after emitting the change to the observer.
            // This means that the state of the data will always be null / non existent.
            // It will only be available to the observer in its callback and since we do not emit null values.
            // The observer never receives a null value and any observers resuming do not receive the last event.
            // Therefore it only emits to the observer the single action.
            value = null
        }
    }

    @MainThread
    fun setValueData(data: T) {
        value = data
    }
}