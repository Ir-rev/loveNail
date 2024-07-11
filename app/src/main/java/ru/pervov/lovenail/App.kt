package ru.pervov.lovenail

import android.app.Application
import ru.pervov.lovenail.clients_api.ClientApiInstanceGetter

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ClientApiInstanceGetter.getInstance().getClientDataBaseSetting().init(this)
    }
}