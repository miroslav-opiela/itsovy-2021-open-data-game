package sk.itsovy.android.opendatagame

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Record::class], version = 1, exportSchema = false)
abstract class NamesDatabase: RoomDatabase() {

    abstract fun namesDao(): NamesDao

   companion object {
        @Volatile
        private var INSTANCE: NamesDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NamesDatabase {
            return INSTANCE ?: synchronized(this) {
                // vyrobim novu instanciu databazy
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NamesDatabase::class.java,
                    "names_database"
                ).build()
                // vlozim do inst. premennej
                INSTANCE = instance
                // vratim vysledok
                instance
            }
        }
    }


}