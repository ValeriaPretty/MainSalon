package com.example.mainsalon.account_helper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mainsalon.Massaj
import com.example.mainsalon.R
import com.example.mainsalon.data.ADD3
import com.example.mainsalon.databinding.ActivityFullMasterBinding
import com.google.firebase.auth.FirebaseAuth
class FullMasterMAssaj : AppCompatActivity() {
    lateinit var binding: ActivityFullMasterBinding
    var lastbutton: String? = null
    val ad: ADD3? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullMasterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        nice()
    }
    private fun init(){
        getIntentFrromParic()
    }
    private fun getIntentFrromParic(){
        val add = intent.getSerializableExtra("ADD") as ADD3
        upDateUI(add)
        nice2(add)
    }
    private fun upDateUI(add: ADD3){
        fillTextViews(add)
    }
    fun fillTextViews(add: ADD3) = with(binding){
        namename.text = add.name
        workwork.text = add.work
        years.text = add.expence
        pricePrice.text = add.price
        dataZapisi1.text = add.data1
        dataZapisi2.text = add.data2
        dataZapisi3.text = add.data3
        timeZapisi11.text= add.time11
        timeZapisi12.text= add.time12
        timeZapisi13.text= add.time13
        timeZapisi21.text= add.time21
        timeZapisi22.text= add.time22
        timeZapisi23.text= add.time23
        timeZapisi31.text= add.time31
        timeZapisi32.text= add.time32
        timeZapisi33.text= add.time33
    }

    fun nice() {
        binding.nazadParicc.setOnClickListener {
            startNewActivity()
        }
    }
    fun startNewActivity() {
        val intent = Intent(this, Massaj::class.java)
        startActivity(intent)
        finish()
    }
    private fun nice2(add: ADD3) {
        binding.timeZapisi11.setOnClickListener {
            val lastbutton = "time11"
            Log.d("MyTag", "Button ID: ${lastbutton}")
            if (isUserAuthenticated()) {
                if (!add.data1.isNullOrEmpty() && !add.time11.isNullOrEmpty()) {
                    navigateToOknoPodtverdit(add.data1.toString(), add.time11.toString(), lastbutton, add, add.name.toString(), add.work.toString(), add.price.toString())
                }
            }else{
                Toast.makeText(this, this.resources.getString(R.string.auth), Toast.LENGTH_LONG ).show()
            }
        }
        binding.timeZapisi12.setOnClickListener {
            val lastbutton = "time12"
            if (isUserAuthenticated()) {
                if (!add.data1.isNullOrEmpty() && !add.time12.isNullOrEmpty() && isUserAuthenticated()) {
                    navigateToOknoPodtverdit(add.data1.toString(), add.time12.toString(), lastbutton,  add, add.name.toString(), add.work.toString(), add.price.toString())
                }
            }else{
                Toast.makeText(this, this.resources.getString(R.string.auth), Toast.LENGTH_LONG ).show()
            }
        }
        binding.timeZapisi13.setOnClickListener {
            val lastbutton = "time13"
            if (isUserAuthenticated()) {
                if (!add.data1.isNullOrEmpty() && !add.time13.isNullOrEmpty() && isUserAuthenticated()) {
                    navigateToOknoPodtverdit(add.data1.toString(), add.time13.toString(), lastbutton, add, add.name.toString(), add.work.toString(), add.price.toString())
                }
            }else{
                Toast.makeText(this, this.resources.getString(R.string.auth), Toast.LENGTH_LONG ).show()
            }
        }
        binding.timeZapisi21.setOnClickListener {
            val lastbutton = "time21"
            if (isUserAuthenticated()) {
                if (!add.data2.isNullOrEmpty() && !add.time21.isNullOrEmpty() && isUserAuthenticated()) {
                    navigateToOknoPodtverdit(add.data2.toString(), add.time21.toString(), lastbutton, add, add.name.toString(), add.work.toString(), add.price.toString())

                }
            }else{
                Toast.makeText(this, this.resources.getString(R.string.auth), Toast.LENGTH_LONG ).show()
            }
        }
        binding.timeZapisi22.setOnClickListener {
            val lastbutton = "time22"
            if (isUserAuthenticated()) {
                if (!add.data2.isNullOrEmpty() && !add.time22.isNullOrEmpty() && isUserAuthenticated()) {
                    navigateToOknoPodtverdit(add.data2.toString(), add.time22.toString(), lastbutton, add, add.name.toString(), add.work.toString(), add.price.toString())

                }
            }else{
                Toast.makeText(this, this.resources.getString(R.string.auth), Toast.LENGTH_LONG ).show()
            }
        }
        binding.timeZapisi23.setOnClickListener {
            val lastbutton = "time23"
            if (isUserAuthenticated()) {
                if (!add.data2.isNullOrEmpty() && !add.time23.isNullOrEmpty() && isUserAuthenticated()) {
                    navigateToOknoPodtverdit(add.data2.toString(), add.time23.toString(),lastbutton, add, add.name.toString(), add.work.toString(), add.price.toString())

                }
            }else{
                Toast.makeText(this, this.resources.getString(R.string.auth), Toast.LENGTH_LONG ).show()
            }
        }
        binding.timeZapisi31.setOnClickListener {
            val lastbutton = "time31"

            if (isUserAuthenticated()) {
                if (!add.data3.isNullOrEmpty() && !add.time31.isNullOrEmpty() && isUserAuthenticated()) {
                    navigateToOknoPodtverdit(add.data3.toString(), add.time31.toString(), lastbutton, add, add.name.toString(), add.work.toString(), add.price.toString())

                }
            }else{
                Toast.makeText(this, this.resources.getString(R.string.auth), Toast.LENGTH_LONG ).show()
            }
        }
        binding.timeZapisi32.setOnClickListener {
            val lastbutton = "time32"

            if (isUserAuthenticated()) {
                if (!add.data3.isNullOrEmpty() && !add.time32.isNullOrEmpty() && isUserAuthenticated()) {
                    navigateToOknoPodtverdit(add.data3.toString(), add.time32.toString(), lastbutton, add, add.name.toString(), add.work.toString(), add.price.toString())
                }
            }else{
                Toast.makeText(this, this.resources.getString(R.string.auth), Toast.LENGTH_LONG ).show()
            }
        }
        binding.timeZapisi33.setOnClickListener {
            val lastbutton = "time33"

            if (isUserAuthenticated()) {
                if (!add.data3.isNullOrEmpty() && !add.time33.isNullOrEmpty() && isUserAuthenticated()) {
                    navigateToOknoPodtverdit(add.data3.toString(), add.time33.toString(),lastbutton, add, add.name.toString(), add.work.toString(), add.price.toString())

                }
            }else{
                Toast.makeText(this, this.resources.getString(R.string.auth), Toast.LENGTH_LONG ).show()
            }
        }
    }
    private fun navigateToOknoPodtverdit(data: String, time: String, count: String, ad:ADD3, name: String, work: String, price: String) {
        val intent = Intent(this, Okno_podtverdit_massaj::class.java).apply {
            putExtra("DATA", data)
            putExtra("TIME", time)
            putExtra("COUNT", count)
            putExtra("AD", ad)
            putExtra("NAME", name)
            putExtra("WORK", work)
            putExtra("PRICE", price)
        }
        startActivity(intent)
    }
    fun isUserAuthenticated(): Boolean {
        val user = FirebaseAuth.getInstance().currentUser
        return user != null
    }
}