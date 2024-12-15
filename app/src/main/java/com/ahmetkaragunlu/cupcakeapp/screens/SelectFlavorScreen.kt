package com.ahmetkaragunlu.cupcakeapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetkaragunlu.cupcakeapp.R
import com.ahmetkaragunlu.cupcakeapp.model.CupcakeAppBar
import com.ahmetkaragunlu.cupcakeapp.model.DataSource
import com.ahmetkaragunlu.cupcakeapp.navigation.Screens


@Composable
fun SelectFlavorScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    subtotal: () -> String,
    setFlavor: (String) -> Unit,
) {
    var selectedFlavor by rememberSaveable { mutableStateOf("") }
    var isSelected by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CupcakeAppBar(
                title = R.string.choose_flavor,
                iconButton = true,
                navigateIcon = {}
            )
        }
    ) { innerPadding ->
        Column(modifier = modifier.fillMaxSize().padding(innerPadding).verticalScroll(rememberScrollState())) {
            Column(modifier = modifier.fillMaxWidth()) {
                DataSource.flavors.forEach { flavors ->
                    Column(
                        modifier = modifier.padding(start = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(
                            modifier = modifier.selectable(
                                selected = selectedFlavor == flavors.toString(),
                                onClick = {
                                    setFlavor(flavors.toString())
                                    selectedFlavor = flavors.toString()
                                    isSelected = true
                                }
                            ),

                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedFlavor == flavors.toString(),
                                onClick = {
                                    setFlavor(flavors.toString())
                                    selectedFlavor = flavors.toString()
                                    isSelected = true
                                },
                                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF984062))
                            )
                            Text(
                                text = stringResource(id = flavors)
                            )
                        }

                    }
                }
            }
            HorizontalDivider(
                thickness = 1.dp,
                modifier = modifier.padding(bottom = 36.dp, start = 16.dp, end = 16.dp)
            )
            Text(
                text = stringResource(R.string.subtotal, subtotal()),
                modifier = modifier.padding(16.dp).fillMaxWidth(),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.End
            )
            Spacer(modifier = modifier.weight(1f))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 48.dp, start = 16.dp, end = 16.dp),
            )
            {
                OutlinedButton(
                    onClick = {
                        navController.navigate(Screens.StartOrderScreen.route)
                    },
                    modifier = modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF984062)
                    )
                }
                Spacer(modifier = modifier.weight(0.1f))
                Button(
                    onClick = {

                    },
                    modifier = modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) Color(0xFF984062) else Color(0xFFe4dfe3)
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.next),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                    )
                }
            }

        }

    }
}



