package dev.mcd.logtask.feature.log.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.mcd.logtask.feature.log.data.serializer.TextFileLogStore
import dev.mcd.logtask.feature.log.domain.LogStore
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LogModule {

    @Binds
    @Singleton
    abstract fun logStore(impl: TextFileLogStore): LogStore

    companion object {
        private const val LOG_FILE_NAME = "log.txt"

        @LogFile
        @Singleton
        fun logFile(@ApplicationContext context: Context): File {
            val parent = context.filesDir
            return File(parent, LOG_FILE_NAME)
        }

        @Singleton
        fun json(): Json = Json
    }
}
