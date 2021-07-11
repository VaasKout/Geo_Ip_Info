package com.example.geoip.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [GeoInfoFlags::class, GeoInfoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GeoInfoDatabase : RoomDatabase() {
    abstract fun getGeoInfoDao(): GeoInfoDao
}