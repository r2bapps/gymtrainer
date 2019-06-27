package apps.r2b.gymtrainer.routine

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import apps.r2b.gymtrainer.App
import apps.r2b.gymtrainer.R
import apps.r2b.gymtrainer.data.entity.Routine
import apps.r2b.gymtrainer.settings.SettingsActivity
import kotlinx.android.synthetic.main.act_routine.*

class RoutineActivity : AppCompatActivity() {

    private val ROUTINE_PREFS = "routine_prefs_filename"
    private val ROUTINE_INDEX_KEY = "index"
    private var prefs : SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_routine)
        btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        viewpager.adapter = ViewPagerAdapter(supportFragmentManager, mutableListOf<Routine>())
        prefs = getSharedPreferences(ROUTINE_PREFS, 0)
    }

    override fun onStart() {
        super.onStart()
        update()
        viewpager.adapter!!.notifyDataSetChanged()
        viewpager.setCurrentItem(getIndex(), false)
    }

    override fun onPause() {
        setIndex(viewpager.currentItem)
        super.onPause()
    }

    private fun update() {
        val routines = (viewpager.adapter as ViewPagerAdapter).getItems()
        val repo = (application as App).getRepository()
        routines.clear()
        routines.addAll(repo.getRoutines().filter { it.enabled })
        if (routines.isEmpty()) {
            btnSettings.visibility = View.VISIBLE
        } else {
            btnSettings.visibility = View.INVISIBLE
        }
    }

    private fun setIndex(index: Int) {
        prefs!!.edit().putInt(ROUTINE_INDEX_KEY, index).apply()
    }

    private fun getIndex(): Int = prefs!!.getInt(ROUTINE_INDEX_KEY, 0)

}
