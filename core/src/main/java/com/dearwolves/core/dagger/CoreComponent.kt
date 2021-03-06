package com.dearwolves.core.dagger

import android.content.Context
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.IRestService
import com.dearwolves.core.interfaces.ISharedPreferenceService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.repository.LocalRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, RestServiceModule::class, StringServiceModule::class, MediaServiceModule::class, DatabaseModule::class, SharedPreferenceModule::class])
@Singleton
interface CoreComponent {
    fun provideApplicationContext(): Context
    fun provideRestService(): IRestService
    fun provideMediaService(): IMediaService
    fun provideStringService(): IStringService
    fun provideLocalRepository() : LocalRepository
    fun provideSharedPreferenceService(): ISharedPreferenceService

}