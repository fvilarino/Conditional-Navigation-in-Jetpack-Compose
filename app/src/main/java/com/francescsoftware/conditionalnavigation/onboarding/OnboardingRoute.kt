package com.francescsoftware.conditionalnavigation.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.francescsoftware.conditionalnavigation.ui.theme.ConditionalNavigationTheme

const val OnboardingKey = "onboarding"

@Composable
fun OnboardingRoute(
    onboardingStorage: OnboardingStorage,
    popBackStack: () -> Unit,
) {
    OnboardingScreen(
        onOnboarded = {
            onboardingStorage.onOnboarded()
            popBackStack()
        },
    )
}

@Composable
private fun OnboardingScreen(
    onOnboarded: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = onOnboarded,
        ) {
            Text(
                text = "Click here to complete onboarding"
            )
        }
    }
}

@Preview(widthDp = 360, showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    ConditionalNavigationTheme {
        OnboardingScreen(
            onOnboarded = {},
        )
    }
}
