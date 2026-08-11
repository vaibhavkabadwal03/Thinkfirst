package com.neighborly.thinkfirst.feature.appselection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neighborly.thinkfirst.domain.model.InstalledApp
import com.neighborly.thinkfirst.domain.usecase.GetInstalledAppsUseCase
import com.neighborly.thinkfirst.domain.usecase.ObserveSelectedAppsUseCase
import com.neighborly.thinkfirst.domain.usecase.SaveSelectedAppsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppSelectionViewModel @Inject constructor(
    private val getInstalledApps: GetInstalledAppsUseCase,
    private val saveSelectedApps: SaveSelectedAppsUseCase,
    private val observeSelectedApps: ObserveSelectedAppsUseCase
) : ViewModel() {
    private var allApps: List<InstalledApp> = emptyList()
    private val _uiState = MutableStateFlow(AppSelectionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeSavedSelection()
        loadInstalledApps()
    }

    private fun observeSavedSelection() {
        viewModelScope.launch {
            observeSelectedApps().collect { selectedPackages ->

                _uiState.update { state ->

                    state.copy(
                        selectedPackages = selectedPackages,
                        apps = getFilteredApps(
                            apps = allApps,
                            selectedPackages = selectedPackages,
                            filter = state.appFilter,
                            searchQuery = state.searchQuery
                        )
                    )
                }
            }
        }
    }

    private fun loadInstalledApps() {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }

            runCatching { getInstalledApps() }.onSuccess { apps ->
                allApps = apps

                _uiState.update {
                    it.copy(
                        apps = allApps,
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
        _uiState.update { state ->

            val updatedSelection =
                state.selectedPackages.toMutableSet()

            if (selected) {
                updatedSelection.add(packageName)
            } else {
                updatedSelection.remove(packageName)
            }

            state.copy(
                selectedPackages = updatedSelection,
                apps = getFilteredApps(
                    apps = allApps,
                    selectedPackages = updatedSelection,
                    filter = state.appFilter,
                    searchQuery = state.searchQuery
                )
            )
        }
    }

    fun onContinueClicked() {
        viewModelScope.launch {

            saveSelectedApps(
                _uiState.value.selectedPackages
            )

            _uiState.update {
                it.copy(
                    showAccessibilityDialog = true
                )
            }
        }
    }

    fun onAccessibilityDialogDismiss() {
        _uiState.update {
            it.copy(showAccessibilityDialog = false)
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { state ->
            state.copy(
                searchQuery = query,
                apps = getFilteredApps(
                    apps = allApps,
                    selectedPackages = state.selectedPackages,
                    filter = state.appFilter,
                    searchQuery = query
                )
            )
        }
    }

    fun onAppFilterChanged(filter: AppFilter) {
        _uiState.update { state ->

            state.copy(
                appFilter = filter,
                apps = getFilteredApps(
                    apps = allApps,
                    selectedPackages = state.selectedPackages,
                    filter = filter,
                    searchQuery = state.searchQuery
                )
            )
        }
    }

    private fun getFilteredApps(
        apps: List<InstalledApp>,
        selectedPackages: Set<String>,
        filter: AppFilter,
        searchQuery: String
    ): List<InstalledApp> {

        val filterApps = when (filter) {
            AppFilter.ALL -> apps

            AppFilter.SELECTED -> {
                apps.filter { app ->
                    app.packageName in selectedPackages
                }
            }
        }

        return if (searchQuery.isBlank()) {
            filterApps
        } else {
            filterApps.filter { app ->
                app.appName.contains(
                    searchQuery.trim(),
                    ignoreCase = true
                )
            }
        }
    }
}