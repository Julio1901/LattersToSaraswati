package com.julio.latterstosaraswati.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.julio.latterstosaraswati.R
import com.julio.latterstosaraswati.dao.PhraseBankEntity
import com.julio.latterstosaraswati.databinding.FragmentAddNewPhraseBinding
import com.julio.latterstosaraswati.databinding.FragmentAddRecordOfTheDayBinding
import com.julio.latterstosaraswati.repository.UserRepository
import com.julio.latterstosaraswati.viewModel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class AddNewPhraseFragment : Fragment() {

    private var _binding: FragmentAddNewPhraseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewPhraseBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel: MainViewModel by viewModel{
            parametersOf(UserRepository(view.context))
        }


        val btnSavePhrase = binding.btnRegisterNewPhrase

        btnSavePhrase.setOnClickListener {
            //This field is autogenerate in db
            val id = 0
            //TODO: Replace it to get the user name by login
            val writerUser = "Julio"
            //TODO: Replace it to get the current date by system
            val creationDate = "24/12/2021"
            val phrase = binding.editTextPhrase.text.toString()
            val phraseOrigin = binding.editTextPhraseOrigin.text.toString()

            val newPhrase = PhraseBankEntity(id, writerUser,creationDate, phrase, phraseOrigin)
            mainViewModel.registerNewPhraseInBank(newPhrase)
            //TODO: Make a dialog confirming the addition of the phrase before switching screens
            val action = AddNewPhraseFragmentDirections.actionNewPhraseToHome()
            findNavController().navigate(action)

        }
    }

}