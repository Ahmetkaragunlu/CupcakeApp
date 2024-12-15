package com.ahmetkaragunlu.cupcakeapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat


const val unitPrice = 2

class OrderViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(OrderData())
    val uiState : StateFlow<OrderData> = _uiState.asStateFlow()

    fun formatTotalPrice() : String = NumberFormat.getCurrencyInstance().format(_uiState.value.price)

    fun totalPrice(quantity : Int) {
        _uiState.update { currentState->
            currentState.copy(
                price = unitPrice * quantity,
                quantity = quantity
            )
        }
    }
    fun setFlavor(setFlavor : String) {
        _uiState.update { currentState->
            currentState.copy(flavor = setFlavor)
        }
    }

}