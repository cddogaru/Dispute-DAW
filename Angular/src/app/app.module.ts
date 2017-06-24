import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { routing } from './app.routes';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header.component';
import { FooterComponent } from './footer.component';
import { IndexComponent } from './index.component';
import { TournamentsComponent } from './tournaments/tournaments.component';
import { NewTournamentComponent } from './tournaments/newTournaments/newTournament.component';
import { TournamentComponent } from './tournaments/tournament/tournament.component';
import { TeamsComponent } from './teams/teams.component'
import { TeamComponent } from './teams/team/team.component'
import { NewTeamComponent } from './teams/newTeam/newTeam.component'
import {LoginComponent} from './login/login.component'
import {ContactusComponent} from './contactus/contactus.component'
import {BugComponent} from './bug/bug.component'
import {UserComponent} from './user/user.component'
import {Settings1Component} from './settings/settings1.component'
import {Settings2Component} from './settings/settings2.component'
@NgModule({
  declarations: [
      AppComponent,
      HeaderComponent,
      FooterComponent,
      IndexComponent,
      TournamentsComponent,
      NewTournamentComponent,
      TournamentComponent,
      TeamsComponent,
      TeamComponent,
      NewTeamComponent,
      LoginComponent,
      ContactusComponent,
      BugComponent,
      UserComponent,
      Settings1Component,
      Settings2Component
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
