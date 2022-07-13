package com.pyc.local.di

import android.content.Context
import com.pyc.local.sharedpref.PrefDatabase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class PrefDatabaseModule {

    @Binds
    abstract fun providePrefDatabase(@ApplicationContext context: Context) : PrefDatabase
}