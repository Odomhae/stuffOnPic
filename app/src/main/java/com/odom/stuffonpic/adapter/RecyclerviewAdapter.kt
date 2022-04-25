package com.odom.stuffonpic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.odom.stuffonpic.R
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class RecyclerviewAdapter(private var data: ArrayList<String>): RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.recyclerviewItem.text = data[position]

        // 폭 설정
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 80
        holder.itemView.requestLayout()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var recyclerviewItem = view.rv_item
}