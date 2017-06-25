import { Component } from '@angular/core';
import { Http } from '@angular/http';
import { Router, ActivatedRoute, NavigationEnd } from "@angular/router";
@Component({
    selector: 'signup',
    templateUrl: './signup.html'
})
export class SignupComponent {
    private data = {
        id: 23,
        name: "",
        userName: "",
        avatar: "",
        nickname: "",
        password: "",
        email: null,
        team: null,
        twitter: null,
        twitch: null,
        youtube: null,
        steam: null,
        origin: null,
        battlenet: null,
        psn: null,
        xbox: null
    };
    private error: boolean = false;
    private errorText: string;
    constructor(private http: Http, private router: Router) { }


    singup(username: string, email: string, nick: string, password: string) {
        if (username != "" && email != "" && nick != "" && password != "") {
            this.data.name = username;
            this.data.userName = username;
            this.data.email = email;
            this.data.nickname = nick;
            this.data.password = password;
            this.data.avatar = "Default";
            this.http.post("https://localhost:8443/api/users/", this.data).subscribe(
                response => {
                    this.router.navigate(['/login']);
                },
                error => {
                    this.error = true;
                    this.errorText = "UserName already exists";
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