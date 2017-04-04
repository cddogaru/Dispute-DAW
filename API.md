# Documentación API REST

## Bug

### URL: /api/reportbug
**Method:** GET  
**Input:** -  
**Output:** Returns all the bugs helded in the data base. 
**Description:** The request always returns the status code "200 OK".

### URL: /api/reportbug
**Method:** POST  
**Input:** (input in format JSON)
```json
{
  "description": "Bug 1"
}
```
**Output:** Returns the bug which just have been added to the data base.  
**Description:** The request returns the status code "201 Created".

## Login

### URL: /api/logIn
**Method:** GET  
**Input:** The user have to get authorize with his username and password for login in.  
**Output:** -  
**Description:** If the user manage to log in, the request returns the status code "200 OK", otherwise (probably due to not matching username or password) the request returns the status code "401 Unauthorized".

### URL: /api/logOut
**Method:** GET  
**Input:** - 
**Output:** The user which was logged in has his session expired now.
**Description:** If the user manage to log out, the request returns the status code "200 OK", otherwise the request will return the status code "401 Unauthorized".

## Tournament

### URL: /api/tournaments
**Method:** GET  
**Input:** -  
**Output:** It returns all the tournaments saved in the data base. 
**Description:** The request returns the status code "200 OK".

### URL: /api/tournaments/{tournamentName}
**Method:** GET 
**Input:** -  
**Output:** Returns all the information belonging to the tournament with the same name as the one wrote in the URL (tournamentName).  
**Description:** If the tournament exists, the request returns the status code "200 OK", otherwise it will return "404 Not Found".

## User

### URL: /api/users
**Method:** GET  
**Input:** -  
**Output:** It returns all the users registered in the application, which are also saved in the data base. 
**Description:** The request returns the status code "200 OK".

### URL: /api/users/{user}
**Method:** GET 
**Input:** -  
**Output:** Returns all the information belonging to the user with the same name as the one wrote in the URL (userName).  
**Description:** If the user exists, the request returns the status code "200 OK", otherwise it will return "404 Not Found".

### URL: /api/users/loggedUser
**Method:** GET  
**Input:** -  
**Output:** -  
**Description:** If there is a logged user, it will return this users information along with status code "200 OK", otherwise it will return "404 Not Found".

### URL: /api/users
**Method:** POST  
**Input:** (input in JSON fomrat)
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
**Output:** -  
**Description:** The request returns the status code "201 Created" if it has been uploaded succesfully, otherwise it will return "400 Bad Request".
