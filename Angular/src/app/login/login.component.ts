import { Component, Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
@Component({
    selector: 'as-login',
    templateUrl: './login.html',
    styleUrls: [
        './login.css'
    ]
})

@Injectable()
export class LoginComponent {
       constructor(private _http: Http) {
       }

        login(username: string, password: string){
            console.log(username + " " + password);
      let headers: Headers = new Headers();
      headers.append("Authorization", "Basic " + btoa(username + ":" + password)); 
      headers.append("Content-Type", "application/x-www-form-urlencoded");
      this._http.get('https://localhost:8443/api/logIn', {headers: headers}).subscribe(
        response => {
            console.log("LOGIN");
        },
        error => console.error(error)

      )
        }

   
}