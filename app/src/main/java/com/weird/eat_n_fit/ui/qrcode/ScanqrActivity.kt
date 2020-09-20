package com.weird.eat_n_fit.ui.qrcode

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.zxing.Result
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.order.OrderSuccessActivity
import com.weird.eat_n_fit.ui.sign.signIn.screen.SignInActivity
import com.weird.eat_n_fit.ui.utils.CustomViewFinderView
import kotlinx.android.synthetic.main.activity_scanqr.*
import me.dm7.barcodescanner.core.IViewFinder
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScanqrActivity : AppCompatActivity(),ZXingScannerView.ResultHandler, View.OnClickListener  {
    private lateinit var mScannerView: ZXingScannerView
    private var isCaptured = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanqr)
        initScannerView()
        initDefaultView()
        button_reset.setOnClickListener(this)
    }

    private fun initScannerView() {
        mScannerView = object : ZXingScannerView(this) {
            override fun createViewFinderView(context: Context?): IViewFinder {
                return CustomViewFinderView(context!!)
            }
        }
        mScannerView.setAutoFocus(true)
        mScannerView.setResultHandler(this)
        frame_layout_camera.addView(mScannerView)
    }

    override fun onStart() {
        mScannerView.startCamera()
        doRequestPermission()
        super.onStart()
    }

    private fun doRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            100 -> {
                initScannerView()
            }
            else -> {
                /* nothing to do in here */
            }
        }
    }

    override fun onPause() {
        mScannerView.stopCamera()
        super.onPause()
    }

    private fun initDefaultView() {
        text_view_qr_code_value.text = ""
        button_reset.visibility = View.GONE
        btn_pay.visibility=View.GONE
    }

    override fun handleResult(rawResult: Result?) {
       // text_view_qr_code_value.text = rawResult?.text
        //button_reset.visibility = View.VISIBLE

            text_view_qr_code_value.text = rawResult?.text
            button_reset.visibility = View.VISIBLE
            btn_pay.visibility = View.VISIBLE
            btn_pay.setOnClickListener {
                val intent = Intent(this,
                    OrderSuccessActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish()
            }
        }



    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_reset -> {
                mScannerView.resumeCameraPreview(this)
                initDefaultView()
            }
            else -> {

            }
        }
    }
}