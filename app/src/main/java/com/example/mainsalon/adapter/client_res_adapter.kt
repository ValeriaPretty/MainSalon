package com.example.mainsalon.adapter
import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainsalon.Admin_make.Master_add
import com.example.mainsalon.Paric
import com.example.mainsalon.Spisok_clients
import com.example.mainsalon.account_helper.FullMaster
import com.example.mainsalon.data.ADD
import com.example.mainsalon.data.ADD2
import com.example.mainsalon.dataBase.Clients
import com.example.mainsalon.dataBase.DbManager
import com.example.mainsalon.databinding.ActivityFullMasterBinding
import com.example.mainsalon.databinding.ClientListBinding
import com.example.mainsalon.databinding.MasterListParicBinding
import com.example.mainsalon.databinding.SpisokclientovBinding
import com.google.firebase.auth.FirebaseAuth
class client_res_adapter(val act: Spisok_clients): RecyclerView.Adapter<client_res_adapter.MasterHolder>() {
    val ADDlist = ArrayList<ADD2>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterHolder {
        val binding = ClientListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MasterHolder(binding, act)
    }
    override fun getItemCount(): Int {
        return ADDlist.size
    }
    override fun onBindViewHolder(holder: MasterHolder, position: Int) {
        holder.setdata(ADDlist[position])
    }
    fun updateAdapter(newList: List<ADD2>){
        ADDlist.clear()
        ADDlist.addAll(newList)
        notifyDataSetChanged()
    }
    class MasterHolder(val binding: ClientListBinding,val act: Spisok_clients): RecyclerView.ViewHolder(binding.root) {
        fun setdata(add: ADD2) = with(binding) {
            clientName.text = add.name_and_lastname
            clientPhone.text = add.phone
            clientEmail.text = add.email
            dataClient.text = add.data
            timeClient.text = add.time
            masterName.text = add.name
            masterWork.text = add.work
            price.text = add.price
            imageButtonDeleteClient.setOnClickListener {
                onDeleteClick(add)
            }
        }
        private fun onDeleteClick(add: ADD2) {
            val dialog = AlertDialog.Builder(act)
                .setTitle("Удаление клиента")
                .setMessage("Вы точно хотите удалить клиента?")
                .setPositiveButton("Да") { _, _ ->
                    // Вызываем метод удаления из DbManager
                    act.clients.deleteclient(add, object : Clients.FinishOnWorkListenerClient {
                        override fun onFinish() {
                            // Обновляем список после удаления
                            act.clients.readDataClientFromDB()
                        }
                    })
                }
                .setNegativeButton("Нет", null)
                .create()
            dialog.show()
        }
    }
}


