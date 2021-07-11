package com.example.geoip.domain

import com.example.geoip.data.database.GeoInfoEntity
import com.example.geoip.data.network.InfoJson
import com.example.geoip.ui.adapter.GeoIpInfoItem
import javax.inject.Inject

class Parser @Inject constructor() {
    fun parseGeoJsonToGeoEntity(infoJson: InfoJson): GeoInfoEntity =
        GeoInfoEntity(
            ip = infoJson.ip,
            country = infoJson.country,
            countryCode = infoJson.countryCode,
            region = infoJson.region,
            regionName = infoJson.regionName,
            city = infoJson.city,
            zip = infoJson.city,
            lat = infoJson.lat,
            lon = infoJson.lon,
            timeZone = infoJson.timeZone,
            isp = infoJson.isp,
            org = infoJson.org,
            asValue = infoJson.asValue
        )

    fun parseGeoEntityToGeoIpInfoItem(geoInfoEntity: GeoInfoEntity): List<GeoIpInfoItem> {

        return listOf(
            GeoIpInfoItem(geoInfoEntity::ip.name, geoInfoEntity.ip),
            GeoIpInfoItem(geoInfoEntity::country.name, geoInfoEntity.country),
            GeoIpInfoItem(geoInfoEntity::countryCode.name, geoInfoEntity.countryCode),
            GeoIpInfoItem(geoInfoEntity::region.name, geoInfoEntity.region),
            GeoIpInfoItem(geoInfoEntity::regionName.name, geoInfoEntity.regionName),
            GeoIpInfoItem(geoInfoEntity::city.name, geoInfoEntity.city),
            GeoIpInfoItem(geoInfoEntity::zip.name, geoInfoEntity.zip),
            GeoIpInfoItem(
                geoInfoEntity::lat.name,
                if (geoInfoEntity.ip.isEmpty()) "" else geoInfoEntity.lat.toString()
            ),
            GeoIpInfoItem(
                geoInfoEntity::lon.name,
                if (geoInfoEntity.ip.isEmpty()) "" else geoInfoEntity.lon.toString()
            ),
            GeoIpInfoItem(geoInfoEntity::timeZone.name, geoInfoEntity.timeZone),
            GeoIpInfoItem(geoInfoEntity::isp.name, geoInfoEntity.isp),
            GeoIpInfoItem(geoInfoEntity::org.name, geoInfoEntity.org),
            GeoIpInfoItem(geoInfoEntity::asValue.name, geoInfoEntity.asValue)
        )
    }

}