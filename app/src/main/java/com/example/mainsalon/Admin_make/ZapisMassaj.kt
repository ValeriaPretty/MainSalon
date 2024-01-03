package com.example.mainsalon.Admin_make
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mainsalon.Massaj
import com.example.mainsalon.account_helper.FullMasterMAssaj
import com.example.mainsalon.data.ADD2
import com.example.mainsalon.data.ADD3
import com.example.mainsalon.dataBase.ClientsMassaj
import com.example.mainsalon.dataBase.DbManagerMassaj
import com.example.mainsalon.databinding.ZapismassajBinding
class ZapisMassaj: AppCompatActivity(), ClientsMassaj.ReadDataCallBackClient {
    private val clients = ClientsMassaj(this, this)
    private lateinit var rootElement:    ZapismassajBinding
    private val dbManager = DbManagerMassaj(null)
    private var isEditState = false
    private var add:ADD2? = null
    val masterAdd =  Massaj_add()
    val full = FullMasterMAssaj()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ZapismassajBinding.inflate(layoutInflater)
        setContentView(rootElement.root)
        nice1()
    }
    fun onclick6(view: View){
        val receivedIntent = intent
        val receivedAddObject = receivedIntent.getSerializableExtra("AD") as? ADD3
        val addTemp = add_master()
        clients.publishClients(addTemp, OnPubFin(), receivedAddObject!!)
    }
    private fun OnPubFin(): ClientsMassaj.FinishOnWorkListenerClient{
        return object: ClientsMassaj.FinishOnWorkListenerClient{
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
        val intent = Intent(this, Massaj::class.java)
        startActivity(intent)
        finish()
    }
    override fun readData(list: List<ADD2>) {
        TODO("Not yet implemented")
    }
}