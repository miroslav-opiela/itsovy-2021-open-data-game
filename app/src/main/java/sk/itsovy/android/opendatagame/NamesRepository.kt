package sk.itsovy.android.opendatagame

import androidx.annotation.WorkerThread
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class NamesRepository(val namesDao: NamesDao) {

    var allRecords = namesDao.readRecords()

    /*@WorkerThread
    suspend fun insertRecord(record: Record){
        namesDao.insertRecord(record)
    }*/

    @WorkerThread
    suspend fun loadRecords(){
        namesDao.deleteAll()

        val call = RestApi.namesRestDao.getNames()
        val response = suspendCancellableCoroutine<Response<List<Record>>> {
            call.enqueue(
                object : Callback<List<Record>> {
                    override fun onResponse(
                        call: Call<List<Record>>,
                        response: Response<List<Record>>
                    ) {
                        it.resume(response)
                    }

                    override fun onFailure(call: Call<List<Record>>, t: Throwable) {
                        it.cancel(t)
                        //it.resumeWithException(t)
                    }
                }
            )
        }

        val list = response.body()
        if (list != null) {
            for (record in list) {
                namesDao.insertRecord(record)
            }
        }

    }

}
