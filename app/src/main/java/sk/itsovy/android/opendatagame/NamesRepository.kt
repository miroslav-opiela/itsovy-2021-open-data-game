package sk.itsovy.android.opendatagame

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class NamesRepository(val namesDao: NamesDao) {

    var allRecords = namesDao.readRecords()

    @WorkerThread
    suspend fun loadRecords() {
        namesDao.deleteAll()

        try {
            val namesResult = RestApi.namesRestDao.getNames()
            namesDao.insert(namesResult)
        } catch (e: Exception) {
            Log.e("EXCEPTION", e.toString())
        }


    }

}
