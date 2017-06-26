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
    private tournament;
    private user = null;
    private admins: any;
    private isLoged: boolean = false;
    private numOfParticipants: number = 0;
    private tournamentId: any;
    private participants = "";
    private isAdmin: any;
    private usersInMatch: any;
    private rounds;
    constructor(private route: ActivatedRoute, private http: Http) {

    }

     ngOnInit() : void{
         
         
           
        this.http.get("https://localhost:8443/api/users/loggedUser/").subscribe(
            response => {
                this.user = response.json();
                this.isLoged = (this.user != null);
                this.isAdmin = this.isAdmin || this.user.roles.includes("ROLE_ADMIN");

                 this.tmethod();
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
                for(let admin of this.tournament.admins){
                if(!this.isAdmin){ this.isAdmin= admin.id === this.user.id ;}
                this.getRounds();
            }
            
            },
            error => console.error(error)
        );
        
        
    }

    getRounds(){
        let url = "https://localhost:8443/api/tournaments/";

        this.http.get(url + this.tournamentId + "/rounds").subscribe(
            response => {
                this.rounds = response.json();
                console.log(this.rounds);
            }
        );
    }
}