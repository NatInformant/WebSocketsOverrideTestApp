package com.example.systembuttonsoverridetestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.fragments_host, MainFragment())
            .commit()

    }

    /*   override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
           return if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
               Toast.makeText(this, "Громкость переопределена", Toast.LENGTH_SHORT).show()
               true
           } else {
               super.onKeyDown(keyCode, event)
           }
       }
    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        val keyCode = event.keyCode
        if (event.action != KeyEvent.ACTION_DOWN) {
            return  super.dispatchKeyEvent(event)
        }

        return when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_VOLUME_DOWN -> {
                Toast.makeText(this, "Громкость переопределена", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.dispatchKeyEvent(event)
        }
    }*/
}