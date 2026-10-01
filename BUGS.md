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