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
                this.router.navigate(['/users/' + this.currentid]);
            },
            error => {
                window.scrollTo(0, 0)
            }
        );
    }

    private data = {
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


    singup(username: string, email: string, nick: string, password: string) {
        this.data.email = email;
        this.data.nickname = nick;
        this.data.password = password;
        this.http.put("https://localhost:8443/api/users/", this.data).subscribe(
            response => {
                this.router.navigate(['/settings1']);
            },
            error => {
                window.scrollTo(0, 0);
            }
        );
    }

}