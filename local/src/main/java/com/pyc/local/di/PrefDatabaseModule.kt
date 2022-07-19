package com.pyc.local.di

import android.content.Context
import com.pyc.local.sharedpref.PrefDatabase
import com.pyc.local.sharedpref.PrefDatabaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object PrefDatabaseModule {

    @Provides
    fun providePrefDatabase(@ApplicationContext context: Context) : PrefDatabase {
        return PrefDatabaseImpl(context)
    }
}