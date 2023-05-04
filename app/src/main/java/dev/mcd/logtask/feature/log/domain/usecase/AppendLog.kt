package dev.mcd.logtask.feature.log.domain.usecase

interface AppendLog {
    suspend operator fun invoke(text: String)
}
