package sk.itsovy.android.opendatagame

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(@PrimaryKey val name: String,
                  val count: Int) 

