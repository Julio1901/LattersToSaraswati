package com.julio.latterstosaraswati.service

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.julio.latterstosaraswati.R
import com.julio.latterstosaraswati.dao.GratitudeOfTheDayEntity
import com.julio.latterstosaraswati.dao.PhraseBankEntity


//TODO: Replace PhraseBank Entity with a Diary Entity
class GratitudeRegisterAdapter(private val context : Context, private val phraseList : List<GratitudeOfTheDayEntity>) : RecyclerView.Adapter<GratitudeRegisterAdapter.MyViewHolder>(){

    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        //Declare here all view holder atributes
        //TODO: Check if there is how to set binder
        val textViewData : TextView = view.findViewById(R.id.text_view_preview_data)
        val textViewTitle : TextView = view.findViewById(R.id.text_view_preview_title)
        val imageViewRegister : ImageView = view.findViewById(R.id.image_view_previw_logo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.fragment_cardview_gratitude_preview, parent, false)
        return MyViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = phraseList[position]
        val imageDaoServiceInstance = ImageDaoService()

        holder.textViewData.text = item.day
        holder.textViewTitle.text = item.highlightedWord
        holder.imageViewRegister.setImageBitmap(imageDaoServiceInstance.convertBankImageToDisplay(item.picture))
    }

    override fun getItemCount() = phraseList.size

}