package com.comp304.deliciadsouza_comp304lab2_ex1.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.comp304.deliciadsouza_comp304lab2_ex1.R
import com.comp304.deliciadsouza_comp304lab2_ex1.utils.HomeType

class CondoActivity : AppCompatActivity() {

    // List of condo apartments
    private val apartmentsList: List<HomeType> = listOf(
        HomeType("123 Mainland St", "$2000/month", R.drawable.condo_1, homeType = "Condo"),
        HomeType("456 York St", "$1500/month", R.drawable.condo_2, homeType = "Condo"),
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_condo)

        // UI elements
        val addToCartButton: Button = findViewById(R.id.addToCartButton)
        val checkoutButton: Button = findViewById(R.id.checkoutButton)
        val listView: ListView = findViewById(R.id.condoListView)

        // Adapter for the ListView
        val adapter = ApartmentAdapter(this, R.layout.item_condo, apartmentsList)
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

            R.id.menu_town_house-> {
                startActivityForTownhouse("Town House")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startActivityForApartment(homeType: String) {
        val intent = Intent(this, ApartmentActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    private fun startActivityForDetached(homeType: String) {
        val intent = Intent(this, DetachedHomeActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    private fun startActivityForSemiDetached(homeType: String) {
        val intent = Intent(this, SemiDetachedActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    private fun startActivityForCondo(homeType: String) {
        val intent = Intent(this, CondoActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

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

        /**
         * Get the view for each item in the ListView.
         *
         * @param position The position of the item in the ListView.
         * @param convertView The recycled view to populate.
         * @param parent The parent ViewGroup.
         * @return The populated view for the item.
         */

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var itemView = convertView
            if (itemView == null) {
                val inflater = LayoutInflater.from(context)
                itemView = inflater.inflate(R.layout.item_condo, parent, false)
            }

            val apartment = getItem(position)

            val imageView: ImageView? = itemView?.findViewById(R.id.condoImageView)
            val addressTextView: TextView? = itemView?.findViewById(R.id.condoAddressTextView)
            val priceTextView: TextView? = itemView?.findViewById(R.id.condoPriceTextView)
            val checkBox: CheckBox? = itemView?.findViewById(R.id.condoCheckBox)

            fun apartment(apartment: HomeType) {
                imageView?.setImageResource(apartment?.imageResourceId ?: 0)
                addressTextView?.text = apartment?.address
                priceTextView?.text = apartment?.price
                checkBox?.isChecked = false
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
