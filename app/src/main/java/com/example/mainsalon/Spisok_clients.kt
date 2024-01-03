package com.example.mainsalon
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mainsalon.adapter.client_res_adapter
import com.example.mainsalon.data.ADD2
import com.example.mainsalon.dataBase.Clients
import com.example.mainsalon.databinding.SpisokclientovBinding
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
class Spisok_clients : AppCompatActivity(), Clients.ReadDataCallBackClient {
    private lateinit var yourButton: MaterialButton
    private lateinit var spisokclientovBinding: SpisokclientovBinding
    val mAuth = Firebase.auth
    val adapter = client_res_adapter(this)
    val clients = Clients(this, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        spisokclientovBinding = SpisokclientovBinding.inflate(layoutInflater)
        setContentView(spisokclientovBinding.root)
        yourButton = findViewById(R.id.buttonnn_12)
        nice()
        clients.readDataClientFromDB()
        initRecycleView()
    }
    private fun initRecycleView(){
        spisokclientovBinding.apply {
            spisokclientovBinding.rcViewClients.layoutManager = LinearLayoutManager(this@Spisok_clients)
            spisokclientovBinding.rcViewClients.adapter = adapter

        }
    }
    override fun readData(list: List<ADD2>) {
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
}