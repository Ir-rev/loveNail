package ru.pervov.lovenail

import android.app.Application
import ru.pervov.calendar_api.CalendarApiInstanceGetter
import ru.pervov.lovenail.clients_api.ClientApiInstanceGetter
import ru.pervov.procedure_api.ProcedureApiInstanceGetter

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ClientApiInstanceGetter.getInstance().getClientDataBaseSetting().init(this)
        ProcedureApiInstanceGetter.getInstance().getProcedureDatabaseSetting().init(this)
        CalendarApiInstanceGetter.getInstance().getCalendarDatabaseSetting().init(this)
    }
}