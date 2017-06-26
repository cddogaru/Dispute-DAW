import { Component } from '@angular/core';
import { Http } from '@angular/http';

@Component({
    selector: 'header',
    templateUrl: './header.component.html',
})

export class HeaderComponent {

     private user;
     private logged: boolean = false;
     private uid: any;
     private avatar: any;

    constructor(private http: Http) {
    }

    logout(){
         this.http.get("https://localhost:8443/api/logOut").subscribe(
            response =>{
                location.reload();
            },
            error => console.log(error)
        );
    }

    ngOnInit(){
         

        let url = "https://localhost:8443/api/users/loggedUser/";
        this.http.get(url).subscribe(
            response => {
                this.user = [];
                this.user = response.json();
                this.uid = this.user.id;
                
                if(this.user!=null){
                    this.logged=true;
                }

                this.http.get("https://localhost:8443/api/users/" + this.uid).subscribe(
                    response => {
                        this.avatar = response.json().avatar;
                    },
                    error => console.error(error)
                );
            }
        );
    }
 }