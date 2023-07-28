package com.test.android94_workmanagerstudy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.test.android94_workmanagerstudy.MainActivity.Companion.PORT_NUMBER
import com.test.android94_workmanagerstudy.MainActivity.Companion.SERVER_IP
import com.test.android94_workmanagerstudy.databinding.ActivityMainBinding
import kotlinx.coroutines.coroutineScope
import java.io.DataOutputStream
import java.lang.Thread.sleep
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    companion object{
        val PORT_NUMBER = 44444
        val SERVER_IP = "172.30.1.85"
    }

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.setOnClickListener {
                // 일회성 작업 예약
                val myWorkRequest = OneTimeWorkRequest.from(MyWork::class.java)
                WorkManager
                    .getInstance(this@MainActivity)
                    .enqueue(myWorkRequest)
            }

            button2.setOnClickListener {
                // 신속 처리 작업 예약
                val request = OneTimeWorkRequestBuilder<MyWork>()
                    .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                    .build()

                WorkManager.getInstance(this@MainActivity)
                    .enqueue(request)
            }
            button3.setOnClickListener {
                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.UNMETERED)
                    .setRequiresCharging(true)
                    .build()

                val myWorkRequest: WorkRequest =
                    OneTimeWorkRequestBuilder<MyWork>()
                        .setConstraints(constraints)
                        .build()

                WorkManager.getInstance(this@MainActivity)
                    .enqueue(myWorkRequest)

            }

            button4.setOnClickListener {

                val sdf = SimpleDateFormat("HH:mm:ss");
                var now = sdf.format(System.currentTimeMillis())
                textView.text = "버튼 누른 시간 : $now"

                val myWorkRequest = OneTimeWorkRequestBuilder<MyWork>()
                    .setInitialDelay(10, TimeUnit.SECONDS)
                    .build()

                WorkManager.getInstance(this@MainActivity)
                    .enqueue(myWorkRequest)
            }

            button5.setOnClickListener {
                val sdf = SimpleDateFormat("HH:mm:ss");
                var now = sdf.format(System.currentTimeMillis())
                textView.text = "버튼 누른 시간 : $now"

                // 실패하면 지정한 시간에 따라 다시 시도하며, 실패할 경우 점점 재시도를 시작하는 시간은 점점 늘어난다.
                val myWorkRequest = OneTimeWorkRequestBuilder<MyWork2>()
                    .setBackoffCriteria(
                        BackoffPolicy.LINEAR,
                        10000,
                        TimeUnit.MILLISECONDS)
                    .build()


                WorkManager.getInstance(this@MainActivity)
                    .enqueue(myWorkRequest)
            }
        }
    }

}

class MyWork2(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    // doWork 는 WorkManager에서 제공하는 백그라운드 스레드에서 비동기적으로 실행된다.
    override fun doWork(): Result {

        thread {
            val socket = Socket(SERVER_IP, PORT_NUMBER)

            val outputStream = socket.getOutputStream()
            val dataOutputStream = DataOutputStream(outputStream)
            val sdf = SimpleDateFormat("HH:mm:ss");
            var now = ""
            sleep(5000)

            for(i in 1 .. 10){
                sleep(500)
                now = sdf.format(System.currentTimeMillis())
                dataOutputStream.writeUTF("$now")
            }
            socket.close()
        }

        // Indicate whether the work finished successfully with the Result
        return Result.retry()
    }
}


class MyWork(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    // doWork 는 WorkManager에서 제공하는 백그라운드 스레드에서 비동기적으로 실행된다.
    override fun doWork(): Result {

        thread {
            val socket = Socket(SERVER_IP, PORT_NUMBER)

            val outputStream = socket.getOutputStream()
            val dataOutputStream = DataOutputStream(outputStream)
            val sdf = SimpleDateFormat("HH:mm:ss");
            var now = ""

            for(i in 1 .. 10){
                sleep(1000)
                now = sdf.format(System.currentTimeMillis())
                dataOutputStream.writeUTF("$now")
            }
            socket.close()
        }

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}


