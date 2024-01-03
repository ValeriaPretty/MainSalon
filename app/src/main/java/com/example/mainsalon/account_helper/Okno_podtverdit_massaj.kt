package com.example.mainsalon.account_helper
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mainsalon.Admin_make.Zapis
import com.example.mainsalon.Admin_make.ZapisMassaj
import com.example.mainsalon.Massaj
import com.example.mainsalon.Paric
import com.example.mainsalon.data.ADD
import com.example.mainsalon.data.ADD3
import com.example.mainsalon.databinding.ActivityOknoPodtverditBinding
class Okno_podtverdit_massaj: AppCompatActivity() {
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
        val receivedAddObject = receivedIntent.getSerializableExtra("AD") as? ADD3
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
            startActivity(Intent(this, Massaj::class.java))
            finish()
        }
    }
    fun nice1(data: String, time: String, count: String, ad: ADD3, name: String, work: String, price: String){
        binding.buttonYaaas.setOnClickListener{
            val intent = Intent(this, ZapisMassaj::class.java).apply {
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
