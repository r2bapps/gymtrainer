package apps.r2b.gymtrainer

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewPagerAdapter internal constructor(fm: FragmentManager, private val keys: ArrayList<String>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? = RoutineFragment.newInstance(keys[position])

    override fun getCount(): Int = keys.size

    fun getItems(): ArrayList<String> = keys

}
