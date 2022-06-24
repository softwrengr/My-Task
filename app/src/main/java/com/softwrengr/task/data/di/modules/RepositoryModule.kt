package com.softwrengr.task.data.di.modules

import android.app.Application
import com.softwrengr.task.data.api.ApiService
import com.softwrengr.task.data.datautils.StringUtils
import com.softwrengr.task.data.repository.MyRepository
import com.softwrengr.task.data.repository.MyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Dagger Module for providing repository instances.
 * @author Wajahat Karim
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideStringUtils(app: Application): StringUtils {
        return StringUtils(app)
    }

    @Singleton
    @Provides
    fun provideImagineRepository(stringUtils: StringUtils, apiService: ApiService): MyRepository {
        return MyRepositoryImpl(apiService)
    }
}
