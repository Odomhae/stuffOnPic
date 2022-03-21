package com.odom.stuffonpic

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.odom.stuffonpic.fragment.LetterFragment
import com.odom.stuffonpic.fragment.ListFragment

class MainActivity : AppCompatActivity() {

    private val arrFragments = arrayOfNulls<Fragment>(2)
    private var fragmentLetter: LetterFragment? = null
    private var fragmentList: ListFragment? = null

    private lateinit var vpContent : ViewPager

    private var rlayout_tab_letter :  RelativeLayout?= null
    private var rlayout_tab_list :  RelativeLayout?= null
    private var bt_add_image : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rlayout_tab_letter = findViewById(R.id.rlayout_tab_letter)
        rlayout_tab_list = findViewById(R.id.rlayout_tab_list)
        bt_add_image = findViewById(R.id.bt_add_image)

        // 처음엔 letter fragment로 시작
        rlayout_tab_letter!!.isSelected = true

        fragmentLetter = LetterFragment.newInstance()
        fragmentList = ListFragment.newInstance()

        // 각 뷰페이저
        arrFragments[0] = fragmentLetter
        arrFragments[1] = fragmentList

        vpContent = findViewById(R.id.vp_content)
        vpContent.overScrollMode = View.OVER_SCROLL_NEVER
        vpContent.adapter = ViewpagerAdapter(supportFragmentManager)
        vpContent.offscreenPageLimit = 3
        vpContent.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            //페이지 넘길
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) { setLayoutTab(position) }
        })

        rlayout_tab_letter!!.setOnClickListener { vpContent.currentItem = 0 }
        rlayout_tab_list!!.setOnClickListener { vpContent.currentItem = 1 }
        bt_add_image!!.setOnClickListener { addImg() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 이미지 선택
        if (resultCode == Activity.RESULT_OK) {

            //    val file = ImagePicker.getFile(data)!!

            val intent = Intent(this, ImageViewerActivity::class.java)
            // 이미지 선택
            //  intent.putExtra("fileName", file)
            // 액티비티 실행
            startActivity(intent)

        }
        //  else if (resultCode == ImagePicker.RESULT_ERROR) {
        //      Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        //  }
    }

    private fun setLayoutTab(nPosition: Int) {

        // 눌린 곳만 체크
        rlayout_tab_letter!!.isSelected = (nPosition == 0)
        rlayout_tab_list!!.isSelected = (nPosition == 1)
    }

    private fun addImg() {

        val items = arrayOf<CharSequence>("앨범에서 가져오기", "사진찍기")

        val dialog: Dialog =
            AlertDialog.Builder(this)
                .setItems(items) { _, position ->
                    selectImg(position)
                }.create()
        dialog.show()
    }

    private fun selectImg(position: Int) {

        if (position == 0) {
            // ImagePicker.with(this).crop().galleryOnly().maxResultSize(1080, 1920).start()
        } else if (position == 1) {
            //   ImagePicker.with(this).crop().cameraOnly().start()
        }
    }

    internal inner class ViewpagerAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment {
            return arrFragments[position]!!
        }

        override fun getCount(): Int {
            return arrFragments.size
        }

    }
}