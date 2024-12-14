package ma.ensaf.welcomeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

class FilterdAdapter(private var idList: List<String>, private val itemClickListener: (String) -> Unit) : RecyclerView.Adapter<FilterdAdapter.ProductViewHolder>() {

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
        searchForIDInCollectionsAndUpdateUI(id, holder)
        holder.parentLayout.setOnClickListener {
            itemClickListener.invoke(id)
        }
    }

    override fun getItemCount(): Int {
        return idList.size
    }


    private fun searchForIDInCollectionsAndUpdateUI(documentID: String, holder: ProductViewHolder) {
        val db = FirebaseFirestore.getInstance()
        val collectionsToSearch = listOf("Jellaba", "caftan", "Babouche")
        collectionsToSearch.forEach { collection ->
            db.collection(collection).document(documentID)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val price = documentSnapshot.getDouble("price") ?: "No price available"
                        val imageUrl = documentSnapshot.getString("imageUrl") ?: "No image available"
                        holder.textPrice.text = price.toString()+" DH"
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
