package com.example.vieweroftheancients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class HeroAdapter : ListAdapter<Hero, HeroAdapter.HeroViewHolder>(HeroDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_list_item, parent, false)
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.hero_name)
        private val attributeTextView: TextView = itemView.findViewById(R.id.hero_attribute)
        private val rolesTextView: TextView = itemView.findViewById(R.id.hero_roles)

        fun bind(hero: Hero) {
            nameTextView.text = hero.localizedName
            attributeTextView.text = hero.primaryAttr
            rolesTextView.text = hero.roles.joinToString(", ")
        }
    }
}

class HeroDiffCallback : DiffUtil.ItemCallback<Hero>() {
    override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem == newItem
    }
}