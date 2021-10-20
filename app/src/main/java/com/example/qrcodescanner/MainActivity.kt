package com.example.qrcodescanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //private var tv_qr_text : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn_qr_code_scanner.setOnClickListener {
            val intent = Intent(this, ScannerView::class.java)
            startActivityForResult(intent,2)
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==2){
            var rawResult = data?.getStringExtra("rawResult")
            tv_qr_text.text = rawResult.toString()
            Toast.makeText(applicationContext, rawResult.toString(), Toast.LENGTH_SHORT).show()

        }
    }


}