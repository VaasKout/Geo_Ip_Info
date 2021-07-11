package com.example.geoip.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.geoip.data.database.GeoInfoDao
import com.example.geoip.data.database.GeoInfoDatabase
import com.example.geoip.data.database.GeoInfoEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun getGeoInfoDB(@ApplicationContext context: Context): GeoInfoDatabase =
        Room.databaseBuilder(
            context,
            GeoInfoDatabase::class.java,
            "geo_info_db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun getGeoInfoDao(geoInfoDatabase: GeoInfoDatabase): GeoInfoDao =
        geoInfoDatabase.getGeoInfoDao()
}