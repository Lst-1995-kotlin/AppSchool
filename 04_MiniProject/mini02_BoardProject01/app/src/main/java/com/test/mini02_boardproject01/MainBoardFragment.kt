package com.test.mini02_boardproject01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.mini02_boardproject01.databinding.FragmentMainBoardBinding
import com.test.mini02_boardproject01.databinding.HeaderBoardMainBinding

class MainBoardFragment : Fragment() {

    lateinit var fragmentMainBoardBinding: FragmentMainBoardBinding
    lateinit var mainactivity: MainActivity

    companion object{
        val POST_LIST_FRAGMENT = "PostListFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentMainBoardBinding = FragmentMainBoardBinding.inflate(inflater)
        mainactivity = activity as MainActivity

        replaceChildFragment(R.id.boardAll, POST_LIST_FRAGMENT,false, null)

        fragmentMainBoardBinding.run{


            mainBoardBarLayout.run{
                setLiftOnScrollTargetView(searchView)
            }

            mainBoardToolbar.run{
                setNavigationIcon(R.drawable.menu_24px)

                setNavigationOnClickListener {
                    if(drawerLayout.isOpen){
                        drawerLayout.close()
                    } else {
                        drawerLayout.open()
                    }
                }

                searchView.setOnMenuItemClickListener { menuItem ->
                    true
                }
            }
            mainBoardNavView.run{

                val headerBoardMainBinding = HeaderBoardMainBinding.inflate(inflater)
                headerBoardMainBinding.textViewHeaderBoardMainNickName.text = "이성태"
                addHeaderView(headerBoardMainBinding.root)

                setNavigationItemSelectedListener { menuItem ->
                    // Handle menu item selected
                    when(menuItem.itemId){
                        // 전체 게시판
                        R.id.boardAll ->{
                            val bundle = Bundle()
                            bundle.putString("name", "전체 게시판")
                            mainBoardToolbar.title = "전체 게시판"
                            drawerLayout.close()
                            replaceChildFragment(R.id.boardAll, POST_LIST_FRAGMENT, true, bundle)
                        }
                        // 자유 게시판
                        R.id.boardFree ->{
                            val bundle = Bundle()
                            bundle.putString("name", "자유 게시판")
                            mainBoardToolbar.title = "자유 게시판"
                            drawerLayout.close()
                            replaceChildFragment(R.id.boardFree, POST_LIST_FRAGMENT, true, bundle)
                        }
                        // 유머 게시판
                        R.id.boardSmile ->{
                            val bundle = Bundle()
                            bundle.putString("name", "유머 게시판")
                            mainBoardToolbar.title = "유머 게시판"
                            drawerLayout.close()
                            replaceChildFragment(R.id.boardSmile, POST_LIST_FRAGMENT, true, bundle)
                        }
                        // 질문 게시판
                        R.id.boardQuestion ->{
                            val bundle = Bundle()
                            bundle.putString("name", "질문 게시판")
                            mainBoardToolbar.title = "질문 게시판"
                            drawerLayout.close()
                            replaceChildFragment(R.id.boardQuestion, POST_LIST_FRAGMENT, true, bundle)
                        }
                        // 스포츠 게시판
                        R.id.boardSports ->{
                            val bundle = Bundle()
                            bundle.putString("name", "스포츠 게시판")
                            mainBoardToolbar.title = "스포츠 게시판"
                            drawerLayout.close()
                            replaceChildFragment(R.id.boardSports, POST_LIST_FRAGMENT, true, bundle)
                        }
                        // 로그아웃
                        R.id.boardUserLogout ->{
                            mainactivity.removeFragment(MainActivity.MAIN_BOARD_FRAGMENT)
                        }
                        R.id.boardUserSingout ->{
                            mainactivity.removeFragment(MainActivity.MAIN_BOARD_FRAGMENT)
                        }

                        else -> {
                            drawerLayout.close()
                            return@setNavigationItemSelectedListener true
                        }
                    }
                    true
                }
            }
        }

        return fragmentMainBoardBinding.root
    }

    fun replaceChildFragment(selItem: Int, name:String, addToBackStack:Boolean, bundle:Bundle?){
        val fragmentByFragmenrManager = childFragmentManager.beginTransaction()
        val newFragment = when(selItem){
            R.id.boardAll ->{
                PostListFragment()
            }
            // 자유 게시판
            R.id.boardFree ->{
                PostListFragment()
            }
            // 유머 게시판
            R.id.boardSmile ->{
                PostListFragment()
            }
            // 질문 게시판
            R.id.boardQuestion ->{
                PostListFragment()
            }
            // 스포츠 게시판
            R.id.boardSports ->{
                PostListFragment()
            }
            else -> Fragment()
        }

        newFragment?.arguments = bundle

        if (addToBackStack == true) {
            // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
            fragmentByFragmenrManager.addToBackStack(name)
        }

        if(newFragment != null) {

            // Fragment를 교채한다.
            fragmentByFragmenrManager.replace(R.id.fragmentByContainerView, newFragment!!)

            // 교체 명령이 동작하도록 한다.
            fragmentByFragmenrManager.commit()
        }
    }

}