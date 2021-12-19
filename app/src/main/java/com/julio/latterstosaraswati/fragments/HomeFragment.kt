package com.julio.latterstosaraswati.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.julio.latterstosaraswati.R
import com.julio.latterstosaraswati.dao.PhraseBankEntity
import com.julio.latterstosaraswati.service.PhraseAdapter


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewHome = view.findViewById<RecyclerView>(R.id.recycler_view_home)

        //TODO: Delete it and replace with db response
        //Test scenario

        val mockPhraseOne = PhraseBankEntity(0,"Julio","19/12/2021",
        "Entre o estimulo e a resposta existe um vazio. Nesse vazio reside nossa liberdade")
        val mockPhraseTwo = PhraseBankEntity(0,"Julio","19/12/2021",
            "A busca pela liberdade começa dentro de ")
        val mockList = mutableListOf(mockPhraseOne, mockPhraseTwo)

        recyclerViewHome.adapter = PhraseAdapter(view.context, mockList)
        recyclerViewHome.setHasFixedSize(true)

    }

}