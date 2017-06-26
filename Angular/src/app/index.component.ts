import { Component } from '@angular/core';
import { HeaderComponent } from './header.component';
import { Http } from '@angular/http';

@Component({
    selector: 'index',
    templateUrl: './index.component.html',
})

export class IndexComponent {
    private tournaments: string[] = [];
    private isLogged: boolean = false;
    private isNotLogged: boolean = true;

    constructor(private http: Http) { 
        this.tournaments = [];

        let url = "https://localhost:8443/api/tournaments/";

        this.http.get(url).subscribe(
            response => {
                let data = response.json();
                this.tournaments[0] = data[0];
                this.tournaments[1] = data[1];
                this.tournaments[2] = data[2];
                
                
                console.log(this.tournaments);
            },
            error => console.error(error)
        );

        this.http.get("https://localhost:8443/api/users/loggedUser/").subscribe(
            response=>{
                let data1 = response.json();
                if(data1 === null){
                    this.isLogged = false;

                }else{
                    this.isLogged = true;
                }
                this.isNotLogged = !this.isLogged;
            },
            error=> console.log(error)
        );
    }
}