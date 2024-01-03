package com.example.mainsalon.account_helper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mainsalon.Admin_make.Master_add
import com.example.mainsalon.Admin_make.Zapis
import com.example.mainsalon.MainActivity
import com.example.mainsalon.Paric
import com.example.mainsalon.R
import com.example.mainsalon.data.ADD
import com.example.mainsalon.databinding.ActivityFullMasterBinding
import com.example.mainsalon.databinding.ActivityOknoPodtverditBinding
import com.example.mainsalon.databinding.MasterListParicBinding
import java.io.Serializable
class Okno_podtverdit : AppCompatActivity() {
    lateinit var binding: ActivityOknoPodtverditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOknoPodtverditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        nice()
    }
    private fun init() {
        getIntentFromFullMaster()
    }
    private fun getIntentFromFullMaster() {
        val data = intent.getStringExtra("DATA")
        val time = intent.getStringExtra("TIME")
        val count = intent.getStringExtra("COUNT")
        val price = intent.getStringExtra("PRICE")
        val receivedIntent = intent
        val receivedAddObject = receivedIntent.getSerializableExtra("AD") as? ADD
        val name = intent.getStringExtra("NAME")
        val work = intent.getStringExtra("WORK")
        if (data != null && time != null && count != null && name != null && work != null && price!= null) {
            updateUI(data, time)
            nice1(data, time, count, receivedAddObject!!, name, work, price)
        }
    }
    private fun updateUI(data: String, time: String) {
        binding.datadata2.text = data
        binding.timetime.text = time
    }
    fun nice(){
        binding.buttonNooo.setOnClickListener{
            startActivity(Intent(this, Paric::class.java))
            finish()
        }
    }
    fun nice1(data: String, time: String, count: String, ad: ADD, name: String, work: String, price: String){
        binding.buttonYaaas.setOnClickListener{
            val intent = Intent(this, Zapis::class.java).apply {
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
    }
}