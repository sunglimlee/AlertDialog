package com.example.alertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), ExampleDialog.ExampleDialogListener {
    private lateinit var counterText : TextView
    private lateinit var button : Button
    private var counter : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterText = findViewById(R.id.counter_text)
        button = findViewById(R.id.button)
        button.setOnClickListener(View.OnClickListener {
            openDialog()
        })
    }

    private fun openDialog() {
        var dialog : ExampleDialog = ExampleDialog()
        dialog.show(supportFragmentManager, "example dialog")
    }

    override fun onYesClicked(dialogValue: Int) {
        super.onYesClicked(dialogValue)
        counter++
        counterText.setText("$counter & $dialogValue")
    }
}