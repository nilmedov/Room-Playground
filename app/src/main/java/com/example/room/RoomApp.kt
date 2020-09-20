package com.example.room

import android.app.Application
import com.example.room.trigger.di.triggerDemoRepositoryModule
import com.example.room.trigger.di.triggerDemoViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RoomApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RoomApp)
            modules(
                listOf(
                    triggerDemoRepositoryModule,
                    triggerDemoViewModelModule
                )
            )
        }
    }
}