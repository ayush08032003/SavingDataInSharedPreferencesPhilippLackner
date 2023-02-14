package com.ayushwalker.savingdatainsharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etAge: EditText
    lateinit var cbAdult: CheckBox
    lateinit var btnLoad: Button
    lateinit var btnSave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        cbAdult = findViewById(R.id.cbAdult)
        btnLoad = findViewById(R.id.btnLoad)
        btnSave = findViewById(R.id.btnSave)

        val sharedPref  = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString().toInt()
            val isAdult = cbAdult.isChecked

            editor.apply {
                putString("name",name)
                putInt("age",age)
                putBoolean("isAdult",isAdult)
                apply()
            }
            Toast.makeText(this,"Your Data has been Saved", Toast.LENGTH_SHORT).show()
        }

        btnLoad.setOnClickListener {
            val name = sharedPref.getString("name", null) // Key and default value(in case when the given key value pair not exists.!
            val age = sharedPref.getInt("age", 0)
            val isAdult = sharedPref.getBoolean("isAdult", false)

            etName.setText(name)
            etAge.setText(age.toString())

            cbAdult.isChecked = isAdult

            Toast.makeText(this,"Your Data has been Recovered   ", Toast.LENGTH_SHORT).show()
        }
    }
}

/*
NOTES/STEPS:
Make sure that it will only store only one key-value pair of data for every input stream..!!
 */