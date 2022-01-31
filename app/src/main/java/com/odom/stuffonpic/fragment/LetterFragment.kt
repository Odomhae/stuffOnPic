package com.odom.stuffonpic.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.odom.stuffonpic.R

class LetterFragment : Fragment() {

    var inputText : TextView?= null
    var saveButton : Button? = null
    var initButton : Button? = null

    companion object {
        fun newInstance() = LetterFragment().apply {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_letter, container, false)

        inputText = view.findViewById(R.id.tv_letter)
        saveButton = view.findViewById(R.id.bt_letter_save)
        initButton = view.findViewById(R.id.bt_letter_init)

        // 화면 관련 설정
        setLayoutFragment()

        return view
    }

    private fun setLayoutFragment() {

        // 글 있으면 보여주기
        if(getStringPref("letter") != null) {
            inputText!!.text = getStringPref("letter")
        }

        // 글 임시저장
        saveButton!!.setOnClickListener {
            setStringPref("letter" , inputText!!.text.toString())
        }

        // 글 초기화
        initButton!!.setOnClickListener {
            setStringPref("letter" , "")
            inputText!!.text = getStringPref("letter")
        }

    }

    // 글 저장
    private fun setStringPref(key: String, values: String) {

        val prefs = requireContext().getSharedPreferences("LETTER", Context.MODE_PRIVATE)
        with(prefs.edit()){
            putString(key, values)
            commit()
        }

    }

    // 저장된 글 가져옴
    private fun getStringPref(key: String) : String? {

        val prefs = requireContext().getSharedPreferences("LETTER", Context.MODE_PRIVATE)
        val savedLetter = prefs.getString(key , " ")

        return savedLetter
    }
}