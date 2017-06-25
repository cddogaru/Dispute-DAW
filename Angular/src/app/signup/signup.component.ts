import { Component } from '@angular/core';
import { Http } from '@angular/http';

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
        email: "",
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

    constructor(private http: Http) { }


    singup(username: string, email: string, nick: string, password: string){
        this.data.name= username;
        this.data.userName = username;
        this.data.email = email;
        this.data.nickname = nick;
        this.data.password = password;
        this.data.avatar = "Default";
        this.http.post("https://localhost:8443/api/users/", this.data).subscribe(
            response =>{
                console.log("CREADO " + this.data);
            },
            error => { console.log("Error singup");
                    console.log(error);
                    console.log(this.data);
            }
        );
        }
    }