import { Component } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router ,  NavigationEnd} from '@angular/router';

@Component({
    selector: 'settings2',
    templateUrl: './settings2.html',
})

export class Settings2Component {
    public user;
    public userAccounts;
    constructor(private route: ActivatedRoute, private http: Http, private router: Router) { }

    ngOnInit() {
        this.http.get("https://localhost:8443/api/users/loggedUser/").subscribe(
            response => {
                this.user = response.json();

                this.http.get("https://localhost:8443/api/users/" + this.user.id).subscribe(
                    response => {this.userAccounts = response.json();
                                        console.log(this.user);
                }
                )
            },
            error => console.error(error)
        );
    }
    private data = {
        name: "",
        avatar: "",
        nickName: "",
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
        xbox: null,
        roles: null
    };
    
    changes(twitter, twitch, youtube, steam, origin, battlenet, psn, xbox){
        this.data.name=this.user.name;
        this.data.avatar=this.user.avatar;
        this.data.nickName=this.user.nickName;
        this.data.password=this.user.password;
        this.data.email=this.user.email;
        this.data.team=this.user.team;
        this.data.roles=this.user.roles;
        if(twitter!=""){
            this.data.twitter = twitter;
        } else {
            this.data.twitter = this.userAccounts.twitter;
        }
        if(twitch!=""){
            this.data.twitch = twitch;
        } else {
            this.data.twitch = this.userAccounts.twitch;
        }
        if(youtube!=""){
            console.log("Aqui ta:" + youtube);
            this.data.youtube = youtube;
        } else {
            console.log("Aqui ta user" + this.user.youtube);
            this.data.youtube = this.userAccounts.youtube;
        }
        if(steam!=""){
            this.data.steam = steam;
        } else {
            this.data.steam = this.userAccounts.steam;
        }
        if(origin!=""){
            this.data.origin = origin;
        } else {
            this.data.origin = this.userAccounts.origin;
        }
        if(psn!=""){
            this.data.psn = psn;
        } else {
            this.data.psn = this.userAccounts.psn;
        }
        if(xbox!=""){
            this.data.xbox = xbox;
        } else {
            this.data.xbox = this.userAccounts.xbox;
        }
        if(battlenet!=""){
            this.data.battlenet = battlenet;
        } else {
            this.data.battlenet = this.userAccounts.battlenet;
        }
        this.http.put("https://localhost:8443/api/users/", this.data).subscribe(
            response => {
                this.router.navigate(['/user/' + this.user.id]);
            },
            error => {
                window.scrollTo(0, 0);
            }
        );
    }
 }