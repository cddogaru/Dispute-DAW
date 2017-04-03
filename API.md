# Documentación API REST

## Bug

### URL: /api/reportbug
**Método:** GET  
**Entrada:** -  
**Salida:** Muestra todos los bugs almacenados en la base de datos.  
**Descripción:** La petición siempre devuelve el código de estado "200 OK".

### URL: /api/reportbug
**Método:** POST  
**Entrada:** (entrada en formato JSON)
```json
{
  "description": "Bug 1"
}
```
**Salida:** Muestra el bug que se acaba de almacenar en la base de datos.  
**Descripción:** La petición devuelve el código de estado "201 Created" si se ha enviado correctamente.

## Login

### URL: /api/logIn
**Método:** GET  
**Entrada:** El usuario tiene que autorizarse con su username y password para iniciar sesión.  
**Salida:** -  
**Descripción:** Si el usuario no está logeado, la petición devuelve el código de estado "401 Unauthorized". En caso contrario, devuelve "200 OK".

### URL: /api/logOut
**Método:** GET  
**Entrada:** -  
**Salida:** El usuario que estaba logeado tiene ahora la sesión cerrada.  
**Descripción:** Si el usuario no está logeado, la petición devuelve el código de estado "401 Unauthorized". En caso contrario, devuelve "200 OK".
