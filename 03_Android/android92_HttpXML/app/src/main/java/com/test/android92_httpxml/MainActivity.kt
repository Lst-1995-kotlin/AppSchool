package com.test.android92_httpxml

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android92_httpxml.databinding.ActivityMainBinding
import org.w3c.dom.Element
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding

    val serverAdress = "https://www.aviationweather.gov/adds/dataserver_current/httpparam?datasource=metars&requestType=retrieve&format=xml&mostRecentForEachStation=constraint&hoursBeforeNow=1.25&stationString=KDE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.setOnClickListener {
                // 접속주소를 관리하는 객체를 생성한다.
                thread{

                    val url = URL(serverAdress)
                    // 접속한다.
                    val httpsURLConnection = url.openConnection() as HttpsURLConnection
                    // 웹 브라우저 종류를 확인할 수도 있기 때문에 .....
                    httpsURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")

                    // DOM 방식으로 XML 문서를 분석할 수 있는 도구를 생성한다.
                    val documentBuilderFactory = DocumentBuilderFactory.newInstance()
                    val documentBuilder = documentBuilderFactory.newDocumentBuilder()
                    // 분석 도구를 이용해 XML 문서를 분석해 각 태그들을 모두 객체로 생성한다.
                    //  태그들을 관리하는 객체를 반환한다.
                    val document = documentBuilder.parse(httpsURLConnection.inputStream)

                    // 최 상위 태그를 가져온다.
                    val root = document.documentElement
                    // data tag를 가져온다.
                    val dataTag = root.getElementsByTagName("data")
                    // METAR tag를 가져온다.
                    val dataElement = dataTag.item(0) as Element
                    val METATag = dataElement.getElementsByTagName("METAR")

                    runOnUiThread{
                        textView.text = "${METATag.length}"
                    }

                    for(idx in 0 until METATag.length){
                        // idx 번째 태그 객체를 가져온다.
                        val METAElement = METATag.item(idx) as Element

                        // META 태그 내에서 필요한 태그들을 가져온다.
                        val rawTextList = METAElement.getElementsByTagName("raw_text")
                        val stationIdList = METAElement.getElementsByTagName("station_id")
                        val latitudeList = METAElement.getElementsByTagName("latitude")
                        val longitudeList = METAElement.getElementsByTagName("longitude")

                        val rawTextTag = rawTextList.item(0) as Element
                        val stationIdTag = stationIdList.item(0) as Element
                        val latitudeTag = latitudeList.item(0) as Element
                        val longitudeTag = longitudeList.item(0) as Element

                        // 태그내의 데이터를 가져온다.
                        val rawText = rawTextTag.textContent
                        val stationId = stationIdTag.textContent
                        val latitude = latitudeTag.textContent.toDouble()
                        val longitude = longitudeTag.textContent.toDouble()

                        runOnUiThread {
                            textView.append("rawText : ${rawText}\n")
                            textView.append("stationId : ${stationId}\n")
                            textView.append("latitude : ${latitude}\n")
                            textView.append("longitude : ${longitude}\n\n")
                        }

                    }
                }

            }
        }
    }
}