package com.neighborly.thinkfirst.feature.intervention

import android.app.Application
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neighborly.thinkfirst.data.appIcon.toBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterventionViewModel @Inject constructor(private val application: Application) : ViewModel() {
    private val _uiState = MutableStateFlow(InterventionUiState())
    val uiState = _uiState.asStateFlow()
    private val _effect = MutableSharedFlow<InterventionEffect>()
    val effect = _effect.asSharedFlow()
    private var countdownJob: Job? = null

    fun startIntervention(appName: String, packageName: String) {
        countdownJob?.cancel()
        val appIcon = runCatching {
            application.packageManager
                .getApplicationIcon(packageName)
                .toBitmap()
                .asImageBitmap()
        }.getOrNull()

        _uiState.update {
            it.copy(
                appName = appName,
                countDown = 5,
                isCountdownFinish = false,
                showDecision = false,
                packageName = packageName,
                appIcon = appIcon
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
                it.copy(countDown = 0, isCountdownFinish = true, showDecision = true)
            }
        }
    }

    override fun onCleared() {
        countdownJob?.cancel()
        super.onCleared()
    }

    fun onCloseClick() {
        viewModelScope.launch {
            _effect.emit(
                InterventionEffect.Close
            )
        }
    }

    fun onOpenClick() {
        val packageName = _uiState.value.packageName

        if (packageName.isBlank()) {
            return
        }

        viewModelScope.launch {
            _effect.emit(
                InterventionEffect.OpenApp(packageName)
            )
        }
    }
}