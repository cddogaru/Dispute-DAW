import { Component } from '@angular/core';
import { HeaderComponent } from './header.component';
import { Http } from '@angular/http';

@Component({
    selector: 'index',
    templateUrl: './index.component.html',
})

export class IndexComponent {
    private tournaments: string[] = [];

    constructor(private http: Http) { 
        this.tournaments = [];

        let url = "https://localHost:8443/api/tournaments/";

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
    }
}