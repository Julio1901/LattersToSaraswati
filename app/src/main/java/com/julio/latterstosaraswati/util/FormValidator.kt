package com.julio.latterstosaraswati.util

import com.julio.latterstosaraswati.dao.UserEntity

class FormValidator {

/*Class designed to create methods for validating different application forms */

    //Returns the field name if it is empty, else, return null
    fun validateCreateAnAccountForm(newUser : UserEntity) : String?{

        val name = newUser.userName
        val email = newUser.email
        val passWord = newUser.password

        if (name.isNullOrEmpty() || name.isBlank()){
            return "User Name"
        }else if(email.isNullOrEmpty() || email.isBlank()){
            return "Email"
        }else if(passWord.isNullOrEmpty() || passWord.isBlank()){
            return "Password"
        }else {
            return null
        }
    }





}