package tronku.projects.carpark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_splash.*
import java.lang.Exception

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val fadeAnimation = AlphaAnimation(0f, 1f)
        fadeAnimation.duration = 1000

        title_layout.startAnimation(fadeAnimation)
        parking_lot.startAnimation(fadeAnimation)

        val intent = Intent(this, MainActivity::class.java)

        val thread = object : Thread() {
            override fun run() {
                try {
                    sleep(2000)
                } catch (exception : InterruptedException) {
                    exception.printStackTrace()
                } finally {
                    startActivity(intent)
                    finish()
                }
            }
        }

        thread.start()
    }

}
