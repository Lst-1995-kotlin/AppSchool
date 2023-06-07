import java.io.File

class DeleteClass {

    fun deleteCategory(name:String){
        File(name).delete()
    }

}