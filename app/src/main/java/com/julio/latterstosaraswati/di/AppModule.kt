package com.julio.latterstosaraswati.di

import com.julio.latterstosaraswati.repository.UserRepository
import com.julio.latterstosaraswati.viewModel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { (userRepository : UserRepository) -> MainViewModel(userRepository) }
}