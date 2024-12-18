package com.ahmetkaragunlu.cupcakeapp.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ahmetkaragunlu.cupcakeapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeAppBar(
    @StringRes title: Int,
    navigateIcon: () -> Unit = {},
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(title),
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                ),
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = { navigateIcon() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFffdce4)),
    )
}

@Preview(showBackground = true)
@Composable
fun Ahmet() {
    CupcakeAppBar(
        title = R.string.choose_flavor,
        navigateIcon = {},
        canNavigateBack = true
    )
}
