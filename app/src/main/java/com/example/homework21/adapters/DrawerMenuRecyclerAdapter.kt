package com.example.homework21.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.homework21.R
import com.example.homework21.databinding.RecyclerMenuBinding
import com.example.homework21.model.MenuItem



typealias selectedItem = (position : Int) -> Unit

class DrawerMenuRecyclerAdapter(private val items : MutableList<MenuItem>): RecyclerView.Adapter<DrawerMenuRecyclerAdapter.ViewHolder>() {


    lateinit var selectedItem :  selectedItem

    var selected :Int = 0

    inner class ViewHolder(val binding: RecyclerMenuBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private lateinit var model : MenuItem

        override fun onClick(v: View?) {
            selectedItem.invoke(adapterPosition)
            selected=adapterPosition
            notifyDataSetChanged()


        }

        fun bind() {

            model = items[adapterPosition]
            binding.icon.setImageResource(model.img)
            binding.title.text = model.title

            binding.root.setOnClickListener(this)

            if (adapterPosition == selected){
                binding.root.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.teal_200))
                binding.view.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.teal_200))
            }else{
                binding.view.visibility = View.INVISIBLE
                binding.root.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.white))

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size


    fun checkReturn(){
        selected = 0
        notifyDataSetChanged()
    }

}

