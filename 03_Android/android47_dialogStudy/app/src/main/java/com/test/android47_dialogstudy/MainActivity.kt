package com.test.android47_dialogstudy

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import com.test.android47_dialogstudy.MainActivity.Companion.dataList
import com.test.android47_dialogstudy.databinding.ActivityMainBinding
import com.test.android47_dialogstudy.databinding.CusdialogBinding
import java.util.Calendar

//1. 기본 다이얼로그 : 메시지와 최대 3개의 버튼을 제공
//
//2. 커스텀 다이얼로그 ; 기본 다이얼로그에 View를 설정하면  표시되는 모양을 자유롭게 구성가능
//
//3. DatePicker : 다이얼로그를 통해 날짜를 선택할 수 있도록 제공되는 다이얼로그
//
//4. TimePicker : 다디얼로그를 통해 시간을 선택할 수 있도록 제공되는 다이얼로그

val mutilChoiceList = BooleanArray(dataList.size)

class MainActivity : AppCompatActivity() {

    companion object{
        var dataList = ArrayList<String>()
    }

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeat(30){
            dataList.add("data$it")
        }


        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            button.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("기본")
                builder.setMessage("기본 다이얼의 메세지")

                builder.setPositiveButton("Positive버튼"){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = "Positive버튼을 눌렀습니다."
                }

                builder.setNegativeButton("Negative버튼"){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = "Negative버튼을 눌렀습니다."
                }

                builder.setNeutralButton("Neutral버튼"){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = "Neutral버튼을 눌렀습니다."
                }

                builder.show()

            }

            button2.setOnClickListener {

                val cusdialogBinding = CusdialogBinding.inflate(layoutInflater)

                val builder = AlertDialog.Builder(this@MainActivity)

                builder.setView(cusdialogBinding.root)

                builder.setNegativeButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                    if(cusdialogBinding.editTextNumber.text.toString() == "100"){
                        textView.text = "정답"
                    } else {
                        textView.text = "오답"
                    }
                }

                builder.setPositiveButton("취소", null)

                builder.show()
            }

            button3.setOnClickListener {
                val calendar = Calendar.getInstance()

                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val dataPickerListner = object : DatePickerDialog.OnDateSetListener{
                    override fun onDateSet(
                        view: DatePicker?,
                        year: Int,
                        month: Int,
                        dayOfMonth: Int
                    ) {
                        textView.text = "${year}년 ${month+1}월 ${dayOfMonth}일 선택하셨습니다."
                    }
                }

                val datePickerDialog = DatePickerDialog(this@MainActivity, dataPickerListner, year, month, day)
                datePickerDialog.show()
            }

            button4.setOnClickListener {
                val calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR)
                val minute = calendar.get(Calendar.MINUTE)

                val timePickerListner = object : TimePickerDialog.OnTimeSetListener{
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        textView.text = "${hourOfDay}시 ${minute}분 선택하셨습니다."
                    }
                }

                val timePickerDialog = TimePickerDialog(this@MainActivity, timePickerListner, hour, minute, true)
                timePickerDialog.show()
            }

            button5.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("리스트뷰 다일로그 단일 선택")

                val adapter = ArrayAdapter<String>(this@MainActivity,android.R.layout.simple_list_item_1,
                    dataList)

                builder.setAdapter(adapter){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = "선택한 항목 : ${dataList[i]}"
                }

                builder.setNegativeButton("취소", null)
                builder.show()
            }

            button6.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("리스트뷰 다일로그 복수 선택")

                builder.setMultiChoiceItems(dataList.toTypedArray(), mutilChoiceList){ dialogInterface: DialogInterface, i: Int, b: Boolean ->
                    mutilChoiceList[i] = b
                }

                builder.setNegativeButton("취소", null)
                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = ""
                    for(idx in 0 until mutilChoiceList.size){
                        if(mutilChoiceList[idx]){
                            textView.append("${dataList[idx]}\n")
                        }
                    }
                }

                builder.show()
            }

        }

    }
}


























