package com.julio.latterstosaraswati.service

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.julio.latterstosaraswati.R
import com.julio.latterstosaraswati.dao.PhraseBankEntity


//TODO: Replace PhraseBank Entity with a Diary Entity
class PhraseAdapter(private val context : Context, private val phraseList : List<PhraseBankEntity>) : RecyclerView.Adapter<PhraseAdapter.MyViewHolder>(){

    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        //Declare here all view holder atributes
        //TODO: Check if there is how to set binder
        val textViewData : TextView = view.findViewById(R.id.text_view_preview_data)
        val textViewTitle : TextView = view.findViewById(R.id.text_view_preview_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.fragment_cardview_gratitude_preview, parent, false)
        return MyViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = phraseList[position]

        holder.textViewData.text = item.creationDate
        //TODO: Replace with title from database
        holder.textViewTitle.text = "Gratitude"
    }

    override fun getItemCount() = phraseList.size

}