package com.julio.latterstosaraswati.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julio.latterstosaraswati.dao.GratitudeOfTheDayEntity
import com.julio.latterstosaraswati.dao.UserEntity
import com.julio.latterstosaraswati.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    val mutableLogin : MutableLiveData<Boolean> = MutableLiveData()
    val mutableGratitudeOfTheDay : MutableLiveData<GratitudeOfTheDayEntity> = MutableLiveData()
    var userName : String = ""


    var gratitudeListFlow = mutableListOf<GratitudeOfTheDayEntity>()

    lateinit var  myQueryResponse : Flow<List<GratitudeOfTheDayEntity>>

    init {
        viewModelScope.launch {
            myQueryResponse = userRepository.getAllGratitudeRegisters()
        }
    }


    //Database functions

    //User table

    fun createNewUser(newUser : UserEntity){

        viewModelScope.launch {
            try {
                 userRepository.registerNewUser(newUser)
            }catch (e: Exception){
                Log.d("Error creating new user", e.toString())
            }
        }
    }

    fun login(userName: String, password: String) {

        viewModelScope.launch {
            try {
                val response = userRepository.getUserInDb(userName)
                mutableLogin.value = response.password == password
            }catch (e: Exception){
                Log.d("Error getting user", e.toString())
                mutableLogin.value = false
            }
        }
    }

    //Gratitude table

    fun createNewGratitudeRecord(newGratitudeRecord : GratitudeOfTheDayEntity){

        viewModelScope.launch {
            try {
                userRepository.registerNewGratitudeOfTheDay(newGratitudeRecord)
            }catch (e: Exception){
                Log.d("Error gratitude record", e.toString())
            }

        }

    }

    fun getGratitudeById( id: Int){

        viewModelScope.launch {
            try {
                mutableGratitudeOfTheDay.value = userRepository.getGratitudeOfTheDayById(id)
            } catch (e: Exception){
                Log.d("Error get gratitude", e.toString())
            }
        }
    }

    fun updateGratitudeRegistersToRecyclerView(){

        viewModelScope.launch {
            myQueryResponse = userRepository.getAllGratitudeRegisters()

            myQueryResponse.collect {
                it.forEach {
                    gratitudeListFlow.add(it)
                }
            }
        }
    }

}
