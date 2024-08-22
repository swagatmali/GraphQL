package com.swagatmali.graphql.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.swagatmali.graphql.presentation.continents.ContinentScreen
import com.swagatmali.graphql.presentation.continents.ContinentsViewModel
import com.swagatmali.graphql.presentation.details.FetchDetailsScreen
import com.swagatmali.graphql.presentation.details.FetchDetailsViewModel
import kotlinx.serialization.Serializable

@Composable
fun MainNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Dest.ContinentScreen) {
        composable<Dest.ContinentScreen> {
            val viewModel = hiltViewModel<ContinentsViewModel>()
            ContinentScreen(viewModel) {
                navController.navigate(Dest.FetchDetailsScreen(it))
            }
        }
        composable<Dest.FetchDetailsScreen> {
            val viewModel = hiltViewModel<FetchDetailsViewModel>()
            val code = it.toRoute<Dest.FetchDetailsScreen>().code
            LaunchedEffect(key1 = Unit) {
                viewModel.getDetails(code)
            }
            FetchDetailsScreen(viewModel)
        }

    }
}

@Serializable
sealed class Dest {
    @Serializable
    object ContinentScreen : Dest()

    @Serializable
    data class FetchDetailsScreen(val code: String) : Dest()
}