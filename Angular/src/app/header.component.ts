import { Component } from '@angular/core';
import { Http } from '@angular/http';

@Component({
    selector: 'header',
    templateUrl: './header.component.html',
})

export class HeaderComponent {

     private user;

    constructor(private http: Http) { 

        let url = "https://localhost:8443/api/users/loggedUser/";
        let logged = false;
        this.http.get(url).subscribe(
            response => {
                console.log("LOGGED");
                this.user = response.json();
                if(this.user!=null){
                    logged=true;
                }
                console.log(this.user);
            }
        );
        console.log(logged);
    }


 }