package com.neighborly.thinkfirst.feature.intervention

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InterventionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(InterventionUiState())
    val uiState = _uiState.asStateFlow()
    private var countdownJob: Job? = null

    fun startIntervention(appName: String) {
        countdownJob?.cancel()
        _uiState.update {
            it.copy(
                appName = appName,
                countDown = 5,
                isCountdownFinish = false
            )
        }

        countdownJob = viewModelScope.launch {
            for (seconds in 5 downTo 1) {
                _uiState.update {
                    it.copy(countDown = seconds)
                }
                delay(1000)
            }
            _uiState.update {
                it.copy(isCountdownFinish = true)
            }
        }
    }
    override fun onCleared() {
        countdownJob?.cancel()
        super.onCleared()
    }
}