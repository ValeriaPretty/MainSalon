package com.example.mainsalon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mainsalon.adapter.master_res_adapter
import com.example.mainsalon.data.ADD
import com.example.mainsalon.dataBase.DbManager
import com.example.mainsalon.dataBase.ReadDataCallBack
import com.example.mainsalon.databinding.ActivityParicBinding
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Paric : AppCompatActivity(), ReadDataCallBack {
    private lateinit var yourButton: MaterialButton
    private lateinit var activityParicBinding: ActivityParicBinding
    val mAuth = Firebase.auth
    val adapter = master_res_adapter(this)
    val dbManager = DbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityParicBinding = ActivityParicBinding.inflate(layoutInflater)
        setContentView(activityParicBinding.root)
        yourButton = findViewById(R.id.buttonnn)
        nice()
        dbManager.readDataFromDB()
        initRecycleView()

    }
    private fun initRecycleView(){
        activityParicBinding.apply {
            activityParicBinding.rcViewParic.layoutManager = LinearLayoutManager(this@Paric)
            activityParicBinding.rcViewParic.adapter = adapter
        }
    }
    fun nice(){
        yourButton.setOnClickListener {
            startNewActivity()
        }
    }
    fun startNewActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun readData(list: List<ADD>) {
        Log.d("Paric", "readData called with data: $list")
        adapter.updateAdapter(list)
    }

    companion object{
        const val EDIT_STATE = "edit_state"
        const val ADS_DATA = "ads_data"


    }


}