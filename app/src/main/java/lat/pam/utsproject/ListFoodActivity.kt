package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var foodList: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        foodList = listOf(
            Food(1, "Batagor", "Batagor khas Bandung dengan cita rasa autentik yang gurih dan lezat", R.drawable.batagor, 15.0),
            Food(2, "Salad Hitam", "Salad segar yang dibuat sesuai pesanan, cocok untuk menjaga kesehatan tubuh", R.drawable.black_salad, 12.0),
            Food(3, "Kopi Cappuccino", "Cappuccino asli yang terbuat dari biji kopi Arabika pilihan", R.drawable.cappuchino, 20.0),
            Food(4, "Kue Keju", "Kue keju manis dengan rasa selembut kasih sayang", R.drawable.cheesecake, 25.0),
            Food(5, "Cireng", "Cireng renyah dari Garut, digoreng hingga krispi dan sangat menggugah selera", R.drawable.cireng, 10.0),
            Food(6, "Donat", "Donat lezat yang tak kalah enak dengan merek-merek terkenal", R.drawable.donut, 15.0),
            Food(7, "Kopi Hitam", "Kopi hitam yang kuat dan mantap untuk menemani hari Anda", R.drawable.kopi_hitam, 10.0),
            Food(8, "Mie Goreng", "Mie goreng spesial dengan rasa unik dan menggugah selera", R.drawable.mie_goreng, 12.0),
            Food(9, "Nasi Goreng", "Nasi goreng autentik khas Padang dengan cita rasa yang kaya", R.drawable.nasigoreng, 20.0),
            Food(10, "Teh Bersoda", "Minuman teh menyegarkan dengan rasa unik yang bikin semangat", R.drawable.sparkling_tea, 15.0)
        )




        adapter = FoodAdapter { food ->
            val intent = Intent(this, OrderActivity::class.java).apply {
                putExtra("FOOD_NAME", food.name)
            }
            startActivity(intent)
        }
        adapter.setFoods(foodList)
        recyclerView.adapter = adapter

        // Set up edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
