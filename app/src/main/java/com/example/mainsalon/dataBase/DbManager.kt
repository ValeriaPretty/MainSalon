package com.example.mainsalon.dataBase
import com.example.mainsalon.data.ADD
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database
class DbManager(val readDataCallBack: ReadDataCallBack?) {
    val databas = Firebase.database.getReference("main") // указывает на путь
    val auth = Firebase.auth
    fun publishad(ad: ADD,  finishListener: FinishOnWorkListener) {
        val key = ad.key ?: databas.push().key.orEmpty()
        databas.child(key).child(auth.uid!!).child("master")
            .setValue(ad.copy(key = key)).addOnCanceledListener {
                finishListener.onFinish()
            }
    }
    fun deleteAd(add: ADD, finishListener: FinishOnWorkListener) {
        if (add.key == null || add.uid == null) return
        databas.child(add.key).child(add.uid).removeValue().addOnCanceledListener {
            finishListener.onFinish()
        }
    }
    fun deleteButton(add: ADD, finishListener: FinishOnWorkListener) {
        if (add.key == null || add.uid == null) return
        databas.child(add.key).child(add.uid).removeValue().addOnCanceledListener {
            finishListener.onFinish()
        }
    }
    fun readDataFromDB(){
        databas.addListenerForSingleValueEvent(object: ValueEventListener { // один раз с этого пути считывает, само по себе не считывает
            override fun onDataChange(snapshot: DataSnapshot) {
                val ADlist = ArrayList<ADD>()
                for (item in snapshot.children) {
                    val master = item.children.iterator().next().child("master").getValue(ADD::class.java)
                    if (master != null)ADlist.add(master)
                }
                readDataCallBack?.readData(ADlist)
            }
            override fun onCancelled(error: DatabaseError) {}
        } )
    }
    interface ReadDataCallBack {
        fun readData(list: List<ADD>)
    }
    interface FinishOnWorkListener{
        fun onFinish()
    }
}