package com.example.myapplication.model
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//https://developer.android.com/codelabs/basic-android-kotlin-training-shared-viewmodel#3

class MainEntryViewModel:ViewModel() {
    private val _ip_address = MutableLiveData<String>("")
    private val _velocity = MutableLiveData<String>( "")
    private val _time = MutableLiveData<String>( "")


    fun setIP(ip_address: String){
        _ip_address.value = ip_address
    }

    fun getIP(): MutableLiveData<String> = _ip_address

    fun setVelocity(vel:String){
        _velocity.value = vel
    }

    fun getVel(): MutableLiveData<String> = _velocity

    fun setTime(time: String){
        _time.value = time
    }

    fun getTime(): MutableLiveData<String> = _time

}