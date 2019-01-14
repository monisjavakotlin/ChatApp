package com.monis.chatapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text
import java.util.ArrayList

class MessageAdapter(var context: Context, var list:ArrayList<User>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            var v = LayoutInflater.from(context).inflate(R.layout.sent_layout, parent, false)
            return SentHolder(v)
        } else {
            var v = LayoutInflater.from(context).inflate(R.layout.rec_layout, parent, false)
            return RecHolder(v)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(list[position].username=="me")
            (holder as SentHolder).show(list[position].msg)
        else
            (holder as RecHolder).show(list[position].msg)
    }

    override fun getItemViewType(position: Int): Int {
        if(list[position].username=="me")
            return 1
        else
            return 2
    }

    public class SentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv = itemView.findViewById<TextView>(R.id.tv_msg) as TextView
        fun show(msg: String) {
            tv.text=msg
        }
    }

    public class RecHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv = itemView.findViewById<TextView>(R.id.tv_msg) as TextView
        fun show(msg: String) {
            tv.text=msg
        }
    }

}