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
        if(!hasName(txtName.text.toString())){
            Toast.makeText(
                this,
                R.string.validName,
                Toast.LENGTH_LONG).show()
            return false
        }
        if(!hasEmail(txtEmail.text.toString())){
            Toast.makeText(
                this,
                R.string.validEmail,
                Toast.LENGTH_LONG).show()
            return false
        }
        if(!hasPhone(txtPhone.text.toString())){
            Toast.makeText(
                this,
                R.string.validPhone,
                Toast.LENGTH_LONG).show()
            return false
        }
        if(!hasPostAddress(txtPostalAddress.text.toString())){
            Toast.makeText(
                this,
                R.string.validPostAddress,
                Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
    private fun hasPhone(phone:String):Boolean{
        var validationExpression = "^[0-9]{2}-[0-9]{9}".toRegex()

        return validationExpression.matches(phone)
        //assertTrue(validationExpression.matches(phone))
    }
    private fun hasName(name:String):Boolean{
        var validationExpression="[A-Z][a-z].* [A-Z][a-z].*".toRegex()
        return validationExpression.matches(name)
    }
    private fun hasEmail(email:String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private fun hasPostAddress(postAddress:String):Boolean{
        var validationExpression = "^[0-9]{5}-([0-9]{3})".toRegex()
        return validationExpression.matches(postAddress)
    }
}