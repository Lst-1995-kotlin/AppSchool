package com.test.android_study_zoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.test.android_study_zoo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding
    lateinit var imm : InputMethodManager

    val animalList = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{


            // 선택된 그룹에 따라 리니어 레이아웃 표시
            animalGroup.run{
                setOnCheckedChangeListener { group, checkedId ->
                    when(checkedId){
                        // 코끼리가 선택된 경우
                        radioButtonElephant.id ->{
                            // 화면 숨기기
                            LinearLayoutElephant.isVisible = true
                            LinearLayoutCat.isVisible = false
                            LinearLayoutDog.isVisible = false

                            // 포커스 가져오기
                            editTextElephantName.run{
                                requestFocus()
                                imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.showSoftInput(currentFocus,0)
                            }

                        }
                        // 고양이가 선택된 경우
                        radioButtonCat.id ->{
                            LinearLayoutElephant.isVisible = false
                            LinearLayoutCat.isVisible = true
                            LinearLayoutDog.isVisible = false

                            // 포커스 가져오기
                            editTextCatName.run{
                                requestFocus()
                                imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.showSoftInput(currentFocus,0)
                            }

                        }
                        // 강아지가 선택된 경우
                        radioButtonDog.id ->{
                            LinearLayoutElephant.isVisible = false
                            LinearLayoutCat.isVisible = false
                            LinearLayoutDog.isVisible = true

                            // 포커스 가져오기
                            editTextDogName.run{
                                requestFocus()
                                imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.showSoftInput(currentFocus,0)
                            }

                        }
                        // 아무것도 선택되지 않은 경우
                        else -> {
                            LinearLayoutElephant.isVisible = false
                            LinearLayoutCat.isVisible = false
                            LinearLayoutDog.isVisible = false

                            if(currentFocus != null){
                                imm.hideSoftInputFromWindow(currentFocus!!.windowToken,0)
                                currentFocus!!.clearFocus()
                            }
                        }
                    }
                }
            }

            // 코 길이 입력 후 엔터키

            editTextElephantNoseLength.setOnEditorActionListener { v, actionId, event ->
                animalList.add(Elephant("코끼리","나뭇잎",editTextElephantName.text.toString(),editTextElephantNoseLength.text.toString()))

                if(currentFocus != null){
                    imm.hideSoftInputFromWindow(currentFocus!!.windowToken,0)
                    currentFocus!!.clearFocus()
                }

                editTextElephantName.text.clear()
                editTextElephantNoseLength.text.clear()
                animalGroup.clearCheck()

                false
            }


            // 냥 펀치 입력 후 엔터키

            editTextCatPunchSpeed.setOnEditorActionListener { v, actionId, event ->
                animalList.add(Cat("고양이","고양이 사료",editTextCatName.text.toString(),editTextCatPunchSpeed.text.toString()))

                if(currentFocus != null){
                    imm.hideSoftInputFromWindow(currentFocus!!.windowToken,0)
                    currentFocus!!.clearFocus()
                }

                editTextCatName.text.clear()
                editTextCatPunchSpeed.text.clear()
                animalGroup.clearCheck()

                false
            }


            // 개인기 개수 입력 후 엔터키

            editTextDogPerforCount.setOnEditorActionListener { v, actionId, event ->
                animalList.add(Dog("강아지","강아지 사료", editTextDogName.text.toString(), editTextDogPerforCount.text.toString()))

                if(currentFocus != null){
                    imm.hideSoftInputFromWindow(currentFocus!!.windowToken,0)
                    currentFocus!!.clearFocus()
                }

                editTextCatName.text.clear()
                editTextCatPunchSpeed.text.clear()
                animalGroup.clearCheck()

                false
            }

            // 동물 정보 출력 버튼 클릭 시
            ButtonPrintInfo.run{
                setOnClickListener {


                    if(currentFocus != null){
                        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(currentFocus!!.windowToken,0)
                        currentFocus!!.clearFocus()
                    }


                    textViewPrintInfo.text = ""

                    val elephantCount = animalList.count{ it is Elephant}
                    val catCount = animalList.count{ it is Cat}
                    val dogCount = animalList.count{ it is Dog}

                    textViewPrintInfo.append("코끼리 : ${elephantCount}마리\n")
                    textViewPrintInfo.append("고양이 : ${catCount}마리\n")
                    textViewPrintInfo.append("강아지 : ${dogCount}마리\n")
                    textViewPrintInfo.append("\n")

                    for(animal in animalList){
                        textViewPrintInfo.append("${animal.returnInfo()}\n")
                        textViewPrintInfo.append("\n")
                    }

                }
            }

        }

    }
}