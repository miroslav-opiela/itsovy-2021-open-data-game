package sk.itsovy.android.opendatagame

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Record(
    @PrimaryKey @Json(name = "Krstné_meno")
    val name: String,
    @Json(name = "Počet")
    val count: Int
)

