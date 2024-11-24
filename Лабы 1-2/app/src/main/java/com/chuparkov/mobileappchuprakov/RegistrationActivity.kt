package com.chuparkov.mobileappchuprakov

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)

        val enter_by_phone = findViewById<TextView>(R.id.ByPhone)
        val enter_by_email = findViewById<TextView>(R.id.ByEmail)

        val login_field = findViewById<EditText>(R.id.EmailField)
        login_field.inputType = InputType.TYPE_CLASS_PHONE
        val password_field = findViewById<EditText>(R.id.PasswordField)
        val confirm_password_field = findViewById<EditText>(R.id.ConfirmPasswordField)

        val enter_button = findViewById<Button>(R.id.EnterButton)

        enter_by_phone.setOnClickListener {
            enter_by_email.setTextColor(ContextCompat.getColor(this, R.color.grey))
            enter_by_phone.setTextColor(ContextCompat.getColor(this, R.color.green))

            login_field.setHint(R.string.enter_by_phone)
            login_field.inputType = InputType.TYPE_CLASS_PHONE
        }

        enter_by_email.setOnClickListener {
            enter_by_email.setTextColor(ContextCompat.getColor(this, R.color.green))
            enter_by_phone.setTextColor(ContextCompat.getColor(this, R.color.grey))

            login_field.setHint(R.string.enter_by_email)
            login_field.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }

        enter_button.setOnClickListener {
            ErrorHandling(login_field, password_field, confirm_password_field)
        }
    }

    fun ShowShortToast(Error: String) {
        Toast.makeText(this, Error, Toast.LENGTH_SHORT).show()
    }

    private fun ErrorHandling(loginField: EditText, passwordField: EditText, confirmPasswordField: EditText): Boolean{
        when {
            (loginField.hint.toString() == resources.getString(R.string.enter_by_email)) && loginField.text.isNotEmpty() && !loginField.text.contains("@") -> {
                ShowShortToast(resources.getString(R.string.error_email_enter))
                return false
            }

            (loginField.hint.toString() == resources.getString(R.string.enter_by_phone)) && loginField.text.isNotEmpty() && !loginField.text.contains("+") -> {
                ShowShortToast(resources.getString(R.string.error_phone_enter))
                return false
            }

            passwordField.text.isNotEmpty() && passwordField.text.length in 1..7 -> {
                ShowShortToast(resources.getString(R.string.error_length_password_enter))
                return false
            }

            passwordField.text.toString() != confirmPasswordField.text.toString() -> {
                ShowShortToast(resources.getString(R.string.error_confirm_password_enter))
                return false
            }
        }
        return true
    }


}
