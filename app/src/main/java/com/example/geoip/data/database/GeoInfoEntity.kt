package com.example.geoip.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "geo_ip_info_table")
data class GeoInfoEntity(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "ip") val ip: String = "",
    @ColumnInfo(name = "country") val country: String = "",
    @ColumnInfo(name = "countryCode") val countryCode: String = "",
    @ColumnInfo(name = "region") val region: String = "",
    @ColumnInfo(name = "regionName") val regionName: String = "",
    @ColumnInfo(name = "city") val city: String = "",
    @ColumnInfo(name = "zip") val zip: String = "",
    @ColumnInfo(name = "lat") val lat: Double = 0.0,
    @ColumnInfo(name = "lon") val lon: Double = 0.0,
    @ColumnInfo(name = "timeZone") val timeZone: String = "",
    @ColumnInfo(name = "isp") val isp: String = "",
    @ColumnInfo(name = "org") val org: String = "",
    @ColumnInfo(name = "asValue") val asValue: String = "",
)

@Entity(tableName = "geo_ip_info_flags")
data class GeoInfoFlags(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "ip_shown") val ip: Boolean = true,
    @ColumnInfo(name = "country_shown") val country: Boolean = true,
    @ColumnInfo(name = "countryCode_shown") val countryCode: Boolean = true,
    @ColumnInfo(name = "region_shown") val region: Boolean = true,
    @ColumnInfo(name = "regionName_shown") val regionName: Boolean = true,
    @ColumnInfo(name = "city_shown") val city: Boolean = true,
    @ColumnInfo(name = "zip_shown") val zip: Boolean = true,
    @ColumnInfo(name = "lat_shown") val lat: Boolean = true,
    @ColumnInfo(name = "lon_shown") val lon: Boolean = true,
    @ColumnInfo(name = "timeZone_shown") val timeZone: Boolean = true,
    @ColumnInfo(name = "isp_shown") val isp: Boolean = true,
    @ColumnInfo(name = "org_shown") val org: Boolean = true,
    @ColumnInfo(name = "asValue_shown") val asValue: Boolean = true,
)