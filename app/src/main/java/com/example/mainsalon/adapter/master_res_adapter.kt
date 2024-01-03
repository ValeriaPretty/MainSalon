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
import com.example.mainsalon.account_helper.FullMaster
import com.example.mainsalon.data.ADD
import com.example.mainsalon.dataBase.DbManager
import com.example.mainsalon.databinding.ActivityFullMasterBinding
import com.example.mainsalon.databinding.MasterListParicBinding
import com.google.firebase.auth.FirebaseAuth
class master_res_adapter(val act: Paric): RecyclerView.Adapter<master_res_adapter.MasterHolder>() {
        val ADDlist = ArrayList<ADD>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterHolder {
        val binding = MasterListParicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MasterHolder(binding, act)
    }
    override fun getItemCount(): Int {
        return ADDlist.size
    }
    override fun onBindViewHolder(holder: MasterHolder, position: Int) {
        holder.setdata(ADDlist[position])
    }
    fun updateAdapter(newList: List<ADD>){
        ADDlist.clear()
        ADDlist.addAll(newList)
        notifyDataSetChanged()
    }
    class MasterHolder(val binding: MasterListParicBinding,val act: Paric): RecyclerView.ViewHolder(binding.root) {
        fun setdata(add: ADD) = with(binding) {
            textTitleParicName.text = add.name
            textTitleParicExpe.text = add.expence
            textTitleParicWork.text = add.work
            priceWork.text = add.price
            ShowPanel(isOvner(add))
            imageButtonEdit.setOnClickListener(OnClickEdit(add))
            imageButtonDelete.setOnClickListener {
                onDeleteClick(add)
            }
            itemView.setOnClickListener{
                val i = Intent(binding.root.context, FullMaster::class.java)
                i.putExtra("ADD", add)
                binding.root.context.startActivity(i)
            }

        }
        private fun onDeleteClick(add: ADD) {
            val dialog = AlertDialog.Builder(act)
                .setTitle("Удаление мастера")
                .setMessage("Вы точно хотите удалить мастера?")
                .setPositiveButton("Да") { _, _ ->
                    // Вызываем метод удаления из DbManager
                    act.dbManager.deleteAd(add, object : DbManager.FinishOnWorkListener {
                        override fun onFinish() {
                            // Обновляем список после удаления
                            act.dbManager.readDataFromDB()
                        }
                    })
                }
                .setNegativeButton("Нет", null)
                .create()
            dialog.show()
        }
        private fun OnClickEdit(add: ADD): View.OnClickListener{
            return  View.OnClickListener {
                val editIntent = Intent(act, Master_add::class.java).apply {
                    putExtra(Paric.EDIT_STATE, true)
                    putExtra(Paric.ADS_DATA, add)
                }
                act.startActivity(editIntent)
            }
        }
        private fun isOvner(add: ADD): Boolean {
            val currentUser = act.mAuth.currentUser
            return currentUser?.uid == "dFtpkTgmJsdaxLbAjUMwbpNBf8v2"
        }
        private fun ShowPanel(isOvner: Boolean){
            if(isOvner){
                binding.linerDelete.visibility = View.VISIBLE
            }else{
                binding.linerDelete.visibility = View.GONE
            }
        }
    }

}


