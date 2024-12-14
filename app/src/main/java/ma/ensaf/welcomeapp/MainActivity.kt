package ma.ensaf.welcomeapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//         val langues = listOf("Choisir une Langue","Français", "Anglais")
//        val adapter = CustomAdapter(this, langues)
//
//
//        val spinner = findViewById<Spinner>(R.id.spinner_language)
//        spinner.adapter = adapter
//
//        val btn_suivant : Button = findViewById(R.id.btn_suivant)
//
//        btn_suivant.setOnClickListener({
//            val intent =  Intent(this,PageLogin::class.java)
//            startActivity(intent)
//        })
//
//    }
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_page_login)
    val auth: FirebaseAuth
    auth= FirebaseAuth.getInstance()
    val motPasseOUblier: TextView =findViewById(R.id.sinscrire)
    val btn_suivant : Button = findViewById(R.id.btn_login)
    val email: EditText =findViewById(R.id.email)
    val password: EditText =findViewById(R.id.pwd)
    motPasseOUblier.setOnClickListener({
        val intent =  Intent(this,PageMotPasseOuvlier::class.java)
        startActivity(intent)
    })
    val text_suivant : TextView = findViewById(R.id.textView11)

    text_suivant.setOnClickListener({
        val intent =  Intent(this,Inscription::class.java)
        startActivity(intent)
    })
    btn_suivant.setOnClickListener {
        if(email.equals("") || password.equals(""))
        {
            Toast.makeText(this,"Remplir tous les champs", Toast.LENGTH_LONG).show()
        }

        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) {task ->

                if (task.isSuccessful) {

                    Log.d(TAG, "createUserWithEmailAndPassword:success")
                    Toast.makeText(
                        baseContext,
                        "vous etes authentifié: ",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, PageCustems::class.java)
                    intent.putExtra("EMAIL", email.text.toString())
                    Log.d(TAG, "email_1: ${email.text.toString()}")
                    startActivity(intent)
                } else {

                    Log.w(TAG, "createUserWithEmailAndPassword:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "essayer avec un autre compte: " + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }

}
}