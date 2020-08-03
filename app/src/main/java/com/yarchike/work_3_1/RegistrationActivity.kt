package com.yarchike.work_3_1

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.coroutines.launch

class RegistrationActivity : AppCompatActivity() {
    private var dialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        btn_register.setOnClickListener {
            lifecycleScope.launch {
                val login = edt_registration_login.text?.toString().orEmpty()
                val password = edt_registration_password.text?.toString().orEmpty()
                val twoPassword = edt_registration_repeat_password.text?.toString().orEmpty()

                if (login == "") {
                    Toast.makeText(this@RegistrationActivity, "Ведите Логин", Toast.LENGTH_LONG).show()
                } else if (password == "") {
                    Toast.makeText(this@RegistrationActivity, "Введите пароль", Toast.LENGTH_LONG).show()
                } else if (twoPassword == "") {
                    Toast.makeText(this@RegistrationActivity, "Введите пароль", Toast.LENGTH_LONG).show()
                } else {
                    dialog = ProgressDialog(this@RegistrationActivity).apply {
                        setMessage(getString(R.string.please_wait))
                        setTitle(getString(R.string.loading_data))
                        show()
                    }
                    Repository.register(login, password)
                    dialog?.dismiss()
                    val intent = Intent(this@RegistrationActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }
            }

        }

    }
}