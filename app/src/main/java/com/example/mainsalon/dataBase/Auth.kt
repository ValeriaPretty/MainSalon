package com.example.mainsalon.dataBase

object Auth {
    var isUserAuthenticated: Boolean = false
        private set
    fun setUserAuthenticated(authenticated: Boolean) {
        isUserAuthenticated = authenticated
    }
}