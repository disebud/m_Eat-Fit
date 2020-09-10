package com.weird.eat_n_fit

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_next_order.*
import java.text.SimpleDateFormat
import java.util.*


class NextOrderActivity : AppCompatActivity() {

    var button_date: Button? = null
    var textview_date: TextView? = null
    var cal = Calendar.getInstance()
    lateinit var picker1: NumberPicker
    lateinit var picker2: NumberPicker
    lateinit var pickerVals: Array<Int>
    lateinit var pickerVals2: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_order)

        // get the references from layout file
        textview_date = this.text_view_date_1
        button_date = this.button_date_1

        textview_date!!.text = "--/--/----"


        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        button_date!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@NextOrderActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

        /////////////////////

        // Picker 1 = Jumlah Porsi
        // Picker 2 = Time

        picker1 = findViewById(R.id.porsi_numberpicker)
        picker2 = findViewById(R.id.time_numberpicker)
        picker1.setMaxValue(1000)
        picker1.setMinValue(5)
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


    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textview_date!!.text = sdf.format(cal.getTime())
    }


}


