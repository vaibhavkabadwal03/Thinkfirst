package com.neighborly.thinkfirst.domain.usecase

import com.neighborly.thinkfirst.domain.repository.AppRepository
import javax.inject.Inject

class SaveSelectedAppsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(
        packageNames: Set<String>
    ) {
        repository.saveSelectedApps(packageNames)
    }
}