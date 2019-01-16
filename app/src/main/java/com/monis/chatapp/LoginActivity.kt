package com.monis.chatapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_signup.setOnClickListener {
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        login_button.setOnClickListener {
            var auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(login_email.text.toString(),login_password.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var intent = Intent(this, UserListActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this,task.exception?.message,Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
