package com.example.mainsalon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mainsalon.Admin_make.Master_add
import com.example.mainsalon.dialog_helper.DialogHelper
import com.example.mainsalon.dialog_helper.DialogHelperClient
import com.example.mainsalon.dialog_helper.dialogconst
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toollbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private val dialogHelper = DialogHelper(this)
    private val dialogHelperClient = DialogHelperClient(this)
    private lateinit var textView: TextView
    val mAuth = FirebaseAuth.getInstance()
    //val dbManager = DbManager()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toollbar = findViewById(R.id.tool_bar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navView)
        init()
        //dbManager.readDataFromDB()

    }

    override fun onStart() {
        super.onStart()
        uiUpdate(mAuth.currentUser)// если мы зарегались то наш mAuth передаст нам нашего юзера
    }
    private fun init(){
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toollbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        textView = navigationView.getHeaderView(0).findViewById(R.id.textView)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.in_ad -> {
                dialogHelper.createSignDialog(dialogconst.SIGN_IN_STATE)

            }
            R.id.reg_admin ->{
                dialogHelper.createSignDialog(dialogconst.SIGN_UP_STATE)

            }

            R.id.inc -> {
                dialogHelperClient.createSignDialog(dialogconst.SIGN_IN_STATE)
            }
            R.id.button_client_Regist -> {
                dialogHelperClient.createSignDialog(dialogconst.SIGN_UP_STATE)
            }
            R.id.out -> {
                uiUpdate(null)
                mAuth.signOut()
            }
            R.id.out2 -> {
                uiUpdate(null)
                mAuth.signOut()
            }



        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }

    fun uiUpdate(user: FirebaseUser?){
        // нам надо чтобы она была доступна из класа хэлпер
        textView.text = if(user == null){
            resources.getString(R.string.not_reg)
        }else {
            user.email // объект типа стринг
        }
    }

    fun start_paric(item: MenuItem){
        val intent = Intent(this, Paric::class.java)
        startActivity(intent)

    }

    fun add_paric(item: MenuItem){
        val intent = Intent(this, Master_add::class.java)
        startActivity(intent)

    }

}