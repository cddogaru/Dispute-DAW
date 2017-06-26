import { Component } from '@angular/core';
import { Http } from '@angular/http';
import { Router, ActivatedRoute, NavigationEnd } from "@angular/router";

@Component({
    selector: 'newTournament',
    templateUrl: './newTournament.html',
    styleUrls: [
        './newTournament.css'
    ]
})
export class NewTournamentComponent {

    constructor(private http: Http, private router: Router) { }
    private games;
    private error : boolean;
    private errorText: string;
    private data = {
        name: null, description: null, maxPlayers: null, mode: null, date: null, singleTournament: null, game: {name: "", img: ""} 
    }
    ngOnInit(){
        this.http.get("https://localhost:8443/api/games/").subscribe(
            response => {
                
                this.games = response.json();
            }
        );
    }

    newTournament(name: string, description: string, maxPlayers: number, 
       mode: string, date1: string, date2: string,  game: string){
        if(name!=""){
            this.data.name = name;
            this.data.description = description;
            this.data.maxPlayers = maxPlayers;
            this.data.game.name = game;
            this.data.mode = mode;
            this.data.singleTournament = (mode==="Single");
            this.data.date = date1 + " at " + date2;
            this.http.post("https://localhost:8443/api/tournaments/", this.data).subscribe(
                response => {
                    this.router.navigate(['/tournaments']);
                },
                error => {
                    this.error = true;
                    this.errorText = "Tournament already exists";
                    window.scrollTo(0, 0);
                }
            );
        } else {

        }
    }
}