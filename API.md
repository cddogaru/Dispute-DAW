# Documentación API REST

## Bug

### URL: /api/reportbug
**Método:** GET  
**Entrada:** -  
**Salida:** Devuelve todos los bugs almacenados en la base de datos.  
**Descripción:** La petición siempre devuelve el código de estado "200 OK".

### URL: /api/reportbug
**Método:** POST  
**Entrada:** (entrada en formato JSON)
```json
{
  "description": "Bug 1"
}
```
**Salida:** Devuelve el bug que se acaba de almacenar en la base de datos.  
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

## Tournament

### URL: /api/tournaments
**Método:** GET  
**Entrada:** -  
**Salida:** Devuelve todos los torneos que se encuentran almacenados en la base de datos.  
**Descripción:** La petición siempre devuelve el código de estado "200 OK".

### URL: /api/tournaments/{tournamentName}
**Método:** GET  
**Entrada:** -  
**Salida:** Devuelve toda la información del torneo cuyo nombre es igual al proporcionado en la URL (tournamentName).  
**Descripción:** Si el torneo existe, la petición devuelve el código de estado "200 OK". En caso contrario, devuelve "404 Not Found".

## User

### URL: /api/users
**Método:** GET  
**Entrada:** -  
**Salida:** Devuelve todos los usuarios que están registrados en la aplicación y, por lo tanto, almacenados en la base de datos.  
**Descripción:** La petición siempre devuelve el código de estado "200 OK".

### URL: /api/users/{user}
**Método:** GET  
**Entrada:** -  
**Salida:** Devuelve toda la información del usuario cuyo nombre es igual al proporcionado en la URL (user).  
**Descripción:** Si el usuario existe, la petición devuelve el código de estado "200 OK". En caso contrario, devuelve "404 Not Found".

### URL: /api/users/loggedUser
**Método:** GET  
**Entrada:** -  
**Salida:** -  
**Descripción:** Si el usuario está logeado, la petición devuelve el código de estado "200 OK". En caso contrario, devuelve "404 Not Found".

### URL: /api/users
**Método:** POST  
**Entrada:** (entrada en formato JSON)
```json
{
  "id": 1,
  "name": "Alex",
  "avatar": "Default",
  "nickName": "Alex",
  "email": "alex@gmail.com",
  "team": {
    "id": 5,
    "name": "Real Madrid",
    "avatar": "DefaultTeam"
  },
  "twitter": null,
  "twitch": null,
  "youtube": null,
  "steam": null,
  "origin": null,
  "battlenet": null,
  "psn": null,
  "xbox": null
}
```
**Salida:** -  
**Descripción:** La petición devuelve el código de estado "201 Created" si se ha enviado correctamente. En caso contrario, devuelve "400 Bad Request".
