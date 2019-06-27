package apps.r2b.gymtrainer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import apps.r2b.gymtrainer.routine.RoutineActivity
import apps.r2b.gymtrainer.settings.SettingsActivity
import kotlinx.android.synthetic.main.act_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        btnRoutine.setOnClickListener {
            startActivity(Intent(this, RoutineActivity::class.java))
        }
        btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}