package ru.pervov.lovenail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

private const val PERMISSION_INSTALL_REQUEST = 200

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}