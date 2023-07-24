package com.test.android93_httpjsonstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.test.android93_httpjsonstudy.JSON.Board
import com.test.android93_httpjsonstudy.JSON.jsonTest
import com.test.android93_httpjsonstudy.databinding.ActivityMainBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.InputStreamReader
import java.io.ObjectOutputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.Socket
import java.net.URL
import kotlin.concurrent.thread



data class Info(val role: String, val name: String?)
class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val serverAddress = "https://a.4cdn.org/boards.json"

    val SERVER_IP = "172.30.1.85"
    var SERVER_PORT = 44445

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            button4.setOnClickListener {
                textView.text = "GSON을 이용하여 JSON 만들기."

                val InfoList = mutableListOf<Info>()

                InfoList.add(Info("Mentee1", "이성태"))
                InfoList.add(Info("Mentee2", "유민국"))
                InfoList.add(Info("Mentee3", "최가연"))
                InfoList.add(Info("Mentee4", "고진호"))
                InfoList.add(Info("Mentor", "정민지"))

                val gson = Gson()
                val g1 = gson.toJson(InfoList)
                textView.append(g1)


            }

            button.setOnClickListener {
                textView.text = "기본 방식\n\n"
                thread{
                    val url = URL(serverAddress)
                    val httpURLConnection = url.openConnection() as HttpURLConnection

                    val inputStreamReader = InputStreamReader(httpURLConnection.inputStream, "UTF-8")
                    val bufferedReader = BufferedReader(inputStreamReader)

                    var str : String? = null
                    val stringBuffer = StringBuffer()

                    do{
                        str = bufferedReader.readLine()
                        if(str != null){
                            stringBuffer.append(str)
                        }
                    } while(str != null)
                    bufferedReader.close()

                    val data = stringBuffer.toString()

                    val root = JSONObject(data)
                    // boards
                    val boards = root.getJSONArray("boards")

                    for(i in 0 until boards.length()){
                        val boardObj = boards.getJSONObject(i)

                        val board = boardObj.getString("board")
                        val title = boardObj.getString("title")
                        val pages = boardObj.getInt("pages")
                        val cooldowns = boardObj.getJSONObject("cooldowns")
                        val threads = cooldowns.getInt("threads")
                        val replies = cooldowns.getInt("replies")
                        val images = cooldowns.getInt("images")

                        runOnUiThread{
                            textView.append("board : ${board}\n")
                            textView.append("title : ${title}\n")
                            textView.append("pages : ${pages}\n")
                            textView.append("threads : ${threads}\n")
                            textView.append("replies : ${replies}\n")
                            textView.append("images : ${images}\n\n")
                        }

                    }
                }
            }

            button2.setOnClickListener {
                textView.text = "assets 내 저장된 JSON파일 읽기\n\n"

                val gson = Gson()
                val boards = gson.fromJson(assets.open("LocalJson.json").reader().readText(), jsonTest::class.java).boards

                for(board in boards){
                    textView.append("board : ${board.board}\n")
                    textView.append("title :${board.title}\n")
                    textView.append("pages :${board.pages}\n")
                    textView.append("cooldowns.threads :${board.cooldowns.threads}\n")
                    textView.append("cooldowns.images :${board.cooldowns.images}\n")
                    textView.append("cooldowns.replies :${board.cooldowns.replies}\n\n")
                }
            }

            button3.setOnClickListener {
                textView.text = "검색 + 강사님 코드 참고하여 작성한 방식\n JSON파일 읽어오기\n\n"
                thread{
                    val url = URL(serverAddress)
                    val httpURLConnection = url.openConnection() as HttpURLConnection

                    val inputStreamReader = InputStreamReader(httpURLConnection.inputStream, "UTF-8")
                    val bufferedReader = BufferedReader(inputStreamReader)

                    var str : String? = null
                    val stringBuffer = StringBuffer()
                    do{
                        str = bufferedReader.readLine()
                        if(str != null){
                            stringBuffer.append(str)
                        }
                    } while(str != null)
                    bufferedReader.close()


                    val gson = Gson()
                    val data = stringBuffer.toString()
                    val boards = gson.fromJson(data.reader().readText(), jsonTest::class.java).boards

                    runOnUiThread {
                        for(board in boards){
                            textView.append("board : ${board.board}\n")
                            textView.append("title :${board.title}\n")
                            textView.append("pages :${board.pages}\n")
                            textView.append("cooldowns.threads :${board.cooldowns.threads}\n")
                            textView.append("cooldowns.images :${board.cooldowns.images}\n")
                            textView.append("cooldowns.replies :${board.cooldowns.replies}\n\n")
                        }
                    }

                }

            }

        }
    }
}