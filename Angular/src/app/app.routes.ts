import { Routes, RouterModule } from '@angular/router';

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
import {SignupComponent} from './signup/signup.component'
const appRoutes = [
    { path: '', component: IndexComponent},
    { path: 'tournaments', component: TournamentsComponent},
    { path: 'newTournament', component: NewTournamentComponent},
    { path: 'tournament/:id', component: TournamentComponent},
    { path: 'teams', component: TeamsComponent},
    { path: 'team/:id', component: TeamComponent},
    { path: 'newTeam', component: NewTeamComponent},
    { path: 'login', component: LoginComponent},
    { path: 'contactus', component: ContactusComponent},
    { path: 'reportBug', component: BugComponent},
    { path: 'user/:id', component: UserComponent},
    { path: 'settings1', component: Settings1Component},
    { path: 'settings2', component: Settings2Component},
    { path: 'signup', component: SignupComponent}
]
export const routing = RouterModule.forRoot(appRoutes);