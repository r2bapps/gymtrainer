package apps.r2b.gymtrainer.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import apps.r2b.gymtrainer.App
import apps.r2b.gymtrainer.R
import apps.r2b.gymtrainer.data.entity.Routine
import kotlinx.android.synthetic.main.act_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_settings)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = RecyclerAdapter(mutableListOf<Routine>(), this)
    }

    override fun onStart() {
        super.onStart()
        update()
        recycler.adapter!!.notifyDataSetChanged()
    }

    private fun update() {
        val repo = (application as App).getRepository()
        val routines = (recycler.adapter as RecyclerAdapter).getItems()
        routines.clear()
        routines.addAll(repo.getRoutines())
    }

}
