package hu.naturlecso.distancetracker.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 1,
    exportSchema = false,
    entities = [TripDataModel::class]
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class DistanceTrackerDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
}
