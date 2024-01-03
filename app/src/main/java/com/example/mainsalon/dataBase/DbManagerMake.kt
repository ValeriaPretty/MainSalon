package com.example.mainsalon.dataBase
import com.example.mainsalon.data.ADD4
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database
class DbManagerMake(val readDataCallBack: ReadDataCallBack?) {
    val databa = Firebase.database.getReference("main_make") // указывает на путь
    val auth = Firebase.auth
    fun publishadd(ad: ADD4, finishListener: FinishOnWorkListener) {
        val key = ad.key ?: databa.push().key.orEmpty()
        databa.child(key).child(auth.uid!!).child("master")
            .setValue(ad.copy(key = key)).addOnCanceledListener {
                finishListener.onFinish()
            }
    }
    fun deleteAd(add: ADD4, finishListener: FinishOnWorkListener) {
        if (add.key == null || add.uid == null) return
        databa.child(add.key).child(add.uid).removeValue().addOnCanceledListener {
            finishListener.onFinish()
        }
    }
    fun deleteButton(add: ADD4, finishListener: FinishOnWorkListener) {
        if (add.key == null || add.uid == null) return
        databa.child(add.key).child(add.uid).removeValue().addOnCanceledListener {
            finishListener.onFinish()
        }
    }
    fun readDataFromDB(){
        databa.addListenerForSingleValueEvent(object: ValueEventListener { // один раз с этого пути считывает, само по себе не считывает
            override fun onDataChange(snapshot: DataSnapshot) {
                val ADlist = ArrayList<ADD4>()
                for (item in snapshot.children) {
                    val master = item.children.iterator().next().child("master").getValue(ADD4::class.java)
                    if (master != null)ADlist.add(master)
                }
                readDataCallBack?.readData(ADlist)
            }
            override fun onCancelled(error: DatabaseError) {}
        } )
    }
    interface ReadDataCallBack {
        fun readData(list: List<ADD4>)
    }
    interface FinishOnWorkListener{
        fun onFinish()
    }
}