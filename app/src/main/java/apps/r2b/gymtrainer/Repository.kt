package apps.r2b.gymtrainer

import android.content.Context

class Repository(val appContext: Context) {
    fun getKeys(): ArrayList<String> {
        var keys = ArrayList<String>()
        val configPrefs = appContext.getSharedPreferences(appContext.resources.getString(R.string.config_filename), 0)
        val imageNames = appContext.resources.getStringArray(R.array.image_names)
        for(name in imageNames) {
            if (configPrefs.getBoolean(name, false)) {
                keys.add(name)
            }
        }
        return keys
    }
    fun getItems(): ArrayList<Config> {
        val items: ArrayList<Config> = ArrayList()
        val prefs = appContext.getSharedPreferences(appContext.resources.getString(R.string.config_filename), 0)!!
        val imageNames = appContext.resources.getStringArray(R.array.image_names)
        for(name in imageNames) {
            val res = appContext.resources.getIdentifier(name, "drawable", appContext.packageName)
            items.add(Config(res, name, prefs.getBoolean(name, false)))
        }
        return items
    }
}