package br.com.fiap.appshoporganico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        txtResName.text = intent.getStringExtra("name").toString()
        txtResEmail.text = intent.getStringExtra("email").toString()
        txtResPhone.text = intent.getStringExtra("phone").toString()
        val postalAddress = intent.getStringExtra("postalAddress")
        val products = intent.getStringExtra("products")
    }
}































