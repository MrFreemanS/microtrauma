package ru.neo31.microtrauma

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val token = MutableLiveData<String>()

}