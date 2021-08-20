package com.kyle.appinit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kyle.library.appinit.AllSdkManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AllSdkManager.instance.add(JpushInit()).addAsync(BuglyInit()).init(application)
    }

    override fun onDestroy() {
        AllSdkManager.instance.unInit()
        super.onDestroy()
    }
}