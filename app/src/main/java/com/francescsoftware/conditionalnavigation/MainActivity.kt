package com.francescsoftware.conditionalnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.francescsoftware.conditionalnavigation.home.HomeRoute
import com.francescsoftware.conditionalnavigation.onboarding.OnboardingKey
import com.francescsoftware.conditionalnavigation.onboarding.OnboardingResult
import com.francescsoftware.conditionalnavigation.onboarding.OnboardingRoute
import com.francescsoftware.conditionalnavigation.onboarding.OnboardingStorage
import com.francescsoftware.conditionalnavigation.ui.theme.ConditionalNavigationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var storage: OnboardingStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConditionalNavigationTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                    ) {
                        composable("home") { backStackEntry ->
                            HomeRoute(
                                onboardingStorage = storage,
                                savedStateHandle = backStackEntry.savedStateHandle,
                                toOnboarding = {
                                    navController.navigate(OnboardingKey)
                                },
                                onOnboardingCancelled = {
                                    this@MainActivity.finish()
                                }
                            )
                        }
                        composable("onboarding") {
                            LaunchedEffect(key1 = Unit) {
                                navController.previousBackStackEntry?.savedStateHandle?.set(
                                    OnboardingKey,
                                    OnboardingResult.Cancelled,
                                )
                            }
                            OnboardingRoute(
                                onboardingStorage = storage,
                                popBackStack = { navController.popBackStack() },
                            )
                        }
                    }
                }
            }
        }
    }
}
