package apps.r2b.gymtrainer.settings

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import apps.r2b.gymtrainer.App
import apps.r2b.gymtrainer.data.entity.Routine
import apps.r2b.gymtrainer.R
import kotlinx.android.synthetic.main.lay_image_list_item.view.*

class RecyclerAdapter(private val items: MutableList<Routine>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.lay_image_list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ivRoutine?.setImageDrawable(context.resources.getDrawable(items[position].imageResId, null))
        val switchBtn = holder.switchDisableRoutine as Switch
        switchBtn.setOnCheckedChangeListener(null)
        switchBtn.isChecked = items[position].enabled
        switchBtn.setOnCheckedChangeListener { _, isChecked ->
            items[position].enabled = isChecked
            val repo = (context.applicationContext as App).getRepository()
            repo.setRoutine(items[position])
        }
    }

    fun getItems(): MutableList<Routine> = items

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val ivRoutine = view.ivRoutine
    val switchDisableRoutine = view.switchEnableRoutine
}

