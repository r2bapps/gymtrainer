package apps.r2b.gymtrainer

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_routine.*
import org.jetbrains.anko.toast


class RoutineFragment : Fragment() {

    private lateinit var key: String

    companion object {
        private val ARG_KEY = "KEY"
        fun newInstance(key: String) = RoutineFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_KEY, key)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_routine, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val defaultValue = Integer.toString(resources.getInteger(R.integer.defaultValue))
        val prefs = context!!.applicationContext.getSharedPreferences(resources.getString(R.string.prefs_filename), 0)!!
        key = arguments!!.getString(ARG_KEY)
        btnSubstract.setOnClickListener {
            if (tvWeight.text.toString().equals(defaultValue)) {
                activity!!.toast(R.string.really)
            } else {
                update(tvWeight, prefs, getValue(tvWeight) - 1)
            }
        }
        btnAdd.setOnClickListener {
            update(tvWeight, prefs, getValue(tvWeight) + 1)
        }
        ivRoutine.setOnClickListener {
            startActivity(Intent(context, ConfigActivity::class.java))
            activity!!.finish()
        }
        ivRoutine.setImageResource(getDrawableResId(key))
        tvWeight.text = prefs.getString(key, defaultValue)
        animate(ivRoutine)
    }

    private fun update(tv: TextView, prefs: SharedPreferences, value: Int) {
        tv.text = Integer.toString(value)
        prefs.edit().putString(key, Integer.toString(value)).commit()
    }

    private fun animate(iv: ImageView) {
        if (iv.drawable is AnimationDrawable) {
            (iv.drawable as AnimationDrawable).start()
        }
    }

    private fun getDrawableResId(name: String): Int {
        var res = resources.getIdentifier(name, "drawable", activity!!.packageName)
        if (res == 0) res = R.drawable.ic_empty
        return res
    }

    private fun getValue(tv: TextView) = Integer.parseInt(tv.text.toString())

}
