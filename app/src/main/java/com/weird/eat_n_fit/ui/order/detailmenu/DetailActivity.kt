package com.weird.eat_n_fit.ui.order.detailmenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weird.eat_n_fit.R


class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val idFood = intent.getStringExtra("idFood")
        val namaFood = intent.getStringExtra("nameFood")
        val price= intent.getStringExtra("price")
        val desc = intent.getStringExtra("desc")
        val fat=  intent.getStringExtra("fat")
        val proteiin =  intent.getStringExtra("protein")
        val carbo =  intent.getStringExtra("carbo")
        val calories= intent.getStringExtra("calories")
        val portion = intent.getStringExtra("portion")

//        var bundle=Bundle()
//        bundle.putString("name",namaFood)
//        var frag=DetailFragment()
//        frag.arguments=bundle

        val bundle = Bundle()
        val myMessage = "Stack Overflow is cool!"
        bundle.putString("message", myMessage)
        val fragInfo = DetailFragment()
        fragInfo.setArguments(bundle)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.detailFragment,fragInfo)
        transaction.commit()
        println(fragInfo)

    }




}