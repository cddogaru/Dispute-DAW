import { Component, Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Router } from '@angular/router';
@Component({
    selector: 'as-login',
    templateUrl: './login.html',
    styleUrls: [
        './login.css'
    ]
})

@Injectable()
export class LoginComponent {
    constructor(private _http: Http, private router: Router) {
    }

    private error: boolean = false;
    login(username: string, password: string) {
        console.log(username + " " + password);
        let headers: Headers = new Headers();
        headers.append("Authorization", "Basic " + btoa(username + ":" + password));
        headers.append("Content-Type", "application/x-www-form-urlencoded");
        this._http.get('https://localhost:8443/api/logIn', { headers: headers }).subscribe(
            response => {
                this.router.navigateByUrl("/");
            },
            error => {
                if(error.status === 500){
                  //  this.router.navigateByUrl("/");
                  location.replace("/");
                } else{
                    this.error = true;
                }
            
        }
        )
    }


}