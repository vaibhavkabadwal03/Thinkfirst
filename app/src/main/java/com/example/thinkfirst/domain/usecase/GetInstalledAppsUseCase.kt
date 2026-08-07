package com.example.thinkfirst.domain.usecase

import com.example.thinkfirst.domain.repository.AppRepository
import javax.inject.Inject

class GetInstalledAppsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke() = repository.getInstalledApps()
}