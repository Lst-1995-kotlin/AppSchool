package com.test.retrofitstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.retrofitstudy.databinding.ActivityMainBinding
import com.test.retrofitstudy.databinding.RvItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface GithubService {
    @GET("users/Kotlin/repos")
    fun users(): Call<Repository>
}

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com") // baseUrl 등록( 반드시 '/'로 마무리
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val adapter = CustomAdapter()

        activityMainBinding.run{
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        activityMainBinding.buttonRequest.setOnClickListener{
            val githubService = retrofit.create(GithubService::class.java)
            githubService.users().enqueue(object: Callback<Repository> { // enqueue 를 사용하여 비동기 통신 실행 + 통신 완료 후 이벤트 처리 위한 Callback 리스너 등록
                override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                    if(response.isSuccessful){ // 정상적으로 통신이 된 경우 여기 부분은 메인 스레드에서 작업하는 부분이다.
                        activityMainBinding.textView.text = "통신 성공"
                        adapter.userList = response.body() as Repository
                        adapter.notifyDataSetChanged()
                    } else {
                        // 통신이 실패한 경우
                        // onRespons가 무조건 성공 응답이 아니기에 확인이 필요하다.
                        activityMainBinding.textView.text = "통신 실패"
                        adapter.userList?.clear()
                        adapter.notifyDataSetChanged()
                    }

                }

                override fun onFailure(call: Call<Repository>, t: Throwable) {
                    // 인터넷 끊김, 예외 발생 등 시스템적인 이유
                    activityMainBinding.textView.text = "통신 오류"
                }
            })
        }
    }

    inner class CustomAdapter: RecyclerView.Adapter<CustomAdapter.Holder>() {
        var userList: Repository? = null

        inner class Holder(val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun setUser(user: RepositoryItem?){
                user?.let{
                    binding.textName.text = user.name
                    binding.textId.text = user.git_url
                    Glide.with(binding.imageAvater).load(user.owner.avatar_url).into(binding.imageAvater)
                }


            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return Holder(binding)
        }

        override fun getItemCount(): Int {
            return userList?.size?: 0
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val user = userList?.get(position)
            return holder.setUser(user)
        }

    }


}




