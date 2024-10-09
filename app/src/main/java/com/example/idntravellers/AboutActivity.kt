package com.example.idntravellers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.idntravellers.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menginisialisasi binding
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur Toolbar sebagai action bar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)

        // Set informasi di About Page
        binding.ivPhoto.setImageResource(R.drawable.about_1)
        binding.tvName.text = "FAY IVANA KARIN"
        binding.tvEmail.text = "a001b4kx1460@dicoding.academy"
    }

    // Menangani aksi tombol back
    override fun onSupportNavigateUp(): Boolean {
        finish() // Menutup aktivitas dan kembali ke halaman sebelumnya
        return true
    }
}
