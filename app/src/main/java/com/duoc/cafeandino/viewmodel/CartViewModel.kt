package com.duoc.cafeandino.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duoc.cafeandino.model.CartItem
import com.duoc.cafeandino.model.MenuItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CartViewModel : ViewModel() {

    private val _cartItems = MutableStateFlow(mutableListOf<CartItem>())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    val orderCount: StateFlow<Int> = _cartItems
        .map { items -> items.sumOf { it.quantity } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    val totalAmount: StateFlow<Int> = _cartItems
        .map { items -> items.sumOf { it.subtotal } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    private val _lastCustomerName = MutableStateFlow<String?>(null)
    val lastCustomerName: StateFlow<String?> = _lastCustomerName.asStateFlow()

    fun addItem(menuItem: MenuItem) {
        val existing = _cartItems.value.firstOrNull { it.item.id == menuItem.id }

        if (existing == null) {
 // bug corregido: creo una lista nueva para que StateFlow detecte el cambio enseguida
            _cartItems.value = (_cartItems.value + CartItem(menuItem, 1)).toMutableList()

        } else {
            increaseQuantity(menuItem.id)
        }
    }

    //reto segun mi rut  limite por producto maximo 5 productos no se puede agregar mas desde el carrito ni desde el menu
    fun increaseQuantity(itemId: Int) {
        _cartItems.value = _cartItems.value
            .map {
                if (it.item.id == itemId && it.quantity < 5) {
                    it.copy(quantity = it.quantity + 1)
                } else {
                    it
                }
            }
            .toMutableList()
    }

    fun decreaseQuantity(itemId: Int) {
// bug corregido: si la cantidad llega a 1, se elimina el producto del carrito en vez de dejarlo solo en 0
        _cartItems.value = _cartItems.value
            .mapNotNull { cartItem ->
                if (cartItem.item.id == itemId) {
                    if (cartItem.quantity <= 1) {
                        null
                    } else {
                        cartItem.copy(quantity = cartItem.quantity - 1)
                    }
                } else {
                    cartItem
                }
            }
            .toMutableList()
    }

    fun removeItem(itemId: Int) {
        _cartItems.value =
            _cartItems.value
                .filter { it.item.id != itemId }
                .toMutableList()
    }

    fun clearCart() {
        _cartItems.value = mutableListOf()
    }

    fun confirmOrder(customerName: String) {
        _lastCustomerName.value = customerName
        clearCart()
 // bug corregido: al confirmar el pedido se debe vaciar el carrito y no conservar los productos que agregaste anteriormente.
    }
}