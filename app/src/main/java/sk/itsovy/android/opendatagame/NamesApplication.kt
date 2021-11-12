package sk.itsovy.android.opendatagame

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NamesApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { NamesDatabase.getDatabase(this, applicationScope) }
    
    val repository by lazy { NamesRepository(database.namesDao()) }

}