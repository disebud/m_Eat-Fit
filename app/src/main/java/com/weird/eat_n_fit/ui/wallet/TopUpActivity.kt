package com.weird.eat_n_fit.ui.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.weird.eat_n_fit.R
import kotlinx.android.synthetic.main.activity_top_up.*
import java.lang.NumberFormatException

class TopUpActivity : AppCompatActivity() {

    private var status100K : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up)

        initListener()
    }

    private fun initListener() {
        btn_top_up2.setOnClickListener {
            startActivity(Intent(this, SuccessTopUpActivity::class.java))
            overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left);
            finish()
        }

        tv_100k.setOnClickListener {
            if (status100K) {
                deselectMoney(tv_100k)
            } else {
                selectMoney(tv_100k)
            }
        }

        et_amount.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) { }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {  }

            override fun afterTextChanged(s: Editable) {
                try {
                    if (s.toString().toInt() >= 100000) {
                        btn_top_up2.visibility = View.VISIBLE
                    } else {
                        tv_100k.setTextColor(resources.getColor(R.color.colorBlack))
                        tv_100k.setBackgroundResource(R.drawable.shape_line_white)
                        status100K = false
                        btn_top_up2.visibility = View.INVISIBLE
                    }
                } catch (e : NumberFormatException) {
                    tv_100k.setTextColor(resources.getColor(R.color.colorBlack))
                    tv_100k.setBackgroundResource(R.drawable.shape_line_white)
                    status100K = false
                    btn_top_up2.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun selectMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.orangeThird))
        textView.setBackgroundResource(R.drawable.shape_line_pink)
        status100K = true

        btn_top_up2.visibility = View.VISIBLE
        et_amount.setText("100000")
    }

    private fun deselectMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.colorWhite))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status100K = false

        btn_top_up2.visibility = View.INVISIBLE
        et_amount.setText("")
    }

}