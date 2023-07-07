package com.test.android73_miniproject3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android73_miniproject3.Auth.AuthFragment
import com.test.android73_miniproject3.Category_Fragment.CategoryListFragment
import com.test.android73_miniproject3.DATA.Category
import com.test.android73_miniproject3.Auth.DAOAuth
import com.test.android73_miniproject3.Auth.LoginFragment
import com.test.android73_miniproject3.DATA.Memo
import com.test.android73_miniproject3.DB.DAOCategory
import com.test.android73_miniproject3.DB.DAOMemo
import com.test.android73_miniproject3.MainActivity.Companion.FRAGMENT_AUTH
import com.test.android73_miniproject3.MainActivity.Companion.FRAGMENT_CATEGORY_LIST
import com.test.android73_miniproject3.Memo_Fragment.MemoAddFragment
import com.test.android73_miniproject3.Memo_Fragment.MemoEditFragment
import com.test.android73_miniproject3.Memo_Fragment.MemoListFragment
import com.test.android73_miniproject3.Memo_Fragment.MemoResultFragment
import com.test.android73_miniproject3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    companion object{
        val FRAGMENT_AUTH = "fragmentAuth"
        val FRAGMENT_CATEGORY_LIST = "fragmentCategoryList"
        val FRAGMENT_MEMO_LIST = "fragmentMemoList"
        val FRAGMENT_MEMO_ADD = "fragmentMemoAdd"
        val FRAGMENT_MEMO_RESULT = "fragmentMemoResult"
        val FRAGMENT_MEMO_EDIT = "fragmentMemoEdit"
        val FRAGMENT_LOGIN = "fragmentLogin"
    }

    var categoryList = mutableListOf<Category>()
    var memoList = mutableListOf<Memo>()
    var selectCategory = ""
    lateinit var selectMemo : Memo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            if(DAOAuth.contains(this@MainActivity)){
                replaceFragment(FRAGMENT_LOGIN, false, false)
            } else {
                replaceFragment(FRAGMENT_AUTH, false, false)
            }

        }
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name:String, addToBackStack:Boolean, animate:Boolean){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        var newFragment = when(name){
            FRAGMENT_LOGIN -> LoginFragment()
            FRAGMENT_AUTH -> AuthFragment()
            FRAGMENT_CATEGORY_LIST -> CategoryListFragment()
            FRAGMENT_MEMO_LIST -> MemoListFragment()
            FRAGMENT_MEMO_ADD -> MemoAddFragment()
            FRAGMENT_MEMO_RESULT -> MemoResultFragment()
            FRAGMENT_MEMO_EDIT -> MemoEditFragment()
            else -> Fragment()
        }

        if(newFragment != null) {
            // Fragment를 교체한다.
            fragmentTransaction.replace(R.id.mainContainerView, newFragment)

            if (animate == true) {
                // 애니메이션을 설정한다.
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            }

            if (addToBackStack == true) {
                // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
                fragmentTransaction.addToBackStack(name)
            }

            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    // Fragment를 BackStack에서 제거한다.
    fun removeFragment(name: String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}