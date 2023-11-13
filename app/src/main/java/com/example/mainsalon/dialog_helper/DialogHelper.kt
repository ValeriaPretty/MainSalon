package com.example.mainsalon.dialog_helper


import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.example.mainsalon.MainActivity
import com.example.mainsalon.R
import com.example.mainsalon.account_helper.accountHelper
import com.example.mainsalon.databinding.SignAdministratorBinding



class DialogHelper(act:MainActivity ) {
    private val activ = act
    private val acchelper = accountHelper(activ)
    fun createSignDialog(index:Int){
        val builder = AlertDialog.Builder(activ)
        val rootDialogElement = SignAdministratorBinding.inflate(activ.layoutInflater)
        val view = rootDialogElement.root
        builder.setView(view)
        setDialogState(index, rootDialogElement)

        val dialog = builder.create()
        rootDialogElement.buttonSignClient.setOnClickListener {// когда мы нажимаем на кнопку запускается этот код
            setOnClickSignUpIn(index, rootDialogElement, dialog)
        }
        rootDialogElement.buttonForgetClient.setOnClickListener {// когда мы нажимаем на кнопку запускается этот код
            setOnClickResetPasswrd(rootDialogElement, dialog)
        }
        dialog.show()
    }

    private fun setOnClickResetPasswrd(rootDialogElement: SignAdministratorBinding, dialog: AlertDialog?) {
// rootDialogElement - прямой доступ к моим элементам которые на моем экране в sign_client
        if (rootDialogElement.edSignEmailClient.text.isNotEmpty()){
            activ.mAuth.sendPasswordResetEmail(rootDialogElement.edSignEmailClient.text.toString()).addOnCompleteListener{task ->
                if(task.isSuccessful){
                    Toast.makeText(activ, R.string.wait_an_email, Toast.LENGTH_LONG).show()
                }
            }
            dialog?.dismiss()
        }else{
            rootDialogElement.inputMail.visibility = View.VISIBLE
        }

    }

    private fun setOnClickSignUpIn(index: Int, rootDialogElement: SignAdministratorBinding, dialog: AlertDialog?) {
        dialog?.dismiss() // dismiss запустится если dialog не нал
        if(index == dialogconst.SIGN_UP_STATE){
            acchelper.signupemail(rootDialogElement.edSignEmailClient.text.toString(),
                rootDialogElement.edSignPasswrdClient.text.toString())
        }else{
            acchelper.signinemail(rootDialogElement.edSignEmailClient.text.toString(),
                rootDialogElement.edSignPasswrdClient.text.toString())
        }
    }

    private fun setDialogState(index: Int, rootDialogElement: SignAdministratorBinding) {
        if (index == dialogconst.SIGN_UP_STATE){ // мы сюда передаем константу в которой хранится число, Зашли для регистрации?
            rootDialogElement.tvClient.text = activ.resources.getString(R.string.registration)
            rootDialogElement.buttonSignClient.text = activ.resources.getString(R.string.log_in)
        }else{
            rootDialogElement.tvClient.text = activ.resources.getString(R.string.Sign_in)
            rootDialogElement.buttonSignClient.text = activ.resources.getString(R.string.sign_in_in)
            rootDialogElement.buttonForgetClient.visibility = View.VISIBLE
        }
    }
}