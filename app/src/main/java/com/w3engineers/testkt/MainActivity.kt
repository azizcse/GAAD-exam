package com.w3engineers.testkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.w3engineers.testkt.notify.NotificationUtil
import com.w3engineers.testkt.toast.ToastUtil
import com.w3engineers.testkt.ui.DispatchQueue
import com.w3engineers.testkt.ui.JobServiceActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var dispatch: DispatchQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_share,
                R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        dispatch = DispatchQueue()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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
            R.id.custom_toast -> {
                prepareLayout()
                return true
            }
            R.id.show_notification -> {
                NotificationUtil.showNotification(applicationContext)
                return true
            }
            R.id.start_job_service -> {
                startActivity(Intent(this, JobServiceActivity::class.java))
                return true
            }

            R.id.called_thread -> {

                for (i in 1..10) {
                    dispatch.dispatch(object : Runnable {
                        override fun run() {
                            //Thread.sleep(2000)
                            Log.e("THREAD_TEST", "After 2 second $i")
                            prepareLayout()

                            //dispatch.close()
                        }
                    })
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun prepareLayout() {
        val inflater = layoutInflater
        val layout: ViewGroup = inflater.inflate(
            R.layout.toast_layout,
            findViewById(R.id.custom_toast_container)
        ) as ViewGroup
        val text: TextView = layout.findViewById(R.id.text)
        text.text = "This is a custom toast"
        ToastUtil.showToastWithCustomLayout(layout)

    }

}
