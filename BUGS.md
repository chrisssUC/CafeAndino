# Bitácora de errores

## Error 1

| Campo | Tu respuesta |
|---|---|
| Prueba que lo detectó | T1 |
| Qué pasaba | Al tocar "Ver carrito", la app se cerraba. |
| Qué esperaba | Que se abriera el carrito vacío. |
| Cómo lo encontré | Revisando el código de navegación. |
| Archivo y función | AppNavHost.kt, onCartClick |
| Por qué fallaba | Se navegaba a "carrito", pero la ruta correcta era "cart". |
| Cómo lo corregí | Cambié la navegación a Routes.CART. |
| Commit | fix: corregir ruta del carrito |





## Error 2

| Campo | Tu respuesta |
|---|---|
| Prueba que lo detectó | T2 |
| Qué pasaba | Al tocar "Agregar" una vez, el contador no cambiaba. Al tocar por segunda vez recién aparecía 2. |
| Qué esperaba | Que al primer toque mostrara 1 inmediatamente. |
| Cómo lo encontré | Revisando CartViewModel.kt. |
| Archivo y función | CartViewModel.kt, addItem |
| Por qué fallaba | Se modificaba la misma lista con add(), por lo que StateFlow no detectaba el cambio. |
| Cómo lo corregí | Se creó una lista nueva y se reasignó a _cartItems.value. |
| Commit | fix: actualizar carrito al primer toque |





## Error 3

| Campo | Tu respuesta |
|---|---|
| Prueba que lo detectó | T3 |
| Qué pasaba | El subtotal y total mostraban $1803 para 3 cafés de $1800. |
| Qué esperaba | Que mostrara $5400. |
| Cómo lo encontré | Revisando el cálculo de subtotal. |
| Archivo y función | CartItem.kt, propiedad subtotal |
| Por qué fallaba | Se sumaba el precio con la cantidad en vez de multiplicarlos. |
| Cómo lo corregí | Cambié + por *. |
| Commit | fix: corregir calculo de subtotal |