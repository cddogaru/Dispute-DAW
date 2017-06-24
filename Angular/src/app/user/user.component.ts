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
                for(var i = 0; i < fatournaments.length; i++){
                    for(var j = 0; j < fatournaments[i].participants.length; j++){
                        if(fatournaments[i].participants[j].id === this.userId){
                            this.tournaments.push(fatournaments[i]);
                        }
                    }
                }
            },
            error => console.log(error)
        );
    }
}