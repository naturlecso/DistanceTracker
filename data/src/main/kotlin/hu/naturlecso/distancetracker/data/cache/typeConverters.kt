package hu.naturlecso.distancetracker.data.cache

import androidx.room.TypeConverter
import org.threeten.bp.LocalDateTime

class LocalDateTimeConverter {
    
    @TypeConverter
    fun toDate(dateString: String?): LocalDateTime? {
        return dateString?.let {
            LocalDateTime.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime?): String? {
        return date?.toString()
    }
}
