package com.jean.mercadopago.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jean.mercadopago.database.dataBaseInter.FindsLogs
import com.jean.mercadopago.database.modelDataBase.FindModel
@Database(
    entities = [FindModel::class],
    version = DatabaseLocal.DB_VERSION,
    exportSchema = false
)
// Class for create Database of dao with room library
abstract class DatabaseLocal : RoomDatabase() {
    abstract fun findsLogs(): FindsLogs

    companion object {

        @Volatile
        private var INSTANCE: DatabaseLocal? = null

        private val LOCK = Any()
        private var DB_NAME = "mercado_database"
        const val DB_VERSION = 1

        fun getDatabase(context: Context): DatabaseLocal {
            return INSTANCE ?: synchronized(LOCK) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        DatabaseLocal::class.java,
                        DB_NAME
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}