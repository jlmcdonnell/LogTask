package dev.mcd.logtask.feature.log.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.mcd.logtask.feature.log.data.UTCLogTimeProvider
import dev.mcd.logtask.feature.log.data.serializer.TextFileLogStore
import dev.mcd.logtask.feature.log.data.usecase.ReadLogImpl
import dev.mcd.logtask.feature.log.domain.LogStore
import dev.mcd.logtask.feature.log.domain.LogTimeProvider
import dev.mcd.logtask.feature.log.domain.usecase.AppendLog
import dev.mcd.logtask.feature.log.domain.usecase.AppendLogImpl
import dev.mcd.logtask.feature.log.domain.usecase.ReadLog
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LogModule {

    @Binds
    @Singleton
    abstract fun logStore(impl: TextFileLogStore): LogStore

    @Binds
    @Singleton
    abstract fun logTimeProvider(impl: UTCLogTimeProvider): LogTimeProvider

    @Binds
    @Singleton
    abstract fun readLog(impl: ReadLogImpl): ReadLog

    @Binds
    @Singleton
    abstract fun appendLog(impl: AppendLogImpl): AppendLog

    companion object {
        private const val LOG_FILE_NAME = "log.txt"

        @ForLogStore
        @Singleton
        @Provides
        fun logFile(@ApplicationContext context: Context): File {
            val parent = context.filesDir
            return File(parent, LOG_FILE_NAME)
        }

        @ForLogStore
        @Singleton
        @Provides
        fun logStoreDispatcher() = Dispatchers.IO

        @Singleton
        @Provides
        fun json(): Json = Json
    }
}
