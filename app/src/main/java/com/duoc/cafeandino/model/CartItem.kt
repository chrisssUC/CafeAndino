package com.duoc.cafeandino.model

data class CartItem(
    val item: MenuItem,
    val quantity: Int
) {
    val subtotal: Int
        // bug corregido: el subtotal debe multiplicar el precio por la cantidad no sumarla.
        get() = item.price * quantity
}