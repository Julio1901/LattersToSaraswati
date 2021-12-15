package com.julio.latterstosaraswati.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.julio.latterstosaraswati.R
import com.julio.latterstosaraswati.dao.UserEntity
import com.julio.latterstosaraswati.databinding.FragmentCreateAnAccountBinding
import com.julio.latterstosaraswati.repository.UserRepository
import com.julio.latterstosaraswati.util.FormValidator
import com.julio.latterstosaraswati.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class CreateAnAccountFragment : Fragment() {

    private var _binding: FragmentCreateAnAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_create_an_account, container, false)

        _binding = FragmentCreateAnAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel: MainViewModel by viewModel{
            parametersOf(UserRepository(view.context))
        }
        val btnCreateUser : Button = binding.btnCreateUser

        btnCreateUser.setOnClickListener {
            val userName = binding.editTextCreateUserName.text.toString()
            val email = binding.editTextCreateEmail.text.toString()
            val password = binding.editTextCreatePassword.text.toString()
            val newUser = UserEntity(userName,email,password)

            val formValidator = FormValidator()
            val validateReturn = formValidator.validateCreateAnAccountForm(newUser)

            if (validateReturn == null){
                mainViewModel.createNewUser(newUser)
            }else{
                val text = "$validateReturn fild is blank"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
                //Change hint color to get user attention
                //TODO: Change this to respond with just one click
                when(validateReturn){
                    "User Name" -> {
                        binding.editTextCreateUserName.setHintTextColor(resources.getColor(R.color.red_alert))
                        //Restore default color to user correct field
                        binding.editTextCreateUserName.setOnClickListener{
                            binding.editTextCreateUserName.setHintTextColor(resources.getColor(R.color.hint_default_color))
                        }
                    }
                    "Email" -> {
                        binding.editTextCreateEmail.setHintTextColor(resources.getColor(R.color.red_alert))
                        binding.editTextCreateEmail.setOnClickListener{
                            binding.editTextCreateEmail.setHintTextColor(resources.getColor(R.color.hint_default_color))
                            }
                        }
                    "Password" -> {
                        binding.editTextCreatePassword.setHintTextColor(resources.getColor(R.color.red_alert))
                        binding.editTextCreatePassword.setOnClickListener{
                            binding.editTextCreatePassword.setHintTextColor(resources.getColor(R.color.hint_default_color))
                        }
                    }
                }
            }
        }
    }


}