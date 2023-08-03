package com.test.mini02_boardproject01.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class PostImageRepository {

    companion object{
        fun postImageAdd(uploadUri: Uri, fileName: String, callback1: (Task<UploadTask.TaskSnapshot>) -> Unit){
            val storage = FirebaseStorage.getInstance()
            val imgRef = storage.reference.child(fileName)
            imgRef.putFile(uploadUri).addOnCompleteListener(callback1)
        }

        fun postImageGet(fileName: String, callback1: (Task<Uri>) -> Unit){
            val storage = FirebaseStorage.getInstance()
            val fileRef = storage.reference.child(fileName)

            fileRef.downloadUrl.addOnCompleteListener(callback1)

        }

    }

}