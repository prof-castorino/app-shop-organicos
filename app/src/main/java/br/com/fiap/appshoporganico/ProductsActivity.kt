package br.com.fiap.appshoporganico


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.card_product.view.*


class ProductsActivity : AppCompatActivity() {
    private fun mountProduct():Array<Product>{
        return arrayOf(
            Product (R.drawable.morango,"Morango","Caixa de 250g","0","5.00",),
            Product (R.drawable.abacaxi,"Abacaxi","Unidade de 700g","0","7.50",),
            Product (R.drawable.melancia,"Melancia","Unidade de 2,5kg","0","22.50",),
        )
    }
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = Products(mountProduct())
        val name = intent.getStringExtra("name")
        lbSetProduct.text = "Olá, ${name}. ${lbSetProduct.text}."
    }
}

class Product(
    val img: Int,
    val name: String,
    val desc: String,
    val qtd: String,
    val price: String,
)

class Products(private var dataSet:Array<Product>) : RecyclerView.Adapter<Products.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemImage: ImageView
        var itemName: TextView
        var itemDesc: TextView
        var itemPrice: TextView
        var itemQtd: TextView
        var itemSubTotal:TextView

        init {
            itemImage = itemView.findViewById(R.id.ImgProd)
            itemName = itemView.findViewById(R.id.txtProdName)
            itemDesc = itemView.findViewById(R.id.txtProdDesc)
            itemPrice = itemView.findViewById(R.id.txtProdPrice)
            itemQtd = itemView.findViewById(R.id.txtProdQtd)
            itemSubTotal = itemView.findViewById(R.id.txtProdSubTotal)
            itemView.faAdd.setOnClickListener{
                val lastQtd: Int = itemQtd.text.toString().toInt()
                var qtd = lastQtd+1
                itemQtd.text = qtd.toString()
                itemSubTotal.text = (qtd * itemPrice.text.toString().toFloat()).toString()
                Toast.makeText(
                    itemView.context,
                    "Foi adicionado o ${itemName.text} ao carrinho",
                    Toast.LENGTH_SHORT).show()
            }
            itemView.faRemove.setOnClickListener{
                val lastQtd: Int = itemQtd.text.toString().toInt()
                if(lastQtd > 0){
                    var qtd = lastQtd-1
                    itemQtd.text = qtd.toString()
                    itemSubTotal.text = (qtd * itemPrice.text.toString().toFloat()).toString()
                    Toast.makeText(
                        itemView.context,
                        "Foi removido o ${itemName.text} ao carrinho",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_product, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.itemImage.setImageResource(dataSet[position].img)
        viewHolder.itemName.text = dataSet[position].name
        viewHolder.itemDesc.text = dataSet[position].desc
        viewHolder.itemQtd.text = dataSet[position].qtd
        viewHolder.itemPrice.text = dataSet[position].price
        viewHolder.itemSubTotal.text = "0.0"
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
