import java.io.File
import java.util.Scanner

enum class CategoryState(val num:Int){
    CATEGORY_ADD(1),
    CATEGORY_DELETE(2),
    CATEGORY_UPDATE(3),
    CATEGORY_MANAGEMENT_EXIT(4)
}

class CategoryManagementClass(val scanner: Scanner) {
    var categoryList = listOf<String>()

    fun selectMenu(){
        while(true){
            try{
                categoryList = LoadClass().loadCategoryName()
                if(categoryList.isEmpty()){
                    println()
                    println("등록된 카테고리가 없습니다.")
                } else {
                    println()
                    for (i in 0 .. categoryList.size-1){
                        println("${i+1} : ${categoryList[i].split(".")[0]}")
                    }
                }

                println()
                println("1. 카테고리 등록")
                println("2. 카테고리 삭제")
                println("3. 카테고리 수정")
                println("4. 이전")
                print("카테고리 관리 메뉴 선택 : ")
                var temp = scanner.nextLine()
                var select = temp.toInt()
                if(select !in 1 ..4) throw Exception()

                when(select){

                    CategoryState.CATEGORY_ADD.num ->{
                        while(true){
                            println()
                            print("등록할 카테고리 이름을 입력해주세요 : ")
                            var categoryAddName = scanner.nextLine()
                            if(categoryList.contains("${categoryAddName}.category")){
                                println("중복된 이름의 카테고리가 있습니다.")
                                continue
                            }
                            SaveClass().addCategory("${categoryAddName}.category")
                            break
                        }
                    }

                    CategoryState.CATEGORY_DELETE.num ->{
                        while (true){
                            try{
                                print("삭제할 카테고리 번호를 입력해주세요 : ")
                                var temp = scanner.nextLine()
                                var deleteNum = temp.toInt()
                                if(deleteNum < 1 || deleteNum > categoryList.size) throw Exception()
                                DeleteClass().deleteCategory(categoryList[deleteNum-1])
                                break
                            }catch (e: Exception){
                                println("잘못 입력하였습니다.")
                            }
                        }
                    }

                    CategoryState.CATEGORY_UPDATE.num ->{
                        while(true){
                            try{
                                println()
                                print("수정할 카테고리 번호를 입력해주세요 : ")
                                var temp = scanner.nextLine()
                                var updateNum = temp.toInt()
                                if(updateNum < 1 || updateNum > categoryList.size) throw Exception()
                                print("${categoryList[updateNum-1].split(".")[0]} - > ")
                                val updateName =  scanner.nextLine()
                                UpdateClass().catagoryNameUpdate(categoryList[updateNum-1],"${updateName}.category")
                                break
                            }catch (e: Exception){
                                println("잘못 입력하였습니다.")
                            }
                        }
                    }

                    CategoryState.CATEGORY_MANAGEMENT_EXIT.num -> break
                }
            } catch (e : Exception){
                println("잘못 입력하였습니다.")
            }
        }

    }

}
