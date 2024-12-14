package ma.ensaf.welcomeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.math.log

class PageRecherche : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_recherche)
        val email = intent.getStringExtra("EMAIL")
        val receivedText = intent.getStringExtra("searchText")
        Log.d("Tag", "Contenu de receivedText : $receivedText")
        searchDocumentsContaining(receivedText.toString(), email!!);
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val layoutManager = GridLayoutManager(this, 2) // 2 colonnes

        recyclerView.layoutManager = layoutManager
        val titleAll: TextView = findViewById(R.id.titleAll)
        titleAll.text = "Résultat de recherche sur  \"${receivedText.toString()}\""
       // val email=intent.getStringExtra("EMAIL")
        Log.d("affcihage",email.toString())
    }
    fun searchDocumentsContaining(searchText: String,email:String) {
        val db = FirebaseFirestore.getInstance()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = ProductAdapter(emptyList()) { documentId ->
            val intent = Intent(this, PageAchat::class.java)
            intent.putExtra("document_id", documentId)
            Log.d("inPageRecherche",email.toString())
            intent.putExtra("EMAIL", email)
            startActivity(intent)
        }

         recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        val collectionsToSearch = listOf("Jellaba", "caftan", "Babouche","accessoires") // Add your collection names here
        val matchingDocumentIDs = mutableSetOf<String>() // To store matching document IDs without duplication
        collectionsToSearch.forEach { collection ->
            db.collection(collection)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val data = document.data
                        data.forEach { (key, value) ->
                            if (value.toString().contains(searchText, ignoreCase = true)) {
                                matchingDocumentIDs.add(document.id) // Add document ID if the field contains the search text
                            }
                        }
                    }
                    adapter.updateData(matchingDocumentIDs.toList()) // Update the adapter with matching document IDs
                }
                .addOnFailureListener { exception ->
                    Log.d("Tag", "FK")
                }
        }
    }
}



