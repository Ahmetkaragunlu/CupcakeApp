package com.ahmetkaragunlu.cupcakeapp.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetkaragunlu.cupcakeapp.R
import com.ahmetkaragunlu.cupcakeapp.model.CupcakeAppBar
import com.ahmetkaragunlu.cupcakeapp.navigation.Screens

@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    quantity: Int,
    flavor: String,
    pieceCupcake: () -> Boolean,
    currentDate: String,
    totalPrice: () -> String,
    navController: NavController,
    resetOrder: () -> Unit,
    canNavigateBack: Boolean
) {
    val context = LocalContext.current
    val shareTitle = stringResource(R.string.share_order)
    val quantityText = context.getString(R.string.quantity)
    val flavorText = context.getString(R.string.flavor)
    val dateText = context.getString(R.string.pickup_date)
    val totalPriceText = context.getString(R.string.total_price)

    val shareOrderSummary = shareOrder(
        quantity = quantity,
        flavor = flavor,
        totalPrice = { totalPrice() },
        date = currentDate,
        quantityText = quantityText,
        flavorText = flavorText,
        dateText = dateText,
        totalPriceText = totalPriceText
    )


    Scaffold(
        topBar = {
            CupcakeAppBar(
                title = R.string.order_summary,
                canNavigateBack = canNavigateBack,
                navigateIcon = { navController.popBackStack() },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Summary(
                    quantity = quantity,
                    flavor = flavor,
                    pieceCupcake = { pieceCupcake() },
                    currentDate = currentDate
                )
                Text(
                    text = stringResource(R.string.subtotal, totalPrice()),
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.End
                )
            }
            Spacer(modifier = modifier.weight(1f))

            Column(modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                Button(
                    onClick = {
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, shareOrderSummary)
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(shareIntent, shareTitle))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF984062))
                ) {
                    Text(
                        text = stringResource(id = R.string.send_order),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                    )
                }
                OutlinedButton(
                    onClick =
                    {
                        navController.navigate(Screens.StartOrderScreen.route)
                        resetOrder()
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF984062)
                    )
                }
            }
        }
    }
}

@Composable
fun Summary(
    quantity: Int,
    flavor: String,
    pieceCupcake: () -> Boolean,
    currentDate: String
) {
    Text(
        text = stringResource(R.string.quantity),
    )
    if (pieceCupcake()) {
        Text(
            text = stringResource(R.string.piece_cupcake, quantity),
            fontWeight = FontWeight.Bold
        )
    } else {
        Text(
            text = stringResource(R.string.piece_cupcakes, quantity),
            fontWeight = FontWeight.Bold
        )
    }
    HorizontalDivider(thickness = 1.dp)
    Text(text = stringResource(R.string.flavor))
    Text(
        text = flavor,
        fontWeight = FontWeight.Bold
    )
    HorizontalDivider(thickness = 1.dp)
    Text(text = stringResource(R.string.pickup_date))
    Text(
        text = currentDate,
        fontWeight = FontWeight.Bold
    )
    HorizontalDivider(thickness = 1.dp)
}

private fun shareOrder(
    quantity: Int,
    flavor: String,
    date: String,
    totalPrice: () -> String,
    quantityText: String,
    flavorText: String,
    dateText: String,
    totalPriceText: String
): String {

    return "$quantityText: $quantity\n$flavorText: $flavor\n$dateText: $date\n$totalPriceText: ${totalPrice()}"
}