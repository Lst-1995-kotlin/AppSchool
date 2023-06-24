package com.test.android51_messagenotificationstudy

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.graphics.drawable.IconCompat
import androidx.core.os.bundleOf
import com.test.android51_messagenotificationstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding

    val NOTIFICATION_CHANNEL1_ID = "첫번째 ID"
    val NOTIFICATION_CHANNEL1_NAME = "첫번째 채널 NAME"

    // 안드로이드 13버전 부터는 Notification 사용을 하기 위해서는 권한을 사용자에게 확인 받아야 한다.
    val permissionList = arrayOf(
        Manifest.permission.POST_NOTIFICATIONS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        requestPermissions(permissionList,0)

        addNotificationChannel(NOTIFICATION_CHANNEL1_ID, NOTIFICATION_CHANNEL1_NAME)


        activityMainBinding.run{
            button.setOnClickListener {
                val bulider = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                bulider.setContentTitle("ContentTitle를 표시")
                bulider.setContentText("ContentText를 표시")
                bulider.setSmallIcon(android.R.drawable.ic_lock_idle_low_battery)

                val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
                bulider.setLargeIcon(bitmap)

                bulider.setNumber(555)

                val notification = bulider.build()
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                // 첫번째 매개변수 : 단말기 전체에서 메시지를 구분하기 위한 값
                // 같은 값으로 메시지를 계속 보내주면 메시지가 갱신 되고, 다른 값일 경우 메세시지 각각 따로 나타난다.
                notificationManager.notify(10, notification)
            }

            button2.setOnClickListener {
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setContentTitle("Big Text 설정 타이틀")
                builder.setContentText("Big Text 설정 텍스트")
                builder.setSmallIcon(android.R.drawable.ic_menu_search)

                //Big Text 설정
                val big = NotificationCompat.BigTextStyle(builder)
                // 알림을 펼쳤을 때
                big.setSummaryText("하기 내용의 종류")
                big.setBigContentTitle("펼쳤을 때 보이는 타이틀")
                big.bigText("""
                    가나다라바마사
                    아자차카타파하
                    안드로이드 스튜디오 공부 어렵다...
                    힘내자~!
                """.trimIndent())

                val notification = builder.build()


                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(20, notification)

            }

            button3.setOnClickListener {
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setContentTitle("InBox 타이틀")
                builder.setContentText("InBox 콘텐츠 텍스트")
                builder.setSmallIcon(android.R.drawable.btn_plus)

                val inBox = NotificationCompat.InboxStyle(builder)
                inBox.setSummaryText("인박스 SummaryText")
                inBox.setBigContentTitle("인박스 BigContentTitle")

                repeat(5){
                    inBox.addLine("addLine을 이용한 라인 추가 $it")
                }

                val notification = builder.build()
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(30, notification)

            }

            button4.setOnClickListener {
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setSmallIcon(android.R.drawable.btn_plus)
                builder.setContentTitle("BigPicture 콘텐츠 타이틀")
                builder.setContentText("BigPicture 콘텐트 텍스트")

                val big = NotificationCompat.BigPictureStyle(builder)
                big.setBigContentTitle("big ContentTitle 지정")
                big.setSummaryText("big SummaryText 지정")

                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.appschool)
                big.bigPicture(bitmap)

                val notification = builder.build()
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

                notificationManager.notify(40,notification)
            }

            button5.setOnClickListener {
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setContentTitle("메시지 콘텐츠 타이틀")
                builder.setContentText("메시지 콘텐츠 텍스트")
                builder.setSmallIcon(android.R.drawable.arrow_up_float)

                // 사람1
                val personBuilder1 = Person.Builder()
                val icon1 = IconCompat.createWithResource(this@MainActivity, R.drawable.appschool)
                personBuilder1.setIcon(icon1)
                personBuilder1.setName("앱스쿨")
                val person1 = personBuilder1.build()

                // 사람2
                val personBuilder2 = Person.Builder()
                val icon2 = IconCompat.createWithResource(this@MainActivity, R.mipmap.ic_launcher)
                personBuilder2.setIcon(icon2)
                personBuilder2.setName("멋쟁이사자")
                val person2 = personBuilder2.build()

                val msg = NotificationCompat.MessagingStyle(person1)

                // 대화 내용을 설정한다.
                msg.addMessage("앱 스쿨에서 공부하기 어때?", System.currentTimeMillis(), person1)
                msg.addMessage("좋아요", System.currentTimeMillis(), person2)
                msg.addMessage("힘들지는 않아?", System.currentTimeMillis(), person1)
                msg.addMessage("모든 사람이 열심히 해서 좋아요", System.currentTimeMillis(), person2)

                builder.setStyle(msg)

                val notification = builder.build()
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

                notificationManager.notify(50, notification)

            }


        }
    }


    // id: 코드에서 채널을 관리하기 위한 이름
    // name : 사용자에게 보여질 이름.
    fun addNotificationChannel(id: String, name: String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 객체 생성
            val notiMan = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            // 등록된 채널이 없다면 null을 반환한다.
            val channel = notiMan.getNotificationChannel(id)
            if(channel == null){
                // 3번째 매개변수 메세지의 중요도 설정.
                val newChannel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
                // 단말기에 LED 램프 있을 경우 사용
                newChannel.enableLights(true)
                newChannel.lightColor = Color.RED
                // 진동여부 설정
                newChannel.enableVibration(false)

                // 채널 등록
                notiMan.createNotificationChannel(newChannel)
            }
        }
    }

    fun getNotificationBuilder(id: String): NotificationCompat.Builder{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val builder = NotificationCompat.Builder(this, id)
            return builder
        } else {
            val builder = NotificationCompat.Builder(this)
            return  builder
        }
    }



}





















