package com.example.geoip.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GeoInfoDao {
    @Insert
    suspend fun insertGeoIpInfo(geoInfoEntity: GeoInfoEntity)

    @Insert
    suspend fun insertGeoIpFlags(geoInfoFlags: GeoInfoFlags)

    @Update
    suspend fun updateGeoIpInfo(geoInfoEntity: GeoInfoEntity)

    @Transaction
    @Query("SELECT * FROM geo_ip_info_table")
    fun getGeoIpInfo(): Flow<GeoInfoEntity>
}