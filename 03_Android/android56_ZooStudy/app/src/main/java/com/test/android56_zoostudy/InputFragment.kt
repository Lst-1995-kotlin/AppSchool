package com.test.android56_zoostudy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import com.test.android56_zoostudy.databinding.FragmentInputBinding
import kotlin.concurrent.thread


class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    val animalKindList = arrayOf("코끼리", "기린", "토끼")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentInputBinding.run{
            InputSpinner.run{
                val a1 = ArrayAdapter<String>(
                    mainActivity, android.R.layout.simple_list_item_1, animalKindList
                )
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                adapter = a1
            }
            TextInputLayoutAge.run{
                editText?.run {
                    addTextChangedListener {
                        if(!it.isNullOrEmpty()){
                            if(it.toString().toInt() <= 0) {
                                TextInputLayoutAge.error = "0 보다 큰 수를 입력해주세요."
                            }else {
                                TextInputLayoutAge.error = null
                            }
                        }
                    }
                }
            }

            TextInputLayoutWeight.run{
                editText?.run{
                    addTextChangedListener {
                        if(!it.isNullOrEmpty()){
                            if(it.toString().toInt() <= 0){
                                TextInputLayoutWeight.error = "0 보다 큰 수를 입력해주세요."
                            } else {
                                TextInputLayoutWeight.error = null
                            }
                        }

                    }
                }
            }



            buttonSave.setOnClickListener {

                if(!TextInputLayoutAge.editText?.text.isNullOrEmpty() && !TextInputLayoutWeight.editText?.text.isNullOrEmpty()){
                    if(TextInputLayoutAge.editText?.text.toString().toInt() > 0 &&
                        TextInputLayoutWeight.editText?.text.toString().toInt() > 0){

                        val kind = animalKindList[InputSpinner.selectedItemPosition]
                        val name = editTextInputName.text.toString()
                        val age = TextInputEditTextAge.text.toString().toInt()
                        val weight = TextInputEditTextWeight.text.toString().toInt()

                        val animal = Animal(kind, name, age, weight)
                        mainActivity.animalList.add(animal)

                        mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                        mainActivity.replaceFragment(FragmentName.FRAGMENT_MAIN,true,true)

                    }
                }

            }

        }

        return fragmentInputBinding.root
    }


}