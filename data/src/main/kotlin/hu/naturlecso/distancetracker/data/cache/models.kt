package hu.naturlecso.distancetracker.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime

@Entity
data class TripDataModel(
    @PrimaryKey val id: Long,
    val name: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime?,
    val distance: Float
)
