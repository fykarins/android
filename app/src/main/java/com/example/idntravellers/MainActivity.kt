package com.example.idntravellers

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvTravellers: RecyclerView
    private val listTravellers = ArrayList<Travellers>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set Toolbar sebagai ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Mengubah warna teks judul toolbar menjadi hitam
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.black))

        rvTravellers = findViewById(R.id.rv_travellers)
        rvTravellers.setHasFixedSize(true)

        listTravellers.addAll(getListTravellers())
        showRecyclerList() // Set default layout saat Activity pertama kali diload
    }

    // Inflate menu options
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Menangani item menu yang dipilih
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_list -> {
                showRecyclerList()
                true
            }
            R.id.action_grid -> {
                showRecyclerGrid()
                true
            }
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showRecyclerList() {
        rvTravellers.layoutManager = LinearLayoutManager(this)

        val listTravellersAdapter = TravellerAdapter(listTravellers) { data ->
            val intentToDetail = Intent(this, DetailActivity::class.java)
            intentToDetail.putExtra("DATA_TRAVELLER", data)  // Mengirim data Traveller
            startActivity(intentToDetail)
        }
        rvTravellers.adapter = listTravellersAdapter
    }

    private fun showRecyclerGrid() {
        rvTravellers.layoutManager = GridLayoutManager(this, 2)

        val listTravellersAdapter = TravellerAdapter(listTravellers) { data ->
            val intentToDetail = Intent(this, DetailActivity::class.java)
            intentToDetail.putExtra("DATA_TRAVELLER", data)  // Mengirim data Traveller
            startActivity(intentToDetail)
        }
        rvTravellers.adapter = listTravellersAdapter
    }

    private fun getListTravellers(): ArrayList<Travellers> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataShortDescription = resources.getStringArray(R.array.data_short_description) // Ambil data deskripsi pendek
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val list = ArrayList<Travellers>()

        for (i in dataName.indices) {
            // Menggunakan dataShortDescription[i] untuk shortDescription
            val travellers = Travellers(dataName[i], dataDescription[i], dataShortDescription[i], dataPhoto.getResourceId(i, -1))
            list.add(travellers)
        }
        dataPhoto.recycle()
        return list
    }

}
