
package com.ahmetkaragunlu.cupcakeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetkaragunlu.cupcakeapp.R
import com.ahmetkaragunlu.cupcakeapp.model.CupcakeAppBar
import com.ahmetkaragunlu.cupcakeapp.navigation.Screens


@Composable
fun StartOrderScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    totalPrice: (Int) -> Unit
) {
      Scaffold(
          topBar = {
              CupcakeAppBar(
                  title = R.string.cupcake,
                  iconButton = false,
              )
          }
      ) { innerPadding ->
          Column(modifier = modifier.fillMaxSize().padding(innerPadding).verticalScroll(rememberScrollState())) {
              Column(
                  modifier = modifier.fillMaxWidth().padding(24.dp),
                  horizontalAlignment = Alignment.CenterHorizontally,
                  verticalArrangement = Arrangement.spacedBy(24.dp)
              ) {
                  Image(
                      painter = painterResource(R.drawable.cupcake),
                      contentDescription = null
                  )
                  Text(
                      text = stringResource(R.string.order_cupcakes),
                      style = MaterialTheme.typography.headlineSmall
                  )
              }
              Spacer(modifier = modifier.weight(1f))
              Column(modifier = modifier.fillMaxWidth()) {
                  Button(
                      onClick = {
                          totalPrice(1)
                          navController.navigate(Screens.SelectFlavorScreen.route)
                      },
                      colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF984062)),
                      shape = RoundedCornerShape(32.dp),
                      modifier = modifier.padding(8.dp)
                  ) {
                      Text(
                          text = stringResource(R.string.one_cupcake),
                          modifier=modifier.fillMaxWidth(),
                          textAlign = TextAlign.Center,
                          style = MaterialTheme.typography.labelLarge
                      )
                  }
                  Button(
                      onClick = {
                          totalPrice(6)
                          navController.navigate(Screens.SelectFlavorScreen.route)
                      },
                      colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF984062)),
                      shape = RoundedCornerShape(32.dp),
                      modifier = modifier.padding(8.dp)
                  ) {
                      Text(
                          text = stringResource(R.string.six_cupcakes),
                          modifier=modifier.fillMaxWidth(),
                          textAlign = TextAlign.Center,
                          style = MaterialTheme.typography.labelLarge
                      )
                  }
                  Button(
                      onClick = {
                          totalPrice(12)
                          navController.navigate(Screens.SelectFlavorScreen.route)
                      },
                      colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF984062)),
                      shape = RoundedCornerShape(32.dp),
                      modifier = modifier.padding(8.dp),
                  ) {
                      Text(
                          text = stringResource(R.string.twelve_cupcakes),
                          modifier=modifier.fillMaxWidth(),
                          textAlign = TextAlign.Center,
                          style = MaterialTheme.typography.labelLarge
                      )
                  }
              }

          }

      }

}

