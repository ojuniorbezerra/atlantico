import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map, retry, catchError } from 'rxjs/operators';

import { environment } from '@environments/environment';
import { User } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        console.log(JSON.parse(localStorage.getItem('currentUser')))
        return this.currentUserSubject.value;
    }
    login(username: string, password: string) {

        var myHeaders = new HttpHeaders();
        myHeaders = myHeaders.set("Content-Type", "application/json").set("Authorization", "Basic YXRsYW50aWNvOiQyYSQxMCRwOVBrMGZRTkFRU2VzSTR2dXZLQTBPWmFuREQy");


        return  this.http.post<any>(`${environment.apiUrlBase}/oauth/token?grant_type=password&username=${username}&password=${password}`,{}, {
            headers: myHeaders
         }).pipe(map(data => {
            let user = new User();
            user.username = "Junior"
            user.token = data.access_token
            console.log(user)
            localStorage.setItem('currentUser', JSON.stringify(user))
            this.currentUserSubject.next(user);
        }));
    }

    getPrincipal() {
        return this.http.get<any>(`${environment.apiUrl}/principal`).pipe(map(data => {
            console.log(data.principal)
        }));
    }

    logout() {
        console.log("logout")
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}