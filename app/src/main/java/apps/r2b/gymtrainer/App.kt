package apps.r2b.gymtrainer

import android.app.Application

class App : Application(), Injector {

    private lateinit var repo: Repository

    override fun onCreate() {
        super.onCreate()
        repo = Repository(this)
    }

    override fun getRepository(): Repository  = repo

}