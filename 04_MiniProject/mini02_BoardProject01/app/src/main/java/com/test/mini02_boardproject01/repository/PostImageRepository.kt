package com.test.mini02_boardproject01.repository

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage

class PostImageRepository {

    companion object{
        fun postImageAdd(uploadUri: Uri, fileName: String){
            val storage = FirebaseStorage.getInstance()
            val imgRef = storage.reference.child(fileName)
            imgRef.putFile(uploadUri)
        }
    }

}