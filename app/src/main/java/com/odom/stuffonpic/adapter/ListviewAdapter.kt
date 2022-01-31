package com.odom.stuffonpic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.odom.stuffonpic.R

class ListviewAdapter : BaseAdapter() {

    private var listViewItemList = ArrayList<String>()

    override fun getCount(): Int {
        return listViewItemList.size
    }

    override fun getItem(position: Int): Any {
        return listViewItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val context = parent!!.context

        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.listview_item, parent, false)
        }

        val item_workoutName = view!!.findViewById(R.id.lv_item_workoutName) as TextView

        val listViewItem = listViewItemList[position]

        // 아이템 내 각 위젯에 데이터 반영
        item_workoutName.text = listViewItem

        return view
    }

    fun updateReceiptsList(newlist: ArrayList<String>) {
        listViewItemList.clear()
        listViewItemList.addAll(newlist)
        this.notifyDataSetChanged()

    }

    // 아이템 데이터 추가를 위한 함수
    fun addItem(workoutName: String) {

        listViewItemList.add(workoutName)
    }
}