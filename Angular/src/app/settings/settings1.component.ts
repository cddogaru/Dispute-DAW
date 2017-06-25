import { Component } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router'

@Component({
    selector: 'settings1',
    templateUrl: './settings1.html',
    styles:['./assets/styles/settingsstyle.css']
})

export class Settings1Component {

    private user: string[] = [];

    constructor(private route: ActivatedRoute, private http: Http) { }

    ngOnInit() {
        this.http.get("https://localhost:8443/api/users/loggedUser/").subscribe(
            response => {
                this.user = response.json();
            },
            error => console.error(error)
        );
    }

}