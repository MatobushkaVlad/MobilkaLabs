package com.chuparkov.mobileappchuprakov

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        val login_field = findViewById<EditText>(R.id.EnterDataField)
        val password_field = findViewById<EditText>(R.id.PasswordField)

        val enter_button = findViewById<Button>(R.id.EnterButton)
        val check_box = findViewById<CheckBox>(R.id.CheckBox)

        enter_button.setOnClickListener {
            if (CorrectUserData(login_field.text.toString(), password_field.text.toString(),
                    sharedPreferences)) {
                AddBooleanSharedData(sharedPreferences, APP_PREFERENCES_USER_AUTO_LOG_IN, check_box.isChecked)
                startActivity(Intent(this, ContentActivity::class.java))
            }
            else {
                ShowShortToast("Incorrect login or password")
            }
        }

    }
    fun ShowShortToast(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}

fun CorrectUserData(login: String, password: String, settings: SharedPreferences): Boolean {
    val sysEmailUser = getStringSharedData(settings, APP_PREFERENCES_EMAIL_USER)
    val sysPhoneNumberUser = getStringSharedData(settings, APP_PREFERENCES_PHONE_NUMBER_USER)
    val sysPasswordUser = getStringSharedData(settings, APP_PREFERENCES_PASSWORD_USER)

    return ((sysEmailUser == login) || (sysPhoneNumberUser == login)) && (sysPasswordUser == password)

}