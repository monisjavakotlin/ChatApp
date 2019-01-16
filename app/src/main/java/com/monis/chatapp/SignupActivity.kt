package com.monis.chatapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signup_button.setOnClickListener {
            if (signup_password.text.toString() == signup_confirm.text.toString()) {
                var auth = FirebaseAuth.getInstance()
                auth.createUserWithEmailAndPassword(signup_email.text.toString(),signup_password.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            var intent = Intent(this, UserListActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this,task.exception?.message,Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this,"Password not match",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
