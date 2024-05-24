package com.comp304.deliciadsouza_comp304lab2_ex1.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.comp304.deliciadsouza_comp304lab2_ex1.R
import com.comp304.deliciadsouza_comp304lab2_ex1.utils.HomeType

class CheckoutActivity : AppCompatActivity() {

    // List of selected apartments
    private lateinit var selectedApartments: List<HomeType>
    private lateinit var radioGroup: RadioGroup

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        // Retrieve selected apartments from the intent
        selectedApartments = intent.getSerializableExtra("selectedApartments") as List<HomeType>
        radioGroup = findViewById(R.id.selectedHomesRadioGroup)

        // Populate the RadioGroup with selected apartments
        populateRadioGroup(selectedApartments)

        // Set up a click listener for the payment button
        val paymentButton: Button = findViewById(R.id.paymentNextButton)
        paymentButton.setOnClickListener {
            val checkedRadioButtonId = radioGroup.checkedRadioButtonId
            if (checkedRadioButtonId != -1) {
                // Retrieve the selected radio button
                val selectedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)

                // Find the corresponding apartment based on the selected address
                val selectedApartments =
                    selectedApartments.find { it.address == selectedRadioButton.text.toString() }

                // Start the PaymentActivity with the selected apartment
                val paymentIntent = Intent(this, PaymentActivity::class.java)
                paymentIntent.putExtra("selectedApartments", selectedApartments)
                startActivity(paymentIntent)
            } else {
                Toast.makeText(this, "Please select an apartment", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Populate the RadioGroup with radio buttons for each selected apartment.
     *
     * @param apartments The list of selected apartments.
     */

    private fun populateRadioGroup(apartments: List<HomeType>) {
        for (apartment in apartments) {
            val radioButton = RadioButton(this)
            radioButton.text = "${apartment.address}, ${apartment.price}"
            radioGroup.addView(radioButton)
        }
    }
}

