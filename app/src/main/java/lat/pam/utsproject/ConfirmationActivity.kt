package lat.pam.utsproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmationActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)

        // Ambil data dari intent
        val foodName = intent.getStringExtra("foodName")
        val servings = intent.getStringExtra("servings")
        val orderingName = intent.getStringExtra("orderingName")
        val additionalNotes = intent.getStringExtra("additionalNotes")

        // Set data ke TextView
        val foodNameTextView: TextView = findViewById(R.id.tvFoodName)
        val servingsTextView: TextView = findViewById(R.id.tvServings)
        val orderingNameTextView: TextView = findViewById(R.id.tvOrderingName)
        val notesTextView: TextView = findViewById(R.id.tvAdditionalNotes)

        foodNameTextView.text = "Food Name: $foodName"
        servingsTextView.text = "Number of Servings: $servings"
        orderingNameTextView.text = "Ordering Name: $orderingName"
        notesTextView.text = "Additional Notes: $additionalNotes"

        // Setup edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tombol "Back to Menu" untuk kembali ke ListFoodActivity
        val backToMenuButton: Button = findViewById(R.id.backtoMenu)
        backToMenuButton.setOnClickListener {
            val intent = Intent(this, ListFoodActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
