package com.comp304.deliciadsouza_comp304lab2_ex1.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.comp304.deliciadsouza_comp304lab2_ex1.R

/**
 * Activity for selecting different types of homes.
 */

class HomeTypesActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_hometypes)
    }

    /**
     * Initialize the contents of the Activity's standard options menu.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false, the menu will not be shown.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_types_menu, menu)
        return true
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
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
     * Start [ApartmentActivity] with the specified home type.
     *
     * @param homeType The type of home to be displayed.
     */

    private fun startActivityForApartment(homeType: String) {
        val intent = Intent(this, ApartmentActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    /**
     * Start [DetachedHomeActivity] with the specified home type.
     *
     * @param homeType The type of home to be displayed.
     */

    private fun startActivityForDetached(homeType: String) {
        val intent = Intent(this, DetachedHomeActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    /**
     * Start [SemiDetachedActivity] with the specified home type.
     *
     * @param homeType The type of home to be displayed.
     */

    private fun startActivityForSemiDetached(homeType: String) {
        val intent = Intent(this, SemiDetachedActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    /**
     * Start [CondoActivity] with the specified home type.
     *
     * @param homeType The type of home to be displayed.
     */

    private fun startActivityForCondo(homeType: String) {
        val intent = Intent(this, CondoActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }

    /**
     * Start [TownHouseActivity] with the specified home type.
     *
     * @param homeType The type of home to be displayed.
     */
    private fun startActivityForTownhouse(homeType: String) {
        val intent = Intent(this, TownHouseActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }
}



