import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { User } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<User[]>(`${environment.apiUrl}/user`);
    }

    register(user: User) {
        return this.http.post(`${environment.apiUrl}/user`, user);
    }

    update(user: User) {
        return this.http.put(`${environment.apiUrl}/user/${user.email}`, user);
    }

    getUserByEmail(email: string){
        return this.http.get<User>(`${environment.apiUrl}/user/${email}`);
    }

    delete(email: string) {
        return this.http.delete(`${environment.apiUrl}/user/${email}`);
    }
}