package sk.itsovy.android.opendatagame

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NamesDao {

    @Query("SELECT * FROM record")
    fun readRecords(): Flow<List<Record>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecord(record: Record)

    @Query("DELETE FROM record")
    suspend fun deleteAll()
}