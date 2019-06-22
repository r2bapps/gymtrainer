package apps.r2b.gymtrainer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_config.*

class ConfigActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = RecyclerAdapter(ArrayList<Config>(), this)
    }

    override fun onStart() {
        super.onStart()
        refresh()
        recycler.adapter!!.notifyDataSetChanged()
    }

    override fun onDestroy() {
        startActivity(Intent(this, MainActivity::class.java))
        super.onDestroy()
    }

    private fun refresh() {
        var repo = (application as Injector).getRepository()
        var items = (recycler.adapter as RecyclerAdapter).getItems()
        items.clear()
        items.addAll(repo.getItems())
    }

}
