package com.neighborly.thinkfirst.domain.usecase

import com.neighborly.thinkfirst.domain.model.InstalledApp
import com.neighborly.thinkfirst.domain.repository.AppRepository
import javax.inject.Inject

class GetInstalledAppsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): List<InstalledApp> {
        return repository.getInstalledApps()
    }
}