package com.w3engineers.testkt.toast

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.w3engineers.testkt.TestApp

object ToastUtil {
    fun showToast(message : String){
        var toast = Toast.makeText(TestApp.onContext(), message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }

    fun showToastWithCustomLayout(layout: View){
        with (Toast(TestApp.onContext())) {
            setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }

    }
}