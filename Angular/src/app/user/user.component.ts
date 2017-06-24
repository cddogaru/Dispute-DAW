import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { Http } from '@angular/http';


@Component({
    selector: 'user',
    templateUrl: './user.html',
})
export class UserComponent {
    private userId: number;
    private user: string[] = [];
    private tournaments: string[] = [];

    constructor(private route: ActivatedRoute, private http: Http) { }

    ngOnInit() {
        this.route.params.subscribe(params => {
            if (params['id'] != null) {
                this.userId = +params['id'];
            }
        });
        let url = "https://localhost:8443/api/users/" + this.userId;
        this.user = [];
        this.http.get(url).subscribe(
            response => {
                this.user = response.json();
            },
            error => console.log(error)
        );

        let url2 = "https://localhost:8443/api/tournaments/";
        this.tournaments = [];
        this.http.get(url2).subscribe(
            response =>{
                let fatournaments = response.json();
                console.log(fatournaments.participants.length);
                for(var i = 0; i < this.tournaments.length; i++){
                    for(var j = 0; j < fatournaments[i].participants.length; j++){
                        console.log(fatournaments[i].participants[j].id);
                        if(fatournaments[i].participants[j].id === this.userId){
                            console.log(fatournaments[i]);
                            this.tournaments.push(fatournaments[i].participants[j]);
                        }
                    }
                }
                console.log(this.tournaments);
            },
            error => console.log(error)
        );
    }
}