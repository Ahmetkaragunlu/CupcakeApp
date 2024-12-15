package com.ahmetkaragunlu.cupcakeapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmetkaragunlu.cupcakeapp.screens.SelectFlavorScreen
import com.ahmetkaragunlu.cupcakeapp.screens.StartOrderScreen
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
             totalPrice ={viewModel.totalPrice(it)}
            )
        }
        composable(route = Screens.SelectFlavorScreen.route) {
           SelectFlavorScreen(
               navController = navController,
               subtotal = {viewModel.formatTotalPrice()},
               setFlavor = {viewModel.setFlavor(it)}
           )
        }



    }
}