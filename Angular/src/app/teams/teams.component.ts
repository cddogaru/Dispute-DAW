import { Component } from '@angular/core';
import { Http } from '@angular/http';

@Component({
    selector: 'teams',
    templateUrl: './teams.html',
    styleUrls: [
        './teams.css'
    ]
})
export class TeamsComponent {
    private teams: string[] = [];

    constructor(private http: Http) { 
        this.teams = [];
        let url = "https://localhost:8443/api/teams/";

        this.http.get(url).subscribe(
            response => {
                this.teams = response.json();
            },
            error => console.error(error)
        );
    }
}