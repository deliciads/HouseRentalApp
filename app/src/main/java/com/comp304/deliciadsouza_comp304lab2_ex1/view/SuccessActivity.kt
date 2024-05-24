package com.comp304.deliciadsouza_comp304lab2_ex1.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.comp304.deliciadsouza_comp304lab2_ex1.R

/**
 * Activity to display a success message after a successful payment.
 */
class SuccessActivity : AppCompatActivity() {

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down, then this Bundle contains the data it most
     * recently supplied in [onSaveInstanceState]. Otherwise, it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        // Reference to the TextView displaying the success message
        val successMessageTextView: TextView = findViewById(R.id.successMessageTextView)

        // Set the success message
        successMessageTextView.text = "Payment successful! Thank you."
    }
}