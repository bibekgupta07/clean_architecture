package com.bibekgupta.cleanarchitecture.data.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManager @Inject constructor(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "AppPreferences", Context.MODE_PRIVATE
    )

    companion object {
        private const val KEY_TOKEN = "auth_token"
    }

    fun saveToken(token: String){
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(): String? = sharedPreferences.getString(KEY_TOKEN, null)

    fun clearToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
    }

}

