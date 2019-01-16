package com.monis.chatapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        var dbr = FirebaseDatabase.getInstance().reference
        var list = ArrayList<String>()
        dbr.child("users").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for (u in p0.children) {
                    list.add(u.key.toString())
                    var adp = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, list)
                    users_lv.adapter = adp
                }
            }
        })

    }
}
