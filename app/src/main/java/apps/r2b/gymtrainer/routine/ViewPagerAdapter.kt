package apps.r2b.gymtrainer.routine

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import apps.r2b.gymtrainer.data.entity.Routine

class ViewPagerAdapter constructor(fm: FragmentManager, private val items: MutableList<Routine>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = RoutineFragment.newInstance(items[position])

    override fun getCount(): Int = items.size

    fun getItems(): MutableList<Routine> = items

}
