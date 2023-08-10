package com.example.alifood

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alifood.databinding.ActivityMainBinding
import com.example.alifood.databinding.DialogAddNewItemBinding
import com.example.alifood.databinding.DialogDeleteItemBinding
import com.example.alifood.databinding.DialogUpdateItemBinding
import com.example.alifood.room.Food
import com.example.alifood.room.FoodDao
import com.example.alifood.room.MyDatabase


class MainActivity : AppCompatActivity(), FoodAdapter.FoodEvents {

    private lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: FoodAdapter
    lateinit var foodList: ArrayList<Food>
    lateinit var foodDao: FoodDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodDao = MyDatabase.getDatabase(this).foodDao

        val sharedPreferences = getSharedPreferences("aliFoodData",Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("first_run",true)){

            firstRun()
            sharedPreferences.edit().putBoolean("first_run",false).apply()

        }

        showAllData()


//        binding.btAddNewFood.setOnClickListener {
//
//            val dialog = AlertDialog.Builder(this).create()
//            val dialogBinding = DialogAddNewItemBinding.inflate(layoutInflater)
//            dialog.setView(dialogBinding.root)
//            dialog.setCancelable(true)
//            dialog.show()
//
//            dialogBinding.btDialogDone.setOnClickListener {
//
//                if (
//                    dialogBinding.etDialogFoodName.length() > 0 &&
//                    dialogBinding.etDialogPrice.length() > 0 &&
//                    dialogBinding.etDialogFoodCity.length() > 0 &&
//                    dialogBinding.etDialogDistance.length() > 0
//                ) {
//
//                    val txtName = dialogBinding.etDialogFoodName.text.toString()
//                    val txtPrice = dialogBinding.etDialogPrice.text.toString()
//                    val txtDistance = dialogBinding.etDialogDistance.text.toString()
//                    val txtCity = dialogBinding.etDialogFoodCity.text.toString()
//                    val ratingBarStar: Float = (1..5).random().toFloat()
//                    val txtRatingNum: Int = (1..150).random()
//                    val randomForUrl = (0 until 12).random()
//                    val urlPic = foodList[randomForUrl].urlImage
//                    val newFood = Food(
//                        txtName,
//                        txtPrice,
//                        txtDistance,
//                        txtCity,
//                        urlPic,
//                        txtRatingNum,
//                        ratingBarStar
//                    )
//                    myAdapter.addFood(newFood)
//                    binding.recyclerMain.scrollToPosition(0)
//                    dialog.dismiss()
//
//                } else {
//                    Toast.makeText(this, "Please enter all of items ...", Toast.LENGTH_SHORT).show()
//                }
//            }
//            dialogBinding.btDialogCancel.setOnClickListener {
//                dialog.dismiss()
//            }
//
//
//        }
//
//        binding.btSearch.addTextChangedListener {editTextInput ->
//
//            if (editTextInput!!.isNotEmpty()){
//                //filter data
//                val cloneList = foodList.clone() as ArrayList<Food>
//                val filteredList = cloneList.filter { foodItem ->
//                    foodItem.txtTitle.contains(editTextInput)
//                }
//                myAdapter.setData(ArrayList(filteredList))
//
//            }else{
//                //show all data :
//                myAdapter.setData(foodList.clone() as ArrayList<Food>)
//            }
//
//
//
//
//        }
//
//
//
    }

    private fun firstRun() {


        foodList = arrayListOf(
            Food(
                txtTitle =     "Hamburger",
                txtPrice =     "15",
                txtDistance =  "3",
                txtLocation =  "Isfahan, Iran",
                urlImage =     "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                numOfRating =   20,
                rating =        4.5f
            ),
            Food(
                txtTitle =     "Grilled fish",
                txtPrice =     "20",
                txtDistance =  "2.1",
                txtLocation =  "Tehran, Iran",
                urlImage =     "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                numOfRating =  10,
                rating =       4f
            ),
            Food(
                txtTitle =      "Lasania",
                txtPrice =      "40",
                txtDistance =   "1.4",
                txtLocation =   "Isfahan, Iran",
                urlImage =      "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                numOfRating =   30,
                rating =        2f
            ),
            Food(
                txtTitle =     "pizza",
                txtPrice =     "10",
                txtDistance =  "2.5",
                txtLocation =  "Zahedan, Iran",
                urlImage =     "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                numOfRating =  80,
                rating =       1.5f
            ),
            Food(
                txtTitle =     "Sushi",
                txtPrice =     "20",
                txtDistance =  "3.2",
                txtLocation =  "Mashhad, Iran",
                urlImage =     "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                numOfRating =  200,
                rating =       3f
            ),
            Food(
               txtTitle =     "Roasted Fish",
               txtPrice =     "40",
               txtDistance =  "3.7",
               txtLocation =  "Jolfa, Iran",
               urlImage =     "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
               numOfRating =  50,
               rating =       3.5f
            ),
            Food(
                txtTitle =     "Fried chicken",
                txtPrice =     "70",
                txtDistance =  "3.5",
                txtLocation =  "NewYork, USA",
                urlImage =     "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                numOfRating =  70,
                rating =       2.5f
            ),
            Food(
                txtTitle =     "Vegetable salad",
                txtPrice =     "12",
                txtDistance =  "3.6",
                txtLocation =  "Berlin, Germany",
                urlImage =     "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                numOfRating =  40,
                rating =       4.5f
            ),
            Food(
               txtTitle =     "Grilled chicken",
               txtPrice =     "10",
               txtDistance =  "3.7",
               txtLocation =  "Beijing, China",
               urlImage =     "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
               numOfRating =  15,
               rating =       5f
            ),
            Food(
               txtTitle =     "Baryooni",
               txtPrice =     "16",
               txtDistance =  "10",
               txtLocation =  "Ilam, Iran",
               urlImage =     "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
               numOfRating =  28,
               rating =       4.5f
            ),
            Food(
               txtTitle =     "Ghorme Sabzi",
               txtPrice =     "11.5",
               txtDistance =  "7.5",
               txtLocation =  "Karaj, Iran",
               urlImage =     "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
               numOfRating =  27,
               rating =       5f
            ),
            Food(
               txtTitle =      "Rice",
               txtPrice =      "12.5",
               txtDistance =   "2.4",
               txtLocation =   "Shiraz, Iran",
               urlImage =      "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
               numOfRating =   35,
               rating =        2.5f
            ),
        )
        foodDao.insertAllFoods(foodList)


    }

    private fun showAllData() {

        val foodData = foodDao.getAllFoods()

        myAdapter = FoodAdapter(ArrayList(foodData), this)
        binding.recyclerMain.adapter = myAdapter
        binding.recyclerMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    Log.v("showData",foodData.toString())

    }

    override fun onFoodClicked(food: Food, position: Int) {
//
//    }
//
//    override fun onFoodLongClicked(food: Food, position: Int) {
//
//        val dialog = AlertDialog.Builder(this).create()
//
//        val updateDialogBinding = DialogUpdateItemBinding.inflate(layoutInflater)
//        dialog.setView(updateDialogBinding.root)
//        dialog.setCancelable(true)
//        dialog.show()
//
//
//
//        updateDialogBinding.etUpdateDialogFoodName.setText(food.txtTitle)
//        updateDialogBinding.etUpdateDialogFoodCity.setText(food.txtLocation)
//        updateDialogBinding.etUpdateDialogPrice.setText(food.txtPrice)
//        updateDialogBinding.etUpdateDialogDistance.setText(food.txtDistance)
//
//        updateDialogBinding.btUpdateDialogDone.setOnClickListener {
//
//            if (
//                updateDialogBinding.etUpdateDialogFoodName.length() > 0 &&
//                updateDialogBinding.etUpdateDialogPrice.length() > 0 &&
//                updateDialogBinding.etUpdateDialogDistance.length() > 0 &&
//                updateDialogBinding.etUpdateDialogFoodCity.length() > 0
//            ) {
//
//                val txtName = updateDialogBinding.etUpdateDialogFoodName.text.toString()
//                val txtPrice = updateDialogBinding.etUpdateDialogPrice.text.toString()
//                val txtDistance = updateDialogBinding.etUpdateDialogDistance.text.toString()
//                val txtCity = updateDialogBinding.etUpdateDialogFoodCity.text.toString()
//
//                val newFood = Food(
//                    txtName,
//                    txtPrice,
//                    txtDistance,
//                    txtCity,
//                    food.urlImage,
//                    food.numOfRating,
//                    food.rating)
//
//                myAdapter.updateFood(newFood, position   )
//                dialog.dismiss()
//            }else{
//                Toast.makeText(this, "Please enter all of items ...", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//        updateDialogBinding.btUpdateDialogCancel.setOnClickListener {
//            dialog.dismiss()
//        }
//        updateDialogBinding.btUpdateDialogDelete.setOnClickListener {
//
//            val dialogDelete = AlertDialog.Builder(this).create()
//            val dialogDeleteItemBinding = DialogDeleteItemBinding.inflate(layoutInflater)
//            dialogDelete.setView(dialogDeleteItemBinding.root)
//            dialogDelete.setCancelable(true)
//            dialogDelete.show()
//
//            dialogDeleteItemBinding.btRemoveDialogYes.setOnClickListener {
//
//                myAdapter.removeFood(food, position)
//                dialogDelete.dismiss()
//                dialog.dismiss()
//            }
//            dialogDeleteItemBinding.btRemoveDialogNo.setOnClickListener {
//                dialogDelete.dismiss()
//
//            }
//
//        }
//
//
    }

    override fun onFoodLongClicked(food: Food, position: Int) {
//
//        val dialog = AlertDialog.Builder(this).create()
//        val dialogDeleteItemBinding = DialogDeleteItemBinding.inflate(layoutInflater)
//        dialog.setView(dialogDeleteItemBinding.root)
//        dialog.setCancelable(true)
//        dialog.show()
//
//        dialogDeleteItemBinding.btRemoveDialogYes.setOnClickListener {
//
//            myAdapter.removeFood(food,position)
//            dialog.dismiss()
//        }
//        dialogDeleteItemBinding.btRemoveDialogNo.setOnClickListener {
//            dialog.dismiss()
//        }
//
    }


}