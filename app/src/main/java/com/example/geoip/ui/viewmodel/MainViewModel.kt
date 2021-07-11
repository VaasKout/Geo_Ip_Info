package com.example.geoip.ui.viewmodel

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geoip.data.database.GeoInfoEntity
import com.example.geoip.data.network.NetState
import com.example.geoip.data.repository.Repository
import com.example.geoip.data.res.Strings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val prefs: SharedPreferences
) : ViewModel() {

    init {
        prefs.getBoolean(Strings.TAG_FIRST_LOAD, true).also {
            if (it) {
                viewModelScope.launch {
                    repository.insertFirstLoadData()
                    prefs.edit {
                        putBoolean(Strings.TAG_FIRST_LOAD, false)
                    }
                }
            }
        }
    }

    val geoIpInfo = repository.geoIpInfo

    private val _netState: MutableStateFlow<NetState> = MutableStateFlow(NetState.Loading)
    val netState: StateFlow<NetState> = _netState

    fun updateInfo() {
        viewModelScope.launch {
            _netState.value = NetState.Loading
            _netState.value = repository.updateGeoInfoData()
        }
    }

    suspend fun parseToGeoIpItemList(geoInfoEntity: GeoInfoEntity) =
        repository.parseToGeoIpItemList(geoInfoEntity)

}