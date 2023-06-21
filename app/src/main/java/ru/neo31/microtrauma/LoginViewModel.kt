package ru.neo31.microtrauma

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.neo31.microtrauma.dataclasses.User

class LoginViewModel : ViewModel() {
    val userData = MutableLiveData<User>()
}