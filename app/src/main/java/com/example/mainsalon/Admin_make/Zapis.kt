package com.example.mainsalon.Admin_make
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mainsalon.Paric
import com.example.mainsalon.R
import com.example.mainsalon.Spisok_clients
import com.example.mainsalon.account_helper.FullMaster
import com.example.mainsalon.data.ADD
import com.example.mainsalon.data.ADD2
import com.example.mainsalon.dataBase.Clients
import com.example.mainsalon.dataBase.DbManager

import com.example.mainsalon.databinding.ZapisBinding
class Zapis: AppCompatActivity(), Clients.ReadDataCallBackClient {
    private val clients = Clients(this, this)
    private lateinit var rootElement: ZapisBinding
    private val dbManager = DbManager(null)
    private var isEditState = false
    private var add:ADD2? = null
    val masterAdd =  Master_add()
    val full = FullMaster()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ZapisBinding.inflate(layoutInflater)
        setContentView(rootElement.root)
        nice1()
    }
    fun onclick23(view: View){
        val receivedIntent = intent
        val receivedAddObject = receivedIntent.getSerializableExtra("AD") as? ADD
        val addTemp = add_master()
        clients.publishClients(addTemp, OnPubFin(), receivedAddObject!!)
    }
    private fun OnPubFin(): Clients.FinishOnWorkListenerClient{
        return object: Clients.FinishOnWorkListenerClient{
            override fun onFinish() {
                finish()
            }
        }
    }
    private fun add_master(): ADD2 {
        val ad: ADD2
        val email = clients.email.toString()
        val data = intent.getStringExtra("DATA")
        val time = intent.getStringExtra("TIME")
        val count = intent.getStringExtra("COUNT" )
        val name = intent.getStringExtra("NAME")
        val work = intent.getStringExtra("WORK")
        val price = intent.getStringExtra("PRICE")
        rootElement.apply {
            //val newKey = dbManager.databas.push().key
            ad = ADD2(namelastnameId.text.toString(),
                phoneId.text.toString(), email, data, time, count, name, work, price, clients.auth.uid)
        }
        return ad
    }
    fun nice1(){
        rootElement.nazadId.setOnClickListener {
            startNewActivity1()
        }
    }
    fun startNewActivity1() {
        val intent = Intent(this, Paric::class.java)
        startActivity(intent)
        finish()
    }
    override fun readData(list: List<ADD2>) {
        TODO("Not yet implemented")
    }
}