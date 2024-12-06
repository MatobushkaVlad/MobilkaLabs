package com.chuparkov.mobileappchuprakov

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

const val APP_PREFERENCES = "settings"
const val APP_PREFERENCES_USER_LOG_IN = "user_log_in"
const val APP_PREFERENCES_USER_AUTO_LOG_IN = "user_auto_log_in"
const val APP_PREFERENCES_USER_SIGN_UP= "user_sign_up"

const val APP_PREFERENCES_EMAIL_USER = "email_"
const val APP_PREFERENCES_PHONE_NUMBER_USER = "phone_number_user"
const val APP_PREFERENCES_PASSWORD_USER= "password_user"

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        val sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        //storage.edit().clear().apply()
        Log.i("DebugInfo", sharedPreferences.all.toString())

        if (getBooleanSharedData(sharedPreferences, APP_PREFERENCES_USER_AUTO_LOG_IN)) {
            startActivity(Intent(this, ContentActivity::class.java))
        }
        else if (getBooleanSharedData(sharedPreferences, APP_PREFERENCES_USER_SIGN_UP)) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        else {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }
}

fun getBooleanSharedData(settings: SharedPreferences, data: String):Boolean {
    return settings.getBoolean(data, false)
}

fun getStringSharedData(settings: SharedPreferences, data: String): String? {
    return settings.getString(data,"")
}

fun AddStringSharedData(settings: SharedPreferences,typeData: String, data: String) {
    return settings.edit().putString(typeData,data).apply()
}

fun AddBooleanSharedData(settings: SharedPreferences,typeData: String, data: Boolean) {
    return settings.edit().putBoolean(typeData,data).apply()
}