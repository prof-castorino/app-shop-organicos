package br.com.fiap.appshoporganico


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSendSetProduct.setOnClickListener{
            if(hasValid()){
                var intentProducts = Intent(this,ProductsActivity::class.java)
                intentProducts.putExtra("name",txtName.text.toString())
                intentProducts.putExtra("email",txtEmail.text.toString())
                intentProducts.putExtra("phone",txtPhone.text.toString())
                intentProducts.putExtra("postalAddress",txtPostalAddress.text.toString())
                startActivity(intentProducts)
            }

        }
    }
    private fun hasValid():Boolean{
        if(txtName.text.toString().trim().isEmpty()
            && txtEmail.text.toString().trim().isEmpty()
            && txtPhone.text.toString().trim().isEmpty()
            && txtPostalAddress.text.toString().trim().isEmpty()
        ){
            Toast.makeText(
                this,
                R.string.txtInfo,
                Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}