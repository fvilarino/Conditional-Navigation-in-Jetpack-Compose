package com.francescsoftware.conditionalnavigation.onboarding

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingStorage @Inject constructor() {

    private val _onboardingState = MutableStateFlow<OnboardingState>(OnboardingState.NotOnboarded)
    val onboardingState = _onboardingState.asStateFlow()

    fun onOnboarded() {
        _onboardingState.value = OnboardingState.Onboarded
    }
}
