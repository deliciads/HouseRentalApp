package com.comp304.deliciadsouza_comp304lab2_ex1.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.comp304.deliciadsouza_comp304lab2_ex1.R

/**
 * Activity for selecting a payment method.
 */
class PaymentActivity : AppCompatActivity() {

    private lateinit var paymentMethodRadioGroup: RadioGroup
    private lateinit var nextButton: Button


    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down, then this Bundle contains the data it most
     * recently supplied in [onSaveInstanceState]. Otherwise, it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)

        // Reference to the RadioGroup for payment methods in the layout
        paymentMethodRadioGroup = findViewById(R.id.paymentMethodRadioGroup)

        // Reference to the "Next" button in the layout
        nextButton = findViewById(R.id.paymentButton)

        nextButton.setOnClickListener {
            val checkedRadioButtonId = paymentMethodRadioGroup.checkedRadioButtonId

            if (checkedRadioButtonId != -1) {
                val selectedRadioButton: RadioButton = findViewById(checkedRadioButtonId)
                val selectedPaymentMethod = selectedRadioButton.text.toString()

                // Pass the selected payment method to the next activity (CardInformationActivity)
                val cardIntent = Intent(this, CardInformationActivity::class.java)
                cardIntent.putExtra("selectedPaymentMethod", selectedPaymentMethod)
                startActivity(cardIntent)
            } else {
                // Display a toast message if no payment method is selected
                Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
            }
        }
    }
}