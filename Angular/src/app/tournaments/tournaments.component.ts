import { Component } from '@angular/core';
import { HeaderComponent } from '../header.component';
import { Http } from '@angular/http';

@Component({
    selector: 'as-tournaments',
    templateUrl: './tournaments.html'
})
export class TournamentsComponent {
    private tournaments: string[] = [];
    private user = null;

    private isLoged: boolean = false;
    private numOfParticipants: number = 0;

    constructor(private http: Http) { 
        this.tmethod();

        this.http.get("https://localhost:8443/api/users/loggedUser/").subscribe(
            response=>{
                this.user = response.json();
                this.isLoged = (this.user!=null);
            },
            error=>console.log(error)
        );
    }
    tmethod(){
        this.tournaments = [];

        let url = "https://localhost:8443/api/tournaments/";

        this.http.get(url).subscribe(
            response => {
                let data = response.json();
                this.tournaments = data;
                this.numOfParticipants = data.participants[1];
                console.log(this.tournaments);
            },
            error => console.error(error)
        );
    }
}