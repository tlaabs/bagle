package io.github.tlaabs.bagel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.github.tlaabs.bagel.api.GithubRepos
import io.github.tlaabs.bagel.api.GithubUser
import io.github.tlaabs.bagel.api.Repo
import io.github.tlaabs.bagel.view.SearchListAdapter
import io.github.tlaabs.bbaaggeell.api.NetworkModule
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val DEFAULT_PATH = "testapp://repos/"

    private val adapter = SearchListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = intent.dataString
        if(data != null && data.startsWith(DEFAULT_PATH)){
            val param = data.replace(DEFAULT_PATH, "")
            Toast.makeText(applicationContext,"param : $param",Toast.LENGTH_SHORT).show()

            NetworkModule.apiService.searchUser(param).enqueue(object : Callback<GithubUser> {
                override fun onFailure(call: Call<GithubUser>, t: Throwable) {

                }

                override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                    val result = response.body()
                    Log.d("devsim","msg : ${result?.login}")

                }
            })
            NetworkModule.apiService.searchUserRepos(param).enqueue(object : Callback<List<Repo>> {
                override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

                }

                override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                    val result = response.body()
                    Log.d("devsim","msg : ${result?.size}")
                    adapter.addItem(result)
                }
            })

            initAdapter()

        }
    }

    fun initAdapter() {
        listView.adapter = adapter
    }
}
