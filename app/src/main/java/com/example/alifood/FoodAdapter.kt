package com.example.alifood

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alifood.room.Food

class FoodAdapter(private val data : ArrayList<Food>, private val foodEvents: FoodEvents) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(itemView: View , private val context : Context) :
        RecyclerView.ViewHolder(itemView) {

        val imgMain = itemView.findViewById<ImageView>(R.id.img_main)
        val txtTitle = itemView.findViewById<TextView>(R.id.tv_title_food)
        val txtLocation = itemView.findViewById<TextView>(R.id.tv_location)
        val txtPrice = itemView.findViewById<TextView>(R.id.tv_price)
        val txtDistance = itemView.findViewById<TextView>(R.id.tv_distance)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
        val txtRating = itemView.findViewById<TextView>(R.id.tv_ratingNum)

        @SuppressLint("SetTextI18n")
        fun bindData(position: Int){

            txtTitle.text = data[position].txtTitle
            txtLocation.text = data[position].txtLocation
            txtPrice.text = "$" + data[position].txtPrice + " vip"
            txtDistance.text = data[position].txtDistance + " km from you"
            txtRating.text = "("+data[position].numOfRating.toString()+" Rating)"
            ratingBar.rating = data[position].rating

            Glide
                .with(context)
                .load(data[position].urlImage)
                .placeholder(R.drawable.burger)
                .into(imgMain)

//            itemView.setOnClickListener {
//
//                foodEvents.onFoodClicked(data[adapterPosition],adapterPosition)
//
//            }
            itemView.setOnLongClickListener {

                foodEvents.onFoodLongClicked(data[adapterPosition],adapterPosition)
                true
            }

        }

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food,parent,false)
        return FoodViewHolder(view,parent.context)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addFood (newFood: Food){
        //add new food to list:
        data.add(0,newFood)
        notifyItemInserted(0)
    }

    fun removeFood(oldFood: Food, oldPosition: Int){
        //remove item from list by long click
        data.remove(oldFood)
        notifyItemRemoved(oldPosition)
    }

    fun updateFood(newFood: Food, position: Int){
        //update item from list :
        data.set(position,newFood)
        notifyItemChanged(position)
    }

    fun setData (newList: ArrayList<Food>){
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }

    interface FoodEvents {
        //1.create interface in adapter
        //2.get an object of interface in args of adapter
        //3.fill (call) object of interface with your data
        //4. implementation in MainActivity

        fun onFoodClicked(food: Food, position: Int)
        fun onFoodLongClicked(food: Food, position: Int)


    }
}