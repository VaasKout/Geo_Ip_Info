package com.example.geoip.data.network

import com.example.geoip.data.res.Strings

sealed class NetState {
    object Loading : NetState()
    object Success : NetState()
    data class Error(val msg: String = Strings.internetError) : NetState()
}
