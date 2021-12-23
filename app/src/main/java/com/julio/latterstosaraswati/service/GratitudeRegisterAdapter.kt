package com.julio.latterstosaraswati.service

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.julio.latterstosaraswati.R
import com.julio.latterstosaraswati.dao.GratitudeOfTheDayEntity
import com.julio.latterstosaraswati.dao.PhraseBankEntity
import com.julio.latterstosaraswati.fragments.HomeFragmentDirections


//TODO: Replace PhraseBank Entity with a Diary Entity
class GratitudeRegisterAdapter(private val context : Context, private val phraseList : List<GratitudeOfTheDayEntity>) : RecyclerView.Adapter<GratitudeRegisterAdapter.MyViewHolder>(){

    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        //Declare here all view holder atributes
        //TODO: Check if there is how to set binder
        val textViewData : TextView = view.findViewById(R.id.text_view_preview_data)
        val textViewTitle : TextView = view.findViewById(R.id.text_view_preview_title)
        val imageViewRegister : ImageView = view.findViewById(R.id.image_view_previw_logo)
        val cardViewContainer : CardView = view.findViewById(R.id.card_view_preview_gratitude)
        val myView = view
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
        holder.cardViewContainer.setOnClickListener ({

            fun onClick(v : View){

                val id = item.id
                val user = item.user
                val day = item.day
                val highlightedWord = item.highlightedWord
                val recordOfTheDay = item.recordOfTheDay
                val picture = imageDaoServiceInstance.convertBankImageToDisplay(item.picture)

                val action = HomeFragmentDirections.actionHomeToDisplayAnRegister(
                    id,
                    user,
                    day,
                    highlightedWord,
                    recordOfTheDay,
                    picture
                )
                v.findNavController().navigate(action)
            }
            onClick(holder.myView)
            }
        )




    }

    override fun getItemCount() = phraseList.size

}