import java.util.Scanner

class CategorySelectClass(val scanner: Scanner) {

    fun selectCategory(){
        while(true){
            try {
                val categoryList = LoadClass().loadCategoryName()
                println()
                if (categoryList.isEmpty()) {
                    println("등록된 카테고리가 없습니다.")
                } else {
                    for (i in 0..categoryList.size - 1) {
                        println("${i + 1} : ${categoryList[i].split(".")[0]}")
                    }
                }
                print("선택할 카테고리 번호를 입력해주세요(0. 이전) : ")
                var temp = scanner.nextLine()
                var select = temp.toInt()
                if(select !in 0 .. categoryList.size) throw Exception()
                if(select == 0) break
                MemoManagementClass(scanner).selectMemoMenu(categoryList[select-1])
            }catch (e: Exception){
                println("잘못 입력하였습니다.")
            }
        }
    }
}

