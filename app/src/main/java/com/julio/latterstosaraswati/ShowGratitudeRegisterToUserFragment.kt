package com.julio.latterstosaraswati

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.julio.latterstosaraswati.databinding.FragmentAddRecordOfTheDayBinding
import com.julio.latterstosaraswati.databinding.FragmentShowGratitudeRegisterToUserBinding
import com.julio.latterstosaraswati.repository.UserRepository
import com.julio.latterstosaraswati.service.ImageDaoService
import com.julio.latterstosaraswati.viewModel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ShowGratitudeRegisterToUserFragment : Fragment() {

    private var _binding: FragmentShowGratitudeRegisterToUserBinding? = null
    private val binding get() = _binding!!

    private val args : ShowGratitudeRegisterToUserFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowGratitudeRegisterToUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel: MainViewModel by viewModel{
            parametersOf(UserRepository(view.context))
        }

        binding.textViewUserName.setText(args.user)
        binding.textViewData.setText(args.day)
        binding.textViewHighlightedWord.setText(args.highlightedWord)
        binding.textViewRecordOfTheDay.setText(args.recordOfTheDay)
        binding.imageViewPicture.setImageBitmap(args.picture)
    }
}