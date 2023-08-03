package com.test.mini02_boardproject01.repository

import android.net.Uri
import android.util.Log
import android.view.View
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.test.mini02_boardproject01.MainActivity
import com.test.mini02_boardproject01.MainActivity.Companion.mainActivity
import com.test.mini02_boardproject01.VM.PostDataClass
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostRepository {

    companion object{

        fun postAdd(postDataClass : PostDataClass, callBack1: (Task<Void>) -> Unit){

            val database = FirebaseDatabase.getInstance()
            val postDataRef = database.getReference("PostData")
            postDataRef.push().setValue(postDataClass).addOnCompleteListener {
                // 게시글 인덱스 번호
                val postIdxRef = database.getReference("PostIdx")
                postIdxRef.setValue(postDataClass.postIdx)
            }.addOnCompleteListener(callBack1)

        }

        fun postGetOne(callBack1: (Task<DataSnapshot>) -> Unit){
            val database = FirebaseDatabase.getInstance()
            val postRef = database.getReference("PostData")

            postRef.orderByChild("postIdx").equalTo(mainActivity.selPostIdx.toDouble()).get().addOnCompleteListener(callBack1)

        }

        fun postGetOneImg(){

        }

    }

}