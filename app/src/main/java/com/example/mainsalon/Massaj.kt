package com.example.mainsalon
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mainsalon.adapter.nassaj_res_adapter
import com.example.mainsalon.data.ADD3
import com.example.mainsalon.dataBase.DbManagerMassaj
import com.example.mainsalon.databinding.ActivityMassajBinding
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
class Massaj : AppCompatActivity(), DbManagerMassaj.ReadDataCallBack {
    private lateinit var yourButton: MaterialButton
    private lateinit var activityMassajBinding: ActivityMassajBinding
    val mAuth = Firebase.auth
    val adapter = nassaj_res_adapter(this)
    val dbManager = DbManagerMassaj(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMassajBinding = ActivityMassajBinding.inflate(layoutInflater)
        setContentView(activityMassajBinding.root)
        yourButton = findViewById(R.id.buttonnn)
        nice()
        dbManager.readDataFromDB()
        initRecycleView()
    }
    private fun initRecycleView(){
        activityMassajBinding.apply {
            activityMassajBinding.rcViewParic.layoutManager = LinearLayoutManager(this@Massaj)
            activityMassajBinding.rcViewParic.adapter = adapter
        }
    }
    override fun readData(list: List<ADD3>) {
        adapter.updateAdapter(list)
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
    companion object{
        const val EDIT_STATE = "edit_state"
        const val ADS_DATA = "ads_data"
    }
}