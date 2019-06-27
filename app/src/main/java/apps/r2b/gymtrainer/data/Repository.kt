package apps.r2b.gymtrainer.data

import android.annotation.SuppressLint
import android.content.Context
import apps.r2b.gymtrainer.data.entity.Routine
import apps.r2b.gymtrainer.R
import com.google.gson.Gson

class Repository(private val appContext: Context) {

    private val ROUTINES = "routines_filename"

    fun init() {
        val storedRoutines = appContext.getSharedPreferences(ROUTINES, 0)!!.all.values
        if (storedRoutines.isEmpty()) {
            val prefsEditor = appContext.getSharedPreferences(ROUTINES, 0)!!.edit()
            val imageNames = appContext.resources.getStringArray(R.array.image_names)
            for(name in imageNames) {
                prefsEditor.putString(name, Gson().toJson(Routine(getDrawableResId(name), name))).apply()
            }
        }
    }

    fun getRoutines(): MutableList<Routine> {
        val routines = mutableListOf<Routine>()
        val storedRoutines = appContext.getSharedPreferences(ROUTINES, 0)!!.all.values
        for(it in storedRoutines) {
            routines.add(Gson().fromJson(it as String, Routine::class.java))
        }
        return routines
    }

    fun setRoutines(routines: MutableList<Routine>) {
        val prefsEditor = appContext.getSharedPreferences(ROUTINES, 0)!!.edit()
        for(routine in routines) {
            prefsEditor.putString(routine.name, Gson().toJson(routine)).apply()
        }
    }

    fun setRoutine(routine: Routine) {
        val prefsEditor = appContext.getSharedPreferences(ROUTINES, 0)!!.edit()
        prefsEditor.putString(routine.name, Gson().toJson(routine)).apply()
    }

    private fun getDrawableResId(name: String): Int {
        var res = appContext.resources.getIdentifier(name, "drawable", appContext.packageName)
        if (res == 0) res = R.drawable.ic_empty
        return res
    }

}
