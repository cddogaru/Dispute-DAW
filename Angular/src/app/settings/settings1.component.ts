import { Component } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';

@Component({
    selector: 'settings1',
    templateUrl: './settings1.html',
    styles: ['../assets/styles/settingsstyle.css']
})

export class Settings1Component {
    private user: string[] = [];
    private userLogged;
    private currentid: any;
    private file: File;
    private formData: FormData;




    constructor(private route: ActivatedRoute, private http: Http, private router: Router) { }

    ngOnInit() {
        this.user = [];
        this.formData = new FormData();
        this.http.get("https://localhost:8443/api/users/loggedUser/").subscribe(
            response => {
                let uuser = response.json();
                this.userLogged = uuser;
                this.currentid = uuser.id;

                this.http.get("https://localhost:8443/api/users/" + this.currentid).subscribe(
                    response => {
                        this.user = response.json();
                    },
                    error => console.error(error)
                );
            },
            error => console.error(error)
        );


    }

    setAvatar(newavatar: any) {
        let eventObj: MSInputMethodContext = <MSInputMethodContext>newavatar;
        let target: HTMLInputElement = <HTMLInputElement>eventObj.target;
        let files: FileList = target.files;
        this.file = files[0];
        let name = files[0].name;
        this.formData.append('uploadFile', this.file, this.currentid);

        console.log(this.file);
        this.http.post('https://localhost:8443/api/users/' + this.currentid + "/image/", this.formData).subscribe(
            response => {
                location.reload();
            },
            error => {
                window.scrollTo(0, 0)
            }
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


    changes( nick: string, email: string, password: string) {
        this.data.name = this.userLogged.name;        
        if(password===""){
            this.data.password=this.userLogged.password;
        } else {
            this.data.password = password;
        }
        if(nick===""){
            this.data.nickName = this.userLogged.nickName;
        } else{
            this.data.nickName = nick;
        }
        if(email===""){
            this.data.email = this.userLogged.email;
        } else{
            this.data.email = email;
        }
        this.data.avatar = this.userLogged.avatar;
        this.data.team = this.userLogged.team;
        this.data.twitch = this.userLogged.twitch;
        this.data.youtube = this.userLogged.youtube;
        this.data.steam = this.userLogged.steam;
        this.data.origin = this.userLogged.origin;
        this.data.battlenet = this.userLogged.battlenet;
        this.data.psn = this.userLogged.psn;
        this.data.xbox = this.userLogged.xbox;
        this.data.roles = this.userLogged.roles;
        this.http.put("https://localhost:8443/api/users/", this.data).subscribe(
            response => {
                this.router.navigate(['/user/' + this.userLogged.id]);
            },
            error => {
                window.scrollTo(0, 0);
            }
        );
    }

}