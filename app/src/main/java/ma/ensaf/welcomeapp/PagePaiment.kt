package ma.ensaf.welcomeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import android.util.Log
import android.content.Intent
import java.util.HashMap
import com.paypal.android.sdk.payments.*
import java.math.BigDecimal

class PagePaiment : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private lateinit var ville: String
    private val paypalConfig = PayPalConfiguration()
        .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX) // Utilisez ENVIRONMENT_PRODUCTION pour la production
        .clientId("AS75Z3BNsUiIKbl04JPEkGXOca0esrSDvDf83yGZaZR7y8p6EQTE8nv5OaEER-1SAGnCMerssWl43RQV")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_paiment)
        val email = intent.getStringExtra("EMAIL")
        val price = intent.getStringExtra("price")
        Log.d("prix",price.toString())
        val mesachats: ArrayList<String>? = intent.getStringArrayListExtra("mesachats")
        val villes = listOf(
            "   Choisir votre ville",
            "Afghanistan", "Afrique du Sud", "Albanie", "Algérie", "Allemagne", "Andorre", "Angola", "Antigua-et-Barbuda",
            "Arabie Saoudite", "Argentine", "Arménie","Rouen","Fes","Meknes","Casa"
        )
        val villesAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, villes)
        villesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinnerVilles = findViewById<Spinner>(R.id.spinner_ville)
        spinnerVilles.adapter = villesAdapter
        spinnerVilles.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ville = parent?.getItemAtPosition(position).toString()
                Log.d("ville", ville) // Afficher la ville sélectionnée dans les logs
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                ville = "Fes"
            }

        }

        val conserverButton = findViewById<Button>(R.id.conserver)
        val textView7 = findViewById<TextView>(R.id.textView7)
        textView7.visibility=View.GONE
        conserverButton.setOnClickListener {
            val nom = findViewById<EditText>(R.id.nom).text.toString()
            val prenom = findViewById<EditText>(R.id.prénom).text.toString()
            val telephone = findViewById<EditText>(R.id.tel).text.toString()
            val codePostal = findViewById<EditText>(R.id.codepostal).text.toString()
            val adresse = findViewById<EditText>(R.id.adresse).text.toString()

            if (nom.isNotBlank() && prenom.isNotBlank() && telephone.isNotBlank() &&
                codePostal.isNotBlank() && adresse.isNotBlank()  && isPhoneNumberValid(telephone) &&  ville != "   Choisir votre ville")  {

                textView7.visibility = View.INVISIBLE // Rendre le TextView invisible si les champs sont remplis

                // Préparation des données pour Firebase
                val data = HashMap<String, Any>()
                data["nom"] = nom
                data["prenom"] = prenom
                data["mesachats"] = mesachats.toString()
                data["telephone"] = telephone
                data["codePostal"] = codePostal
                data["adresse"] = adresse
                data["email"] = email.toString()
                data["ville"] = ville
                data["date"] = Calendar.getInstance().time // Ajouter la date actuelle

                // Envoi des données à la collection "Achat" dans Firestore
                db.collection("Achat")
                    .add(data)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this, "Informations envoyées avec succès!", Toast.LENGTH_SHORT).show()
                        onPayButtonClick()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Erreur lors de l'envoi des informations: $e", Toast.LENGTH_SHORT).show()
                    }
            } else {
                        // Vérifier manuellement si les chaînes sont vides ou nulles
                        val anyFieldEmpty = nom.isEmpty() || prenom.isEmpty() || telephone.isEmpty() ||
                                codePostal.isEmpty() || adresse.isEmpty() ||
                                !isPhoneNumberValid(telephone)|| (ville == "   Choisir votre ville")

                        if (anyFieldEmpty) {
                            textView7.visibility = View.VISIBLE // Rendre le TextView visible si un champ est vide ou les données ne sont pas valides
                        }
                else{
                            textView7.visibility = View.GONE
                        }
                    }
            }

        val intent = Intent(this, PayPalService::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig)
        startService(intent)










        }


    // Vérification du numéro de téléphone
    private fun isPhoneNumberValid(phoneNumber: String): Boolean {
        // Ajoutez votre logique de validation de numéro de téléphone ici
        // Par exemple, utilisez une expression régulière ou une bibliothèque de validation de numéro de téléphone
        return android.util.Patterns.PHONE.matcher(phoneNumber).matches()
    }
    private fun onPayButtonClick() {
        val price = intent.getStringExtra("price")
        val pricfinal = price?.replace(" Dh", "")?.replace(",", ".")
        Log.d("prix",pricfinal+" im2")
        val payment = PayPalPayment(
            BigDecimal(pricfinal), "USD", "Prix total",
            PayPalPayment.PAYMENT_INTENT_SALE
        )

        val intentPaypal = Intent(this, PaymentActivity::class.java)
        intentPaypal.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig)
        intentPaypal.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)

        startActivityForResult(intentPaypal,123)
       }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                val confirmation: PaymentConfirmation? = data?.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION)
                if (confirmation != null) {
                    val paymentDetails = confirmation.toJSONObject().toString(4)
                    // Handle payment details, e.g., send to your server for further processing
                    Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()



                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Payment Cancelled", Toast.LENGTH_SHORT).show()
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Toast.makeText(this, "Invalid Payment", Toast.LENGTH_SHORT).show()
            }
       }
   }
}
