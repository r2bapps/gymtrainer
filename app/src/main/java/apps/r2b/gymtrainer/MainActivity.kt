package apps.r2b.gymtrainer

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnConfig.setOnClickListener {
            startActivity(Intent(this, ConfigActivity::class.java))
            finish()
        }
        viewpager.adapter = ViewPagerAdapter(supportFragmentManager, ArrayList())
    }

    override fun onStart() {
        super.onStart()
        loadKeys()
        viewpager.adapter?.notifyDataSetChanged()
        viewpager.setCurrentItem(getIndex(), false)
    }

    override fun onPause() {
        setIndex(viewpager.currentItem)
        super.onPause()
    }

    private fun loadKeys() {
        var keys = (viewpager.adapter as ViewPagerAdapter).getItems()
        var repo = (application as Injector).getRepository()
        keys.clear()
        keys.addAll(repo.getKeys())
    }

    private fun setIndex(index: Int) {
        getPrefs().edit().putInt(getString(R.string.index_key), index).commit()
    }

    private fun getIndex(): Int =
        getPrefs().getInt(getString(R.string.index_key), resources.getInteger(R.integer.defaultValue))

    private fun getPrefs(): SharedPreferences =
        applicationContext.getSharedPreferences(resources.getString(R.string.prefs_filename), 0)

}
