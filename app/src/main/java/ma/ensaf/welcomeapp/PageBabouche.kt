package ma.ensaf.welcomeapp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import java.util.*
class PageBabouche : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
//    private lateinit var viewPager: ViewPager2
//    private lateinit var viewPager1: ViewPager2
//    private lateinit var viewPager2: ViewPager2
//    private lateinit var viewPager3: ViewPager2
//    private lateinit var viewPagerAdapter: ViewPagerAdapter
//    private lateinit var dots: Array<ImageView?>
//    private lateinit var dots1: Array<ImageView?>
//    private lateinit var dots2: Array<ImageView?>
//    private lateinit var dots3: Array<ImageView?>
//    private lateinit var toggle: ActionBarDrawerToggle
//
//    private lateinit var timer: Timer
//    private var dotscount: Int = 0
//    private var dotscount1: Int = 0
//    private var dotscount2: Int = 0
//    private var dotscount3: Int = 0
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_page_babouche)
//        viewPager = findViewById(R.id.ViewPager)
//        viewPager1 = findViewById(R.id.ViewPager1)
//        viewPager2 = findViewById(R.id.ViewPager2)
//        viewPager3 = findViewById(R.id.ViewPager3)
//        val email = intent.getStringExtra("EMAIL")
//        val imageId = intent.getStringExtra("IMAGE_ID")
//        viewPagerAdapter = ViewPagerAdapter(this)
//        viewPager.adapter = viewPagerAdapter
//        val viewPagerAdapter1 = ViewPagerAdapter(this)
//        viewPager1.adapter = viewPagerAdapter1
//        val viewPagerAdapter2 = ViewPagerAdapter(this)
//        viewPager2.adapter = viewPagerAdapter2
//        val viewPagerAdapter3 = ViewPagerAdapter(this)
//        viewPager3.adapter = viewPagerAdapter3
//        val drawerlayout : DrawerLayout = findViewById(R.id.drawer_layout)
//        val navigationView : NavigationView = findViewById(R.id.nav_view)
//        val toolbar : androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
//        val favori: ImageView = findViewById(R.id.LoveImage)
//        favori.setOnClickListener {
//            val intent = Intent(this, PageFavoris::class.java)
//            intent.putExtra("EMAIL", email)
//            startActivity(intent)
//        }
//        toggle = ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        drawerlayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        navigationView.setNavigationItemSelectedListener(this)
//
//        navigationView.bringToFront()
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        toolbar.setNavigationOnClickListener {
//            if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
//                drawerlayout.closeDrawer(GravityCompat.START)
//            } else {
//                drawerlayout.openDrawer(GravityCompat.START)
//            }
//        }
//
//        val imageIds = arrayOf(
//           "APfIvXzrnGeRoOp4n823u","AKBQO1vEVuG6zd7bDCSa9",
//            "AsOIrsI188Re2eph6w3bN","AEn6QVxvhfvE387EJydPP","AKtaUda6mSojn2GAj52aq",
//           "AEOx32dxt02oRTzXDft6o","AYVf5VsSrCmEC0yh7BHwa","AdqG7HwnjeLhDYpdDuHoc",
//            "A3qNf9Ee3xwRg2djnhw6L","AuqwLw7q9sdHhhl2KZHAu","AkO8mLCA2REmagUkEhQk8",
//            "AsGPe8poE6zrd8Dzeh55C","AkDbVI81gaD1KjeGKXEey","AUiKU9gZC4QrHGOf1ba8N",
//        )
//
//        val db = FirebaseFirestore.getInstance()
//
//        for ((index, imageId) in imageIds.withIndex()) {
//            val firstChar = imageId.substring(1)
//            val docRef = db.collection("Babouche").document(firstChar)
//
//            docRef.get().addOnSuccessListener { documentSnapshot ->
//                if (documentSnapshot.exists()) {
//                    val imageUrl = documentSnapshot.getString("imageUrl")
//                    val imageViewId = resources.getIdentifier(imageId, "id", packageName)
//                    val imageView: ImageView = findViewById(imageViewId)
//                    Picasso.get().load(imageUrl).into(imageView)
//                } else {
//                    // Le document n'existe pas
//                }
//            }
//        }
//        val filterIcon: ImageView = findViewById(R.id.filterid)
//
//        filterIcon.setOnClickListener {
//                val view4 = layoutInflater.inflate(R.layout.filter_menu_layout, null)
//                val layoutTaille = view4.findViewById<LinearLayout>(R.id.tissuLayout)
//               layoutTaille.visibility= View.GONE
//            val bottomSheetDialog = BottomSheetDialog(this)
//            var taille:String="Tous"
//            var couleur:String ="Tous"
//            var sexe:String="Tous"
//            var prix:Double=151.00
//            val view = layoutInflater.inflate(R.layout.filter_menu_layout, null)
//            //COLOR
//            val spinner = view.findViewById<Spinner>(R.id.spinner_couleurs)
//            val adapter = ArrayAdapter.createFromResource(this, R.array.spinner_items, android.R.layout.simple_spinner_item)
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner.adapter = adapter
//            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                    val selectedItem = resources.getStringArray(R.array.spinner_items)[position]
//                    Log.d("NoNO", "Selected item: ${selectedItem.toString()}")
//                    couleur =selectedItem.toString()
//                }
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    Log.d("NoNO", "NoNi")
//                    couleur ="Tous"
//                }
//            }
//
//            //FILTRER  sexe
//            val checkBoxFemme = view.findViewById<CheckBox>(R.id.checkBox)
//            val checkBoxHomme = view.findViewById<CheckBox>(R.id.checkBox3)
//            checkBoxFemme.setOnCheckedChangeListener { _, isChecked ->
//                val hommeChecked = checkBoxHomme.isChecked
//
//                if (isChecked && hommeChecked) {
//                    sexe = "Tous"
//                } else if (isChecked && !hommeChecked) {
//                    sexe = "Femme"
//                } else if (!isChecked && hommeChecked) {
//                    sexe = "Homme"
//                } else {
//                    sexe = "Tous"
//                }
//            }
//
//            checkBoxHomme.setOnCheckedChangeListener { _, isChecked ->
//                val femmeChecked = checkBoxFemme.isChecked
//
//                if (isChecked && femmeChecked) {
//                    sexe = "Tous"
//                } else if (isChecked && !femmeChecked) {
//                    sexe = "Homme"
//                } else if (!isChecked && femmeChecked) {
//                    sexe = "Femme"
//                } else {
//                    sexe = "Tous"
//                }
//            }
//            //taille
//            val spinner_taille= view.findViewById<Spinner>(R.id.spinner_couleurs2)
//            val adapter_taille = ArrayAdapter.createFromResource(this, R.array.spinner_pointure, android.R.layout.simple_spinner_item)
//            adapter_taille.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner_taille.adapter = adapter_taille
//            spinner_taille.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                    val selectedItem = resources.getStringArray(R.array.spinner_pointure)[position]
//                    taille=selectedItem.toString()
//                }
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    taille="Tous"
//
//                }
//            }
//
//
//
//            //FILTRER LE PRIX
//            val textView17: TextView = view.findViewById(R.id.textView17)
//            val textView18: TextView = view.findViewById(R.id.textView18)
//            val db = FirebaseFirestore.getInstance()
//            val jellabaCollectionRef = db.collection("Jellaba")
//
//            var minPrice = Double.MAX_VALUE
//            var maxPrice = Double.MIN_VALUE
//
//            jellabaCollectionRef.get()
//                .addOnSuccessListener { documents ->
//                    for (document in documents) {
//                        val price = document.getDouble("price")
//                        if (price != null) {
//                            if (price < minPrice) {
//                                minPrice = price
//                            }
//                            if (price > maxPrice) {
//                                maxPrice = price
//                            }
//                        }
//                    }
//
//                    textView17.text = "Prix Min: $minPrice"
//                    textView18.text = "Prix Max: $maxPrice"
//
//                    val seekBar = view.findViewById<SeekBar>(R.id.seekBar)
//                    seekBar.min = (minPrice * 100).toInt()
//                    seekBar.max = (maxPrice * 100).toInt()
//                    seekBar.progress = seekBar.max // Positionner le SeekBar à la valeur maximale au départ
//
//                    seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//                        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                            val currentPrice = progress.toDouble() / 100
//
//                            if (currentPrice < minPrice) {
//                                if (seekBar != null) {
//                                    seekBar?.progress = seekBar.min
//                                    val currentPrice =minPrice
//                                    prix=currentPrice
//                                    textView18.text = "Prix Max: $currentPrice"
//                                    prix=currentPrice
//                                } // Empêcher de descendre en dessous de la valeur minimale
//                            } else {
//                                textView18.text = "Prix Max: $currentPrice"
//                                prix=currentPrice
//                            }
//                            Log.d("priix",prix.toString())
//                        }
//
//                        override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                            // Pas besoin d'implémenter quelque chose ici pour cette démonstration
//                        }
//
//                        override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                            // Pas besoin d'implémenter quelque chose ici pour cette démonstration
//
//                        }
//                    })
//                }
//                .addOnFailureListener { exception ->
//                    Log.d("TAG", "Erreur lors de la récupération des données : $exception")
//                }
//            Log.d("selectionne","ancienne"+taille)
//            val validetBtn: Button =view.findViewById(R.id.button2)
//            validetBtn.setOnClickListener({
//                Log.d("selectionne","prix"+prix.toDouble())
//                Log.d("selectionne","couleur"+couleur.toString())
//                Log.d("selectionne","sexe"+sexe.toString())
//                Log.d("selectionne","taille"+taille.toString())
//                UpdatePage(couleur,sexe,prix,taille)
//            })
//            val clearBtn: Button =view.findViewById(R.id.button)
//            clearBtn.setOnClickListener({
//                couleur="Tous"
//                sexe="Tous"
//                taille="Tous"
//                prix=151.00
//                //  UpdatePage(couleur,sexe,prix)
//                val intent =  Intent(this,PageJellaba::class.java)
//                intent.putExtra("EMAIL", email)
//                startActivity(intent)
//            })
//
//            bottomSheetDialog.setContentView(view)
//            bottomSheetDialog.show()
//        }
//
//        dotscount = viewPagerAdapter.itemCount
//        dotscount1 = viewPagerAdapter1.itemCount
//        dotscount2 = viewPagerAdapter2.itemCount
//        dotscount3 = viewPagerAdapter3.itemCount
//
//        dots = arrayOfNulls(dotscount)
//        dots1 = arrayOfNulls(dotscount1)
//        dots2 = arrayOfNulls(dotscount2)
//        dots3 = arrayOfNulls(dotscount3)
//
//        for (i in 0 until dotscount) {
//            dots[i] = ImageView(this)
//            dots[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
//
//            val params = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//            )
//
//            params.setMargins(8, 0, 8, 0)
//
//
//        }
//        viewPagerAdapter.setData(
//            arrayOf("xLjjqbuBWmc06bPAiMkO","3O7EpRuxcOS4di8jXy6g","971V4WNGvLEz86MXACJk"),
//            arrayOf("tous", "Gauche", "Droit"),
//            arrayOf("tous1", "Gauche2", "Droit3")
//        )
//        for (i in 0 until dotscount1) {
//            dots1[i] = ImageView(this)
//            dots1[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
//
//            val params = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//            )
//
//            params.setMargins(8, 0, 8, 0)
//
//
//        }
//        viewPagerAdapter1.setData(
//            arrayOf("R4zBrJyUn4bayFzrMlgy","971V4WNGvLEz86MXACJk"),
//            arrayOf("Title1", "Title2", "Title3"),
//            arrayOf("Description1", "Description2", "Description3")
//        )
//
//        for (i in 0 until dotscount2) {
//            dots2[i] = ImageView(this)
//            dots2[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
//
//            val params = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT)
//
//            params.setMargins(8, 0, 8, 0)
//        }
//        viewPagerAdapter2.setData(
//            arrayOf("IqUs9XNJ5BtjsyWfkAkO","KtaUda6mSojn2GAj52aq"),
//            arrayOf("tous", "Gauche", "Droit"),
//            arrayOf("tous1", "Gauche2", "Droit3")
//        )
//
//
//
//        for (i in 0 until dotscount3) {
//            dots3[i] = ImageView(this)
//            dots3[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
//
//            val params = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//            )
//
//            params.setMargins(8, 0, 8, 0)
//
//
//        }
//        viewPagerAdapter3.setData(
//            arrayOf("CE23LlkwGpC3zQN0GDGT","I8r7Qg9Z80W4HhEb7xeA"),
//            arrayOf("Title1", "Title2", "Title3"),
//            arrayOf("Description1", "Description2", "Description3")
//        )
//        dots[0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
//        dots1[0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
//        dots2[0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
//        dots3[0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                for (i in 0 until dotscount) {
//                    dots[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
//                }
//
//                dots[position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
//            }
//        })
//        viewPager1.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                for (i in 0 until dotscount1) {
//                    dots1[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
//                }
//
//                dots1[position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
//            }
//        })
//        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                for (i in 0 until dotscount2) {
//                    dots2[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
//                }
//
//                dots2[position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
//            }
//        })
//        viewPager3.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                for (i in 0 until dotscount3) {
//                    dots3[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
//                }
//
//                dots3[position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
//            }
//        })
//        timer = Timer()
//        timer.scheduleAtFixedRate(MyTimerTask(viewPager, dotscount), 2000, 4000)
//        timer.scheduleAtFixedRate(MyTimerTask(viewPager1, dotscount1), 2000, 4000)
//        timer.scheduleAtFixedRate(MyTimerTask(viewPager2, dotscount2), 2000, 4000)
//        timer.scheduleAtFixedRate(MyTimerTask(viewPager3, dotscount3), 2000, 4000)
//
//        val textCafton: TextView = findViewById(R.id.textCafton)
//        val textSac: TextView = findViewById(R.id.textAccessoires)
//        val textBabouches: TextView = findViewById(R.id.textBabouches)
//        val textDejallaba: TextView = findViewById(R.id.textDjellaba)
//        // Sous-lignez le texte Cafton par défaut
//        textBabouches.paintFlags = textCafton.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//        textCafton.setOnClickListener {
//            val intent =  Intent(this,PageCaftan::class.java)
//            intent.putExtra("EMAIL", email)
//            startActivity(intent)
//            textCafton.paintFlags = textCafton.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//            textBabouches.paintFlags = textBabouches.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textDejallaba.paintFlags = textDejallaba.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textSac.paintFlags = textSac.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//        }
//        textBabouches.setOnClickListener {
//            textBabouches.paintFlags = textBabouches.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//            textCafton.paintFlags = textCafton.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textDejallaba.paintFlags = textDejallaba.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textSac.paintFlags = textSac.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            val intent =  Intent(this,PageBabouche::class.java)
//            intent.putExtra("EMAIL", email)
//            startActivity(intent)
//        }
//
//        textDejallaba.setOnClickListener {
//            textDejallaba.paintFlags = textDejallaba.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//            textCafton.paintFlags = textCafton.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textBabouches.paintFlags = textBabouches.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textSac.paintFlags = textSac.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            val intent =  Intent(this,PageJellaba::class.java)
//            intent.putExtra("EMAIL", email)
//            startActivity(intent)
//        }
//        textSac.setOnClickListener {
//            textSac.paintFlags = textSac.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//            textCafton.paintFlags = textCafton.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textBabouches.paintFlags = textBabouches.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textDejallaba.paintFlags = textDejallaba.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            val intent =  Intent(this,PageAccessoires::class.java)
//            intent.putExtra("EMAIL", email)
//            startActivity(intent)
//        }
//        findViewById<ImageView>(R.id.APfIvXzrnGeRoOp4n823u).setOnClickListener { onImageClick(imageIds[0],email!!) }
//        findViewById<ImageView>(R.id.AKBQO1vEVuG6zd7bDCSa9).setOnClickListener { onImageClick(imageIds[1],email!!) }
//        findViewById<ImageView>(R.id.AsOIrsI188Re2eph6w3bN).setOnClickListener { onImageClick(imageIds[2],email!!) }
//        findViewById<ImageView>(R.id.AEn6QVxvhfvE387EJydPP).setOnClickListener { onImageClick(imageIds[3],email!!) }
//        findViewById<ImageView>(R.id.AKtaUda6mSojn2GAj52aq).setOnClickListener { onImageClick(imageIds[4],email!!) }
//        findViewById<ImageView>(R.id.AEOx32dxt02oRTzXDft6o).setOnClickListener { onImageClick(imageIds[5],email!!) }
//        findViewById<ImageView>(R.id.AYVf5VsSrCmEC0yh7BHwa).setOnClickListener { onImageClick(imageIds[6],email!!) }
//        findViewById<ImageView>(R.id.AdqG7HwnjeLhDYpdDuHoc).setOnClickListener { onImageClick(imageIds[7],email!!) }
//        findViewById<ImageView>(R.id.A3qNf9Ee3xwRg2djnhw6L).setOnClickListener { onImageClick(imageIds[8],email!!) }
//        findViewById<ImageView>(R.id.AuqwLw7q9sdHhhl2KZHAu).setOnClickListener { onImageClick(imageIds[9],email!!) }
//        findViewById<ImageView>(R.id.AkO8mLCA2REmagUkEhQk8).setOnClickListener { onImageClick(imageIds[10],email!!) }
//        findViewById<ImageView>(R.id.AsGPe8poE6zrd8Dzeh55C).setOnClickListener { onImageClick(imageIds[11],email!!) }
//        findViewById<ImageView>(R.id.AkDbVI81gaD1KjeGKXEey).setOnClickListener { onImageClick(imageIds[12],email!!) }
//        findViewById<ImageView>(R.id.AUiKU9gZC4QrHGOf1ba8N).setOnClickListener { onImageClick(imageIds[13],email!!) }
//
//
//
//        val searchEditText = findViewById<EditText>(R.id.searchEditText)
//        val researchIcon2 : ImageView = findViewById(R.id.searchIcon2)
//        researchIcon2.setOnClickListener({
//            val intent = Intent(this@PageBabouche, PageRecherche::class.java)
//            intent.putExtra("searchText", searchEditText.text.toString())
//            intent.putExtra("EMAIL", email)
//            startActivity(intent)
//        })
//
//
//    }
//    private fun onImageClick(imageId:String,email:String) {
//        val intent = Intent(this, PageAchat::class.java)
//        intent.putExtra("IMAGE_ID", imageId)
//        intent.putExtra("condition", "true")
//        intent.putExtra("EMAIL", email)
//        startActivity(intent)
//    }
//    class MyTimerTask(private val viewPager: ViewPager2, private val dotscount: Int) : TimerTask() {
//        override fun run() {
//            viewPager.post {
//                val currentItem = viewPager.currentItem
//                viewPager.setCurrentItem((currentItem + 1) % dotscount, true)
//            }
//        }
//    }
//
//    private fun UpdatePage(
//        couleur: String,
//        sexe: String,
//        prix: Double,
//        taille: String
//    ) {
//        try {
//            val firestore = FirebaseFirestore.getInstance()
//            var query: Query = firestore.collection("caftan")
//            // Vérification pour les champs "Tous" et prix égal à 0.0
//            if (couleur != "Tous") {
//                query = query.whereEqualTo("couleur", couleur)
//            }
//            if (sexe != "Tous") {
//
//                query = query.whereEqualTo("genre", sexe)
//            }
//            if (prix > 0.0) {
//                query = query.whereLessThan("price", prix)
//            }
//            query.get()
//                .addOnSuccessListener { documents ->
//                    val foundIds = mutableListOf<String>()
//                    for (document in documents) {
//                        if(taille!="Tous"){
//                            val taillesDocument = document.getString("Taille") // Remplacez "tailles" par le nom du champ correspondant dans votre base de données
//                            if (taillesDocument != null && verifierTaille(taillesDocument, taille)) {
//                                val documentId = document.id
//                                foundIds.add(documentId)
//                                Log.d("selectionne"," not Tous ici")
//                            }
//                        }else{
//                            val documentId = document.id
//                            foundIds.add(documentId)
//                            Log.d("selectionne","Tous ici")
//                        }
//                    }
//                    val intent = Intent(this, PageFilterd::class.java)
//                    intent.putStringArrayListExtra("found_ids", ArrayList(foundIds))
//                    val email = intent.getStringExtra("EMAIL")
//                    intent.putExtra("EMAIL", email)
//                    intent.putExtra("page", "babouche")
//                    startActivity(intent)
//                }
//                .addOnFailureListener { exception ->
//                    // Gérer les erreurs éventuelles lors de la récupération des données depuis Firebase
//                    exception.printStackTrace()
//                    Log.e("UpdatePage", "Exception: ${exception.message}") // Log l'exception
//
//                    Toast.makeText(this@PageBabouche, exception.message, Toast.LENGTH_SHORT).show()
//                }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            // Gérer les erreurs éventuelles lors de l'exécution de la fonction UpdatePage
//            // Afficher un message d'erreur à l'utilisateur si nécessaire
//            Toast.makeText(this@PageBabouche, e.message, Toast.LENGTH_SHORT).show()
//        }
//    }
//    fun verifierTaille(tailles: String, tailleAVerifier: String): Boolean {
//        val taillesListe = tailles.split("-")
//        return tailleAVerifier in taillesListe
//    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        val email = intent.getStringExtra("EMAIL") ?: "" // Récupérer l'email de l'intent
//        Log.d("emaill",email.toString())
//        when (item.itemId) {
//            R.id.caftan -> {
//                val intent = Intent(this,PageCaftan::class.java)
//                intent.putExtra("EMAIL", email)
//                startActivity(intent)
//            }
//            R.id.babouche -> {
//                val intent = Intent(this,PageBabouche::class.java)
//                intent.putExtra("EMAIL", email)
//                startActivity(intent)
//            }
//            R.id.djellaba -> {
//                val intent = Intent(this,PageJellaba::class.java)
//                intent.putExtra("EMAIL", email)
//                startActivity(intent)
//            }
//            R.id.artisanat -> {
//                val intent = Intent(this,PageAccessoires::class.java)
//                intent.putExtra("EMAIL", email)
//                startActivity(intent)
//            }
//        }
//        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
//        drawer.closeDrawer(GravityCompat.START)
//        return false
//    }
//}

    private lateinit var textSac: TextView
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPager1: ViewPager2
    private lateinit var viewPager2: ViewPager2
    private lateinit var viewPager3: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var dots: Array<ImageView?>
    private lateinit var dots1: Array<ImageView?>
    private lateinit var dots2: Array<ImageView?>
    private lateinit var dots3: Array<ImageView?>
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var timer: Timer
    private var dotscount: Int = 0
    private var dotscount1: Int = 0
    private var dotscount2: Int = 0
    private var dotscount3: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager = findViewById(R.id.ViewPager)
        viewPager1 = findViewById(R.id.ViewPager1)
        viewPager2 = findViewById(R.id.ViewPager2)
        viewPager3 = findViewById(R.id.ViewPager3)
        val email = intent.getStringExtra("EMAIL")
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter
        val viewPagerAdapter1 = ViewPagerAdapter(this)
        viewPager1.adapter = viewPagerAdapter1
        val viewPagerAdapter2 = ViewPagerAdapter(this)
        viewPager2.adapter = viewPagerAdapter2
        val viewPagerAdapter3 = ViewPagerAdapter(this)
        viewPager3.adapter = viewPagerAdapter3
        val drawerlayout : DrawerLayout = findViewById(R.id.drawer_layout)
        val navigationView : NavigationView = findViewById(R.id.nav_view)
//        val toolbar : androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)

//        toggle = ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        drawerlayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        navigationView.setNavigationItemSelectedListener(this)
//
//        navigationView.bringToFront()
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        toolbar.setNavigationOnClickListener {
//            if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
//                drawerlayout.closeDrawer(GravityCompat.START)
//            } else {
//                drawerlayout.openDrawer(GravityCompat.START)
//            }
//        }
//
//        toggle = ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        drawerlayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        navigationView.setNavigationItemSelectedListener(this)
//
//        navigationView.bringToFront()
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        toolbar.setNavigationOnClickListener {
//            if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
//                drawerlayout.closeDrawer(GravityCompat.START)
//            } else {
//                drawerlayout.openDrawer(GravityCompat.START)
//            }
//        }
        var imageIds = arrayOf(
            "AX4fyqj818siT8GS9SxWw","AD4I83E7IQs7yweEG4fHN","AeSNyx6DDC7RtG7NlMPkn","Adhwy1nHx3r0F1GsDgvb5",
            "A5UEW9pCkBxdwT6mxwZDk","AXJfoTjoUQFCoXeJdPmvI","AO86KiDotx6tQp2JIlBha","AUxyMzTUo7VS3czFz0b2g","ACEeR1Jq1L70AFqMSgPYL"
            , "AsKO9kqEbYpKTGc5HWHGJ","AOvbK20lQ6lg3lderN9F1","AIFXi7HROfXDfjtteb8XW","ALHOzqGPgcyfMcxIGhWQU","AV2DfNKtrGc2jad9NwVfs",
        )
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val researchIcon2 : ImageView = findViewById(R.id.searchIcon2)
        researchIcon2.setOnClickListener({
            val intent = Intent(this@PageBabouche, PageRecherche::class.java)
            intent.putExtra("searchText", searchEditText.text.toString())
            intent.putExtra("EMAIL", email)
            startActivity(intent)
        })

//        val image1: ImageView = findViewById(R.id.AX4fyqj818siT8GS9SxWw)
        val image2: ImageView = findViewById(R.id.AD4I83E7IQs7yweEG4fHN)
        val image3: ImageView = findViewById(R.id.AeSNyx6DDC7RtG7NlMPkn)
        val image4: ImageView = findViewById(R.id.Adhwy1nHx3r0F1GsDgvb5)
        val image5: ImageView = findViewById(R.id.A5UEW9pCkBxdwT6mxwZDk)
        val image6: ImageView = findViewById(R.id.AXJfoTjoUQFCoXeJdPmvI)
        val image7: ImageView = findViewById(R.id.AO86KiDotx6tQp2JIlBha)
        val image8: ImageView = findViewById(R.id.AUxyMzTUo7VS3czFz0b2g)
        val image9: ImageView = findViewById(R.id.ACEeR1Jq1L70AFqMSgPYL)
        val image10: ImageView = findViewById(R.id.AsKO9kqEbYpKTGc5HWHGJ)
        val image11: ImageView = findViewById(R.id.AOvbK20lQ6lg3lderN9F1)
        val image12: ImageView = findViewById(R.id.AIFXi7HROfXDfjtteb8XW)
        val image13: ImageView = findViewById(R.id.ALHOzqGPgcyfMcxIGhWQU)
        val image14: ImageView = findViewById(R.id.AV2DfNKtrGc2jad9NwVfs)

//        val favori: ImageView = findViewById(R.id.LoveImage)
//        favori.setOnClickListener {
//            val intent = Intent(this, PageFavoris::class.java)
//            intent.putExtra("EMAIL", email)
//            startActivity(intent)
//        }
//
//        image1.setOnClickListener {
//            onImageClick("AX4fyqj818siT8GS9SxWw",email!!)
//        }
//        image2.setOnClickListener {
//            onImageClick("AD4I83E7IQs7yweEG4fHN",email!!)
//        }
//        image3.setOnClickListener {
//            onImageClick("AeSNyx6DDC7RtG7NlMPkn",email!!)
//        }
//        image4.setOnClickListener {
//            onImageClick("Adhwy1nHx3r0F1GsDgvb5",email!!)
//        }
//        image5.setOnClickListener {
//            onImageClick("A5UEW9pCkBxdwT6mxwZDk",email!!)
//        }
//        image6.setOnClickListener {
//            onImageClick("AXJfoTjoUQFCoXeJdPmvI",email!!)
//        }
//        image7.setOnClickListener {
//            onImageClick("AO86KiDotx6tQp2JIlBha",email!!)
//        }
//        image8.setOnClickListener {
//            onImageClick("AUxyMzTUo7VS3czFz0b2g",email!!)
//        }
//        image9.setOnClickListener {
//            onImageClick("ACEeR1Jq1L70AFqMSgPYL",email!!)
//        }
//        image10.setOnClickListener {
//            onImageClick("AsKO9kqEbYpKTGc5HWHGJ",email!!)
//        }
//        image11.setOnClickListener {
//            onImageClick("AOvbK20lQ6lg3lderN9F1",email!!)
//        }
//        image12.setOnClickListener {
//            onImageClick("AIFXi7HROfXDfjtteb8XW",email!!)
//        }
//        image13.setOnClickListener {
//            onImageClick("ALHOzqGPgcyfMcxIGhWQU",email!!)
//        }
//        image14.setOnClickListener {
//            onImageClick("AV2DfNKtrGc2jad9NwVfs",email!!)
//        }

        val db = FirebaseFirestore.getInstance()
        for ((index, imageId) in imageIds.withIndex()) {
            val firstChar = imageId.substring(1)
            val docRef = db.collection("accessoires").document(firstChar)

            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val imageUrl = documentSnapshot.getString("imageUrl")
                    val imageViewId = resources.getIdentifier(imageId, "id", packageName)
                    val imageView: ImageView = findViewById(imageViewId)
                    Picasso.get().load(imageUrl).into(imageView)
                } else {
                    // Le document n'existe pas
                }
            }
        }
        dotscount = viewPagerAdapter.itemCount
        dotscount1 = viewPagerAdapter1.itemCount
        dotscount2 = viewPagerAdapter2.itemCount
        dotscount3 = viewPagerAdapter3.itemCount

        dots = arrayOfNulls(dotscount)
        dots1 = arrayOfNulls(dotscount1)
        dots2 = arrayOfNulls(dotscount2)
        dots3 = arrayOfNulls(dotscount3)

        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            dots[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(8, 0, 8, 0)


        }
        viewPagerAdapter.setData(
            arrayOf("w8GZePDJTnyPtW6SyWTf", "Eq9u9tKtPhlRqmOWWVw2", "MIIUX6TSalv2jyTy85a5"),
            arrayOf("tous", "Gauche", "Droit"),
            arrayOf("tous1", "Gauche2", "Droit3")
        )
        for (i in 0 until dotscount1) {
            dots1[i] = ImageView(this)
            dots1[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(8, 0, 8, 0)


        }
        viewPagerAdapter1.setData(
            arrayOf("JdnoEcruQ2N2MplBOU7D", "msQ4gxhkDG0tbYOUMeIK", "LA9dZCecDZxVTdmJTFyi"),
            arrayOf("Title1", "Title2", "Title3"),
            arrayOf("Description1", "Description2", "Description3")
        )

        for (i in 0 until dotscount2) {
            dots2[i] = ImageView(this)
            dots2[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(8, 0, 8, 0)


        }
        viewPagerAdapter2.setData(
            arrayOf("w41KO4d0OCap522xHTqe", "FNPmlmz4gWXc63wvOMYH", "vXVdTOe1qE59DUDYdIwF"),
            arrayOf("tous", "Gauche", "Droit"),
            arrayOf("tous1", "Gauche2", "Droit3")
        )



        for (i in 0 until dotscount3) {
            dots3[i] = ImageView(this)
            dots3[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(8, 0, 8, 0)


        }
        viewPagerAdapter3.setData(
            arrayOf("x7IF4Ly9nzM5mkUfQJhF", "faHgnGLJyFM92S7qxamy", "p6yzQOtUfluswRqRjsSH"),
            arrayOf("Title1", "Title2", "Title3"),
            arrayOf("Description1", "Description2", "Description3")
        )
        dots[0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
        dots1[0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
        dots2[0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
        dots3[0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
                }

                dots[position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
            }
        })
        viewPager1.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount1) {
                    dots1[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
                }

                dots1[position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
            }
        })
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount2) {
                    dots2[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
                }

                dots2[position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
            }
        })
        viewPager3.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount3) {
                    dots3[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.nonactive_dot))
                }

                dots3[position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
            }
        })

//
//        val textCafton: TextView = findViewById(R.id.textCafton)
//        val textSac: TextView = findViewById(R.id.textAccessoires)
//        val textBabouches: TextView = findViewById(R.id.textBabouches)
//        val textDejallaba: TextView = findViewById(R.id.textDjellaba)
//        textSac.paintFlags = textCafton.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//        textSac.setOnClickListener {
//            textSac.paintFlags = textSac.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//            textCafton.paintFlags = textCafton.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//            textBabouches.paintFlags = textBabouches.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textDejallaba.paintFlags = textDejallaba.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textSac.paintFlags = textSac.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//        }
//        textBabouches.setOnClickListener {
//            textBabouches.paintFlags = textBabouches.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//            textCafton.paintFlags = textCafton.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textDejallaba.paintFlags = textDejallaba.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textSac.paintFlags = textSac.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            val intent =  Intent(this,PageBabouche::class.java)
//            intent.putExtra("EMAIL", email)
//            startActivity(intent)
//        }
//
//        textDejallaba.setOnClickListener {
//            textDejallaba.paintFlags = textDejallaba.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//            textCafton.paintFlags = textCafton.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textBabouches.paintFlags = textBabouches.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textSac.paintFlags = textSac.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            val intent =  Intent(this,PageJellaba::class.java)
//            intent.putExtra("EMAIL", email)
//            startActivity(intent)
//        }
//        textCafton.setOnClickListener {
//            textSac.paintFlags = textSac.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//            textCafton.paintFlags = textCafton.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textBabouches.paintFlags = textBabouches.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            textDejallaba.paintFlags = textDejallaba.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
//            val intent =  Intent(this,PageJellaba::class.java)
//            intent.putExtra("EMAIL", email)
//            startActivity(intent)
//        }
//
    }
    class MyTimerTask(private val viewPager: ViewPager2, private val dotscount: Int) : TimerTask() {
        override fun run() {
            viewPager.post {
                val currentItem = viewPager.currentItem
                viewPager.setCurrentItem((currentItem + 1) % dotscount, true)
            }
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val email = intent.getStringExtra("EMAIL") ?: "" // Récupérer l'email de l'intent
        Log.d("emaill",email.toString())
        when (item.itemId) {
            R.id.caftan -> {
                val intent = Intent(this,PageCustems::class.java)
                intent.putExtra("EMAIL", email)
                startActivity(intent)
            }
            R.id.babouche -> {
                val intent = Intent(this,PageBabouche::class.java)
                intent.putExtra("EMAIL", email)
                startActivity(intent)
            }

        }
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return false
    }

    private fun onImageClick(imageId:String,email:String) {
        val intent = Intent(this, PageAchat::class.java)
        intent.putExtra("IMAGE_ID", imageId)

        intent.putExtra("EMAIL", email)

        startActivity(intent)
    }
}