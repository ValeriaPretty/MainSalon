package com.example.mainsalon.Admin_make
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mainsalon.MainActivity
import com.example.mainsalon.Paric
import com.example.mainsalon.R
import com.example.mainsalon.account_helper.Okno_podtverdit
import com.example.mainsalon.data.ADD
import com.example.mainsalon.dataBase.DbManager
import com.example.mainsalon.databinding.AddParicBinding
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
class Master_add : AppCompatActivity() {
    private val dbManager = DbManager(null)
    private lateinit var yourButton1: MaterialButton
    private lateinit var rootElement: AddParicBinding
    private var isEditState = false
    private var add:ADD? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = AddParicBinding.inflate(layoutInflater)
        setContentView(rootElement.root)
        yourButton1 = findViewById(R.id.button2_nazad)
        nice1()
        checkEfditState()
    }
    private fun checkEfditState(){
        isEditState = isEditState()
        if (isEditState){
            add = (intent.getSerializableExtra(Paric.ADS_DATA)) as ADD
            if (add != null) fiiViews(add!!)
        }
    }
    private fun isEditState(): Boolean{
        return intent.getBooleanExtra(Paric.EDIT_STATE, false)
    }
    fun fiiViews(add: ADD) = with(rootElement){
        addNameOfMaster1.setText(add.name)
        addAgeOfMaster.setText(add.expence)
        addWorkOfMaster2.setText(add.work)
        addPriceOfMaster.setText(add.price)
        data1.setText(add.data1)
        data2.setText(add.data2)
        data3.setText(add.data3)
        timeOfData11.setText(add.time11)
        timeOfData12.setText(add.time12)
        timeOfData13.setText(add.time13)
        timeOfData21.setText(add.time21)
        timeOfData22.setText(add.time22)
        timeOfData23.setText(add.time23)
        timeOfData31.setText(add.time31)
        timeOfData32.setText(add.time32)
        timeOfData33.setText(add.time33)
    }
    fun onclick2(view: View){
        val addTemp = add_master()
        if(isEditState){
            dbManager.publishad(addTemp.copy(key = add?.key), OnPubFin() )
        }else{
            dbManager.publishad(addTemp, OnPubFin())
        }
    }
    private fun OnPubFin(): DbManager.FinishOnWorkListener{
        return object: DbManager.FinishOnWorkListener{
            override fun onFinish() {
                finish()
            }
        }
    }
    fun add_master(): ADD{
        val ad: ADD
        rootElement.apply {
            //val newKey = dbManager.databas.push().key
            ad = ADD(addNameOfMaster1.text.toString(),
                addAgeOfMaster.text.toString(),
                addWorkOfMaster2.text.toString(),
                addPriceOfMaster.text.toString(),
                data1.text.toString(),
                data2.text.toString(),
                data3.text.toString(),
                timeOfData11.text.toString(),
                timeOfData12.text.toString(),
                timeOfData13.text.toString(),
                timeOfData21.text.toString(),
                timeOfData22.text.toString(),
                timeOfData23.text.toString(),
                timeOfData31.text.toString(),
                timeOfData32.text.toString(),
                timeOfData33.text.toString(), dbManager.auth.uid)
        }
        return ad
    }
    fun nice1(){
        yourButton1.setOnClickListener {
            startNewActivity1()
        }
    }
    fun startNewActivity1() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}