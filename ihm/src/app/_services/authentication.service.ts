import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map, first } from 'rxjs/operators';

import { User } from '@/_models';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

    private host1:string="http://localhost:8080";
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

    constructor(private http: HttpClient,private router:Router) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }


    createUserSession(tokenValue:string){
        localStorage.setItem("token",tokenValue);
        this.http.get(this.host1+"/user").pipe(first()).subscribe(data=>this.createUser(data,tokenValue));
        
    }

    login(username: string, password: string) {
        return this.http.post(this.host1+"/login", { username, password },{observe:'response'});
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
    createUser(data:any,tokenValue:string){
        if(data && tokenValue){ 
            let user:User = new User();
            user=data
            user.token=tokenValue;
            if (user && user.token) {
                localStorage.setItem('currentUser', JSON.stringify(user));
                this.currentUserSubject.next(user);
                this.router.navigate(['home']);
        }
        }
    }
}