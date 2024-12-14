package ma.ensaf.welcomeapp

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class Inscription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)
        val auth: FirebaseAuth

        auth= FirebaseAuth.getInstance()
        val compte : TextView = findViewById(R.id.textView13)
        val btn_inscrire:Button=findViewById(R.id.btn_login)
        val email: EditText =findViewById(R.id.email)
        val pwd: EditText =findViewById(R.id.pwd)
        val pwd2: EditText =findViewById(R.id.pwd2)
        compte.setOnClickListener({
            val intent =  Intent(this,PageLogin::class.java)
            startActivity(intent)
        })
        btn_inscrire.setOnClickListener {
            val emailInput: String = email.text.toString()
            val pwdInput: String = pwd.text.toString()
            val pwd2Input: String = pwd2.text.toString()

            if (emailInput.isEmpty() || pwdInput.isEmpty() || pwd2Input.isEmpty()) {
                Toast.makeText(this, "Remplir tous les champs", Toast.LENGTH_LONG).show()
            } else if (pwdInput != pwd2Input) {
                Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_LONG).show()
            } else {
                // Les champs sont tous remplis et les mots de passe correspondent
                auth.createUserWithEmailAndPassword(emailInput, pwdInput)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(ContentValues.TAG, "createUserWithEmailAndPassword:success")
                            Toast.makeText(baseContext, "Création avec succès", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, PageLogin::class.java)
                            startActivity(intent)
                        } else {
                            Log.w(ContentValues.TAG, "createUserWithEmailAndPassword:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Création éronée: " + task.exception?.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }


    }
}