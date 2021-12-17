package com.julio.latterstosaraswati.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
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
            view.context.hideKeyboard(view)

        }

        mainViewModel.mutableLogin.observe(this, Observer {
            if (it){
                val action = LoginFragmentDirections.actionLoginToHome()
                findNavController().navigate(action)
                //TODO: Create an function to restore default value
                //Restore default
                mainViewModel.mutableLogin.value = false
                binding.editTextUserName.setText("")
                binding.editTextPassword.setText("")
                binding.textViewLoginError.setTextColor(resources.getColor(R.color.transparent))
            } else{
                binding.textViewLoginError.setTextColor(resources.getColor(R.color.red_alert))
            }
        })
        binding.editTextUserName.setOnClickListener{
          binding.textViewLoginError.setTextColor(resources.getColor(R.color.transparent))
        }
        binding.editTextPassword.setOnClickListener{
            binding.textViewLoginError.setTextColor(resources.getColor(R.color.transparent))
        }

        //Hide keyboard on screen click
        view.setOnClickListener {
            view.context.hideKeyboard(view)
        }

    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //makes the error message transparent if the user goes back to the login screen
    override fun onResume() {
        super.onResume()
        binding.textViewLoginError.setTextColor(resources.getColor(R.color.transparent))
    }


}