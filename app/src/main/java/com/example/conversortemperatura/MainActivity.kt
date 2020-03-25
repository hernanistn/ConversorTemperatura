package com.example.conversortemperatura

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Layout
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var switch : Switch
    private lateinit var edtC : EditText
    private lateinit var edtF : EditText
    private lateinit var btnC : Button
    lateinit var imn : InputMethodManager


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switch = findViewById<Switch>(R.id.switch1)
        btnC = findViewById<Button>(R.id.btnCalc)
        edtC = findViewById<EditText>(R.id.edtCelsios)
        edtF = findViewById<EditText>(R.id.edtFahrenheit)
        imn = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        switch.text = "Celsius"
        edtF.isFocusableInTouchMode = false
        edtC.requestFocus()
        imn.showSoftInput(edtC, InputMethodManager.SHOW_IMPLICIT)


        switch.setOnClickListener {
                if (switch.isChecked) {
                    switch.text = "Fahrenheit"
                    edtF.isFocusableInTouchMode = true
                    edtC.isFocusableInTouchMode = false
                    edtF.requestFocus()
                    edtF.selectAll()
                    imn.showSoftInput(edtF, InputMethodManager.SHOW_IMPLICIT)

                } else {
                    switch.text = "Celsius"
                    edtC.isFocusableInTouchMode = true
                    edtF.isFocusableInTouchMode = false
                    edtC.requestFocus()
                    edtC.selectAll()
                    imn.showSoftInput(edtC, InputMethodManager.SHOW_IMPLICIT)
                }
        }
        btnC.setOnClickListener {
            try {
                if (switch.isChecked) {
                    edtC.setText("${(edtF.text.toString().toDouble() - 32) * 5 / 9}")
                } else {
                    edtF.setText("${edtC.text.toString().toDouble() * 1.8 + 32}")
                }
            }catch (e: Exception){
                Toast.makeText(applicationContext, "Favor inserir os dados acima", Toast.LENGTH_LONG).show()
            }
        }
    }
}
