package com.test.android39_menu_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.PopupMenu
import com.test.android39_menu_study.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val dataList = arrayOf(
        "항목1", "항목2", "항목3", "항목4", "항목5", "항목6", "항목7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {

            registerForContextMenu(textViewContextSelect)
            registerForContextMenu(listView)

            listView.run{
                adapter = ArrayAdapter<String>(
                    this@MainActivity, android.R.layout.simple_list_item_1, dataList
                )
            }


            buttonPopupMenu.setOnClickListener {
                // 첫번째 매개 변수로는 생성되는 액티비티를 전달한다.
                // 두번째 매개 변수로는 액티비티에서 해당 팝업메뉴가 나올 View를 전달한다.
                val popupMenu = PopupMenu(this@MainActivity, textView)

                // 메뉴를 구성한다.
                // 첫번째 매개 변수 : 해당 메뉴의 레이아웃 파일 위치
                // 두번째 매개 변수 : 해당 메뉴의 정보를 가지고 있는 객체의 menu
                menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

                // 팝업메뉴를 띄운다.
                popupMenu.show()

                // 팝업메뉴 중 특정 메뉴를 눌렀을때 동작하는 리스너
                popupMenu.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.pop1 -> textView.text = "팝업메뉴 1 선택"
                        R.id.pop2 -> textView.text = "팝업메뉴 2 선택"
                        R.id.pop3 -> textView.text = "팝업메뉴 3 선택"
                    }

                    true
                }

            }

        }


    }

    // v : 사용자가 길게 누르면 뷰 객체가 들어온다.
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        // View의 아이디로 분기한다.
        v?.run {
            when(v.id){
                R.id.textViewContextSelect -> {
                    // 메뉴의 제목
                    menu?.setHeaderTitle("텍스트 뷰의 메뉴")
                    // 첫번째 매개 변수 : 해당 메뉴의 레이아웃 파일 위치
                    // 두번째 매개 변수 : 해당 메뉴의 정보를 가지고 있는 객체의 menu
                    menuInflater.inflate(R.menu.context_menu,menu)
                }
                R.id.listView ->{
                    // 별도 기재
                    val info = menuInfo as AdapterView.AdapterContextMenuInfo
                    menu?.setHeaderTitle("${dataList[info.position]}의 메뉴")
                    menuInflater.inflate(R.menu.list_menu,menu)
                }
            }
        }

    }

    // 컨텍스트 메뉴의 항목을 선택하였을 때 호출되는 메서드
    // 해당 메서드에서는 메뉴를 띄우기 위해 길게 누를 뷰가 무엇인지 구분할 방법이 없기 때문에
    // 이에 서로 다른 뷰의 컨텍트스 메뉴라고 하더라도 메뉴의 id는 모두 다 다르게 구성해줘야 한다.
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            // TextView의 컨텍스트 메뉴
            R.id.context1 -> activityMainBinding.textViewContextSelect.text = "텍스트뷰 - 메뉴1"
            R.id.context2 -> activityMainBinding.textViewContextSelect.text = "텍스트뷰 - 메뉴2"
            R.id.context3 -> activityMainBinding.textViewContextSelect.text = "텍스트뷰 - 메뉴3"
            // ListView의 컨텍스트 메뉴
            R.id.list_menu1 -> {
                val info  = item.menuInfo as AdapterView.AdapterContextMenuInfo
                activityMainBinding.textViewContextSelect.text = "리스트뷰 - ${dataList[info.position]}의 메뉴1"
            }
            R.id.list_menu2 -> {
                val info  = item.menuInfo as AdapterView.AdapterContextMenuInfo
                activityMainBinding.textViewContextSelect.text = "리스트뷰 - ${dataList[info.position]}의 메뉴2"
            }
            R.id.list_menu3 -> {
                val info  = item.menuInfo as AdapterView.AdapterContextMenuInfo
                activityMainBinding.textViewContextSelect.text = "리스트뷰 - ${dataList[info.position]}의 메뉴3"
            }
        }

        return super.onContextItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.add(Menu.NONE,Menu.FIRST + 1, Menu.NONE, "코드 메뉴1")
        menu?.add(Menu.NONE,Menu.FIRST + 2, Menu.NONE, "코드 메뉴2")

        val subMenu = menu?.addSubMenu("코드 메뉴3")
        subMenu?.add(Menu.NONE, Menu.FIRST + 3, Menu.NONE, "코드 메뉴 3-1")

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        activityMainBinding.run{
            when(item.itemId){
                Menu.FIRST + 1 ->{
                    textView.text = "첫 번째 옵션을 선택하였습니다."
                }
                Menu.FIRST + 2 ->{
                    textView.text = "두 번째 옵션을 선택하였습니다."
                }
                Menu.FIRST + 3 ->{
                    textView.text = "세 번째 옵션을 선택하였습니다."
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}