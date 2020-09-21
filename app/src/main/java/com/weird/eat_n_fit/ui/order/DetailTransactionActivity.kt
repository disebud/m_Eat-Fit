package com.weird.eat_n_fit.ui.order

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.weird.eat_n_fit.R
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_detail_transaction.*
import kotlinx.android.synthetic.main.activity_detail_transaction.tv_content_address
import kotlinx.android.synthetic.main.activity_detail_transaction.tv_idr_ck
import kotlinx.android.synthetic.main.activity_detail_transaction.tv_porsi_idr_ck
import kotlinx.android.synthetic.main.activity_detail_transaction.tv_result_date_ck
import kotlinx.android.synthetic.main.activity_detail_transaction.tv_result_time_ck
import kotlinx.android.synthetic.main.activity_generateqr.*
import kotlinx.android.synthetic.main.activity_generateqr.qrcode
import java.text.NumberFormat
import java.util.*

class DetailTransactionActivity : AppCompatActivity() {
    private var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaction)

        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences_name),
            Context.MODE_PRIVATE
        )

        val iduser = sharedPreferences?.getString(getString(R.string.user_id), "")
        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
        val idPacket = intent.getStringExtra("idPacket")
        val price = intent.getStringExtra("price")
        val alamat = intent.getStringExtra("alamat")
        val waktu = intent.getStringExtra("waktu")
        val tanggal = intent.getStringExtra("tanggal")
        val porsi = intent.getStringExtra("porsi")
        val idTrans = intent.getStringExtra("idTras")
        val totalPorsi = intent.getStringExtra("totalPorsi")
        val content = idTrans

        tv_result_date_ck.text=tanggal
        tv_result_time_ck.text=waktu
//        tv_idr_ck.text=price
        tv_porsi_idr_ck.text=porsi
        tv_content_address.text=alamat
        var total = price!!.toInt() * porsi!!.toInt()
        currency(price!!.toDouble(), tv_idr_ck)
        currency(total.toDouble(), total_trans)
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        qr_code.setImageBitmap(bitmap)
    }
    fun currency(harga: Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga as Double))

    }
    }
