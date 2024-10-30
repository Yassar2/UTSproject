package lat.pam.utsproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val usernameInput = findViewById<EditText>(R.id.etUsername)
        val passwordInput = findViewById<EditText>(R.id.etPassword)
        val namaInput = findViewById<EditText>(R.id.etNama) // Assuming you have an email field
        val phoneNumberInput =
            findViewById<EditText>(R.id.etPhoneNumber) // Assuming you have a phone number field
        val registerButton = findViewById<Button>(R.id.btnRegister)

        registerButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            val email = namaInput.text.toString()
            val phoneNumber = phoneNumberInput.text.toString()

            // Check if all fields are filled
            if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
                Toast.makeText(this, "Please, Fill in all fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save the username and password in SharedPreferences
            val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("username", username)
            editor.putString("password", password)
            editor.apply()

            Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show()
            finish() // Optionally go back to the MainActivity
        }
    }
}
