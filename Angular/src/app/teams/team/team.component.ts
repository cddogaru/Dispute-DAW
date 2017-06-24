import { Component } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router'

@Component({
    selector: 'team',
    templateUrl: './team.html',
    styleUrls: [
        './team.css'
    ]
})
export class TeamComponent {
    public idTeam: number;
    public actualTeam;
    constructor(
        private route: ActivatedRoute,
        private http: Http
  ) {}

    ngOnInit() : void{
        this.route.params.subscribe(params => {
            if(params['id']!=null){
                this.idTeam = +params['id'];
            }
	    });
    let url = "https://localhost:8443/api/teams/" + this.idTeam ;

    this.http.get(url).subscribe(
            response => {
                this.actualTeam = response.json();
                console.log("EQUIPO: ")
                console.log(this.actualTeam);
            },
            error => console.error(error)
        );

    }
}