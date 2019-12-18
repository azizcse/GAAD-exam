package com.w3engineers.testkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import com.w3engineers.testkt.toast.ToastUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.show_toast -> {
                ToastUtil.showToast("Hello Gaad examinee")
                return true
            }
            R.id.custom_toast->{
                prepareLayout()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun prepareLayout(){
        val inflater = layoutInflater
        val layout: ViewGroup = inflater.inflate(R.layout.toast_layout, findViewById(R.id.custom_toast_container)) as ViewGroup
        val text: TextView = layout.findViewById(R.id.text)
        text.text = "This is a custom toast"
        ToastUtil.showToastWithCustomLayout(layout)

    }

}
