package com.julio.latterstosaraswati.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julio.latterstosaraswati.dao.UserEntity
import com.julio.latterstosaraswati.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    //Database functions

    fun createNewUser(newUser : UserEntity){

        viewModelScope.launch {
            try {
                userRepository.registerNewUser(newUser)
            }catch (e: Exception){
                Log.d("Error creating new user", e.toString())
            }
        }
    }




}