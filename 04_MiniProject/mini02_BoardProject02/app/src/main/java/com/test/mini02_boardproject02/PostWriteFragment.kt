package com.test.mini02_boardproject02

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.test.mini02_boardproject02.databinding.FragmentPostWriteBinding
import com.test.mini02_boardproject02.repository.PostRepository
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class PostWriteFragment : Fragment() {

    lateinit var fragmentPostWriteBinding: FragmentPostWriteBinding
    lateinit var mainActivity: MainActivity

    // 게시판 종류
    var boardType = 0

    // 업로드할 이미지의 Uri
    var uploadUri: Uri? = null

    lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    lateinit var albumLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentPostWriteBinding = FragmentPostWriteBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        // 카메라 설정
        cameraLauncher = cameraSetting(fragmentPostWriteBinding.imageViewPostWrite)
        albumLauncher = albumSetting(fragmentPostWriteBinding.imageViewPostWrite)

        fragmentPostWriteBinding.run{

            toolbarPostWrite.run{
                title = "글 작성"
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.POST_WRITE_FRAGMENT)
                }
                inflateMenu(R.menu.menu_post_write)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.item_post_write_album -> {
                            val newIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                            newIntent.setType("image/*")
                            val mimeType = arrayOf("image/*")
                            newIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
                            albumLauncher.launch(newIntent)

                        }
                        R.id.item_post_write_camera -> {
                            val newIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                            // 사진이 저장될 파일 이름
                            val fileName = "/temp_upload.jpg"
                            // 경로
                            val filePath = mainActivity.getExternalFilesDir(null).toString()
                            // 경로 + 파일이름
                            val picPath = "${filePath}/${fileName}"

                            // 사진이 저장될 경로를 관리할 Uri 객체를 만들어준다.
                            // 업로드 할 때 사용할 Uri이다.
                            val file = File(picPath)
                            uploadUri = FileProvider.getUriForFile(mainActivity, "com.test.mini02_boardproject02.file_provider", file)

                            newIntent.putExtra(MediaStore.EXTRA_OUTPUT, uploadUri)
                            cameraLauncher.launch(newIntent)

                        }
                        R.id.item_post_write_done -> {

                            // 입력한 내용을 가져온다.
                            val subject = textInputEditTextPostWriteSubject.text.toString()
                            val text = textInputEditTextPostWriteText.text.toString()

                            if(subject.isEmpty()){
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("제목 입력 오류")
                                builder.setMessage("제목을 입력해주세요")
                                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.showSoftInput(textInputEditTextPostWriteSubject)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener true
                            }

                            if(text.isEmpty()){
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("내용 입력 오류")
                                builder.setMessage("내용을 입력해주세요")
                                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.showSoftInput(textInputEditTextPostWriteText)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener true
                            }

                            if(boardType == 0){
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("게시판 종류 선택 오류")
                                builder.setMessage("게시판 종류를 선택해주세요.")
                                builder.setPositiveButton("확인", null)
                                builder.show()
                                return@setOnMenuItemClickListener true
                            }

                            PostRepository.getPostIdx {
                                var postIdx = it.result.value as Long
                                // 게시글 인덱스를 증가시킨다.
                                postIdx++

                                // 게시글을 저장한다.
                                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                val writeDate = sdf.format(Date(System.currentTimeMillis()))

                                val fileName = if(uploadUri == null){
                                    "None"
                                } else {
                                    "image/img_${System.currentTimeMillis()}.jpg"
                                }

                                val postDataClass = PostDataClass(postIdx, boardType.toLong(), subject, text, writeDate, fileName, mainActivity.loginUserClass.userIdx)

                                PostRepository.addPostInfo(postDataClass){
                                    PostRepository.setPostIdx(postIdx){

                                        // 글 번호를 번들에 담아준다.
                                        val newBundle = Bundle()
                                        newBundle.putLong("readPostIdx", postIdx)

                                        // 이미지 업로드
                                        if(uploadUri != null){
                                            PostRepository.uploadImage(uploadUri!!, fileName){
                                                Snackbar.make(fragmentPostWriteBinding.root, "저장되었습니다.", Snackbar.LENGTH_SHORT).show()
                                                mainActivity.replaceFragment(MainActivity.POST_READ_FRAGMENT, true, newBundle)
                                            }
                                        } else{
                                            Snackbar.make(fragmentPostWriteBinding.root, "저장되었습니다.", Snackbar.LENGTH_SHORT).show()
                                            mainActivity.replaceFragment(MainActivity.POST_READ_FRAGMENT, true, newBundle)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    true
                }
            }

            // 게시판 종류 버튼
            buttonPostWriteType.run{
                setOnClickListener {
                    val builder = MaterialAlertDialogBuilder(mainActivity)
                    builder.setTitle("게시판 종류")
                    builder.setItems(mainActivity.boardTypeList){ dialogInterface: DialogInterface, i: Int ->
                        boardType = i + 1
                        text = mainActivity.boardTypeList[i]
                    }
                    builder.setPositiveButton("취소", null)
                    builder.show()
                }
            }

        }

        return fragmentPostWriteBinding.root
    }

    // 카메라 관련 설정
    fun cameraSetting(previewImageView: ImageView) : ActivityResultLauncher<Intent>{
        // 사진 촬영을 위한 런처
        val cameraContract = ActivityResultContracts.StartActivityForResult()
        val cameraLauncher = registerForActivityResult(cameraContract){
            if(it.resultCode == AppCompatActivity.RESULT_OK){
                // Uri를 이용해 이미지에 접근하여 Bitmap 객체로 생성하낟.
                val bitmap = BitmapFactory.decodeFile(uploadUri?.path)

                // 이미지 크기 조정
                val ratio = 1024.0 / bitmap.width
                val targetHeight = (bitmap.height * ratio).toInt()
                val bitmap2 = Bitmap.createScaledBitmap(bitmap, 1024, targetHeight, false)

                // 회전 각도값을 가져온다.
                val degree = getDegree(uploadUri!!)

                // 회전 이미지를 생성한다.
                val matrix = Matrix()
                matrix.postRotate(degree.toFloat())
                val bitmap3 = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.width, bitmap2.height, matrix, false)
                previewImageView.setImageBitmap(bitmap3)
            }
        }

        return cameraLauncher
    }

    // 앨범 관련 설정
    fun albumSetting(previewImageView: ImageView) : ActivityResultLauncher<Intent>{
        val albumContract = ActivityResultContracts.StartActivityForResult()
        val albumLauncher = registerForActivityResult(albumContract){
            if(it.resultCode == AppCompatActivity.RESULT_OK){
                // 선택한 이미지에 접근할 수 있는 Uri 객체를 추출한다.
                if(it.data?.data != null){
                    uploadUri = it.data?.data

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                        // 이미지를 생성할 수 있는 디코더를 생성한다.
                        val source = ImageDecoder.createSource(mainActivity.contentResolver, uploadUri!!)
                        // Bitmap 객체를 생성한다.
                        val bitmap = ImageDecoder.decodeBitmap(source)

                        previewImageView.setImageBitmap(bitmap)
                    } else {
                        // 컨텐츠 프로바이더를 통해 이미지 데이터 정보를 가져온다.
                        val cursor = mainActivity.contentResolver.query(uploadUri!!, null, null, null, null)
                        if(cursor != null){
                            cursor.moveToFirst()

                            // 이미지의 경로를 가져온다.
                            val idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                            val source = cursor.getString(idx)

                            // 이미지를 생성하여 보여준다.
                            val bitmap = BitmapFactory.decodeFile(source)
                            previewImageView.setImageBitmap(bitmap)
                        }
                    }
                }
            }
        }

        return albumLauncher
    }



    // 이미지 파일에 기록되어 있는 회전 정보를 가져온다.
    fun getDegree(uri:Uri) : Int{

        var exifInterface: ExifInterface? = null

        // 사진 파일로 부터 tag 정보를 관리하는 객체를 추출한다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            val photoUri = MediaStore.setRequireOriginal(uri)
            // 스트림을 추출한다.
            val inputStream  = mainActivity.contentResolver.openInputStream(photoUri)
            // ExifInterface  정보를 읽어온다.
            exifInterface = ExifInterface(inputStream!!)
        } else {
            exifInterface = ExifInterface(uri.path!!)
        }

        if(exifInterface != null){
            // 각도 값을 가지고온다.
            var degree = 0
            val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)
            when(orientation){
                ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
                ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
                ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
            }
            return degree
        }
        return 0
    }
}