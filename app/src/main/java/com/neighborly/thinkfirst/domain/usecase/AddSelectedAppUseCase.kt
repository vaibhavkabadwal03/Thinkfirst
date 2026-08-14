package com.neighborly.thinkfirst.domain.usecase

import com.neighborly.thinkfirst.domain.repository.AppRepository
import javax.inject.Inject

class AddSelectedAppUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(packageName: String) {
        repository.addSelectedApp(packageName)
    }
}