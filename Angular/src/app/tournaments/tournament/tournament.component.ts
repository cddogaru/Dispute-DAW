import { Component } from '@angular/core';
import { HeaderComponent } from '../../header.component';
import { Http } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
    selector: 'tournament',
    templateUrl: './tournament.html',
    styleUrls: [
        './tournament.css'
    ]
})
export class TournamentComponent {
    private tournament: string[] = [];
    private user = null;
    private admins: any;
    private isLoged: boolean = false;
    private numOfParticipants: number = 0;
    private tournamentId: any;
    private participants = "";
    private isAdmin: any;
    private usersInMatch: any;

    constructor(private route: ActivatedRoute, private http: Http) {
        this.tmethod();

        this.http.get("https://localhost:8443/api/users/loggedUser/").subscribe(
            response => {
                this.user = response.json();
                this.isLoged = (this.user != null);
            },
            error => console.log(error)
        );
    }
    tmethod() {
        this.tournament = [];
        this.route.params.subscribe(params => {
            if (params['id'] != null) {
                this.tournamentId = +params['id'];
            }
        });


        let url = "https://localhost:8443/api/tournaments/";

        this.http.get(url + this.tournamentId).subscribe(
            response => {
                let data = response.json();
                this.tournament = data;
                this.admins = data.admins;
                this.participants = data.participants;
                this.usersInMatch
            },
            error => console.error(error)
        );
        this.isAdmin = this.admins.contains(this.user);
    }
}