package com.example.idntravellers

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.idntravellers.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)

        // Mendapatkan data Travellers dari Intent
        val data: Travellers? = intent.getParcelableExtra("DATA_TRAVELLER")

        // Menampilkan data di layout
        binding.tvItemName.text = data?.name

        // Menambahkan <br> untuk spasi antar paragraf
        val formattedDescription = data?.description?.replace("\n", "<br>")

        // Menggunakan Html.fromHtml untuk menampilkan deskripsi dengan format HTML
        binding.tvItemDescription.text = Html.fromHtml(formattedDescription, Html.FROM_HTML_MODE_COMPACT)

        // Mengatur teks rata kiri-kanan
        binding.tvItemDescription.textAlignment = View.TEXT_ALIGNMENT_VIEW_START

        data?.photo?.let {
            binding.ivItemPhoto.setImageResource(it)  // Menampilkan foto sesuai data
        }

        // Menambahkan aksi klik untuk tombol "Lihat Selengkapnya"
        binding.btnLihatSelengkapnya.setOnClickListener {
            // Tindakan ketika tombol Lihat Selengkapnya diklik
            Toast.makeText(this, "Lihat Selengkapnya diklik", Toast.LENGTH_SHORT).show()
            // Tambahkan logika untuk membuka halaman baru atau memperluas deskripsi
        }

        // Menambahkan aksi klik untuk tombol "Pesan Tiket"
        binding.btnPesanTiket.setOnClickListener {
            // Tindakan ketika tombol Pesan Tiket diklik
            Toast.makeText(this, "Pesan Tiket diklik", Toast.LENGTH_SHORT).show()
            // Tambahkan logika untuk membuka halaman pemesanan tiket
            val intent = Intent(this, PesanTiketActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

class PesanTiketActivity {

}
