package com.test.android38_permissionstudy

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android38_permissionstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 승인 요청을 받을 권한 목록
    // 만약 이미 승인이 되어있거나, 승인이 필요 없는 권한은 requestPermission 에서 넘어간다.
    val permissionList = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.run{
                setOnClickListener {
                    requestPermissions(permissionList,0)
                }
            }
        }
    }

    // requestPermissions 을 통하여 권한을 요청하면 요청 작업이 끝난면 호출되는 메서드
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        activityMainBinding.run{
            textView2.text = ""

            // permissions 권한 목록
            // grantResults 권한 승인 요청에 대한 결과.

            for(idx in 0 .. permissions.size-1){
                textView2.append("${idx+1}번 권한 : ${permissions[idx]} , \n 승인 결과 : ${grantResults[idx]}\n")
            }


        }

    }

}