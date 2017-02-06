# Dispute *DAW*

## Our web theme description:
+ It is a web page, which’s main focus is to organize competitive videogames (e-sports) events, giving support to the brackets, the administrative aspect linked with them (permissions given to the user who has created the tournament/event, such as setting the rules, approving sign-ins...), the social one (each user, as long as he has the permissions, he can sign in any tournament or event, he can claim victories or defeats, which also needs to be approve by the admin, every user can link his Dispute account with Twitter, Steam, Battle .NET... , in all the events, the users which are in the competition, can chat with each other. All the users can private message each other...).

### Private: 

+ Tournament’s management, by the user who has created it (admin), functions such as approve sign-ins, approve results committed by the players, link the tournament with a twitch.tv livestream, kick participants... 
+ Personal data management (e-mail, external accounts [for example Twitter], avatars...).

### Public:

+ Participate in tournaments.
+ Visualizing tournaments, profiles and results.

## Entities:

+ Users: Unique nickname, avatar, roll, tournaments playing/ed, tournaments organized by the user, e-mail, external accounts and gold.
+ Tournaments: The game they are playing, players signed in, format (like double elimination for example), prices.
+ Teams: Multiple users can get together in a team, specially in order to compete in non-individual games.
+ Games: Data base with various videogames' names and icons.
+ Leagues: Multiple tournaments and a league score sheet. All the users have to pay a fee with gold to compete in leagues, and they can win unique prices. 

## Development team:

+ Constin Dragos Dogaru  cd.dogaru@alumnos.urjc.es cddogaru
+ Alejandro Romero Fonteboa a.romerofo@alumnos.urjc.es Romero11
+ Raúl Soto Sánchez r.sotosan@alumnos.urjc.es rsotosan
+ Rafal Daniel Szpecht rd.szpecht@alumnos.urjc.es dimi3sinculotes



![ScreenShot](https://i.gyazo.com/ce4a02719d83a6b41ae8294a52b17aa6.png)


# Model:

## Main Screen (index.html)
![ScreenShot](https://i.gyazo.com/9757c07f5e4bd8153fcb7dcdd2c263d9.png)

This is the first screen met, from here you can get to every page referenced in the navigation bar, the top 3 most popular tournaments being played at the moment, and the social networks linked in the footer.

## Profile
![ScreenShot](https://i.gyazo.com/d21403f97de83e29b09328a99096549f.png)

(only registered users)
This is the profile page, it will be one of this for every person who sign in the web page, if it is your profile, you can change it's data in settings page, if it is not, you can add a person as a friend or invite him to your team

## Tournaments
![ScreenShot](https://i.gyazo.com/4329b9e1b1d6a2a76daa6b873fda6e68.png)

This is the Tournaments page, here any user (registered or not) can search any tournament made in our web page

## Sample tournament
![ScreenShot](https://i.gyazo.com/a7eba405f59c6b9d2b543ca44b2a6e58.png)

This is a sample tournament's page, there will be one of this for each tournament made, it contains data, such date, game... You can join in if you are loged in, there is a bracket, a chat at the bottom, match-ups' tab, and an form to contact with the orginizer in the support tab

## Create a tournament
![ScreenShot](https://i.gyazo.com/42be215780c612cb14912d2a12775e1a.png)

Similar to register a user, log in, create a team... It is a form, which asks the user for necesarry information so an instance of in this case a tournament can be made

## Settings
![ScreenShot](SCREEN SHOT NEEDA BE TAKEN)

This is the settings tab, for all registered users, it is separated in privacy, profile settings(avatar, email, nick and password) and a linked accounts tab, so people can play their match-ups faster

