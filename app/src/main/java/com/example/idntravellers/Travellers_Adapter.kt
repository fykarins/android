package com.example.idntravellers

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TravellerAdapter(
    private val listTravellers: ArrayList<Travellers>,
    private val onClick: (Travellers) -> Unit
) : RecyclerView.Adapter<TravellerAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_travellers, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val travellers = listTravellers[position]
        holder.bind(travellers)
    }

    override fun getItemCount(): Int = listTravellers.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        private val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        private val tvShortDescription: TextView = itemView.findViewById(R.id.tv_item_description) // Short description for MainActivity
        private val btnSetFavorite: ImageButton = itemView.findViewById(R.id.btn_set_favorite)
        private val btnSetShare: ImageButton = itemView.findViewById(R.id.btn_set_share)

        fun bind(travellers: Travellers) {
            tvName.text = travellers.name
            tvShortDescription.text = travellers.shortDescription // Use short description for MainActivity

            // Log untuk ID gambar
            Log.d("TravellerAdapter", "Loading image with ID: ${travellers.photo}")

            try {
                imgPhoto.setImageResource(travellers.photo) // Menampilkan foto
            } catch (e: Resources.NotFoundException) {
                Log.e("TravellerAdapter", "Image not found for ID: ${travellers.photo}", e)
                imgPhoto.setImageResource(R.drawable.about_2)
            }

            // Set click listener untuk item
            itemView.setOnClickListener {
                onClick(travellers) // Pass full traveller data to the onClick listener (will be used for DetailActivity)
            }

            btnSetFavorite.setOnClickListener {
                Toast.makeText(itemView.context, "Favorite ${travellers.name}", Toast.LENGTH_SHORT).show()
            }

            btnSetShare.setOnClickListener {
                Toast.makeText(itemView.context, "Share ${travellers.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
