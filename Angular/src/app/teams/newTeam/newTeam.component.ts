import { Component } from '@angular/core';
import { Http } from '@angular/http';
import { Router, ActivatedRoute, NavigationEnd } from "@angular/router";
@Component({
    selector: 'newTeam',
    templateUrl: './newTeam.html',
    styleUrls: [
        './newTeam.css'
    ]
})
export class NewTeamComponent {

     constructor(private http: Http, private router: Router) { }

     private error: boolean;
     private errorText: string;

     private data={
        name: "",
        avatar: "DefaultTeam",
        acronym: "",
        description: "",
        games: null,
        creator: null,
        users: null
     }

     newTeam(name: string, acronym: string, description: string){
        if(name!="" && acronym!=""){
            this.data.name = name;
            this.data.acronym = acronym;
            this.data.description = description;
            this.http.post("https://localhost:8443/api/teams/", this.data).subscribe(
                response => {
                    this.router.navigate(['/teams']);
                },
                error => {
                    this.error = true;
                    this.errorText = "Data error";
                    window.scrollTo(0, 0);
                }
            );
        } else {
            this.error = true;
            this.errorText = "Empty fields";
            window.scrollTo(0, 0);
        }
     }

}