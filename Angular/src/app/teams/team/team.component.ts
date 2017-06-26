import { Component } from '@angular/core';
import { Http , RequestOptions} from '@angular/http';
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
    private admin: boolean = false;
    private user;
    private isLogged: boolean = false;
    private requests;
    private isInTeam;
    private successRequest = false; 
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
                this.isAdmin();
            },
            error => console.error(error)
        );
        
    
    }
    
    isAdmin(){
        let url = "https://localhost:8443/api/users/loggedUser/";
        this.http.get(url).subscribe(
            response => {
                this.user = response.json();
                if(this.user!=null){
                    this.isInList();
                    this.isLogged = true;
                    let isInAdmins = false;
                    for(let admin of this.actualTeam.admins){
                        if(!isInAdmins){isInAdmins= admin.id == this.user.id}
                    }
                   if(isInAdmins|| this.user.roles.includes("ROLE_ADMIN")){
                       this.admin = true;
                       this.http.get("https://localhost:8443/api/teams/" + this.idTeam + "/admin").subscribe(
                        response => {
                            this.requests = response.json();
                            
                        }
                    );
                }
            }
        }
        );
    }
    refuse(request){
        this.http.delete("https://localhost:8443/api/teams/" + this.idTeam + "/admin", new RequestOptions({
            body: request
            })).subscribe(
            response => {
                this.ngOnInit();
            }
        );
    }
    
    kick(id){
        this.http.delete("https://localhost:8443/api/teams/" + this.idTeam + "/admin", new RequestOptions({
            body: id
            })).subscribe(
            response => {
                this.ngOnInit();
            }
        );
    }

    accept(request){
        this.http.put("https://localhost:8443/api/teams/" + this.idTeam + "/admin", request).subscribe(
            response => {
                console.log(request);
                this.ngOnInit();
            }
        );
    }

    newRequest(){
        this.http.put("https://localhost:8443/api/teams/" + this.idTeam, null).subscribe(
            response => {
                this.ngOnInit();
                this.successRequest = true;
            }
        );
    }

    isInList(){
        this.isInTeam = false;
            for(let user of this.actualTeam.users){
                if(!this.isInTeam){this.isInTeam = user.id === this.user.id}
            }
            console.log(this.isInTeam);
    }

    leaveTeam(){
        this.kick(this.user.id);
    }
}
