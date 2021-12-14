package com.julio.latterstosaraswati.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.julio.latterstosaraswati.R


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    //TODO: Replace it with MVVM patter (viewModelClass)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCreateAnAccount : Button = view.findViewById(R.id.btn_create_an_account)

        btnCreateAnAccount.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginToCreateAnAccount()
            findNavController().navigate(action)
        }

    }


}