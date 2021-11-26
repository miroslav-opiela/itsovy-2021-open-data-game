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
        namesDao.insertRecord(Record("Daniel", 1))
        namesDao.insertRecord(Record("Brano", 1))
        namesDao.insertRecord(Record("Simon", 1))
        namesDao.insertRecord(Record("Patrik", 1))
        namesDao.insertRecord(Record("Adam", 1))
        namesDao.insertRecord(Record("Kovi", 1))
        namesDao.insertRecord(Record("Illia", 1))
    }

}
