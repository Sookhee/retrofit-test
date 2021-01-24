package kr.h.emirim.sookhee.sfoid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var mBackWait:Long = 0
    override fun onBackPressed() {
        if(System.currentTimeMillis() - mBackWait >=2000 ) {
            mBackWait = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기 버튼을 한 번 더 누르면 종료됩니", Toast.LENGTH_LONG).show()
        } else {
            finish() //액티비티 종료
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setItemData()

        pullToRefresh.setOnRefreshListener {
            recyclerView.removeAllViewsInLayout()
            setItemData()

            pullToRefresh.isRefreshing = false
        }
    }

    fun setItemData(){
        val request = ServiceBuilder.buildService(UserEndpoints::class.java)
        val call = request.getUsers(10)

        progress_bar.visibility = View.VISIBLE

        call.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful) {

                    progress_bar.visibility = View.GONE

                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = UsersAdapter(response.body()!!.results)
                    }
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_LONG).show()
                Log.d("MESSAGE", t.message.toString())
            }
        })
    }
}
