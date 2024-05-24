package com.comp304.deliciadsouza_comp304lab2_ex1.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.comp304.deliciadsouza_comp304lab2_ex1.R
import com.comp304.deliciadsouza_comp304lab2_ex1.utils.HomeType

class ApartmentActivity : AppCompatActivity() {

    // List of apartments
    private val apartmentsList: List<HomeType> = listOf(
        HomeType("123 Main St", "$1200/month", R.drawable.apt_1, homeType = "Apartment"),
        HomeType("456 Oak St", "$1500/month", R.drawable.apt_2, homeType = "Apartment"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment)

        // UI elements
        val addToCartButton: Button = findViewById(R.id.addToCartButton)
        val checkoutButton: Button = findViewById(R.id.checkoutButton)
        val listView: ListView = findViewById(R.id.apartmentsListView)


        // Adapter for the ListView
        val adapter = ApartmentAdapter(this, R.layout.item_apartment, apartmentsList)
        listView.adapter = adapter

        // List to store selected apartments
        val selectedApartments = mutableListOf<HomeType>()

        // Set up a click listener for each item in the ListView
        listView.setOnItemClickListener { _, _, position, _ ->
            // Toggle the selected state of the apartment when clicked
            val apartment = apartmentsList[position]
            apartment.isSelected = !apartment.isSelected

            // Notify the adapter that the data set has changed
            adapter.notifyDataSetChanged()
        }

        // Button click listener to add selected items to the list
        addToCartButton.setOnClickListener {
            // Add selected items to the list
            selectedApartments.clear()
            selectedApartments.addAll(apartmentsList.filter { it.isSelected })
            Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show()
        }

        // Button click listener to start CheckoutActivity with selected items
        checkoutButton.setOnClickListener {
            // Start the CheckoutActivity with selected items
            val intent = Intent(this, CheckoutActivity::class.java)
            // Add other relevant data as needed
            intent.putExtra("selectedApartments", ArrayList(selectedApartments))
            startActivity(intent)
        }
    }

    // Options menu creation
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_types_menu, menu)
        return true
    }

    // Options menu item selection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_apartment -> {
                startActivityForApartment("Apartment")
                return true
            }

            R.id.menu_detached_home -> {
                startActivityForDetached("Detached Home")
                return true
            }

            R.id.menu_semi_detached_home -> {
                startActivityForSemiDetached("Semi-Detached Home")
                return true
            }

            R.id.menu_condo_apartment -> {
                startActivityForCondo("Condo Apartment")
                return true
            }

            R.id.menu_town_house -> {
                startActivityForTownhouse("Town House")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Start ApartmentActivity for a specific home type.
     *
     * @param homeType The type of home to display.
     */

    private fun startActivityForApartment(homeType: String) {
        val intent = Intent(this, ApartmentActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    /**
     * Start DetachedHomeActivity for a specific home type.
     *
     * @param homeType The type of home to display.
     */

    private fun startActivityForDetached(homeType: String) {
        val intent = Intent(this, DetachedHomeActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    /**
     * Start SemiDetachedActivity for a specific home type.
     *
     * @param homeType The type of home to display.
     */

    private fun startActivityForSemiDetached(homeType: String) {
        val intent = Intent(this, SemiDetachedActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    /**
     * Start CondoActivity for a specific home type.
     *
     * @param homeType The type of home to display.
     */

    private fun startActivityForCondo(homeType: String) {
        val intent = Intent(this, CondoActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    /**
     * Start TownHouseActivity for a specific home type.
     *
     * @param homeType The type of home to display.
     */
    private fun startActivityForTownhouse(homeType: String) {
        val intent = Intent(this, TownHouseActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    // Adapter for the ListView
    private class ApartmentAdapter(
        private val context: AppCompatActivity,
        private val resource: Int,
        private val objects: List<HomeType>
    ) : ArrayAdapter<HomeType>(context, resource, objects) {

        // Get the view for each item in the ListView
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var itemView = convertView
            if (itemView == null) {
                val inflater = LayoutInflater.from(context)
                itemView = inflater.inflate(R.layout.item_apartment, parent, false)
            }

            val apartment = getItem(position)

            val imageView: ImageView? = itemView?.findViewById(R.id.apartmentImageView)
            val addressTextView: TextView? = itemView?.findViewById(R.id.apartmentAddressTextView)
            val priceTextView: TextView? = itemView?.findViewById(R.id.apartmentPriceTextView)
            val checkBox: CheckBox? = itemView?.findViewById(R.id.apartmentCheckBox)

            // Function to set data for each apartment item
            fun apartment(apartment: HomeType) {
                imageView?.setImageResource(apartment?.imageResourceId ?: 0)
                addressTextView?.text = apartment?.address
                priceTextView?.text = apartment?.price
                checkBox?.setOnCheckedChangeListener(null)
                //checkBox?.isChecked = false
                checkBox?.isChecked = apartment?.isSelected == true
                checkBox?.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        apartment?.isSelected = isChecked
                    }

                        notifyDataSetChanged()
                    }
            }
            if (apartment != null) {
                apartment(apartment)
            }
            return itemView!!
        }
    }
}
