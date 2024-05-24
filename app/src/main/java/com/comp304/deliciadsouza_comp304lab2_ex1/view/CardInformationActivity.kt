package com.comp304.deliciadsouza_comp304lab2_ex1.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.comp304.deliciadsouza_comp304lab2_ex1.R
import com.comp304.deliciadsouza_comp304lab2_ex1.utils.HomeType
import java.util.regex.Pattern

class CardInformationActivity : AppCompatActivity() {

    private lateinit var fullNameEditText: EditText
    private lateinit var cardNumberEditText: EditText
    private lateinit var favoriteSportEditText: EditText
    private lateinit var favoriteTeamEditText: EditText
    private lateinit var favoriteFoodEditText: EditText
    private lateinit var submitInfoButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_information)

        // Initialize UI elements
        fullNameEditText = findViewById(R.id.fullNameEditText)
        cardNumberEditText = findViewById(R.id.cardNumberEditText)
        favoriteSportEditText = findViewById(R.id.favoriteSportEditText)
        favoriteTeamEditText = findViewById(R.id.favoriteTeamEditText)
        favoriteFoodEditText = findViewById(R.id.favoriteFoodEditText)
        submitInfoButton = findViewById(R.id.submitInfoButton)

        // Set up click listener for the submit button
        submitInfoButton.setOnClickListener {
            // Validate and handle the customer information
            val fullName = fullNameEditText.text.toString()
            val cardNumber = cardNumberEditText.text.toString()
            val favoriteSport = favoriteSportEditText.text.toString()
            val favoriteTeam = favoriteTeamEditText.text.toString()
            val favoriteFood = favoriteFoodEditText.text.toString()

            // Validate input fields
            if (fullName.isEmpty() || cardNumber.isEmpty() || favoriteSport.isEmpty() ||
                favoriteTeam.isEmpty() || favoriteFood.isEmpty()
            ) {
                showToast("All fields are required")
            } else {
                // Display a success message and proceed to the next screen
                showToast("Payment Submitted")
                // Proceed to the next screen or perform any other action as needed
                val successIntent = Intent(this, SuccessActivity::class.java)
                successIntent.putExtra("fullName", fullName)
                successIntent.putExtra("cardNumber", cardNumber)
                successIntent.putExtra("favoriteSport", favoriteSport)
                successIntent.putExtra("favoriteTeam", favoriteTeam)
                successIntent.putExtra("favoriteFood", favoriteFood)
                startActivity(successIntent)
            }
        }
    }

    /**
     * Display a toast message.
     *
     * @param message The message to display in the toast.
     */
    private fun showToast(message: String) {
        // Display a toast message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}


