package ma.ensaf.welcomeapp


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*
class MainActivity2 : AppCompatActivity() {

    private lateinit var storage: FirebaseStorage
    private lateinit var firestore: FirebaseFirestore

    private var selectedImageUri: String? = null
    private lateinit var Description: EditText
    private lateinit var tissus: EditText
    private lateinit var couleur: EditText
    private lateinit var taille: EditText
    private lateinit var genre: EditText
    private lateinit var price: EditText


    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->

        if (uri != null) {
            selectedImageUri = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        Description=findViewById(R.id.description);
        tissus=findViewById(R.id.Tissus)
        couleur=findViewById(R.id.couleur)
        taille=findViewById(R.id.Taille)
        genre=findViewById(R.id.genre)
        price=findViewById(R.id.priceEditText)



        storage = FirebaseStorage.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val selectImageButton: Button = findViewById(R.id.selectImageButton)
        selectImageButton.setOnClickListener {
            // Open the image picker
            getContent.launch("image/*")
        }

        val uploadButton: Button = findViewById(R.id.uploadButton)
        uploadButton.setOnClickListener {
            // Upload the selected image to Firebase Storage
            if (!selectedImageUri.isNullOrEmpty()) {
                uploadImage(selectedImageUri!!)
            } else {
                // Handle the case when no image is selected
            }
        }
    }

    private fun uploadImage(imageUri: String) {
        val storageRef = storage.reference.child("photos/${UUID.randomUUID()}.jpg")
        val uploadTask = storageRef.putFile(imageUri.toUri())
        val descriptionText = Description.text.toString()
        val tissusText = tissus.text.toString()
        val couleurText = couleur.text.toString()
        val tailleText = taille.text.toString()
        val genreText = genre.text.toString()
        val priceText = price.text.toString().toDoubleOrNull() ?: 0.0

        uploadTask.addOnSuccessListener {
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                addImageDetails(uri.toString(), descriptionText, priceText,tailleText,genreText,tissusText)
            }.addOnFailureListener { e ->
                Log.e("MainActivity", "Error getting download URL: ${e.message}")
            }
        }.addOnFailureListener { e ->
            // Handle unsuccessful uploads
            Log.e("MainActivity", "Error uploading image: ${e.message}")
        }
    }

    private fun addImageDetails(imageUrl: String, descriptionText: String,priceText:Double,tailleText:String,genreText:String,tissusText:String) {
        val caftan = hashMapOf(
            "imageUrl" to imageUrl,
            "description" to descriptionText,
            "price" to priceText,
            "Taille" to tailleText,
            "genre" to genreText,
            "Tissus" to tissusText
        )

        firestore.collection("Babouche")
            .add(caftan)
            .addOnSuccessListener { documentReference ->
                Log.d("MainActivity", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.e("MainActivity", "Error adding document: ${e.message}")
            }
    }
}