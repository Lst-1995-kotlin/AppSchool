package com.test.android25_homeworkmovie

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
import com.test.android25_homeworkmovie.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var imm : InputMethodManager

    val movieList = ArrayList<Movie>()

    data class Movie(val movieName: String, val mdName: String, val price: Int, val lookAge: String, val starPoint: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            editTextMovieTitle.run{
                // 포커스를 가져온다.
                requestFocus()
            }

            thread {
                if(currentFocus != null){
                    imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(currentFocus,0)
                }
            }


            // 시크바에서 현재 가격을 보여준다.
            seekBarMovePrice.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    // 가격이 변한다면 하기 텍스트 뷰에 현재 가격을 표시한다.
                     textViewPriceText.text = "${progress * 1000}원"
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            // 관람등급을 선택하면 표시를 한다.
            chipLookAge.run{
                setOnCheckedChangeListener { group, checkedId ->
                    when(checkedId){
                        chipAgeAll.id -> {
                            textViewSelectLookAge.text = "전체연령가 선택되었습니다."
                            chipAgeAll.setTextColor(Color.BLACK)
                            chipAge12.setTextColor(Color.WHITE)
                            chipAge15.setTextColor(Color.WHITE)
                            chipAgeAdult.setTextColor(Color.WHITE)
                        }
                        chipAge12.id -> {
                            textViewSelectLookAge.text = "12세 연령가 선택되었습니다."
                            chipAgeAll.setTextColor(Color.WHITE)
                            chipAge12.setTextColor(Color.BLACK)
                            chipAge15.setTextColor(Color.WHITE)
                            chipAgeAdult.setTextColor(Color.WHITE)
                        }
                        chipAge15.id -> {
                            textViewSelectLookAge.text = "15세 연령가 선택되었습니다."
                            chipAgeAll.setTextColor(Color.WHITE)
                            chipAge12.setTextColor(Color.WHITE)
                            chipAge15.setTextColor(Color.BLACK)
                            chipAgeAdult.setTextColor(Color.WHITE)
                        }
                        chipAgeAdult.id -> {
                            textViewSelectLookAge.text = "성인 연령 선택되었습니다."
                            chipAgeAll.setTextColor(Color.WHITE)
                            chipAge12.setTextColor(Color.WHITE)
                            chipAge15.setTextColor(Color.WHITE)
                            chipAgeAdult.setTextColor(Color.BLACK)
                        }
                    }
                }
            }

            // 레이팅바에서 현재 평점을 보여준다.
            ratingBar3.run{
                setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                    textViewStarPoint.text = rating.toString()
                }
            }

            //data class Move(val moveName: String, val MDName: String, val price: String, val lookAge: String, val starPoint: String)
            // 영화 감독의 이름을 받고 엔터를 눌렀을 경우
            editTextMDName.run{
                setOnEditorActionListener { v, actionId, event ->
                    val movieName = editTextMovieTitle.text.toString()
                    val mdName = editTextMDName.text.toString()
                    val price = seekBarMovePrice.progress * 1000
                    var lookAge = when(chipLookAge.checkedChipId){
                        chipAgeAll.id -> {
                            "전체"
                        }
                        chipAge12.id -> {
                            "12세"
                        }
                        chipAge15.id -> {
                            "15세"
                        }
                        chipAgeAdult.id -> {
                            "성인"
                        }
                        // 만약 그냥 넘길 경우
                        else ->{
                            "전체"
                        }

                    }

                    val starPoint = ratingBar3.rating.toString()

                    val movie = Movie(movieName, mdName, price, lookAge, starPoint)
                    movieList.add(movie)

                    editTextMovieTitle.text.clear()
                    seekBarMovePrice.progress = 5
                    chipLookAge.check(chipAgeAll.id)
                    textViewSelectLookAge.text = "전체연령가 선택되었습니다."
                    ratingBar3.progress = 0
                    editTextMDName.text.clear()

                    // 영화 제목에 포커스를 준다.
                    editTextMovieTitle.requestFocus()
                    true
                }

            }

            // 버튼 눌렀을때
            buttonLoadData.run{
                setOnClickListener {
                    textViewPrintData.text = ""
                    for(movie in movieList){
                        textViewPrintData.append("영화제목 : ${movie.movieName}\n")
                        textViewPrintData.append("영화요금 : ${movie.price}\n")
                        textViewPrintData.append("관람등급 : ${movie.lookAge}\n")
                        textViewPrintData.append("별점 : ${movie.starPoint}\n")
                        textViewPrintData.append("영화감독 : ${movie.mdName}\n")
                        textViewPrintData.append("\n")
                    }

                }
            }

        }
    }


}