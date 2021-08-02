package com.adewijayanto.defilmsapp2.di

import com.adewijayanto.defilmsapp2.data.CatalogueRepository
import com.adewijayanto.defilmsapp2.data.source.network.ApiConfig
import com.adewijayanto.defilmsapp2.data.source.remote.RemoteDataSource


object Injection {
    fun provideRepository(): CatalogueRepository {
        val remoteRepository = RemoteDataSource.getInstance(ApiConfig())
        return CatalogueRepository.getInstance(remoteRepository)!!
    }
}