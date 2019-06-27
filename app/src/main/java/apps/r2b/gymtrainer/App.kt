package apps.r2b.gymtrainer

import android.app.Application
import apps.r2b.gymtrainer.data.Repository

class App : Application() {

    private lateinit var repo: Repository

    override fun onCreate() {
        super.onCreate()
        repo = Repository(this)
        repo.init()
    }

    fun getRepository(): Repository = repo

}