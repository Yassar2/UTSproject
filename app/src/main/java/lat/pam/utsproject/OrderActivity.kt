package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {

    private lateinit var servingsEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var notesEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Ambil data nama makanan dari intent
        val foodName = intent.getStringExtra("FOOD_NAME") ?: "Enter food name - Get From List"

        // Tampilkan nama makanan di TextView
        val foodNameTextView: TextView = findViewById(R.id.etFoodName)
        foodNameTextView.text = foodName

        servingsEditText = findViewById(R.id.etServings)
        nameEditText = findViewById(R.id.etName)
        notesEditText = findViewById(R.id.etNotes)

        val orderButton: Button = findViewById(R.id.btnOrder)
        orderButton.setOnClickListener {
            val servings = servingsEditText.text.toString()
            val orderingName = nameEditText.text.toString()
            val additionalNotes = notesEditText.text.toString()

            // Check if all fields are filled
            if (servings.isEmpty() || orderingName.isEmpty()) {
                Toast.makeText(this, "Please, fill in all fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Kirim data ke ConfirmationActivity
            val intent = Intent(this, ConfirmationActivity::class.java).apply {
                putExtra("foodName", foodName)
                putExtra("servings", servings)
                putExtra("orderingName", orderingName)
                putExtra("additionalNotes", additionalNotes)
            }
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
