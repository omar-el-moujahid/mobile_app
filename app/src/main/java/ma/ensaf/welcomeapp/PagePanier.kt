package ma.ensaf.welcomeapp
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.util.Locale


class PagePanier : AppCompatActivity() {
    private val panierCollection: CollectionReference = FirebaseFirestore.getInstance().collection("panier")

    private lateinit var  recyclerView: RecyclerView
    private lateinit var panierAdapter: PanierAdapter
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private var mesachats: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_panier)
        val fleshe: ImageView = findViewById(R.id.FlesheImage)
        val email: String? = intent.getStringExtra("EMAIL")
        val prixTotalTextView: TextView = findViewById(R.id.prixToal)



        Log.d("mailpanier",email.toString())
        val imageId = intent.getStringExtra("IMAGE_ID")
        fleshe.setOnClickListener {
            val intent = Intent(this, PageAchat::class.java)
            intent.putExtra("EMAIL",email)
            intent.putExtra("IMAGE_ID",imageId)
            startActivity(intent)
        }


        val Payer: Button = findViewById(R.id.bottomButton)
        Payer.setOnClickListener {
            val intent = Intent(this, PagePaiment::class.java)
            intent.putStringArrayListExtra("mesachats", ArrayList(mesachats))
            intent.putExtra("EMAIL",email)
            intent.putExtra("price",  prixTotalTextView.text.toString())
            startActivity(intent)


        }
        recyclerView = findViewById(R.id.panierArticle)

        panierAdapter = PanierAdapter(emptyList(), object : PanierAdapter.OnItemInteractionListener {


            override fun onDecrementButtonClick(documentId: String, position: Int, quantite: Int) {
                updateQuantity(documentId, quantite)
                loadPanierDataFromFirebase(email ?: "")
                updateTotalPrice()

            }


            override fun onIncrementButtonClick(documentId: String, position: Int, quantite: Int) {
                // Appelé lorsque le bouton d'incrémentation est cliqué
                // Vous pouvez implémenter la logique de mise à jour de la quantité ici
                updateQuantity(documentId, quantite)
                loadPanierDataFromFirebase(email ?: "")
                updateTotalPrice()

            }

            override fun onQuantityUpdate(documentId: String, quantite: Int) {
                updateQuantity(documentId, quantite)

            }
        })
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = panierAdapter
        panierAdapter.setOnItemClickListener(object : PanierAdapter.OnItemClickListener {
            override fun onItemClick(documentId: String) {
                val intent = Intent(this@PagePanier, PageAchat::class.java)
                intent.putExtra("documentId1", documentId)
                intent.putExtra("EMAIL",email)
                startActivity(intent)
            }
            override fun onCancelButtonClick(documentId: String, position: Int) {
                val alertDialogBuilder = AlertDialog.Builder(this@PagePanier)
                alertDialogBuilder.setMessage("Voulez-vous vraiment supprimer cette article du panier ?")
                alertDialogBuilder.setPositiveButton("Non") { dialog, _ ->
                    dialog.dismiss()
                }

                alertDialogBuilder.setNegativeButton("Oui") { dialog, _ ->
                    panierCollection.document(documentId).delete()
                        .addOnSuccessListener {
                            panierAdapter.onRemoveItemClick(position)
                            panierAdapter.removeItem(position) // Supprimez visuellement l'élément de la page
                            updateTotalPrice()
                        }
                        .addOnFailureListener { e ->
                            Log.e(TAG, "Erreur lors de la suppression de l'article du panier", e)
                            // Gérer les erreurs ici
                        }
                    dialog.dismiss() // Fermer la boîte de dialogue après la suppression
                }

                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }

        })

        loadPanierDataFromFirebase(email ?: "")

    }
    private fun updateQuantity(documentId: String, newQuantity: Int) {
        val panierItemRef = panierCollection.document(documentId)
        panierItemRef.update("quantite", newQuantity.toString())
            .addOnSuccessListener {

            }
            .addOnFailureListener { e ->
                // Gérer les erreurs ici
                Log.e(TAG, "Erreur lors de la mise à jour de la quantité dans le document", e)
            }
    }

    private fun loadPanierDataFromFirebase(email: String) {
        panierCollection.whereEqualTo("email", email).get()
            .addOnCompleteListener { task: Task<QuerySnapshot?> ->
                if (task.isSuccessful) {
                    val panierItems = mutableListOf<PanierItem>()
                    for (document in task.result!!) {
                        val documentId = document.id
                        mesachats.add(documentId)
                        val panierItem = document.toObject(PanierItem::class.java)
                        panierItem.documentId = documentId
                        Log.e(TAG, "Le nouveaux Id $documentId" )
                        panierItems.add(panierItem)
                    }
                    panierAdapter.setPanierItemList(panierItems)
                    panierAdapter.notifyDataSetChanged()
                    updateTotalPrice()
                } else {
                    // Gérer les erreurs ici
                }
            }
    }



    private fun updateTotalPrice() {
        var totalPrice = 0.0
        // Parcourez les articles dans le panier et ajoutez le prix total
        for (panierItem in panierAdapter.getPanierItemList()) {
            val quantiteString = panierItem.getQuantite()
            val prixUnitaireString = panierItem.getPrice()

            // Vérifiez que quantiteString et prixUnitaireString ne sont pas nuls
            if (quantiteString != null && prixUnitaireString != null) {
                val quantite = quantiteString.toDoubleOrNull() ?: 0.0
                val prixUnitaire = prixUnitaireString
                totalPrice += quantite * prixUnitaire

            }
        }

        // Mettez à jour le TextView avec le nouveau prix total
        val prixTotalTextView: TextView = findViewById(R.id.prixToal)
        prixTotalTextView.text = String.format(Locale.getDefault(), "%.2f Dh", totalPrice)
        panierAdapter.notifyDataSetChanged()
    }



}