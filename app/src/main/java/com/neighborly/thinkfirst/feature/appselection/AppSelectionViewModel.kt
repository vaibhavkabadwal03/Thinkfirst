package com.neighborly.thinkfirst.feature.appselection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neighborly.thinkfirst.domain.usecase.GetInstalledAppsUseCase
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
    private val _uiState = MutableStateFlow(AppSelectionUiState())

    val uiState = _uiState.asStateFlow()

    init {
        loadInstalledApps()
    }

    private fun loadInstalledApps() {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }

            runCatching { getInstalledApps() }.onSuccess { apps ->
                _uiState.update {
                    it.copy(
                        apps = apps,
                        isLoading = false
                    )
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = throwable.message
                    )
                }
            }
        }
    }

    fun onAppSelectionChanged(
        packageName: String,
        selected: Boolean
    ) {
        val updatedSelection =
            _uiState.value.selectedPackages.toMutableSet()

        if (selected) {
            updatedSelection.add(packageName)
        } else {
            updatedSelection.remove(packageName)
        }

        _uiState.update {
            it.copy(
                selectedPackages = updatedSelection
            )
        }
    }
    fun onContinueClicked(){
        _uiState.update {
            it.copy(showAccessibilityDialog = true)
        }
    }

    fun onAccessibilityDialogDismiss() {
        _uiState.update {
            it.copy(showAccessibilityDialog = false)
        }
    }
}