package com.test.mini02_boardproject01

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import com.test.mini02_boardproject01.databinding.FragmentMainBoardBinding
import com.test.mini02_boardproject01.databinding.HeaderBoardMainBinding
import kotlinx.parcelize.Parcelize
@Parcelize
class MainBoardFragment : Fragment(), Parcelable {

    lateinit var fragmentBoardMainBinding: FragmentMainBoardBinding
    lateinit var mainActivity: MainActivity

    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null

    companion object{
        val POST_LIST_FRAGMENT = "PostListFragment"
        val MODIFY_USER_FRAGMENT = "ModifyUserFragment"
        val POST_WRITE_FRAGMENT = "PostWriteFragment"
        val POST_READ_FRAGMENT = "PostReadFragment"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentBoardMainBinding = FragmentMainBoardBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentBoardMainBinding.run{

            // toolbar
            toolbarBoardMain.run{
                title = "게시판 메인"
                setNavigationIcon(R.drawable.menu_24px)
                setNavigationOnClickListener {
                    // 네비게이션 뷰를 보여준다.
                    drawerLayoutBoardMain.open()
                }
            }
            // DrawerView
            navigationViewBoardMain.run{

                // 헤더 설정
                val headerBoardMainBinding = HeaderBoardMainBinding.inflate(inflater)
                headerBoardMainBinding.textViewHeaderBoardMainNickName.text = "홍길동"
                addHeaderView(headerBoardMainBinding.root)

                // 항목 선택시 동작하는 리스너
                setNavigationItemSelectedListener {

                    // 누른 메뉴를 체크상태로 둔다.
                    //it.isChecked = true

                    val newBundle = Bundle()
                    val mainFragment = this@MainBoardFragment
                    newBundle.putParcelable("mainFragment", mainFragment)

                    // 사용자가 누른 메뉴의 id로 분기한다.
                    when(it.itemId){
                        // 전체 게시판
                        R.id.boardAll ->{
                            replaceFragment(POST_LIST_FRAGMENT, false, false, newBundle)
                            drawerLayoutBoardMain.close()
                        }
                        // 자유 게시판
                        R.id.boardFree ->{
                            replaceFragment(POST_LIST_FRAGMENT, false, false, newBundle)
                            drawerLayoutBoardMain.close()
                        }
                        // 유머 게시판
                        R.id.boardSmile ->{
                            replaceFragment(POST_LIST_FRAGMENT, false, false, newBundle)
                            drawerLayoutBoardMain.close()
                        }
                        // 질문 게시판
                        R.id.boardQuestion ->{
                            replaceFragment(POST_LIST_FRAGMENT, false, false, newBundle)
                            drawerLayoutBoardMain.close()
                        }
                        // 스포츠 게시판
                        R.id.boardSports ->{
                            replaceFragment(POST_LIST_FRAGMENT, false, false, newBundle)
                            drawerLayoutBoardMain.close()
                        }
                        // 사용자 정보 수정
                        R.id.boardUserInfoEdit ->{
                            replaceFragment(MODIFY_USER_FRAGMENT, false, false, null)
                            drawerLayoutBoardMain.close()
                        }
                        // 로그아웃
                        R.id.boardUserLogout ->{
                            mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT, false, null)
                        }
                        // 회원 탈퇴
                        R.id.boardUserSingout ->{
                            mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT, false, null)
                        }

                    }
                    // 닫아준다.
                    //drawerLayoutBoardMain.close()
                    true
                }

            }
        }

        return fragmentBoardMainBinding.root
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name:String, addToBackStack:Boolean,  animate:Boolean,  bundle:Bundle?){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()

        // newFragment에 Fragment가 들어있으면 oldFragment에 넣어준다.
        if(newFragment != null){
            oldFragment = newFragment
        }

        // 새로운 Fragment를 담을 변수
        newFragment = when(name){
            POST_LIST_FRAGMENT -> PostListFragment()
            MODIFY_USER_FRAGMENT -> ModifyUserFragment()
            POST_WRITE_FRAGMENT -> PostWriteFragment()
            POST_READ_FRAGMENT -> PostReadFragment()
            else -> Fragment()
        }

        newFragment?.arguments = bundle

        if(newFragment != null) {

            if(animate == true){
                // 애니메이션 설정
                if(oldFragment != null){
                    oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                    oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
            } else {
                if(oldFragment != null){
                    oldFragment?.exitTransition = null
                    oldFragment?.reenterTransition =null
                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
                newFragment?.enterTransition = null
                newFragment?.returnTransition = null
            }


            // Fragment를 교채한다.
            fragmentTransaction.replace(R.id.boardMainContainer, newFragment!!)

            if (addToBackStack == true) {
                // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
                fragmentTransaction.addToBackStack(name)
            }

            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    // Fragment를 BackStack에서 제거한다.
    fun removeFragment(name:String){
        mainActivity.supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


}