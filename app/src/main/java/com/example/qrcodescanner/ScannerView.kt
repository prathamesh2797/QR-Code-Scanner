package com.example.qrcodescanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.zxing.Result
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.util.jar.Manifest

class ScannerView : AppCompatActivity(), ZXingScannerView.ResultHandler {

    var zXingScannerView: ZXingScannerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        zXingScannerView =  ZXingScannerView(this)
        setContentView(zXingScannerView)

        Dexter.withContext(applicationContext)
            .withPermission(android.Manifest.permission.CAMERA)
            .withListener(object :PermissionListener{
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {


                    zXingScannerView!!.startCamera()
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {

                    p1?.continuePermissionRequest()
                }

            }).onSameThread().check()

    }

    override fun handleResult(rawResult: Result?) {
        val rawResult = rawResult!!.text
        //val intent = Intent(applicationContext, MainActivity::class.java)
        val intent = Intent()

        intent.putExtra("rawResult", rawResult.toString())
        //Toast.makeText(applicationContext, rawResult.toString(), Toast.LENGTH_SHORT).show()
        setResult(2,intent)
        //startActivity(intent)
        onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        zXingScannerView?.stopCamera()
    }

    override fun onResume() {
        super.onResume()
        zXingScannerView?.setResultHandler(this)
        //zXingScannerView?.startCamera()
    }

}