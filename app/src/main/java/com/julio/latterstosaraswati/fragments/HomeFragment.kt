package com.julio.latterstosaraswati.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.julio.latterstosaraswati.R
import com.julio.latterstosaraswati.dao.GratitudeOfTheDayEntity
import com.julio.latterstosaraswati.dao.PhraseBankEntity
import com.julio.latterstosaraswati.databinding.FragmentHomeBinding
import com.julio.latterstosaraswati.databinding.FragmentLoginBinding
import com.julio.latterstosaraswati.repository.UserRepository
import com.julio.latterstosaraswati.service.GratitudeRegisterAdapter
import com.julio.latterstosaraswati.viewModel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel: MainViewModel by viewModel{
            parametersOf(UserRepository(view.context))
        }

        val recyclerViewHome = view.findViewById<RecyclerView>(R.id.recycler_view_home)

        mainViewModel.updateGratitudeRegistersToRecyclerView()

        lifecycleScope.launch {
            mainViewModel.myQueryResponse.collect {
                response -> recyclerViewHome.adapter = GratitudeRegisterAdapter(view.context, response)
            }
        }

        recyclerViewHome.setHasFixedSize(true)

        val btnAddNewGratitudeRegister = binding.btnAddNewGartitude

        btnAddNewGratitudeRegister.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToAddNewGratitudeRegister()
            findNavController().navigate(action)
        }
    }


}