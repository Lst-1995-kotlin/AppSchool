package com.test.mini02_boardproject01.VM

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.mini02_boardproject01.MainActivity
import com.test.mini02_boardproject01.MainActivity.Companion.mainActivity
import com.test.mini02_boardproject01.repository.PostImageRepository.Companion.postImageGet
import com.test.mini02_boardproject01.repository.PostRepository
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class ViewModelPost() : ViewModel() {

    var postData = MutableLiveData<PostDataClass>()
    var postImageData = MutableLiveData<Bitmap>()

    fun postGetOne(){
        PostRepository.postGetOne{
            for(p1 in it.result.children){
                val postIdx = p1.child("postIdx").value as Long
                val postType = p1.child("postType").value as Long
                val postSubject = p1.child("postSubject").value as String
                val postText = p1.child("postText").value as String
                val postWriteDate = p1.child("postWriteDate").value as String
                val postImage = p1.child("postImage").value as String
                val postWriterUserId = p1.child("postWriterUserId").value as String

                postData.value = PostDataClass(postIdx, postType, postSubject, postText, postWriteDate, postImage, postWriterUserId)

                if(postImage != "None"){
                    postImageGet(postImage){

                        thread{
                            // 파일에 접근할 수 있는 경로를 이용해 URL 객체를 생성한다.
                            val url = URL(it.result.toString())
                            // 접속한다.
                            val httpURLConnection = url.openConnection() as HttpURLConnection
                            // 이미지 객체를 생성한다.
                            postImageData.value = BitmapFactory.decodeStream(httpURLConnection.inputStream)
                        }

                    }
                }
            }
        }
    }



}

// 게시글 정보를 담을 클래스
data class PostDataClass(var postIdx:Long,              // 게시글 인덱스 번호
                         var postType:Long,             // 게시판 종류
                         var postSubject:String,        // 제목
                         var postText:String,           // 내용
                         var postWriteDate:String,      // 작성일
                         var postImage:String,          // 첨부이미지 파일 이름
                         var postWriterUserId:String)        // 작성자 인덱스 번호