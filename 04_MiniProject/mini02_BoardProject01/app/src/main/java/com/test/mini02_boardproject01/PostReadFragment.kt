package com.test.mini02_boardproject01

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.test.mini02_boardproject01.VM.ViewModelPost
import com.test.mini02_boardproject01.databinding.FragmentPostReadBinding


class PostReadFragment : Fragment() {

    lateinit var fragmentPostReadBinding: FragmentPostReadBinding
    lateinit var mainActivity: MainActivity

    lateinit var viewModelPost: ViewModelPost

    override fun onResume() {
        super.onResume()
        viewModelPost.postGetOne()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentPostReadBinding = FragmentPostReadBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        viewModelPost = ViewModelProvider(mainActivity)[ViewModelPost::class.java]

        fragmentPostReadBinding.run{

            viewModelPost.run{
                postData.observe(mainActivity){
                    textInputEditTextPostReadSubject.setText(it.postSubject)
                    textInputEditTextPostReadText.setText(it.postText)
                    textViePostReadWriteUserId.text = it.postWriterUserId
                    textViePostReadWriteTime.text = it.postWriteDate
                }
                postImageData.observe(mainActivity){
                    imageView.setImageBitmap(it)
                }
            }

            toolbarPostRead.run{
                title = "글읽기"
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.POST_WRITE_FRAGMENT)
                    mainActivity.removeFragment(MainActivity.POST_READ_FRAGMENT)
                }
                inflateMenu(R.menu.menu_post_read)

                setOnMenuItemClickListener {

                    when(it.itemId){
                        R.id.item_post_read_modify -> {
//                            if(textInputEditTextPostReadSubject.isEnabled == false) {
//                                textInputEditTextPostReadSubject.isEnabled = true
//                                textInputEditTextPostReadText.isEnabled = true
//                            } else {
//                                textInputEditTextPostReadSubject.isEnabled = false
//                                textInputEditTextPostReadText.isEnabled = false
//                            }
                            mainActivity.replaceFragment(MainActivity.POST_MODIFY_FRAGMENT, true, null)
                        }
                        R.id.item_post_read_delete -> {
                            mainActivity.removeFragment(MainActivity.POST_WRITE_FRAGMENT)
                            mainActivity.removeFragment(MainActivity.POST_READ_FRAGMENT)
                        }
                    }

                    true
                }
            }

            textInputEditTextPostReadSubject.run{
                setTextColor(Color.BLACK)
            }

            textInputEditTextPostReadText.run{
                setTextColor(Color.BLACK)
            }

        }

        return fragmentPostReadBinding.root
    }

}