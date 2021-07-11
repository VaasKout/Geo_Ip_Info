package com.example.geoip.di

import android.content.Context
import android.content.SharedPreferences
import com.example.geoip.data.res.Strings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @Provides
    @Singleton
    fun getDefaultSharedPrefs(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            Strings.TAG_DEFAULT_PREFS, Context.MODE_PRIVATE
        )
}