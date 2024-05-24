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

/**
 * Activity for displaying and selecting semi-detached houses.
 */
class SemiDetachedActivity : AppCompatActivity()  {

    // List of semi-detached houses
    private val apartmentsList: List<HomeType> = listOf(
        HomeType("123 Midland St", "$1200/month", R.drawable.semi_detached_1, homeType = "Semi-detached house"),
        HomeType("456 Progress St", "$1500/month", R.drawable.semi_detached_2, homeType = "Semi-detached house"),
    )

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down, then this Bundle contains the data it most
     * recently supplied in [onSaveInstanceState]. Otherwise, it is null.
     */
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semidetached)

        // References to UI elements
        val addToCartButton: Button = findViewById(R.id.addToCartButton)
        val checkoutButton: Button = findViewById(R.id.checkoutButton)
        val listView: ListView = findViewById(R.id.semidetachedListView)

        // Adapter for displaying semi-detached houses in the ListView
        val adapter = ApartmentAdapter(this, R.layout.item_semidetached, apartmentsList)
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

        // Click listener for the "Add to Cart" button
        addToCartButton.setOnClickListener {
            // Add selected items to the list
            selectedApartments.clear()
            selectedApartments.addAll(apartmentsList.filter { it.isSelected })
            Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show()
        }

        // Click listener for the "Checkout" button
        checkoutButton.setOnClickListener {
            // Start the CheckoutActivity with selected items
            val intent = Intent(this, CheckoutActivity::class.java)
            // Add other relevant data as needed
            intent.putExtra("selectedApartments", ArrayList(selectedApartments))
            startActivity(intent)
        }
    }

    /**
     * Inflate the menu.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_types_menu, menu)
        return true
    }

    /**
     * Handle menu item selection.
     *
     * @param item The menu item that was selected.
     * @return Return false to allow normal menu processing to proceed,
     * true to consume it here.
     */
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
     * Start the [ApartmentActivity].
     *
     * @param homeType The type of home to display.
     */
    private fun startActivityForApartment(homeType: String) {
        val intent = Intent(this, ApartmentActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    /**
     * Start the [DetachedHomeActivity].
     *
     * @param homeType The type of home to display.
     */
    private fun startActivityForDetached(homeType: String) {
        val intent = Intent(this, DetachedHomeActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    /**
     * Start the [SemiDetachedActivity].
     *
     * @param homeType The type of home to display.
     */
    private fun startActivityForSemiDetached(homeType: String) {
        val intent = Intent(this, SemiDetachedActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    /**
     * Start the [CondoActivity].
     *
     * @param homeType The type of home to display.
     */
    private fun startActivityForCondo(homeType: String) {
        val intent = Intent(this, CondoActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    /**
     * Start the [TownHouseActivity].
     *
     * @param homeType The type of home to display.
     */
    private fun startActivityForTownhouse(homeType: String) {
        val intent = Intent(this, TownHouseActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    /**
     * Adapter for displaying apartments in a ListView.
     *
     * @param context The context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     * instantiating views.
     * @param objects The objects to represent in the ListView.
     */
    private class ApartmentAdapter(
        private val context: AppCompatActivity,
        private val resource: Int,
        private val objects: List<HomeType>
    ) : ArrayAdapter<HomeType>(context, resource, objects) {

        /**
         * Get a View that displays the data at the specified position in the data set.
         *
         * @param position The position of the item within the adapter's data set.
         * @param convertView The old view to reuse, if possible.
         * @param parent The parent that this view will eventually be attached to.
         * @return A View corresponding to the data at the specified position.
         */
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var itemView = convertView
            if (itemView == null) {
                val inflater = LayoutInflater.from(context)
                itemView = inflater.inflate(R.layout.item_semidetached, parent, false)
            }

            // Get the HomeType object at the specified position
            val apartment = getItem(position)

            // References to UI elements in the item layout
            val imageView: ImageView? = itemView?.findViewById(R.id.semidetachedImageView)
            val addressTextView: TextView? = itemView?.findViewById(R.id.semidetachedAddressTextView)
            val priceTextView: TextView? = itemView?.findViewById(R.id.semidetachedPriceTextView)
            val checkBox: CheckBox? = itemView?.findViewById(R.id.semidetachedCheckbox)

            // Function to set values for the UI elements based on the HomeType object
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
            // Call the setupApartmentView function if the HomeType object is not null
            if (apartment != null) {
                apartment(apartment)
            }
            return itemView!!
        }
    }
}
