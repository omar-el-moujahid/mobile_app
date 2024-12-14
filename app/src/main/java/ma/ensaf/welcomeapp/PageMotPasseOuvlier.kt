package ma.ensaf.welcomeapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PageMotPasseOuvlier : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_mot_passe_ouvlier)
        val btn:Button=findViewById(R.id.btn_login)
        val email:EditText=findViewById(R.id.email)
        val auth: FirebaseAuth
        auth= FirebaseAuth.getInstance()
        btn.setOnClickListener {
            val emailInput: String = email.text.toString()

            if (emailInput.isEmpty()) {
                Toast.makeText(this, "Entrez votre adresse e-mail", Toast.LENGTH_SHORT).show()
            } else {
                auth.sendPasswordResetEmail(emailInput)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "E-mail de réinitialisation envoyé", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(
                                this,
                                "Erreur lors de l'envoi de l'e-mail: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

    }
}