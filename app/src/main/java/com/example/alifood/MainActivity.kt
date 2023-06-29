package com.example.alifood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alifood.databinding.ActivityMainBinding
import com.example.alifood.databinding.DialogAddNewItemBinding
import com.example.alifood.databinding.DialogDeleteItemBinding
import com.example.alifood.databinding.DialogUpdateItemBinding


class MainActivity : AppCompatActivity(), FoodAdapter.FoodEvents {

    private lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: FoodAdapter
    lateinit var foodList: ArrayList<Food>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // how to use recycler view :
        // 1. create view of recyclerView in activity_main.xml
        // 2. create item for recyclerView
        // 3. create adapter and view holder for recyclerView
        // 4. set adapter to recyclerView and set layout manager

         foodList = arrayListOf(
            Food(
                "Hamburger",
                "15",
                "3",
                "Isfahan, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                20,
                4.5f
            ),
            Food(
                "Grilled fish",
                "20",
                "2.1",
                "Tehran, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                10,
                4f
            ),
            Food(
                "Lasania",
                "40",
                "1.4",
                "Isfahan, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                30,
                2f
            ),
            Food(
                "pizza",
                "10",
                "2.5",
                "Zahedan, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                80,
                1.5f
            ),
            Food(
                "Sushi",
                "20",
                "3.2",
                "Mashhad, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                200,
                3f
            ),
            Food(
                "Roasted Fish",
                "40",
                "3.7",
                "Jolfa, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                50,
                3.5f
            ),
            Food(
                "Fried chicken",
                "70",
                "3.5",
                "NewYork, USA",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                70,
                2.5f
            ),
            Food(
                "Vegetable salad",
                "12",
                "3.6",
                "Berlin, Germany",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                40,
                4.5f
            ),
            Food(
                "Grilled chicken",
                "10",
                "3.7",
                "Beijing, China",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                15,
                5f
            ),
            Food(
                "Baryooni",
                "16",
                "10",
                "Ilam, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
                28,
                4.5f
            ),
            Food(
                "Ghorme Sabzi",
                "11.5",
                "7.5",
                "Karaj, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
                27,
                5f
            ),
            Food(
                "Rice",
                "12.5",
                "2.4",
                "Shiraz, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
                35,
                2.5f
            ),
        )

        myAdapter = FoodAdapter(foodList.clone() as ArrayList<Food> , this)
        binding.recyclerMain.adapter = myAdapter
        binding.recyclerMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.btAddNewFood.setOnClickListener {

            val dialog = AlertDialog.Builder(this).create()
            val dialogBinding = DialogAddNewItemBinding.inflate(layoutInflater)
            dialog.setView(dialogBinding.root)
            dialog.setCancelable(true)
            dialog.show()

            dialogBinding.btDialogDone.setOnClickListener {

                if (
                    dialogBinding.etDialogFoodName.length() > 0 &&
                    dialogBinding.etDialogPrice.length() > 0 &&
                    dialogBinding.etDialogFoodCity.length() > 0 &&
                    dialogBinding.etDialogDistance.length() > 0
                ) {

                    val txtName = dialogBinding.etDialogFoodName.text.toString()
                    val txtPrice = dialogBinding.etDialogPrice.text.toString()
                    val txtDistance = dialogBinding.etDialogDistance.text.toString()
                    val txtCity = dialogBinding.etDialogFoodCity.text.toString()
                    val ratingBarStar: Float = (1..5).random().toFloat()
                    val txtRatingNum: Int = (1..150).random()
                    val randomForUrl = (0 until 12).random()
                    val urlPic = foodList[randomForUrl].urlImage
                    val newFood = Food(
                        txtName,
                        txtPrice,
                        txtDistance,
                        txtCity,
                        urlPic,
                        txtRatingNum,
                        ratingBarStar
                    )
                    myAdapter.addFood(newFood)
                    binding.recyclerMain.scrollToPosition(0)
                    dialog.dismiss()

                } else {
                    Toast.makeText(this, "Please enter all of items ...", Toast.LENGTH_SHORT).show()
                }
            }
            dialogBinding.btDialogCancel.setOnClickListener {
                dialog.dismiss()
            }


        }

        binding.btSearch.addTextChangedListener {editTextInput ->

            if (editTextInput!!.isNotEmpty()){
                //filter data
                val cloneList = foodList.clone() as ArrayList<Food>
                val filteredList = cloneList.filter { foodItem ->
                    foodItem.txtTitle.contains(editTextInput)
                }
                myAdapter.setData(ArrayList(filteredList))

            }else{
                //show all data :
                myAdapter.setData(foodList.clone() as ArrayList<Food>)
            }




        }



    }



    override fun onFoodClicked(food: Food, position: Int) {

    }

    override fun onFoodLongClicked(food: Food , position: Int) {

        val dialog = AlertDialog.Builder(this).create()

        val updateDialogBinding = DialogUpdateItemBinding.inflate(layoutInflater)
        dialog.setView(updateDialogBinding.root)
        dialog.setCancelable(true)
        dialog.show()



        updateDialogBinding.etUpdateDialogFoodName.setText(food.txtTitle)
        updateDialogBinding.etUpdateDialogFoodCity.setText(food.txtLocation)
        updateDialogBinding.etUpdateDialogPrice.setText(food.txtPrice)
        updateDialogBinding.etUpdateDialogDistance.setText(food.txtDistance)

        updateDialogBinding.btUpdateDialogDone.setOnClickListener {

            if (
                updateDialogBinding.etUpdateDialogFoodName.length() > 0 &&
                updateDialogBinding.etUpdateDialogPrice.length() > 0 &&
                updateDialogBinding.etUpdateDialogDistance.length() > 0 &&
                updateDialogBinding.etUpdateDialogFoodCity.length() > 0
            ) {

                val txtName = updateDialogBinding.etUpdateDialogFoodName.text.toString()
                val txtPrice = updateDialogBinding.etUpdateDialogPrice.text.toString()
                val txtDistance = updateDialogBinding.etUpdateDialogDistance.text.toString()
                val txtCity = updateDialogBinding.etUpdateDialogFoodCity.text.toString()

                val newFood = Food(
                    txtName,
                    txtPrice,
                    txtDistance,
                    txtCity,
                    food.urlImage,
                    food.numOfRating,
                    food.rating)

                myAdapter.updateFood(newFood, position   )
                dialog.dismiss()
            }else{
                Toast.makeText(this, "Please enter all of items ...", Toast.LENGTH_SHORT).show()
            }
            
        }
        updateDialogBinding.btUpdateDialogCancel.setOnClickListener {
            dialog.dismiss()
        }
        updateDialogBinding.btUpdateDialogDelete.setOnClickListener {

            val dialogDelete = AlertDialog.Builder(this).create()
            val dialogDeleteItemBinding = DialogDeleteItemBinding.inflate(layoutInflater)
            dialogDelete.setView(dialogDeleteItemBinding.root)
            dialogDelete.setCancelable(true)
            dialogDelete.show()

            dialogDeleteItemBinding.btRemoveDialogYes.setOnClickListener {

                myAdapter.removeFood(food, position)
                dialogDelete.dismiss()
                dialog.dismiss()
            }
            dialogDeleteItemBinding.btRemoveDialogNo.setOnClickListener {
                dialogDelete.dismiss()

            }

        }


    }

//    override fun onFoodLongClicked(food: Food, position: Int) {
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
//    }


}