package com.julio.latterstosaraswati.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.julio.latterstosaraswati.R
import com.julio.latterstosaraswati.dao.UserEntity
import com.julio.latterstosaraswati.repository.UserRepository
import com.julio.latterstosaraswati.viewModel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class CreateAnAccountFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_an_account, container, false)
    }

    //TODO: Replace it with MVVM patter (viewModelClass)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel: MainViewModel by viewModel{
            parametersOf(UserRepository(view.context))
        }

        val btnCreateUser : Button = view.findViewById(R.id.btn_create_user)

        btnCreateUser.setOnClickListener {
            val mockUser = UserEntity("Mock user", "mockuser@gmail.com", "mock123")
            mainViewModel.createNewUser(mockUser)
        }




    }



}