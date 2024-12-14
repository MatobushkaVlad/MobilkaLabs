package com.chuparkov.mobileappchuprakov

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.chuparkov.mobileappchuprakov.WorkWithSharedPreferences.Companion.APP_PREFERENCES
import com.chuparkov.mobileappchuprakov.WorkWithSharedPreferences.Companion.APP_PREFERENCES_USER_AUTO_LOG_IN
import com.chuparkov.mobileappchuprakov.WorkWithSharedPreferences.Companion.APP_PREFERENCES_USER_SIGN_UP

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        val sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        //sharedPreferences.edit().clear().apply()
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

    fun getBooleanSharedData(settings: SharedPreferences, data: String): Boolean {
        return settings.getBoolean(data, false)
    }
}