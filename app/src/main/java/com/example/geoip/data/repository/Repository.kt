package com.example.geoip.data.repository

import com.example.geoip.data.database.GeoInfoDao
import com.example.geoip.data.database.GeoInfoEntity
import com.example.geoip.data.database.GeoInfoFlags
import com.example.geoip.data.network.InfoJson
import com.example.geoip.data.network.IpApiService
import com.example.geoip.data.network.NetState
import com.example.geoip.data.res.Strings
import com.example.geoip.domain.Parser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val geoInfoDao: GeoInfoDao,
    private val ipApiService: IpApiService,
    private val parser: Parser
) {

    val geoIpInfo: Flow<GeoInfoEntity> = geoInfoDao.getGeoIpInfo()

    suspend fun insertFirstLoadData() = withContext(Dispatchers.IO) {
        geoInfoDao.insertGeoIpInfo(GeoInfoEntity())
        geoInfoDao.insertGeoIpFlags(GeoInfoFlags())
    }

    private suspend fun getInfoJson(): InfoJson = withContext(Dispatchers.IO) {
        ipApiService.getInfo()
    }

    private suspend fun updateGeoInfoEntity(geoInfoEntity: GeoInfoEntity) =
        withContext(Dispatchers.IO) {
            geoInfoDao.updateGeoIpInfo(geoInfoEntity)
        }

    suspend fun parseToGeoIpItemList(geoInfoEntity: GeoInfoEntity) =
        withContext(Dispatchers.Default) {
            parser.parseGeoEntityToGeoIpInfoItem(geoInfoEntity)
        }

    suspend fun updateGeoInfoData(): NetState = withContext(Dispatchers.IO) {
        try {
            val infoJson = getInfoJson()
            if (infoJson.status == Strings.statusFail) {
                return@withContext NetState.Error(infoJson.message)
            }
            val geoInfoEntity = parser.parseGeoJsonToGeoEntity(infoJson)
            updateGeoInfoEntity(geoInfoEntity)
            return@withContext NetState.Success
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext NetState.Error()
        }
    }
}