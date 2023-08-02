package com.test.mini02_boardproject01

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.test.mini02_boardproject01.databinding.FragmentPostWriteBinding
import java.io.File


class PostWriteFragment : Fragment() {

    lateinit var fragmentPostWriteBinding: FragmentPostWriteBinding
    lateinit var mainActivity: MainActivity
    lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    lateinit var filePath: String
    lateinit var contentUri: Uri

    val cameraContract = ActivityResultContracts.StartActivityForResult()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentPostWriteBinding = FragmentPostWriteBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        // 경로를 가져온다.
        filePath = mainActivity.getExternalFilesDir(null).toString()

        cameraLauncher = registerForActivityResult(cameraContract){

            if(it?.resultCode == RESULT_OK){
                // Uri를 이용해 이미지에 접근하여 Bitmap 객체로 생성한다.
                val bitmap = BitmapFactory.decodeFile(contentUri.path)

                // 이미지의 크기를 조정한다.
                // 이미지의 축소/ 확대 비율을 구한다.
                val ratio = 1024.0 / bitmap.width
                // 세로 길이를 구한다.
                val targetHeight = (bitmap.height * ratio).toInt()

                // 크기를 조정한 Bitmap을 생성한다.
                val bitmap2 = Bitmap.createScaledBitmap(bitmap, 1024, targetHeight, false)

                // 회전 각도를 가져온다
                val degree = getDegree(contentUri)

                // 회전 이미지를 생성하기 위한 변환 행렬
                val matrix = Matrix()
                matrix.postRotate(degree.toFloat())

                // 회전 행렬을 적용하여 회전된 이미지를 생성한다.
                // 원본 이미지, 원본이미지에서의 X좌표, 원본 이미지에서의 Y좌표, 원본 이미지에서의 가로길이.
                // 원본 이미지에서의 세로길이, 변환행렬, 필터정보
                // 원본 이미지에서 지정된 x,y 좌표를 찍고 지정된 가로 세로 길이만큼의 이미지 데이터를 가져와
                // 변환 행렬을 적용하여 이미지를 변환한다.
                val bitmap3 = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.width, bitmap2.height, matrix, false)

                fragmentPostWriteBinding.imageViewPostWrite.setImageBitmap(bitmap3)

            }
        }

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

                        }
                        R.id.item_post_write_camera -> {
                            val newIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            // 촬영될 사진이 저장될 파일 이름
                            val now = System.currentTimeMillis()
                            val userName = mainActivity.nowLoginUser.userId
                            val fileNmae = "/${userName}_${now}.jpg"
                            val picPath = "${filePath}/${fileNmae}"

                            val file = File(picPath)
                            contentUri = FileProvider.getUriForFile(mainActivity, "com.miniProject.file_provider", file)

                            newIntent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
                            cameraLauncher.launch(newIntent)

                        }
                        R.id.item_post_write_done -> {
                            if(!check())return@setOnMenuItemClickListener true
                            mainActivity.replaceFragment(MainActivity.POST_READ_FRAGMENT, true, null)
                        }
                    }
                    true
                }
            }
        }

        return fragmentPostWriteBinding.root
    }




    fun check(): Boolean{
        if(!mainActivity.inputCheck(fragmentPostWriteBinding.textInputEditTextPostWriteSubject,"제목")) return false
        if(!mainActivity.inputCheck(fragmentPostWriteBinding.textInputEditTextPostWriteText, "내용")) return false
        return true
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