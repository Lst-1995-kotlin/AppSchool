package com.test.mini02_boardproject01.repository

import android.net.Uri
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.test.mini02_boardproject01.MainActivity
import com.test.mini02_boardproject01.VM.PostDataClass
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostRepository {

    companion object{

        fun postAdd(postDataClass : PostDataClass){

            val database = FirebaseDatabase.getInstance()
            val postDataRef = database.getReference("PostData")
            postDataRef.push().setValue(postDataClass).addOnCompleteListener {
                // 게시글 인덱스 번호
                val postIdxRef = database.getReference("PostIdx")
                postIdxRef.push().setValue(postDataClass.postIdx)
            }

        }

        fun postGetOne(postFindIdx: Long): PostDataClass{
            val database = FirebaseDatabase.getInstance()
            val postRef = database.getReference("PostData")

            var selPost : PostDataClass? = null

            postRef.orderByChild("postIdx").equalTo(postFindIdx.toDouble()).get().addOnCompleteListener {
                for(p1 in it.result.children){
                    val postIdx = p1.child("postIdx").value as Long
                    val postType = p1.child("postType").value as Long
                    val postSubject = p1.child("postSubject").value as String
                    val postText = p1.child("postText").value as String
                    val postWriteDate = p1.child("postWriteDate").value as String
                    val postImage = p1.child("postImage").value as String
                    val postWriterUserId = p1.child("postWriterUserId").value as String

                    selPost = PostDataClass(postIdx, postType, postSubject, postText, postWriteDate, postImage, postWriterUserId)

                    Log.d("aaaaa", "${selPost}")
                }
            }

            return selPost!!
        }

    }

}