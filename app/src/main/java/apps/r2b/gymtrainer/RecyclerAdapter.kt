package apps.r2b.gymtrainer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import kotlinx.android.synthetic.main.layout_image_list_item.view.*

class RecyclerAdapter(private val items : ArrayList<Config>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {


    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_image_list_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var resId = R.drawable.ic_empty
        if (items.get(position).imageResId != 0) {
            resId = items.get(position).imageResId
        }
        holder.ivRoutine?.setImageDrawable(context.resources.getDrawable(resId, null))
        val switchBtn = holder.switchDisableRoutine as Switch
        switchBtn.setOnCheckedChangeListener(null)
        switchBtn.isChecked = items.get(position).enabled
        switchBtn.setOnCheckedChangeListener { _, isChecked ->
            saveSettings(items.get(position).name, isChecked)
        }
    }

    fun getItems(): ArrayList<Config> = items

    private fun saveSettings(name: String, enabled: Boolean) {
        val prefs = context.applicationContext.getSharedPreferences(context.resources.getString(R.string.prefs_filename), 0)!!
        val configPrefs = context.applicationContext.getSharedPreferences(context.resources.getString(R.string.config_filename), 0)!!
        configPrefs.edit().putBoolean(name, enabled).commit()
        prefs.edit().putInt(context.resources.getString(R.string.index_key), context.resources.getInteger(R.integer.defaultValue)).commit()
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val ivRoutine = view.ivRoutine
    val switchDisableRoutine = view.switchDisableRoutine
}

