package com.weird.eat_n_fit.ui.order

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.food.FoodViewModel
import com.weird.eat_n_fit.model.order.OrderFoodPacket
import com.weird.eat_n_fit.model.order.OrderViewModel
import com.weird.eat_n_fit.model.signin.UserSignInModel
import com.weird.eat_n_fit.ui.utils.DatePickerHelper
import kotlinx.android.synthetic.main.activity_next_order.*
import okhttp3.internal.format
import java.text.SimpleDateFormat
import java.util.*


class NextOrderActivity : AppCompatActivity() {
    val orderViewModel by viewModels<OrderViewModel>()
    var button_date: Button? = null
    var textview_date: TextView? = null
    var cal = Calendar.getInstance()
    lateinit var picker1: NumberPicker
    lateinit var picker2: NumberPicker
    lateinit var pickerVals: Array<Int>
    lateinit var pickerVals2: Array<String>
    private var sharedPreferences: SharedPreferences? = null
    @RequiresApi(Build.VERSION_CODES.Q)
    lateinit var datePicker: DatePickerHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_order)
        datePicker = DatePickerHelper(this)
        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences_name),
            Context.MODE_PRIVATE
        )
        val iduser = sharedPreferences?.getString(getString(R.string.user_id), "")
        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
        val idFood = intent.getStringExtra("idFood")
        // get the references from layout file
        textview_date = this.text_view_date_1
        button_date = this.button_date_1

        textview_date!!.text = "----/--/--"
        button_date_1.setOnClickListener {
            showDatePickerDialog()
        }

        picker1 = findViewById(R.id.porsi_numberpicker)
        picker2 = findViewById(R.id.time_numberpicker)
        picker1.setMaxValue(1000)
        picker1.setMinValue(1)

        val valuePicker1 = picker1.getValue()
        val update: String =
            java.lang.String.valueOf(valuePicker1)
        System.out.println("Persentase Api: " + update);

//        pickerVals = arrayOf(20, 30, 50, 75, 100, 125, 150, 200, 300, 400, 500, 750, 1000)
        pickerVals2 = arrayOf("Breakfast", "Lunch", "Dinner")

//        picker1.setDisplayedValues(pickerVals)
//        picker1.setOnValueChangedListener(OnValueChangeListener { numberPicker, i, i1 ->
//            val valuePicker1 = picker1.getValue()
//            Log.d("picker value", pickerVals[valuePicker1])
//        })

        picker2.setDisplayedValues(pickerVals2)
        picker2.setOnValueChangedListener(OnValueChangeListener { numberPicker, i, i1 ->
            val valuePicker2 = picker2.getValue()
            Log.d("picker value", pickerVals2[valuePicker2])
        })


        btn_next_order.setOnClickListener {
            val porsi = porsi_numberpicker.value.toString()
            val tanggal = text_view_date_1.text.toString()
            val waktu = time_numberpicker.value.toString()
            val alamat = Alamat.text.toString()
            var order = OrderFoodPacket(iduser!!,idFood!!,porsi,tanggal,waktu,alamat)
            println(idFood)
            println(token)
            println(iduser)

            orderViewModel.orderFoodPacket(token!!,order)
        }

    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)
        val minDate = Calendar.getInstance()
        minDate.set(Calendar.HOUR_OF_DAY, 0)
        minDate.set(Calendar.MINUTE, 0)
        minDate.set(Calendar.SECOND, 0)
        datePicker.setMinDate(minDate.timeInMillis)
        datePicker.showDialog(d, m, y, object : DatePickerHelper.Callback {
            override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                val mon = month + 1
                val monthStr = if (mon < 10) "0${mon}" else "${mon}"
                text_view_date_1.text = "${year}-${monthStr}-${dayStr}"
            }
        })

    }

//    private fun updateDateInView() {
//        val myFormat = "yyyy/MM/dd" // mention the format you need
//        val sdf = SimpleDateFormat(myFormat, Locale.US)
//        textview_date!!.text = sdf.format(cal.getTime())
//    }


}


