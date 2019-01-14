package com.monis.chatapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ChatDB(context:Context) : SQLiteOpenHelper(context,"chat.db",null,1){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table chat(user text,msg text)")
        db.execSQL("insert into chat values('me','Welcome')")
        db.execSQL("insert into chat values('snow','Hi')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}