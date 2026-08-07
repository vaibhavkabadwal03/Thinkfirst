package com.example.thinkfirst.feature.appselection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thinkfirst.domain.usecase.GetInstalledAppsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppSelectionViewModel @Inject constructor(
    private val getInstalledApps: GetInstalledAppsUseCase
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(AppSelectionUiState())

    val uiState = _uiState.asStateFlow()

    init {
        loadInstalledApps()
    }

    private fun loadInstalledApps() {
        viewModelScope.launch {

            _uiState.update {
                it.copy(isLoading = true)
            }

            val apps = getInstalledApps()

            _uiState.update {
                it.copy(
                    isLoading = false,
                    installedApps = apps
                )
            }
        }
    }
}