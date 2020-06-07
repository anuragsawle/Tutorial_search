package com.example.searching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_second.*
import org.json.JSONArray
import org.json.JSONObject

class secondActivity : AppCompatActivity() {
    var finalResultsArrayList = ArrayList<videoItem>()
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//        var obtfoss = intent.getIntExtra("fossID")
        var obtfoss = intent.getIntExtra("fossID",0)
        var obtlang = intent.getIntExtra("languageID",0)
        welcomeText.text = "FossID $obtfoss LanguageID $obtlang"

        var myRecyclerView = findViewById<RecyclerView>(R.id.videos_recyclerview)

        myAdapter = MyAdapter(applicationContext, finalResultsArrayList)

        myRecyclerView.layoutManager = LinearLayoutManager(this)
        myRecyclerView.setHasFixedSize(true)
        myRecyclerView.adapter = myAdapter
        val queue = Volley.newRequestQueue(this)
        val url = "https://spoken-tutorial.org/api/get_tutorials/" + obtfoss + "/" + obtlang

        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->

            Toast.makeText(applicationContext,"Received server response!", Toast.LENGTH_SHORT).show()

            extractJsonData(response)

        }, Response.ErrorListener {
            Toast.makeText(applicationContext, "Unable to connect to the server", Toast.LENGTH_SHORT).show()
        })
        Toast.makeText(applicationContext,
            "Contacting Server...", Toast.LENGTH_SHORT).show()
        queue.add(stringRequest)

    }
    private fun extractJsonData(jsonResponse: String){
        var videosDataArray = JSONArray(jsonResponse)
        var singleVideoJsonObject: JSONObject
        var singleVideoItem: videoItem
        var i = 0
        var size = videosDataArray.length()
        while (i < size)
        {
            singleVideoJsonObject = videosDataArray.getJSONObject(i)

            singleVideoItem = videoItem(singleVideoJsonObject.getString("video_id"),singleVideoJsonObject.getString("tutorial_name"),singleVideoJsonObject.getString("tutorial_level"))

            finalResultsArrayList.add(singleVideoItem)

            i++
        }

        println("The parsed videoItems are :")
        finalResultsArrayList.forEach { println(it) }
        myAdapter.notifyDataSetChanged()
    }
}