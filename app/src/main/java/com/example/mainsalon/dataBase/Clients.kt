package com.example.mainsalon.dataBase
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.mainsalon.Paric
import com.example.mainsalon.account_helper.FullMaster
import com.example.mainsalon.data.ADD
import com.example.mainsalon.data.ADD2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
class Clients(val context: Context, val readDataCallBack: ReadDataCallBackClient) {
    val db = Firebase.database.getReference("clients")
    val databas = Firebase.database.getReference("main") // указывает на путь
    val fullMaster = FullMaster()
    val auth = Firebase.auth
    val user = FirebaseAuth.getInstance().currentUser
    val email = user!!.email
    fun publishClients( ad: ADD2, finishListener:FinishOnWorkListenerClient, add:ADD) {
        if (!ad.name_and_lastname.isNullOrEmpty() && !ad.phone.isNullOrEmpty()) {
            val key = ad.key ?: db.push().key.orEmpty()
            db.child(key).child(auth.uid!!).child("client_data").setValue(ad.copy(key = key))
                .addOnCanceledListener {
                    finishListener.onFinish()
                }
            Toast.makeText(context, "Вы записаны", Toast.LENGTH_SHORT).show()
            if (context is Activity) {
                context.finish()
            }
            deleteButton(add, ad.count.toString(), object : FinishOnWorkListenerClient {
                override fun onFinish() {
                }
            })
            startNewActivity(context)
        }    else { Toast.makeText(context, "Введите данные", Toast.LENGTH_SHORT).show() }
    }
    fun deleteButton(add: ADD, count: String, finishListener: FinishOnWorkListenerClient) {
        if (add.key == null || add.uid == null) return
        databas.child(add.key).child(add.uid).child("master").child(count).removeValue().addOnCanceledListener {
            finishListener.onFinish()
        }
    }
    fun deleteclient(add: ADD2, finishListener: FinishOnWorkListenerClient) {
        if (add.key == null || add.uid == null) return
        db.child(add.key).child(add.uid).removeValue().addOnCanceledListener {
            finishListener.onFinish()
        }
    }
    fun startNewActivity(context: Context) {
        val intent = Intent(context, Paric::class.java)
        context.startActivity(intent)
    }
    fun readDataClientFromDB(){
        db.addListenerForSingleValueEvent(object: ValueEventListener { // один раз с этого пути считывает, само по себе не считывает
            override fun onDataChange(snapshot: DataSnapshot) {
                val ADDlist = ArrayList<ADD2>()
                for (item in snapshot.children) {
                    val client_data = item.children.iterator().next().child("client_data").getValue(ADD2::class.java)
                    if (client_data != null) ADDlist.add(client_data)
                }
                readDataCallBack?.readData(ADDlist)
            }
            override fun onCancelled(error: DatabaseError) {}
        } )
    }
    interface ReadDataCallBackClient {
        fun readData(list: List<ADD2>)
    }
    interface FinishOnWorkListenerClient{
        fun onFinish()
    }
}