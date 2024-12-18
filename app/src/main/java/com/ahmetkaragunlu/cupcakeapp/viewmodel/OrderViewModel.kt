package com.ahmetkaragunlu.cupcakeapp.viewmodel

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

const val unitPrice = 2
const val sameDay = 3

class OrderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OrderData())
    val uiState: StateFlow<OrderData> = _uiState.asStateFlow()

    fun subTotal(quantity: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                price = unitPrice * quantity,
                quantity = quantity
            )
        }
    }

    fun setDate(setDate: String) {
        _uiState.update { currentState ->
            currentState.copy(date = setDate)
        }
    }

    fun setFlavor(setFlavor: String) {
        _uiState.update { currentState ->
            currentState.copy(flavor = setFlavor)
        }
    }

    fun pieceCupcake(): Boolean = uiState.value.quantity <= 1

    fun currentDateList(): MutableList<String> {
        _uiState.value.dateList.clear()
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        repeat(4) {
            _uiState.value.dateList.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return _uiState.value.dateList
    }

    fun formatTotalPrice(): String {
        return if (_uiState.value.dateList.isNotEmpty() && _uiState.value.date == _uiState.value.dateList[0]) {
            NumberFormat.getCurrencyInstance().format(_uiState.value.price + sameDay)
        } else {
            NumberFormat.getCurrencyInstance().format(_uiState.value.price)
        }
    }

    fun resetOrder() {
        _uiState.update { currentState ->
            currentState.copy(
                quantity = 0,
                date = "",
                price = 0,
                flavor = ""
            )
        }
    }
}