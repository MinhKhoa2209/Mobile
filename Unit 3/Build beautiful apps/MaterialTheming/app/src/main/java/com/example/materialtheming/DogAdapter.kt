package com.example.materialtheming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialtheming.databinding.ItemDogBinding

class DogAdapter(private val dogList: List<Dog>) : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(dogList[position])
    }

    override fun getItemCount() = dogList.size

    class DogViewHolder(private val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dog: Dog) {
            binding.dog = dog  // Sử dụng Data Binding
            binding.executePendingBindings()
        }
    }
}
