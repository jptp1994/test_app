package com.jean.mercadopago.di.module

import android.content.Context
import com.jean.mercadopago.database.DatabaseLocal
import com.jean.mercadopago.database.dataBaseInter.FindsLogs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
//MOdule for inyect Database room
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): DatabaseLocal = DatabaseLocal.getDatabase(context)

    @Provides
    @Singleton
    fun provideFindsLogs(database: DatabaseLocal): FindsLogs =
        database.findsLogs()
}