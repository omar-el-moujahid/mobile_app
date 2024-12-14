package ma.ensaf.welcomeapp

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomAdapter(context: Context, items: List<String>) :
    ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val textView = view.findViewById<TextView>(android.R.id.text1)

        textView.setTextColor(Color.WHITE)
        textView.textSize = 17f
        textView.typeface = Typeface.DEFAULT

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val textView = view.findViewById<TextView>(android.R.id.text1)

        // Modifier la couleur, la taille et la police du texte dans la liste déroulante
        textView.setTextColor(Color.BLACK) // Couleur du texte
        textView.textSize = 17f // Taille du texte en SP
        textView.typeface = Typeface.DEFAULT // Police du texte

        return view
    }
}
