import { Component } from '@angular/core';
import { HeaderComponent } from './header.component';
import { Http } from '@angular/http';

@Component({
    selector: 'index',
    templateUrl: './index.component.html',
})

export class IndexComponent {
    private tournaments: string[] = [];

    constructor(private http: Http) { }

    dathing(){
        console.log("hi");
        this.tournaments = [];

        let url = "https://localHost:8443/api/tournaments/";

        this.http.get(url).subscribe(
            response => {
                console.log("response");
                /*
                let data = response.json();
                console.log(data);
                for (var i = 0; i < data.items.length; i++) {
                    let tournament = data.items[i].tournament.name;
                    this.tournaments.push(tournament);
                }*/
            },
            error => console.error(error)
        );
    }
}