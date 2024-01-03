package com.example.mainsalon.Admin_make
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mainsalon.MainActivity
import com.example.mainsalon.MakeUp
import com.example.mainsalon.Massaj
import com.example.mainsalon.Paric
import com.example.mainsalon.R
import com.example.mainsalon.data.ADD3
import com.example.mainsalon.data.ADD4
import com.example.mainsalon.dataBase.DbManagerMake
import com.example.mainsalon.dataBase.DbManagerMassaj
import com.example.mainsalon.databinding.AddMakeBinding
import com.example.mainsalon.databinding.AddMassajBinding
import com.google.android.material.button.MaterialButton
class Make_add : AppCompatActivity() {
    private val dbManagerMas = DbManagerMake(null)
    private lateinit var yourButton1: MaterialButton
    private lateinit var rootElement: AddMakeBinding
    private var isEditState = false
    private var add: ADD4? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = AddMakeBinding.inflate(layoutInflater)
        setContentView(rootElement.root)
        yourButton1 = findViewById(R.id.button2_nazad)
        nice1()
        checkEfditState()
    }
    private fun checkEfditState() {
        isEditState = isEditState()
        if (isEditState) {
            add = (intent.getSerializableExtra(MakeUp.ADS_DATA)) as ADD4
            if (add != null) fiiViews(add!!)
        }
    }
    private fun isEditState(): Boolean {
        return intent.getBooleanExtra(MakeUp.EDIT_STATE, false)
    }
    fun fiiViews(add: ADD4) = with(rootElement) {
        addNameOfMaster1Massaj.setText(add.name)
        addAgeOfMasterMassaj.setText(add.expence)
        addWorkOfMaster2Massaj.setText(add.work)
        addPriceOfMasterMassaj.setText(add.price)
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
    fun onclick0(view: View) {
        val addTemp = add_master()
        if (isEditState) {
            dbManagerMas.publishadd(addTemp.copy(key = add?.key), OnPubFin())
        } else {
            dbManagerMas.publishadd(addTemp, OnPubFin())
        }
    }
    private fun OnPubFin(): DbManagerMake.FinishOnWorkListener {
        return object : DbManagerMake.FinishOnWorkListener {
            override fun onFinish() {
                finish()
            }
        }
    }
    fun add_master(): ADD4 {
        val ad: ADD4
        rootElement.apply {
            ad = ADD4(
                addNameOfMaster1Massaj.text.toString(),
                addAgeOfMasterMassaj.text.toString(),
                addWorkOfMaster2Massaj.text.toString(),
                addPriceOfMasterMassaj.text.toString(),
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
                timeOfData33.text.toString(), dbManagerMas.auth.uid
            )
        }
        return ad
    }
    fun nice1() {
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