import { Component } from '@angular/core';
import { Http } from '@angular/http';

@Component({
    selector: 'header',
    templateUrl: './header.component.html',
})

export class HeaderComponent {

     private user;
     private logged: boolean = false;
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
                console.log("LOGGED");
                this.user = response.json();
                if(this.user!=null){
                    this.logged=true;
                }
            }
        );
    }
 }