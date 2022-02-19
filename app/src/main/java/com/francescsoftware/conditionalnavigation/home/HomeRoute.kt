package com.francescsoftware.conditionalnavigation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import com.francescsoftware.conditionalnavigation.onboarding.OnboardingKey
import com.francescsoftware.conditionalnavigation.onboarding.OnboardingResult
import com.francescsoftware.conditionalnavigation.onboarding.OnboardingState
import com.francescsoftware.conditionalnavigation.onboarding.OnboardingStorage
import com.francescsoftware.conditionalnavigation.ui.theme.ConditionalNavigationTheme

@Composable
fun HomeRoute(
    onboardingStorage: OnboardingStorage,
    savedStateHandle: SavedStateHandle,
    toOnboarding: () -> Unit,
    onOnboardingCancelled: () -> Unit,
) {
    val onboardingState by onboardingStorage.onboardingState.collectAsState()
    val onboardingResult by savedStateHandle.getLiveData<OnboardingResult>(OnboardingKey)
        .observeAsState()
    when (onboardingState) {
        OnboardingState.NotOnboarded -> {
            when (onboardingResult) {
                null -> LaunchedEffect(key1 = Unit) {
                    toOnboarding()
                }
                OnboardingResult.Completed -> HomeScreen()
                OnboardingResult.Cancelled -> LaunchedEffect(key1 = Unit) {
                    onOnboardingCancelled()
                }
            }
        }
        OnboardingState.Onboarded -> HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "You have onboarded"
        )
    }
}

@Preview(widthDp = 360, showBackground = true)
@Composable
fun PreviewHomeScreen() {
    ConditionalNavigationTheme {
        HomeScreen()
    }
}
