package com.example.mainsalon.adapter
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainsalon.Admin_make.Massaj_add
import com.example.mainsalon.Admin_make.Master_add
import com.example.mainsalon.Massaj
import com.example.mainsalon.Paric
import com.example.mainsalon.account_helper.FullMaster
import com.example.mainsalon.account_helper.FullMasterMAssaj
import com.example.mainsalon.data.ADD
import com.example.mainsalon.data.ADD3
import com.example.mainsalon.dataBase.DbManager
import com.example.mainsalon.dataBase.DbManagerMassaj
import com.example.mainsalon.databinding.ListMassajBinding
class nassaj_res_adapter(val act: Massaj) : RecyclerView.Adapter<nassaj_res_adapter.MasterHolder>() {
    val ADDlist = ArrayList<ADD3>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterHolder {
        val binding =
            ListMassajBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MasterHolder(binding, act)
    }
    override fun getItemCount(): Int {
        return ADDlist.size
    }
    override fun onBindViewHolder(holder: MasterHolder, position: Int) {
        holder.setdata(ADDlist[position])
    }
    fun updateAdapter(newList: List<ADD3>) {
        ADDlist.clear()
        ADDlist.addAll(newList)
        notifyDataSetChanged()
    }
    class MasterHolder(val binding: ListMassajBinding, val act: Massaj) :
        RecyclerView.ViewHolder(binding.root) {
        fun setdata(add: ADD3) = with(binding) {
            textTitleParicName.text = add.name
            textTitleParicExpe.text = add.expence
            textTitleParicWork.text = add.work
            priceWork.text = add.price
            ShowPanel(isOvner(add))
            imageButtonEdit1.setOnClickListener(OnClickEdit(add))
            imageButtonDelete1.setOnClickListener {
                onDeleteClick(add)
            }
            itemView.setOnClickListener {
                val i = Intent(binding.root.context, FullMasterMAssaj::class.java)
                i.putExtra("ADD", add)
                binding.root.context.startActivity(i)
            }
        }
        private fun onDeleteClick(add: ADD3) {
            val dialog = AlertDialog.Builder(act)
                .setTitle("Удаление мастера")
                .setMessage("Вы точно хотите удалить мастера?")
                .setPositiveButton("Да") { _, _ ->
                    // Вызываем метод удаления из DbManager
                    act.dbManager.deleteAd(add, object : DbManagerMassaj.FinishOnWorkListener {
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
        private fun OnClickEdit(add: ADD3): View.OnClickListener {
            return View.OnClickListener {
                val editIntent = Intent(act, Massaj_add::class.java).apply {
                    putExtra(Massaj.EDIT_STATE, true)
                    putExtra(Massaj.ADS_DATA, add)
                }
                act.startActivity(editIntent)
            }
        }
        private fun isOvner(add: ADD3): Boolean {
            val currentUser = act.mAuth.currentUser
            return currentUser?.uid == "dFtpkTgmJsdaxLbAjUMwbpNBf8v2"
        }
        private fun ShowPanel(isOvner: Boolean) {
            if (isOvner) {
                binding.linerDelete.visibility = View.VISIBLE
            } else {
                binding.linerDelete.visibility = View.GONE
            }
        }
    }
}