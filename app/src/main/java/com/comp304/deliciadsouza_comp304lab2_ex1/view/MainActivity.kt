package com.comp304.deliciadsouza_comp304lab2_ex1.view

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.comp304.deliciadsouza_comp304lab2_ex1.R

/**
 * The main entry point of the application.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down, then this Bundle contains the data it most
     * recently supplied in [onSaveInstanceState]. Otherwise, it is null.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reference to the "Enter" button in the layout
        val btnEnter: Button = findViewById(R.id.btnEnter)

        // Set click listener for the "Enter" button
        btnEnter.setOnClickListener {
            // Start the HomeTypesActivity
            startActivity(Intent(this, HomeTypesActivity::class.java))
        }
    }
}