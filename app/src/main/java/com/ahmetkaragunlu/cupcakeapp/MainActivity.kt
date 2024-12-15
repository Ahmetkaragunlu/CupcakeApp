package com.ahmetkaragunlu.cupcakeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ahmetkaragunlu.cupcakeapp.navigation.OrderNavigation
import com.ahmetkaragunlu.cupcakeapp.ui.theme.CupcakeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CupcakeAppTheme {
            OrderNavigation()
            }
        }
    }
}


