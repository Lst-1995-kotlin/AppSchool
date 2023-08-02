package com.test.mini02_boardproject01.VM

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.mini02_boardproject01.MainActivity
import com.test.mini02_boardproject01.repository.PostRepository

class ViewModelPost() : ViewModel() {

    var postData = MutableLiveData<PostDataClass>()

    init{
        postData = postData()
    }

    fun postGetOne(postIdx: Long){
        val p1 = PostRepository.postGetOne(postIdx)
        postData.value = p1
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