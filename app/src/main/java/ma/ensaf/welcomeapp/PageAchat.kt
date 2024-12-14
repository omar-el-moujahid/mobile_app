package ma.ensaf.welcomeapp
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.graphics.Typeface
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.squareup.picasso.Picasso
class PageAchat : AppCompatActivity() {
    private val firestore: FirebaseFirestore = Firebase.firestore
    var selectedSize: String = "XXS"
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_achat)
        val iconBottom = findViewById<ImageView>(R.id.bottomIcon)
        val cond= intent.getStringExtra("condition")
        val docId = intent.getStringExtra("document_id")
        if(cond.toString().equals("true")) {
            findViewById<TextView>(R.id.t1).text="36"
            findViewById<TextView>(R.id.t2).text="37"
            findViewById<TextView>(R.id.t3).text="38"
            findViewById<TextView>(R.id.t4).text="39"
            findViewById<TextView>(R.id.t5).text="40"
            findViewById<TextView>(R.id.t6).text="41"
            findViewById<TextView>(R.id.t7).text="42"
             selectedSize="36"
        }
        val linearLayout = findViewById<LinearLayout>(R.id.linearToChange)
        val black = ContextCompat.getColor(this, R.color.black)
        val white = ContextCompat.getColor(this, R.color.white)

        for (i in 0 until linearLayout.childCount) {
            val textView = linearLayout.getChildAt(i) as TextView

            textView.setOnClickListener {
                // Réinitialisez les styles pour tous les textes à l'exception de celui sélectionné
                for (j in 0 until linearLayout.childCount) {
                    val otherTextView = linearLayout.getChildAt(j) as TextView
                    otherTextView.setTextColor(black)
                    otherTextView.setBackgroundColor(white)
                    otherTextView.setTypeface(null, Typeface.NORMAL)
                }
                // Appliquez le style au texte sélectionné
                textView.setTextColor(white)
                textView.setBackgroundColor(black)
                textView.setTypeface(null, Typeface.BOLD)
                selectedSize = textView.text.toString()
            }}

        val email = intent.getStringExtra("EMAIL")
        Log.d("mailpanier",email.toString()+" fromAchatDessus")
        val imageId = intent.getStringExtra("IMAGE_ID")
        val documentId1=intent.getStringExtra("documentId1").toString()
        Log.d("docuemntId1",documentId1+"    hiDebut")
        Log.e(ContentValues.TAG, "Le nouveaux Id dans page achat $documentId1" )
        val collections = listOf("caftan", "Jellaba", "Babouche","panier","favoris","accessoires")
        for (collection in collections) {
            val documentId: String = imageId.toString().substring(1)
            val collectionRef = firestore.collection(collection)

            // Récupérer l'imageUrl du document actuel
            collectionRef.document(documentId).get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val imageUrl = documentSnapshot.getString("imageUrl")
                        val favorisRef = firestore.collection("favoris")
                        favorisRef.whereEqualTo("imageUrl", imageUrl).get()
                            .addOnSuccessListener { querySnapshot ->
                                if (!querySnapshot.isEmpty) {
                                    // Vérifier également si le champ "email" est égal à la variable email
                                    val favoriteDocument = querySnapshot.documents[0]
                                    val favoriteEmail = favoriteDocument.getString("email")
                                    if (favoriteEmail == email) {
                                        iconBottom.setImageResource(R.drawable.redheart)
                                    } else {
                                        iconBottom.setImageResource(R.drawable.heart)
                                    }
                                } else {
                                    iconBottom.setImageResource(R.drawable.heart)
                                }
                            }
                            .addOnFailureListener { exception ->
                                // Gérer l'échec si nécessaire
                            }

                        // Mise à jour de l'UI avec les autres données du document actuel
                        val description = documentSnapshot.getString("description")
                        val prix = documentSnapshot.getDouble("price").toString()
                        updateUI(imageUrl, description, prix)
                    }
                }
                .addOnFailureListener { exception ->
                    // Gérer l'échec si nécessaire
                }
        }

        for (collection in collections) {
            val collectionRef = firestore.collection(collection)
            collectionRef.document(documentId1).get()
                .addOnSuccessListener {
                        documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        Log.d("docuemntId1","documentId1here")
                        val imageUrl = documentSnapshot.getString("imageUrl")
                        val description = documentSnapshot.getString("description")
                        val prix = documentSnapshot.getDouble("price").toString()

                        updateUI(imageUrl, description, prix)
                        return@addOnSuccessListener
                    }else{
                        Log.d("docuemntId1","not documentId1here")
                    }
                }.addOnFailureListener { exception ->
                    // Handle failure if needed
                }
        }

        for (collection in collections) {
            val docId = intent.getStringExtra("document_id")
            val documentId: String = docId.toString()
            val collectionRef = firestore.collection(collection)
            collectionRef.document(documentId).get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val imageUrl = documentSnapshot.getString("imageUrl")
                        val description = documentSnapshot.getString("description")
                        val prix = documentSnapshot.getDouble("price").toString()
                        updateUI(imageUrl, description, prix)
                        return@addOnSuccessListener // exit loop if document found
                    }
                }
                .addOnFailureListener { exception ->
                }
        }





        val fleshe: ImageView = findViewById(R.id.FlesheImage)
        fleshe.setOnClickListener {
            finish()
        }
        val panier: ImageView = findViewById(R.id.PanierImage)
        panier.setOnClickListener {
            val intent = Intent(this, PagePanier::class.java)
            Log.d("mailpanier",email.toString()+" fromAchat")
            intent.putExtra("EMAIL",email)
            startActivity(intent)
        }

        val searchEditText : EditText = findViewById(R.id.searchEditText)
        val researchIcon2 : ImageView = findViewById(R.id.searchIcon2)
        researchIcon2.setOnClickListener({
            val intent = Intent(this@PageAchat, PageRecherche::class.java)
            intent.putExtra("searchText", searchEditText.text.toString())
            intent.putExtra("EMAIL", email)
            startActivity(intent)
        })

        // ...

        iconBottom.setOnClickListener {
            val collections = listOf("caftan", "Jellaba", "Babouche","accessoires","panier")

            for (collection in collections) {
                val documentId: String = imageId.toString().substring(1)
                val collectionRef = firestore.collection(collection)

                // Vérifier si le document existe dans la collection "favoris"
                val favorisRef = firestore.collection("favoris")
                // Récupérer le champ "imageUrl" du document correspondant à documentId
                collectionRef.document(documentId).get()
                    .addOnSuccessListener { documentSnapshot ->
                        if (documentSnapshot.exists()) {
                            val imageUrl = documentSnapshot.getString("imageUrl")
                            // Vérifier si le document avec le même imageUrl existe dans "favoris"
                            favorisRef.whereEqualTo("imageUrl", imageUrl)
                                .whereEqualTo("email",email)
                                .get()
                                .addOnSuccessListener { querySnapshot ->
                                    if (querySnapshot.isEmpty) {
                                        // Le document n'existe pas dans "favoris", ajoutez-le
                                        val documentData = documentSnapshot.data
                                        documentData?.put("email", email)
                                        documentData?.remove("Taille")
                                        documentData?.put("taille", selectedSize)
                                        favorisRef.add(documentData!!)
                                            .addOnSuccessListener {
                                                Log.d("FavorisLog", "Document added to Favoris collection successfully")
                                                // Mise à jour de l'icône vers "redHeart"
                                                iconBottom.setImageResource(R.drawable.redheart)
                                            }
                                            .addOnFailureListener { exception ->
                                                Log.e("FavorisLog", "Error adding document to Favoris collection", exception)
                                                // Gérer l'échec si nécessaire
                                            }
                                    } else {
                                        // Le document existe dans "favoris", supprimez-le
                                        for (document in querySnapshot.documents) {
                                            val favorisDocumentId = document.id
                                            favorisRef.document(favorisDocumentId).delete()
                                                .addOnSuccessListener {
                                                    Log.d("FavorisLog", "Document removed from Favoris collection successfully")
                                                    // Mise à jour de l'icône vers "whiteHeart"
                                                    iconBottom.setImageResource(R.drawable.heart)
                                                }
                                                .addOnFailureListener { exception ->
                                                    Log.e("FavorisLog", "Error removing document from Favoris collection", exception)
                                                    // Gérer l'échec si nécessaire
                                                }
                                        }
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    // Gérer l'échec si nécessaire
                                    Log.e("FavorisLog", "Error checking for existing document in Favoris collection", exception)
                                }
                        }
                    }
                    .addOnFailureListener { exception ->
                        // Gérer l'échec si nécessaire
                        Log.e("DocumentLog", "Error getting document details", exception)
                    }
            }
            //
            for (collection in collections) {
                val documentId: String = docId.toString()
                val collectionRef = firestore.collection(collection)

                // Vérifier si le document existe dans la collection "favoris"
                val favorisRef = firestore.collection("favoris")
                // Récupérer le champ "imageUrl" du document correspondant à documentId
                collectionRef.document(documentId).get()
                    .addOnSuccessListener { documentSnapshot ->
                        if (documentSnapshot.exists()) {
                            val imageUrl = documentSnapshot.getString("imageUrl")
                            Log.d("email image","$email")
                            // Vérifier si le document avec le même imageUrl existe dans "favoris"
                            favorisRef.whereEqualTo("imageUrl", imageUrl)
                                .whereEqualTo("email",email)
                                .get()
                                .addOnSuccessListener { querySnapshot ->
                                    if (querySnapshot.isEmpty) {
                                        val documentData = documentSnapshot.data
                                        Log.d("email put","$email")
                                        documentData?.put("email", email)
                                        documentData?.remove("Taille")
                                        documentData?.put("taille", selectedSize)
                                        favorisRef.add(documentData!!)
                                            .addOnSuccessListener {
                                                Log.d("FavorisLog", "Document added to Favoris collection successfully")
                                                // Mise à jour de l'icône vers "redHeart"
                                                iconBottom.setImageResource(R.drawable.redheart)
                                            }
                                            .addOnFailureListener { exception ->
                                                Log.e("FavorisLog", "Error adding document to Favoris collection", exception)
                                                // Gérer l'échec si nécessaire
                                            }
                                    } else {
                                        // Le document existe dans "favoris", supprimez-le
                                        for (document in querySnapshot.documents) {
                                            val favorisDocumentId = document.id
                                            favorisRef.document(favorisDocumentId).delete()
                                                .addOnSuccessListener {
                                                    Log.d("FavorisLog", "Document removed from Favoris collection successfully")
                                                    // Mise à jour de l'icône vers "whiteHeart"
                                                    iconBottom.setImageResource(R.drawable.heart)
                                                }
                                                .addOnFailureListener { exception ->
                                                    Log.e("FavorisLog", "Error removing document from Favoris collection", exception)
                                                    // Gérer l'échec si nécessaire
                                                }
                                        }
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    // Gérer l'échec si nécessaire
                                    Log.e("FavorisLog", "Error checking for existing document in Favoris collection", exception)
                                }
                        }
                    }
                    .addOnFailureListener { exception ->
                        // Gérer l'échec si nécessaire
                        Log.e("DocumentLog", "Error getting document details", exception)
                    }
            }
        }

// ...




        val buttonBottom = findViewById<Button>(R.id.bottomButton)
        buttonBottom.setOnClickListener {
            // Utilisez l'imageId pour récupérer les détails du document
            val documentId: String = imageId.toString().substring(1)
            val collections = listOf("caftan", "Jellaba", "Babouche","accessoires")
            for (collection in collections) {
                val collectionRef = firestore.collection(collection)
                collectionRef.document(documentId).get()
                    .addOnSuccessListener { documentSnapshot ->
                        if (documentSnapshot.exists()) {
                            val documentData = documentSnapshot.data

                            // Ajoutez le champ "email" au contenu du document
                            documentData?.put("email", email)
                            documentData?.put("quantite", "1")
                            documentData?.remove("Taille")
                            documentData?.put("taille", selectedSize)

                            // Consultez la collection "panier" pour voir si un document avec le même imageUrl et la même taille existe déjà
                            val panierCollectionRef = firestore.collection("panier")
                            panierCollectionRef.whereEqualTo("imageUrl", documentData?.get("imageUrl"))
                                .whereEqualTo("taille", selectedSize)
                                .whereEqualTo("email",email)
                                .get()
                                .addOnSuccessListener { querySnapshot ->
                                    if (querySnapshot.isEmpty) {
                                        // Aucun document existant trouvé, ajoutez un nouveau document à la collection "panier"
                                        panierCollectionRef.add(documentData!!)
                                            .addOnSuccessListener {
                                                showSnackbarRed("Article ajoutée en succès")
                                                Log.d("PanierLog", "Document added to Panier collection successfully")
                                                // Vous pouvez ajouter des actions supplémentaires ou des mises à jour de l'interface utilisateur ici
                                            }
                                            .addOnFailureListener { exception ->
                                                Log.e("PanierLog", "Error adding document to Panier collection", exception)
                                                // Gérez l'échec si nécessaire
                                            }
                                    } else {
                                        // Un document existant a été trouvé, mettez à jour le champ quantite du document existant
                                        val existingDocumentId = querySnapshot.documents[0].id
                                        val existingDocumentRef = panierCollectionRef.document(existingDocumentId)
                                        existingDocumentRef.get().addOnSuccessListener { existingDocumentSnapshot ->
                                            if (existingDocumentSnapshot.exists()) {
                                                // Mettez à jour le champ quantite
                                                val existingQuantite = existingDocumentSnapshot.getString("quantite")?.toInt() ?: 0
                                                existingDocumentRef.update("quantite", (existingQuantite + 1).toString())
                                                    .addOnSuccessListener {
                                                        Log.d("PanierLog", "Existing document quantity updated successfully")
                                                        // Vous pouvez ajouter des actions supplémentaires ou des mises à jour de l'interface utilisateur ici
                                                    }
                                                    .addOnFailureListener { exception ->
                                                        Log.e("PanierLog", "Error updating existing document quantity", exception)
                                                        // Gérez l'échec si nécessaire
                                                    }
                                            }
                                        }
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    Log.e("PanierLog", "Error checking for existing document in Panier collection", exception)
                                    // Gérez l'échec si nécessaire
                                }
                        }
                    }
                    .addOnFailureListener { exception ->
                        // Gérez l'échec si nécessaire
                        Log.e("PanierLog", "Error getting document details", exception)
                    }
            }
            val documentId1: String = docId.toString()

            for (collection in collections) {
                val collectionRef = firestore.collection(collection)
                collectionRef.document(documentId1).get()
                    .addOnSuccessListener { documentSnapshot ->
                        if (documentSnapshot.exists()) {
                            val documentData = documentSnapshot.data


                            // Ajoutez le champ "email" au contenu du document
                            documentData?.put("email", email)
                            documentData?.put("quantite", "1")
                            documentData?.remove("Taille")
                            documentData?.put("taille", selectedSize)

                            // Consultez la collection "panier" pour voir si un document avec le même imageUrl et la même taille existe déjà
                            val panierCollectionRef = firestore.collection("panier")
                            panierCollectionRef.whereEqualTo("imageUrl", documentData?.get("imageUrl"))
                                .whereEqualTo("taille", selectedSize)
                                .whereEqualTo("email",email)
                                .get()
                                .addOnSuccessListener { querySnapshot ->
                                    if (querySnapshot.isEmpty) {
                                        // Aucun document existant trouvé, ajoutez un nouveau document à la collection "panier"
                                        panierCollectionRef.add(documentData!!)
                                            .addOnSuccessListener {
                                                showSnackbarRed("Article ajoutée en succès")
                                                Log.d("PanierLog", "Document added to Panier collection successfully")
                                                // Vous pouvez ajouter des actions supplémentaires ou des mises à jour de l'interface utilisateur ici
                                            }
                                            .addOnFailureListener { exception ->
                                                Log.e("PanierLog", "Error adding document to Panier collection", exception)
                                                // Gérez l'échec si nécessaire
                                            }
                                    } else {
                                        // Un document existant a été trouvé, mettez à jour le champ quantite du document existant
                                        val existingDocumentId = querySnapshot.documents[0].id
                                        val existingDocumentRef = panierCollectionRef.document(existingDocumentId)
                                        existingDocumentRef.get().addOnSuccessListener { existingDocumentSnapshot ->
                                            if (existingDocumentSnapshot.exists()) {
                                                // Mettez à jour le champ quantite
                                                val existingQuantite = existingDocumentSnapshot.getString("quantite")?.toInt() ?: 0
                                                existingDocumentRef.update("quantite", (existingQuantite + 1).toString())
                                                    .addOnSuccessListener {
                                                        Log.d("PanierLog", "Existing document quantity updated successfully")
                                                        // Vous pouvez ajouter des actions supplémentaires ou des mises à jour de l'interface utilisateur ici
                                                    }
                                                    .addOnFailureListener { exception ->
                                                        Log.e("PanierLog", "Error updating existing document quantity", exception)
                                                        // Gérez l'échec si nécessaire
                                                    }
                                            }
                                        }
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    Log.e("PanierLog", "Error checking for existing document in Panier collection", exception)
                                    // Gérez l'échec si nécessaire
                                }
                        }
                    }
                    .addOnFailureListener { exception ->
                        // Gérez l'échec si nécessaire
                        Log.e("PanierLog", "Error getting document details", exception)
                    }
            }

        }


    }

    private fun showSnackbarRed(s: String) {
            val rootView = findViewById<View>(android.R.id.content)
            Snackbar.make(rootView, s, Snackbar.LENGTH_LONG)
                .setTextColor(ContextCompat.getColor(this, R.color.white)) // Couleur du texte en rouge
                .setDuration(Snackbar.LENGTH_LONG)
                .show()
    }

    private fun updateUI(imageUrl: String?, description: String?, prix: String?) {
        Log.d("docuemntId1","imhere with")
        val imageView: ImageView = findViewById(R.id.imagePrincipale)
        if (imageUrl != null && imageUrl.isNotEmpty()) {
            Picasso.get().load(imageUrl).into(imageView)
        }
        val descriptionTextView: TextView = findViewById(R.id.description)
        descriptionTextView.text = description
        val prixTextView: TextView = findViewById(R.id.prix)
        prixTextView.text = prix
    }
}
