package lat.pam.utsproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView

    // Variable untuk mengontrol visibilitas password
    private var isPasswordVisible = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Retrieve stored username and password from SharedPreferences
            val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
            val storedUsername = sharedPreferences.getString("username", null)
            val storedPassword = sharedPreferences.getString("password", null)

            // Check if all fields are filled
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please, Fill in all fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if the credentials match
            if (username == storedUsername && password == storedPassword) {
                val intent = Intent(this, ListFoodActivity::class.java)
                startActivity(intent)
                finish() // Close MainActivity
            } else {
                Toast.makeText(this, "Username or password is incorrect", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Mengatur onTouchListener untuk visibilitas password
        etPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val DRAWABLE_END = 2
                if (event.rawX >= (etPassword.right - etPassword.compoundDrawables[DRAWABLE_END].bounds.width())) {
                    isPasswordVisible = !isPasswordVisible
                    updatePasswordVisibility()
                    return@setOnTouchListener true
                }
            }
            false
        }
    }

    // Fungsi untuk mengubah visibilitas password
    private fun updatePasswordVisibility() {
        if (isPasswordVisible) {
            etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0)
        } else {
            etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0)
        }
        etPassword.setSelection(etPassword.text.length) // Memastikan kursor tetap di akhir teks
    }
}
