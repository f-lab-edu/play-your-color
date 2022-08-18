package com.pyc.data.di

import com.pyc.data.repository.ColorInfoRepositoryImpl
import com.pyc.data.repository.LastPlayerStatusInformationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pyc.domain.repository.ColorInfoRepository
import pyc.domain.repository.LastPlayerStatusInformationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindColorInfoRepository(
        colorInfoRepositoryImpl: ColorInfoRepositoryImpl
    ): ColorInfoRepository

    @Binds
    @Singleton
    internal abstract fun bindLastPlayerStatusInformationRepository(
        lastPlayerStatusInformationRepositoryImpl: LastPlayerStatusInformationRepositoryImpl
    ): LastPlayerStatusInformationRepository
}