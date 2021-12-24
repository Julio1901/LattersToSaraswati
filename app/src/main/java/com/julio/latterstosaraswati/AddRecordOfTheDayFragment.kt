package com.julio.latterstosaraswati

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.julio.latterstosaraswati.dao.GratitudeOfTheDayEntity
import com.julio.latterstosaraswati.databinding.FragmentAddRecordOfTheDayBinding
import com.julio.latterstosaraswati.databinding.FragmentLoginBinding
import com.julio.latterstosaraswati.repository.UserRepository
import com.julio.latterstosaraswati.service.ImageDaoService
import com.julio.latterstosaraswati.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_add_record_of_the_day.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class AddRecordOfTheDayFragment : Fragment() {

    private var _binding: FragmentAddRecordOfTheDayBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddRecordOfTheDayBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel: MainViewModel by viewModel{
            parametersOf(UserRepository(view.context))
        }

        val buttonAddRecordOfTheDay : Button = view.findViewById(R.id.btn_add_record_of_the_day)
        val buttonTakeAnPicture : Button = view.findViewById(R.id.btn_take_an_picture)

        //Taking photo
        buttonTakeAnPicture.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 3)
        }

        buttonAddRecordOfTheDay.setOnClickListener {

            val imageDaoServiceInstance = ImageDaoService()

            val id = 0
            //TODO: Get it to system
            val user = "Julio"
            val day = "22/12/2021"
            val highlightedWord = binding.editTextHighlightedWord.text.toString()
            val recordOfTheDay = binding.editTextRecordOfTheDay.text.toString()
            val pictureImageView : ImageView = binding.imageViewPhotoCaptured
            val imageBitMap = pictureImageView.drawable.toBitmap()
            val imagemCodificadaParaDb = imageDaoServiceInstance.saveImageInBank(imageBitMap)
            val mockGratitudeTwo = GratitudeOfTheDayEntity(id, user,day,highlightedWord,recordOfTheDay,imagemCodificadaParaDb)
            mainViewModel.createNewGratitudeRecord(mockGratitudeTwo)


            val action = AddRecordOfTheDayFragmentDirections.actionAddNewGratitudeToHome()
            findNavController().navigate(action)


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 3 && resultCode == RESULT_OK){
            val extras = data?.extras
            val imageBitMap = extras?.get("data") as Bitmap
            image_view_photo_captured.setImageBitmap(imageBitMap)
        }
    }

}