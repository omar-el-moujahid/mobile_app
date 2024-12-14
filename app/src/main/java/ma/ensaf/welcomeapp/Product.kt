package ma.ensaf.welcomeapp
data class Product(
    val Taille: String = "",
    val Tissus: String = "",
    val couleur: String = "",
    val description: String = "",
    val genre: String = "",
    val imageUrl: String = "",
    val price: Double = 0.0 // Utilisez Double pour les prix décimaux ou Int pour les prix entiers


)
