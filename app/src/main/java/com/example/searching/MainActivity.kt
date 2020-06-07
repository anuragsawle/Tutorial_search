package com.example.searching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fossVsId = HashMap<String,Int>()
        fossVsId.put("Java",10)
        fossVsId.put("Python",26)
        fossVsId.put("Cpp",57)
        fossVsId.put("RDBMS",92)
        fossVsId.put("Kotlin",5)

        var langVsId = HashMap<String,Int>()
        langVsId.put("English",22)
        langVsId.put("Hindi",6)
        langVsId.put("Gujrati",5)
        langVsId.put("Kannada",7)

        var fossVsLanguage = HashMap<String, ArrayList<String>>()
        var availableLanguagesForFoss: ArrayList<String>

        availableLanguagesForFoss = arrayListOf("English", "Gujarati","Hindi",  "Kannada")
        fossVsLanguage.put("Java", availableLanguagesForFoss)

        availableLanguagesForFoss = arrayListOf("English", "Gujarati", "Hindi","Kannada", "Marathi", "Tamil")
        fossVsLanguage.put("Cpp", availableLanguagesForFoss)

        availableLanguagesForFoss = arrayListOf("English", "Hindi")
        fossVsLanguage.put("Python", availableLanguagesForFoss)

        availableLanguagesForFoss = arrayListOf("English")
        fossVsLanguage.put("RDBMS", availableLanguagesForFoss)

        availableLanguagesForFoss = arrayListOf("English","Hindi")
        fossVsLanguage.put("Kotlin",availableLanguagesForFoss)

        var fossSpin = findViewById<Spinner>(R.id.fossSpinner)
        var langSpin = findViewById<Spinner>(R.id.langSpinner)

        var fossopt = fossVsId.keys.toList()
        var langopt = langVsId.keys.toMutableList()
        var selectedFoss = "none"
        var selectedLanguage = "none"

        fossSpin.adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,fossopt)
//        langSpin.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,langopt)

//        var fossAdpt = ArrayAdapter<String>(applicationContext,android.R.layout.simple_spinner_item,fossopt)
//        fossSpin.adapter = fossAdpt

        var langAdpt = ArrayAdapter<String>(applicationContext,android.R.layout.simple_spinner_item,langopt)
        langSpin.adapter = langAdpt

        fossSpin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                langopt.clear()
                selectedFoss = fossopt[position]
                var lng= fossVsLanguage.get(selectedFoss)
                if (lng != null) langopt.addAll(lng)
                langSpin.adapter = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_item,langopt)
            }
        }
        langSpin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedLanguage = langopt[position]
            }
        }
        SearchButton.setOnClickListener {
            if (selectedFoss == "none" || selectedLanguage == "none") {
                Toast.makeText(this, "Please select FOSS and Language both", Toast.LENGTH_SHORT).show()
            }
            else{
                var fossId = fossVsId.get(selectedFoss)
                var langId = langVsId.get(selectedLanguage)
                var stIntent = Intent(this, secondActivity::class.java).apply {
                    putExtra("fossID",fossId)
                    putExtra("languageID",langId)
                }
                startActivity(stIntent)
//                var searchIntent = Intent(this, secondActivity::class.java).apply {
//                    putExtra("fossID", "" + fossId)
//                    putExtra("languageID", "" + langId)
//                }
//
//                startActivity(searchIntent)
            }
        }
    }
}