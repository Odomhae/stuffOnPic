package com.odom.stuffonpic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.odom.stuffonpic.adapter.ViewpagerAdapter
import com.odom.stuffonpic.fragment.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager : ViewPager = findViewById(R.id.viewPager)
        viewPager.offscreenPageLimit = 2

        val adapter = ViewpagerAdapter(supportFragmentManager)
        adapter.addItem(MainFragment())

        viewPager.adapter = adapter
    }
}