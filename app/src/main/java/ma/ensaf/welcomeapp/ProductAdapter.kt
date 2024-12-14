package ma.ensaf.welcomeapp;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import ma.ensaf.welcomeapp.Product
import ma.ensaf.welcomeapp.R
class ProductAdapter(private var idList: List<String>, private val itemClickListener: (String) -> Unit) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // ViewHolder class
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textPrice: TextView = itemView.findViewById(R.id.productPriceTextView)
        val imageView: ImageView = itemView.findViewById(R.id.productImageView)
        val parentLayout: LinearLayout = itemView.findViewById(R.id.parentLayout)
        // Replace with the actual parent layout ID
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.your_product_item_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val id = idList[position]
        // Search for the ID in collections and retrieve price and imageUrl
        searchForIDInCollectionsAndUpdateUI(id, holder)

        // Handle click on the parent layout
        holder.parentLayout.setOnClickListener {
            // Send the document ID to the Achat page
            itemClickListener.invoke(id)
        }

    }

    override fun getItemCount(): Int {
        return idList.size
    }

    private fun searchForIDInCollectionsAndUpdateUI(documentID: String, holder: ProductViewHolder) {
        val db = FirebaseFirestore.getInstance()
        val collectionsToSearch = listOf("Jellaba", "caftan", "Babouche","accessoires") // Add your collection names here
        collectionsToSearch.forEach { collection ->
            db.collection(collection).document(documentID)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val price = documentSnapshot.getDouble("price") ?: "No price available"
                        val imageUrl = documentSnapshot.getString("imageUrl") ?: "No image available"

                        // Update the UI with the retrieved price and imageUrl
                        holder.textPrice.text = price.toString()+" DH"
                       // Load image into imageView using imageUrl (you can use Glide or Picasso library for this)
                        Glide.with(holder.itemView).load(imageUrl).into(holder.imageView)
                    }
                }
                .addOnFailureListener { exception ->
                    // Handle any failures
                }

        }
    }
    fun updateData(newIdList: List<String>) {
        idList = newIdList
        notifyDataSetChanged()
    }
}

