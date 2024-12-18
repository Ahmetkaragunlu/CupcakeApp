package com.ahmetkaragunlu.cupcakeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmetkaragunlu.cupcakeapp.screens.SelectDateScreen
import com.ahmetkaragunlu.cupcakeapp.screens.SelectFlavorScreen
import com.ahmetkaragunlu.cupcakeapp.screens.StartOrderScreen
import com.ahmetkaragunlu.cupcakeapp.screens.SummaryScreen
import com.ahmetkaragunlu.cupcakeapp.viewmodel.OrderViewModel


@Composable
fun OrderNavigation(
    viewModel: OrderViewModel = viewModel(),
) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screens.StartOrderScreen.route,
    ) {
        composable(route = Screens.StartOrderScreen.route) {
            StartOrderScreen(
                navController = navController,
                subTotal = { viewModel.subTotal(it) }
            )
        }
        composable(route = Screens.SelectFlavorScreen.route) {
            SelectFlavorScreen(
                navController = navController,
                totalPrice = { viewModel.formatTotalPrice() },
                setFlavor = { viewModel.setFlavor(it) },
                flavor = uiState.flavor,
                resetOrder = { viewModel.resetOrder() },
                canNavigateBack = navController.previousBackStackEntry != null
            )
        }
        composable(route = Screens.SelectDateScreen.route) {
            SelectDateScreen(
                navController = navController,
                currentDateList = { viewModel.currentDateList() },
                totalPrice = { viewModel.formatTotalPrice() },
                setDate = { viewModel.setDate(it) },
                date = uiState.date,
                resetOrder = { viewModel.resetOrder() },
                canNavigateBack = navController.previousBackStackEntry != null
            )
        }
        composable(route = Screens.SummaryScreen.route) {
            SummaryScreen(
                quantity = uiState.quantity,
                flavor = uiState.flavor,
                pieceCupcake = { viewModel.pieceCupcake() },
                currentDate = uiState.date,
                totalPrice = { viewModel.formatTotalPrice() },
                navController = navController,
                resetOrder = { viewModel.resetOrder() },
                canNavigateBack = navController.previousBackStackEntry != null
            )
        }
    }
}