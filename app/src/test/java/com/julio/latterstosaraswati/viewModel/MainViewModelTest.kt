package com.julio.latterstosaraswati.viewModel
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.julio.latterstosaraswati.dao.UserEntity
import com.julio.latterstosaraswati.repository.UserRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel

import org.koin.core.parameter.parametersOf
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*


//14 min


class MainViewModelTest {

    private val repository = mockk<UserRepository>()
    private val mainViewModel = MainViewModel(repository)

    @Test
    fun createNewUserTest() = runBlocking{
        val mockUser = UserEntity("mock", "mock", "mock")
        coEvery { repository.registerNewUser(mockUser) } returns 1L
        mainViewModel.createNewUser(mockUser)
        assertEquals(1L, repository.registerNewUser(mockUser))

    }

    @Test
    fun loginTest() = runBlocking{

        coEvery { repository.getUserInDb("mock") } returns UserEntity("mock", "mock", "mock")

//        `when`( mainViewModel.login("mock", "mock")
//           ).thenAnswer { mainViewModel.mutableLogin.value }


        fun alterLogin(){
            mainViewModel.mutableLogin.value = true

        }

//
//          Mockito.doubleThat(alterLogin()).`when`(mainViewModel.login("mock", "mock")
//
//
//        mainViewModel.login("mock", "mock")
//        assertEquals(true, mainViewModel.mutableLogin.value)
    }

}


