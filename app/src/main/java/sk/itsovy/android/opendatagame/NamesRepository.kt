package sk.itsovy.android.opendatagame

import androidx.annotation.WorkerThread

class NamesRepository(val namesDao: NamesDao) {

    var allRecords = namesDao.readRecords()

    /*@WorkerThread
    suspend fun insertRecord(record: Record){
        namesDao.insertRecord(record)
    }*/

    @WorkerThread
    suspend fun loadRecords(){
        namesDao.deleteAll()
        namesDao.insertRecord(Record("Bromilda",956))
        namesDao.insertRecord(Record("Jaro",65))
        namesDao.insertRecord(Record("Petra",1))
    }

}
