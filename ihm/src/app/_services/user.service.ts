import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '@/_models';

@Injectable({ providedIn: 'root' })
export class UserService {

    private host1:string="http://localhost:8080";
    constructor(private http: HttpClient) { }

    register(user: User) {
        return this.http.post(this.host1+`/register`, user);
    }
    
    getAll() {
        return this.http.get<User[]>(this.host1+`/users`);
    }

    getById(id: number) {
        return this.http.get(this.host1+`/users/${id}`);
    }

    update(user: User) {
        return this.http.put(this.host1+`/users/${user.id}`, user);
    }

    delete(id: number) {
        return this.http.delete(this.host1+`/users/${id}`);
    }
}