package com.pyc.local.di

import com.pyc.data.datasource.LastPlayerStatusInformationDatasource
import com.pyc.local.datasource.LastPlayerStatusInformationDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalLastPlayerStatusInformationModule {

    @Binds
    @Singleton
    internal abstract fun bindLocalLastPlayerStatusInformationDataSource(
        lastPlayerStatusInformationDatasourceImpl: LastPlayerStatusInformationDatasourceImpl
    ) : LastPlayerStatusInformationDatasource
}