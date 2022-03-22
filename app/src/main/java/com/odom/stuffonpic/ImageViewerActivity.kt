package com.odom.stuffonpic

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_image_viewer.*
import java.io.File
import java.io.Serializable

class ImageViewerActivity : AppCompatActivity() {

    // 운동 리스트뷰 데이터
    val workoutItems = ArrayList<String>()

    var listPref = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)

        getData()
        setLayoutActivity()
    }

    // 기록 리스트 가져오기
    private fun getData(){

        listPref = getStringArrayPref("listData")!! // null 가능한데,
        if(listPref.size > 0){
            for(value in listPref){
                workoutItems.add(value)
            }
        }

    }

    private fun setLayoutActivity(){

        // 이미지 로드
        val file: Uri?= intent.getParcelableExtra("fileName")
        imgProfile.setImageURI(file)
    }

    // 저장된 배열 받아옴
    private fun getStringArrayPref(key: String): ArrayList<String>? {

        val prefs = this.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        val json = prefs.getString(key, null)
        val gson = Gson()

        val restoredData: ArrayList<String>? = gson.fromJson(json,
            object : TypeToken<ArrayList<String?>>() {}.type
        )

        return restoredData
    }
}