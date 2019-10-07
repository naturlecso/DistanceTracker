package hu.naturlecso.distancetracker.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class TripDao {

    @Query("SELECT * FROM TripDataModel ORDER BY startDate DESC")
    abstract fun getAll(): Flowable<List<TripDataModel>>

    @Query("SELECT * FROM TripDataModel WHERE endDate IS NULL LIMIT 1")
    abstract fun getOngoing(): Single<TripDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(trip: TripDataModel)

    @Update
    abstract fun update(trip: TripDataModel)
}
