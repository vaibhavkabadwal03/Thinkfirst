package com.neighborly.thinkfirst.domain.usecase

import com.neighborly.thinkfirst.domain.repository.AppRepository
import javax.inject.Inject

class RemoveSelectedAppUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(packageName: String) {
        repository.removeSelectedApp(packageName)
    }
}