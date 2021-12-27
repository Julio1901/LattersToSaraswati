package com.julio.latterstosaraswati.fragments

import android.content.Intent
import android.net.Uri
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
        val btnAddNewGratitudeRegister = binding.btnAddNewGartitude
        val btnAddNewPhrase = binding.btnAddNewPhrase
        val btnSharePhrase = binding.btnSharePhrase

        mainViewModel.updateGratitudeRegistersToRecyclerView()

        lifecycleScope.launch {
            mainViewModel.myQueryResponse.collect {
                response -> recyclerViewHome.adapter = GratitudeRegisterAdapter(view.context, response)
            }
        }

        recyclerViewHome.setHasFixedSize(true)

        btnAddNewGratitudeRegister.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToAddNewGratitudeRegister()
            findNavController().navigate(action)
        }

        btnAddNewPhrase.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToAddNewPhrase()
            findNavController().navigate(action)
        }

        btnSharePhrase.setOnClickListener {
           // val stickerAssetUri = Uri.parse("src/main/res/drawable/ingyang.PNG")
            val stickerAssetUri = Uri.parse("android.resource://com.julio.latterstosaraswati/" + R.drawable.ingyang)
            val sourceApplication = "com.julio.latterstosaraswati"

            val intent = Intent("com.instagram.share.ADD_TO_STORY")
            intent.putExtra("source_application", sourceApplication)
            //intent.setType(MEDIA_TYPE_JPEG)
            intent.setType("JPEG")
            intent.putExtra("interactive_asset_uri", stickerAssetUri);
            intent.putExtra("top_background_color", "#33FF33");
            intent.putExtra("bottom_background_color", "#FF00FF");

            val activity = activity!!
            activity.grantUriPermission(
                "com.instagram.android", stickerAssetUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (activity.getPackageManager().resolveActivity(intent, 0) != null) {
                activity.startActivityForResult(intent, 0);
            }

        }


    }

}