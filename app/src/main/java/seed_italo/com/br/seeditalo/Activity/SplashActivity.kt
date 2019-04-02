package seed_italo.com.br.seeditalo.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import seed_italo.com.br.seeditalo.R
import java.lang.Thread.sleep

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init(){
        Thread({
            sleep(5000)

            val intent = Intent(this@SplashActivity, MainActivity::class.java )
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent);
            finish();
        }).start()
    }

}
