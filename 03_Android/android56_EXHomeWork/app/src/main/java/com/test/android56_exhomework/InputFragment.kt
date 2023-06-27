package com.test.android56_exhomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.test.android56_exhomework.data.FragmentInputName
import com.test.android56_exhomework.databinding.FragmentInputBinding


class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    val inputSpinnerData = arrayOf("야구부", "축구부", "수영부")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentInputBinding = FragmentInputBinding.inflate(inflater)
        mainActivity = activity as MainActivity


        fragmentInputBinding.run{

            spinnerInput.run {
                val siAdapter = ArrayAdapter<String>(
                    mainActivity, android.R.layout.simple_list_item_1,inputSpinnerData
                )
                siAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                adapter = siAdapter

                setSelection(0)

                onItemSelectedListener = object : OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        when(selectedItemPosition){
                            0 -> {
                                mainActivity.replaceFragment2(FragmentInputName.FRAGMENT_BASEBALL_ADD, true, true)
                            }
                            1 -> {
                                mainActivity.replaceFragment2(FragmentInputName.FRAGMENT_SOCCER_ADD, true, true)
                            }
                            2 -> {
                                mainActivity.replaceFragment2(FragmentInputName.FRAGMENT_SWIMMING_ADD, true, true)
                            }
                        }
//                        mainActivity.removeFragment2(FragmentInputName.FRAGMENT_SWIMMING_ADD)
//                        mainActivity.removeFragment2(FragmentInputName.FRAGMENT_SOCCER_ADD)
//                        mainActivity.removeFragment2(FragmentInputName.FRAGMENT_BASEBALL_ADD)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        //TODO("Not yet implemented")
                    }

                }

            }
        }

        return fragmentInputBinding.root
    }


}