package com.example.nasa.data.di

import com.example.nasa.data.repository.NasaRepositoryImpl
import com.example.nasa.domain.NasaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNasaRepository(
        nasaRepositoryImpl: NasaRepositoryImpl
    ): NasaRepository

}