package apps.r2b.gymtrainer.routine

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import apps.r2b.gymtrainer.App
import apps.r2b.gymtrainer.R
import apps.r2b.gymtrainer.data.entity.Routine
import apps.r2b.gymtrainer.settings.SettingsActivity
import kotlinx.android.synthetic.main.frg_routine.*
import org.jetbrains.anko.toast


class RoutineFragment : Fragment() {

    private lateinit var routine: Routine

    companion object {
        private val ARG_ROUTINE = "ROUTINE"
        fun newInstance(routine: Routine) = RoutineFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_ROUTINE, routine)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.frg_routine, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        routine = arguments!!.getParcelable(ARG_ROUTINE)
        btnSubstract.setOnClickListener {
            if (tvWeight.text.toString().equals("0")) {
                activity!!.toast(R.string.really)
            } else {
                update(tvWeight, getValue(tvWeight) - 1)
            }
        }
        btnAdd.setOnClickListener {
            update(tvWeight, getValue(tvWeight) + 1)
        }
        ivRoutine.setOnClickListener {
            startActivity(Intent(context, SettingsActivity::class.java))
        }
        ivRoutine.setImageResource(routine.imageResId)
        tvWeight.text = routine.weight.toString()
        animate(ivRoutine)
    }

    @SuppressLint("SetTextI18n")
    private fun update(tv: TextView, value: Int) {
        tv.text = value.toString()
        routine.weight = value
        val repo = (activity!!.applicationContext as App).getRepository()
        repo.setRoutine(routine)
    }

    private fun animate(iv: ImageView) {
        if (iv.drawable is AnimationDrawable) {
            (iv.drawable as AnimationDrawable).start()
        }
    }

    private fun getValue(tv: TextView) = Integer.parseInt(tv.text.toString())

}
