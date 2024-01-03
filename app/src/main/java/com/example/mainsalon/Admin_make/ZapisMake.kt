package com.example.mainsalon.Admin_make
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mainsalon.MakeUp
import com.example.mainsalon.account_helper.FullMasterMake
import com.example.mainsalon.data.ADD2
import com.example.mainsalon.data.ADD4
import com.example.mainsalon.dataBase.ClientsMake
import com.example.mainsalon.dataBase.DbManagerMake
import com.example.mainsalon.databinding.ZapismakeBinding
class ZapisMake: AppCompatActivity(), ClientsMake.ReadDataCallBackClient {
    private val clients = ClientsMake(this, this)
    private lateinit var rootElement:    ZapismakeBinding
    private val dbManager = DbManagerMake(null)
    private var isEditState = false
    private var add:ADD2? = null
    val masterAdd =  Make_add()
    val full = FullMasterMake()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ZapismakeBinding.inflate(layoutInflater)
        setContentView(rootElement.root)
        nice1()
    }
    fun onclick66(view: View){
        val receivedIntent = intent
        val receivedAddObject = receivedIntent.getSerializableExtra("AD") as? ADD4
        val addTemp = add_master()
        clients.publishClients(addTemp, OnPubFin(), receivedAddObject!!)
    }
    private fun OnPubFin(): ClientsMake.FinishOnWorkListenerClient{
        return object: ClientsMake.FinishOnWorkListenerClient{
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
        val intent = Intent(this, MakeUp::class.java)
        startActivity(intent)
        finish()
    }
    override fun readData(list: List<ADD2>) {
        TODO("Not yet implemented")
    }
}