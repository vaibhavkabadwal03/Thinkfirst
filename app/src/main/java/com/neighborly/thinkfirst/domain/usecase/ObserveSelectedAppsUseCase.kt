package com.neighborly.thinkfirst.domain.usecase

import com.neighborly.thinkfirst.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveSelectedAppsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    operator fun invoke(): Flow<Set<String>> {
        return repository.observeSelectedApps()
    }
}