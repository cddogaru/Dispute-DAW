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

# <h2 align="center">:shipit: Try out our webs *demo* on http://dispute.esy.es :shipit: (only front)</h2>

![ScreenShot](https://i.gyazo.com/ce4a02719d83a6b41ae8294a52b17aa6.png)

# Template: 
![ScreenShot](https://startbootstrap.com/img/templates/small-business.jpg)
https://startbootstrap.com/template-overviews/small-business/

# Model:

## Main Screen (index.html)
![ScreenShot](https://i.gyazo.com/be24c2e16983977bfcacc42a3cbf708d.png)

This is the first screen met, from here you can get to every page referenced in the navigation bar, the top 3 most popular tournaments being played at the moment, and the social networks linked in the footer.

## Sign in / register form
![ScreenShot](https://i.gyazo.com/e549069abc2a583671bb5e0ec0155e46.png)
Not much to say about this page here, you can make your user through it.

## Log in form
![ScreenShot](https://i.gyazo.com/aea9eb54e3c3ccef8e543e4a41e3e47f.png)
Registered users log in to the page using this tab.

## Profile
![ScreenShot](https://i.gyazo.com/c18d3a49b1ed20349e79c986d8cac7af.png)
![ScreenShot](https://i.gyazo.com/5bc4456620ea798894ff5c6c80d946a4.png)

(only registered users)
This is the profile page, it will be one of this for every person who sign in the web page, if it is your profile, you can change it's data in settings page, if it is not, you can add a person as a friend or invite him to your team

## Tournaments
![ScreenShot](https://i.gyazo.com/eb601c6efd3bc5163ec9b999979f92ca.png)

This is the Tournaments page, here any user (registered or not) can search any tournament made in our web page, and registered ones can create their own new tournaments.

## Sample tournament
![ScreenShot](https://i.gyazo.com/09574a6c04fe0bbe784735eb7e488042.png)

This is a sample tournament's page, there will be one of this for each tournament made, it contains data, such date, game... You can join in if you are loged in, there is a bracket, a chat at the bottom, match-ups' tab, and an form to contact with the orginizer in the support tab

## Teams
![ScreenShot](https://i.gyazo.com/aed11102d88719512d440bfadd2d8d32.png)

This is the "teams" tab, you can browse through all the teams made so far, and make your own one.

## A team
![ScreenShot](https://i.gyazo.com/c3872646de7c4166ac5b93ec74dba249.png)

This is a sample team made at the moment, every team has a page like this one.

## Create a tournament
![ScreenShot](https://i.gyazo.com/e14a81be8e815134d0be1e6f3798bc22.png)

Similar to register a user, log in, create a team... It is a form, which asks the user for necesarry information so an instance of in this case a tournament can be made

## Settings
![ScreenShot](https://i.gyazo.com/933884a37dbb8335f6fe8b2efdaa9ba5.png)

This is the settings tab, for all registered users, it is separated in privacy, profile settings(avatar, email, nick and password) and a linked accounts tab, so people can play their match-ups faster

## Contact
![ScreenShot](https://i.gyazo.com/422d9ca547e53563d1792696a4bff03b.png)

This page provides all visitors forms to contact the developers along with the page's code located in this very github repository, they can also take the easy way and just type their issues in the "report bug" tab, if they do not expect any feedback from us.

# Navigation diagram:
![ScreenShot](https://i.gyazo.com/5d25d3665c21b0804acddf85a7f7659e.png)

# Database entity diagram:
![ScreenShot](https://i.gyazo.com/0f8b527293c27950e91d27c2959db8b3.png)

# Class and Template Diagram
![ScreenShot](https://i.gyazo.com/2e4d00167ad7f5b037fd8805bb48b3c0.png)

# Angular Diagram
![ScreenShot](https://gyazo.com/2a70c19bc759195d462bd721a715478b.png)

## API REST documentation: https://github.com/cddogaru/Dispute-DAW/blob/master/API.md
