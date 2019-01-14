package com.monis.chatapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

      /*  fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        var list=ArrayList<User>()
    /*    list.add(User("me","Welcome"))
        list.add(User("lulu","Hi"))*/

        var obj = ChatDB(this)
//        var db=obj.readableDatabase
        var db=obj.writableDatabase
        var cur = db.rawQuery("select * from chat", null)
        cur.moveToFirst()
        while (cur.isAfterLast == false) {
            list.add(User(cur.getString(0), cur.getString(1)))
            cur.moveToNext()

        }

        var adp = MessageAdapter(this, list)
        rv_chat.adapter=adp
        rv_chat.layoutManager=LinearLayoutManager(this)
        rv_chat.scrollToPosition(list.size-1)

        btn_chat.setOnClickListener {
            db.execSQL("insert into chat values(?,?)",
                arrayOf("me",et_chat.text.toString()))
            list.add(User("me",et_chat.text.toString()))
            adp.notifyDataSetChanged()
            et_chat.setText("")
            rv_chat.scrollToPosition(list.size-1)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
