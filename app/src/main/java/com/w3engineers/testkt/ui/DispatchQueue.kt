package com.w3engineers.testkt.ui

import android.os.Handler
import android.os.Looper
import android.util.Log
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class DispatchQueue {
    private val TAG = "THREAD_TEST"
    private var handler: Handler? = null

    constructor() {
        DispatchQueue(null as String?)
    }

    fun DispatchQueue(name: String?) {
        //handler = Handler()
        Log.e(TAG, "constructor called")
        val latch = CountDownLatch(1)

        val thread = Thread(Runnable {

            Log.e(TAG, "Called prepare")
            Looper.prepare()

            handler = Handler()

            Log.e(TAG, "Called countdown")
            latch.countDown()

            Log.e(TAG, "Called loop")

            Looper.loop()

        })
        if (name != null) thread.name = name

        thread.isDaemon = true

        Log.e(TAG, "Thread started")

        thread.start()

        try {
            latch.await(100, TimeUnit.SECONDS)

            Log.e(TAG, "Latch timeout")

        } catch (ex: InterruptedException) {

            return
        }
    }

    fun dispatch(runnable: Runnable): Boolean {
        if (handler == null)
            return false
        return handler!!.post(runnable);
    }

    fun close() {
        handler!!.looper.quit()
    }


}