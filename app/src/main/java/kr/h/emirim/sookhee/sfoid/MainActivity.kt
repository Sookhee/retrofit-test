package kr.h.emirim.sookhee.sfoid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progress_bar: ProgressBar = findViewById(R.id.progress_bar)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        Log.d("MESSAGE", "fine here :)")

        val request = ServiceBuilder.buildService(UserEndpoints::class.java)
        val call = request.getUsers(50)

        call.enqueue(object : Callback<Users>{
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful){
                    Log.d("MESSAGE", "success here :)")

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