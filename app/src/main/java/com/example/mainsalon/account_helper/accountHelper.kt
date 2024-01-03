package com.example.mainsalon.account_helper
import android.widget.Toast
import com.example.mainsalon.MainActivity
import com.example.mainsalon.R
import com.google.firebase.auth.FirebaseUser
class accountHelper(act: MainActivity) {
    private val activ = act
    fun signupemail(email:String, password:String){
        if (email.isNotEmpty() && password.isNotEmpty()){
            activ.mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {task->
                if (task.isSuccessful) {
                    sendemailverification(task.result?.user!!)
                    activ.uiUpdate(task.result?.user)

                }else{
                    Toast.makeText(activ, activ.resources.getString(R.string.error_signin), Toast.LENGTH_LONG ).show()
                }
            }
        }
    }
    fun signinemail(email:String, password:String){
        if (email.isNotEmpty() && password.isNotEmpty()){
            activ.mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {task->
                if (task.isSuccessful) {
                    activ.uiUpdate(task.result?.user)
                }else{
                    Toast.makeText(activ, activ.resources.getString(R.string.error_signin_), Toast.LENGTH_LONG ).show()
                }
            }
        }
    }
    private fun sendemailverification(user:FirebaseUser){
        user.sendEmailVerification().addOnCompleteListener {task -> // принимаем такс и из такса берем
            if (task.isSuccessful){ // если сообщение успешно отправлено
                Toast.makeText(activ, activ.resources.getString(R.string.suck_signin), Toast.LENGTH_LONG ).show()
            }else {
                Toast.makeText(activ, activ.resources.getString(R.string.error_signin_emai), Toast.LENGTH_LONG ).show()
            }
        }
    }
}