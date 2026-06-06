package com.asad.composeuipractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asad.composeuipractice.screens.HomeScreen
import com.asad.composeuipractice.screens.SampleScreen
import com.asad.composeuipractice.ui.theme.ComposeUiPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeUiPracticeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.HOME,
                    // 화면 전환 효과 제거 (디폴트는 페이드 어웨이)
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None }
                ) {
                    composable(Routes.HOME) {
                        HomeScreen(onNavigate = { route -> navController.navigate(route) })
                    }
                    composable(
                        Routes.SAMPLE,
                        // 화면 전환 시 좌우 슬라이드
                        enterTransition = { slideIntoContainer(SlideDirection.Left, tween(300)) },
                        exitTransition = { slideOutOfContainer(SlideDirection.Left, tween(300)) },
                        popEnterTransition = { slideIntoContainer(SlideDirection.Right, tween(300)) },
                        popExitTransition = { slideOutOfContainer(SlideDirection.Right, tween(300)) }
                    ) {
                        SampleScreen()
                    }
                }
            }
        }
    }
}
