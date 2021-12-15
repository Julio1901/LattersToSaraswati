package com.julio.latterstosaraswati.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.julio.latterstosaraswati.R
import com.julio.latterstosaraswati.databinding.FragmentCreateAnAccountBinding
import com.julio.latterstosaraswati.databinding.FragmentLoginBinding
import com.julio.latterstosaraswati.repository.UserRepository
import com.julio.latterstosaraswati.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel: MainViewModel by viewModel{
            parametersOf(UserRepository(view.context))
        }

        binding.btnCreateAnAccount.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginToCreateAnAccount()
            findNavController().navigate(action)
        }

        binding.btnLogIn.setOnClickListener {
            val name = binding.editTextUserName.text.toString()
            val password = binding.editTextPassword.text.toString()

            mainViewModel.login(name, password)

            if(mainViewModel.login){
                val action = LoginFragmentDirections.actionLoginToHome()
                findNavController().navigate(action)
                //TODO: Make method here to reset the login variable in viewModel how to false
                //TODO: Replace login variable to a observer
            }
        }
    }


}