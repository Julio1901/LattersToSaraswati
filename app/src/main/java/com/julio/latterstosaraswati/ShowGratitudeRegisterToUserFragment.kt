package com.julio.latterstosaraswati

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
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
        //TODO: Replace it to get the database id
        mainViewModel.getGratitudeById(2)
        val imageDaoServiceInstance = ImageDaoService()

        mainViewModel.mutableGratitudeOfTheDay.observe(this, Observer {

            binding.textViewUserName.setText(it.user)
            binding.textViewData.setText(it.day)
            binding.textViewHighlightedWord.setText(it.highlightedWord)
            binding.textViewRecordOfTheDay.setText(it.recordOfTheDay)
            val imageConverted = imageDaoServiceInstance.convertBankImageToDisplay(it.picture)
            binding.imageViewPicture.setImageBitmap(imageConverted)
        })
    }
}